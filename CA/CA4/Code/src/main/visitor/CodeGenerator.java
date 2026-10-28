package main.visitor;

import main.ast.core.Program;
import main.ast.declarations.Member;
import main.ast.declarations.Method;
import main.ast.declarations.MethodDecl;
import main.ast.declarations.Module;
import main.ast.declarations.ModuleDecl;
import main.ast.declarations.Parameter;
import main.ast.declarations.Struct;
import main.ast.declarations.StructDecl;
import main.ast.declarations.TopLevelDecl;
import main.ast.declarations.Var;
import main.ast.declarations.VarDecl;
import main.ast.expressions.BinaryExpression;
import main.ast.expressions.ConstantExpression;
import main.ast.expressions.ConstructorCall;
import main.ast.expressions.Expression;
import main.ast.expressions.Location;
import main.ast.expressions.MemberLoc;
import main.ast.expressions.MethodCall;
import main.ast.expressions.MethodCallLoc;
import main.ast.expressions.ParanthesisExpr;
import main.ast.expressions.SimpleLoc;
import main.ast.expressions.ThisLoc;
import main.ast.expressions.UnaryExpression;
import main.ast.expressions.UnaryOpExpr;
import main.ast.expressions.literals.BoolLiteral;
import main.ast.expressions.literals.CharLiteral;
import main.ast.expressions.literals.DoubleLiteral;
import main.ast.expressions.literals.FloatLiteral;
import main.ast.expressions.literals.IntLiteral;
import main.ast.expressions.operators.BinaryOperator;
import main.ast.statements.AssignStmt;
import main.ast.statements.Block;
import main.ast.statements.BreakJump;
import main.ast.statements.ContinueJump;
import main.ast.statements.ForStmt;
import main.ast.statements.IfStmt;
import main.ast.statements.InputStmt;
import main.ast.statements.MethodCallStmt;
import main.ast.statements.OutputStmt;
import main.ast.statements.ReturnStmt;
import main.ast.statements.Statement;
import main.ast.statements.VarDeclStmt;
import main.ast.statements.WhileStmt;
import main.ast.types.AccessModifier;
import main.ast.types.Identifier;
import main.ast.types.PrimitiveType;
import main.ast.types.Type;
import main.ast.types.UserDefinedType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CodeGenerator extends Visitor<String> {
    private static final int LIMIT = 128;
    private final String outputPath;

    private final Map<String, ClassInfo> classes = new LinkedHashMap<>();
    private final Map<String, List<String>> generatedMethods = new LinkedHashMap<>();
    private final Map<String, Set<String>> requestedConstructors = new LinkedHashMap<>();

    private final Map<String, LocalInfo> locals = new LinkedHashMap<>();
    private final Deque<String> breakLabels = new ArrayDeque<>();
    private final Deque<String> continueLabels = new ArrayDeque<>();

    private ClassInfo currentClass;
    private MethodInfo currentMethod;
    private int currentSlot;
    private int labelCount;

    public CodeGenerator() {
        this.outputPath = "./codeGenOutput/";
        prepareOutputDirectory();
    }

    private void prepareOutputDirectory() {
        File directory = new File(outputPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IllegalStateException("Could not create output directory: " + directory.getAbsolutePath());
        }
    }

    @Override
    public String visit(Program program) {
        try {
            resetGeneratorState();
            collectClassInformation(program);
            generateAllMethods();
            writeAllClasses();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not write generated Jasmin files", exception);
        }
        return null;
    }

    private void resetGeneratorState() {
        classes.clear();
        generatedMethods.clear();
        requestedConstructors.clear();
        locals.clear();
        breakLabels.clear();
        continueLabels.clear();
        currentClass = null;
        currentMethod = null;
        currentSlot = 1;
        labelCount = 0;

        File directory = new File(outputPath);
        File[] oldJasminFiles = directory.listFiles((dir, name) -> name.endsWith(".j"));
        if (oldJasminFiles != null) {
            for (File file : oldJasminFiles) {
                if (!file.delete()) {
                    throw new IllegalStateException("Could not remove stale output file: " + file.getAbsolutePath());
                }
            }
        }
    }

    private void collectClassInformation(Program program) {
        for (TopLevelDecl declaration : program.getTopLevelDeclarations()) {
            if (declaration instanceof ModuleDecl) {
                Module module = ((ModuleDecl) declaration).getModule();
                ClassInfo info = new ClassInfo(module.getName().getName(), true, module, null);
                for (Identifier include : module.getIncludes()) {
                    info.includes.add(include.getName());
                }
                classes.put(info.name, info);
            } else if (declaration instanceof StructDecl) {
                Struct struct = ((StructDecl) declaration).getStruct();
                ClassInfo info = new ClassInfo(struct.getName().getName(), false, null, struct);
                classes.put(info.name, info);
            }
        }

        for (ClassInfo info : classes.values()) {
            List<Member> members = info.isModule ? info.module.getMembers() : info.struct.getMembers();
            for (Member member : members) {
                if (member instanceof VarDecl) {
                    Var var = ((VarDecl) member).getVar();
                    FieldInfo field = new FieldInfo(
                            var.getName().getName(), descriptorOfVar(var), var, accessOf(member));
                    info.fields.put(field.name, field);
                    info.fieldOrder.add(field);
                } else if (member instanceof MethodDecl) {
                    Method method = ((MethodDecl) member).getMethod();
                    MethodInfo methodInfo = new MethodInfo(method, accessOf(member));
                    info.methods.computeIfAbsent(methodInfo.name, key -> new ArrayList<>()).add(methodInfo);
                }
            }
            generatedMethods.put(info.name, new ArrayList<>());
            requestedConstructors.put(info.name, new LinkedHashSet<>());
        }
    }

    private String accessOf(Member member) {
        return member.getAccessModifier() == AccessModifier.PRIVATE ? "private" : "public";
    }

    private void generateAllMethods() {
        for (ClassInfo classInfo : classes.values()) {
            currentClass = classInfo;
            List<MethodInfo> methods = new ArrayList<>();
            for (List<MethodInfo> overloads : classInfo.methods.values()) {
                methods.addAll(overloads);
            }
            for (MethodInfo methodInfo : methods) {
                currentMethod = methodInfo;
                generatedMethods.get(classInfo.name).add(generateMethod(methodInfo));
            }
        }
        currentClass = null;
        currentMethod = null;
    }

    private String generateMethod(MethodInfo methodInfo) {
        resetMethodState();
        for (int index = 0; index < methodInfo.method.getParameters().size(); index++) {
            Parameter parameter = methodInfo.method.getParameters().get(index);
            declareLocal(parameter.getName().getName(), methodInfo.parameterDescriptors.get(index));
        }

        StringBuilder code = new StringBuilder();
        code.append(".method ").append(methodInfo.access).append(' ')
                .append(methodInfo.name).append(methodInfo.descriptor()).append('\n');
        code.append("    .limit stack ").append(LIMIT).append('\n');
        code.append("    .limit locals ").append(LIMIT).append('\n');

        if (methodInfo.method.getBody() != null) {
            code.append(nonNull(methodInfo.method.getBody().accept(this)));
        }
        if (!statementAlwaysReturns(methodInfo.method.getBody())) {
            code.append(defaultReturn(methodInfo.returnDescriptor));
        }
        code.append(".end method\n\n");
        return code.toString();
    }

    private void resetMethodState() {
        locals.clear();
        breakLabels.clear();
        continueLabels.clear();
        currentSlot = 1; 
    }

    private void writeAllClasses() throws IOException {
        for (ClassInfo info : classes.values()) {
            File outputFile = new File(outputPath, info.name + ".j");
            try (FileWriter classWriter = new FileWriter(outputFile)) {
                classWriter.write(".class public " + info.name + "\n");
                classWriter.write(".super " + superNameOf(info) + "\n\n");

                for (FieldInfo field : info.fieldOrder) {
                    classWriter.write(".field " + field.access + " " + field.name + " " + field.descriptor + "\n");
                }
                for (String include : additionalIncludes(info)) {
                    classWriter.write(".field public $include$" + include + " L" + include + ";\n");
                }
                if (!info.fieldOrder.isEmpty() || !additionalIncludes(info).isEmpty()) {
                    classWriter.write("\n");
                }

                classWriter.write(generateDefaultConstructor(info));
                Set<String> constructorDescriptors = requestedConstructors.get(info.name);
                if (constructorDescriptors != null) {
                    for (String descriptor : constructorDescriptors) {
                        if (!"()V".equals(descriptor)) {
                            classWriter.write(generateRequestedConstructor(info, descriptor));
                        }
                    }
                }

                for (String methodCode : generatedMethods.get(info.name)) {
                    classWriter.write(methodCode);
                }

                if ("Main".equals(info.name) && hasRunnableMain(info)) {
                    classWriter.write(generateStaticMainWrapper());
                }
            }
        }
    }

    private String superNameOf(ClassInfo info) {
        return info.includes.isEmpty() ? "java/lang/Object" : info.includes.get(0);
    }

    private List<String> additionalIncludes(ClassInfo info) {
        if (info.includes.size() <= 1) {
            return java.util.Collections.emptyList();
        }
        return info.includes.subList(1, info.includes.size());
    }

    private String generateDefaultConstructor(ClassInfo info) {
        StringBuilder code = new StringBuilder();
        code.append(".method public <init>()V\n");
        code.append("    .limit stack ").append(LIMIT).append('\n');
        code.append("    .limit locals ").append(LIMIT).append('\n');
        code.append("    aload_0\n");
        code.append("    invokespecial ").append(superNameOf(info)).append("/<init>()V\n");

        for (String include : additionalIncludes(info)) {
            code.append("    aload_0\n");
            code.append("    new ").append(include).append('\n');
            code.append("    dup\n");
            code.append("    invokespecial ").append(include).append("/<init>()V\n");
            code.append("    putfield ").append(info.name).append("/$include$")
                    .append(include).append(" L").append(include).append(";\n");
        }

        for (FieldInfo field : info.fieldOrder) {
            if (field.var.getConstructorCall() != null) {
                String typeName = field.var.getConstructorCall().getName().getName();
                code.append("    aload_0\n");
                code.append("    new ").append(typeName).append('\n');
                code.append("    dup\n");
                code.append("    invokespecial ").append(typeName).append("/<init>()V\n");
                code.append("    putfield ").append(info.name).append('/').append(field.name)
                        .append(' ').append(field.descriptor).append('\n');
            }
        }

        code.append("    return\n");
        code.append(".end method\n\n");
        return code.toString();
    }

    private String generateRequestedConstructor(ClassInfo info, String constructorDescriptor) {
        List<String> argumentDescriptors = parseArgumentDescriptors(constructorDescriptor);
        StringBuilder code = new StringBuilder();
        code.append(".method public <init>").append(constructorDescriptor).append('\n');
        code.append("    .limit stack ").append(LIMIT).append('\n');
        code.append("    .limit locals ").append(LIMIT).append('\n');
        code.append("    aload_0\n");
        code.append("    invokespecial ").append(superNameOf(info)).append("/<init>()V\n");

        int slot = 1;
        int assignable = Math.min(argumentDescriptors.size(), info.fieldOrder.size());
        for (int index = 0; index < assignable; index++) {
            String argumentDescriptor = argumentDescriptors.get(index);
            FieldInfo field = info.fieldOrder.get(index);
            if (canConvert(argumentDescriptor, field.descriptor)) {
                code.append("    aload_0\n");
                code.append(loadInstruction(argumentDescriptor, slot));
                code.append(conversion(argumentDescriptor, field.descriptor));
                code.append("    putfield ").append(info.name).append('/').append(field.name)
                        .append(' ').append(field.descriptor).append('\n');
            }
            slot += slotWidth(argumentDescriptor);
        }

        for (String include : additionalIncludes(info)) {
            code.append("    aload_0\n");
            code.append("    new ").append(include).append('\n');
            code.append("    dup\n");
            code.append("    invokespecial ").append(include).append("/<init>()V\n");
            code.append("    putfield ").append(info.name).append("/$include$")
                    .append(include).append(" L").append(include).append(";\n");
        }

        code.append("    return\n");
        code.append(".end method\n\n");
        return code.toString();
    }

    private boolean hasRunnableMain(ClassInfo info) {
        List<MethodInfo> methods = info.methods.get("main");
        if (methods == null) {
            return false;
        }
        for (MethodInfo method : methods) {
            if (method.parameterDescriptors.isEmpty() && "V".equals(method.returnDescriptor)) {
                return true;
            }
        }
        return false;
    }

    private String generateStaticMainWrapper() {
        return ".method public static main([Ljava/lang/String;)V\n"
                + "    .limit stack " + LIMIT + "\n"
                + "    .limit locals " + LIMIT + "\n"
                + "    new Main\n"
                + "    dup\n"
                + "    invokespecial Main/<init>()V\n"
                + "    invokevirtual Main/main()V\n"
                + "    return\n"
                + ".end method\n";
    }

    @Override
    public String visit(ModuleDecl moduleDecl) {
        return moduleDecl.getModule().accept(this);
    }

    @Override
    public String visit(Module module) {
        return "";
    }

    @Override
    public String visit(StructDecl structDecl) {
        return structDecl.getStruct().accept(this);
    }

    @Override
    public String visit(Struct struct) {
        return "";
    }

    @Override
    public String visit(MethodDecl methodDecl) {
        return methodDecl.getMethod().accept(this);
    }

    @Override
    public String visit(Method method) {
        MethodInfo info = findOwnMethodInfo(currentClass, method);
        return info == null ? "" : generateMethod(info);
    }

    @Override
    public String visit(Parameter parameter) {
        return "";
    }

    @Override
    public String visit(Var var) {
        return "";
    }

    @Override
    public String visit(VarDecl varDecl) {
        Var var = varDecl.getVar();
        return ".field " + accessOf(varDecl) + " " + var.getName().getName() + " " + descriptorOfVar(var) + "\n";
    }

    @Override
    public String visit(Block block) {
        StringBuilder code = new StringBuilder();
        for (Statement statement : block.getStatements()) {
            code.append(nonNull(statement.accept(this)));
        }
        return code.toString();
    }

    @Override
    public String visit(VarDeclStmt varDeclStmt) {
        Var var = varDeclStmt.getVar();
        String descriptor = descriptorOfVar(var);
        int slot = declareLocal(var.getName().getName(), descriptor);
        StringBuilder code = new StringBuilder();

        Expression initial = varDeclStmt.getInitial();
        if (initial != null) {
            code.append(emitExpressionAs(initial, descriptor));
        } else if (var.getConstructorCall() != null) {
            code.append(var.getConstructorCall().accept(this));
        } else {
            code.append(defaultValue(descriptor));
        }
        code.append(storeInstruction(descriptor, slot));
        return code.toString();
    }

    @Override
    public String visit(AssignStmt assignStmt) {
        Location left = assignStmt.getLeft();
        if (left instanceof SimpleLoc) {
            String name = ((SimpleLoc) left).getId().getName();
            LocalInfo local = locals.get(name);
            if (local != null) {
                return emitExpressionAs(assignStmt.getRight(), local.descriptor)
                        + storeInstruction(local.descriptor, local.slot);
            }
        }

        FieldTarget target = resolveFieldTarget(left);
        StringBuilder code = new StringBuilder();
        code.append(target.ownerCode);
        code.append(emitExpressionAs(assignStmt.getRight(), target.field.descriptor));
        code.append("    putfield ").append(target.declaringClass).append('/')
                .append(target.field.name).append(' ').append(target.field.descriptor).append('\n');
        return code.toString();
    }

    @Override
    public String visit(MethodCallStmt methodCallStmt) {
        MethodCall call = methodCallStmt.getMethodCall();
        String descriptor = inferType(call);
        StringBuilder code = new StringBuilder(nonNull(call.accept(this)));
        if (!"V".equals(descriptor)) {
            code.append(slotWidth(descriptor) == 2 ? "    pop2\n" : "    pop\n");
        }
        return code.toString();
    }

    @Override
    public String visit(ReturnStmt returnStmt) {
        if (returnStmt.getValue() == null) {
            return "    return\n";
        }
        String returnDescriptor = currentMethod == null ? inferType(returnStmt.getValue()) : currentMethod.returnDescriptor;
        return emitExpressionAs(returnStmt.getValue(), returnDescriptor) + returnInstruction(returnDescriptor);
    }

    @Override
    public String visit(IfStmt ifStmt) {
        String elseLabel = newLabel("if_else");
        String endLabel = newLabel("if_end");
        StringBuilder code = new StringBuilder();
        code.append(emitExpressionAs(ifStmt.getCondition(), "Z"));
        code.append("    ifeq ").append(elseLabel).append('\n');
        code.append(nonNull(ifStmt.getThenBranch().accept(this)));
        code.append("    goto ").append(endLabel).append('\n');
        code.append(elseLabel).append(":\n");
        if (ifStmt.getElseBranch() != null) {
            code.append(nonNull(ifStmt.getElseBranch().accept(this)));
        }
        code.append(endLabel).append(":\n");
        code.append("    nop\n");
        return code.toString();
    }

    @Override
    public String visit(ForStmt forStmt) {
        String conditionLabel = newLabel("for_condition");
        String updateLabel = newLabel("for_update");
        String endLabel = newLabel("for_end");
        StringBuilder code = new StringBuilder();

        for (Statement initializer : forStmt.getInitializers()) {
            code.append(nonNull(initializer.accept(this)));
        }

        breakLabels.push(endLabel);
        continueLabels.push(updateLabel);
        code.append(conditionLabel).append(":\n");
        if (forStmt.getCondition() != null) {
            code.append(emitExpressionAs(forStmt.getCondition(), "Z"));
            code.append("    ifeq ").append(endLabel).append('\n');
        }
        if (forStmt.getBody() != null) {
            code.append(nonNull(forStmt.getBody().accept(this)));
        }
        code.append(updateLabel).append(":\n");
        for (AssignStmt updater : forStmt.getUpdaters()) {
            code.append(nonNull(updater.accept(this)));
        }
        code.append("    goto ").append(conditionLabel).append('\n');
        code.append(endLabel).append(":\n");
        continueLabels.pop();
        breakLabels.pop();
        return code.toString();
    }

    @Override
    public String visit(WhileStmt whileStmt) {
        String conditionLabel = newLabel("while_condition");
        String endLabel = newLabel("while_end");
        StringBuilder code = new StringBuilder();

        breakLabels.push(endLabel);
        continueLabels.push(conditionLabel);
        code.append(conditionLabel).append(":\n");
        code.append(emitExpressionAs(whileStmt.getCondition(), "Z"));
        code.append("    ifeq ").append(endLabel).append('\n');
        if (whileStmt.getBody() != null) {
            code.append(nonNull(whileStmt.getBody().accept(this)));
        }
        code.append("    goto ").append(conditionLabel).append('\n');
        code.append(endLabel).append(":\n");
        continueLabels.pop();
        breakLabels.pop();
        return code.toString();
    }

    @Override
    public String visit(BreakJump breakJump) {
        if (breakLabels.isEmpty()) {
            throw generationError("break used outside a loop", breakJump.getLine());
        }
        return "    goto " + breakLabels.peek() + "\n";
    }

    @Override
    public String visit(ContinueJump continueJump) {
        if (continueLabels.isEmpty()) {
            throw generationError("continue used outside a loop", continueJump.getLine());
        }
        return "    goto " + continueLabels.peek() + "\n";
    }

    @Override
    public String visit(InputStmt inputStmt) {
        Location location = inputStmt.getLoc();
        String targetDescriptor;
        LocalInfo local = null;
        if (location instanceof SimpleLoc) {
            local = locals.get(((SimpleLoc) location).getId().getName());
        }

        StringBuilder code = new StringBuilder();
        FieldTarget fieldTarget = null;
        if (local != null) {
            targetDescriptor = local.descriptor;
        } else {
            fieldTarget = resolveFieldTarget(location);
            targetDescriptor = fieldTarget.field.descriptor;
            code.append(fieldTarget.ownerCode);
        }

        code.append(scannerRead(targetDescriptor));
        if (local != null) {
            code.append(storeInstruction(targetDescriptor, local.slot));
        } else {
            code.append("    putfield ").append(fieldTarget.declaringClass).append('/')
                    .append(fieldTarget.field.name).append(' ').append(targetDescriptor).append('\n');
        }
        return code.toString();
    }

    @Override
    public String visit(OutputStmt outputStmt) {
        String descriptor = inferType(outputStmt.getValue());
        String printlnDescriptor = descriptor;
        if (isReference(descriptor)) {
            printlnDescriptor = "Ljava/lang/Object;";
        }
        StringBuilder code = new StringBuilder();
        code.append("    getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        code.append(nonNull(outputStmt.getValue().accept(this)));
        code.append("    invokevirtual java/io/PrintStream/println(")
                .append(printlnDescriptor).append(")V\n");
        return code.toString();
    }

    @Override
    public String visit(MethodCall methodCall) {
        if (isConstructorLike(methodCall)) {
            return emitConstructorCall(methodCall.getCallee().getName(), methodCall.getArguments());
        }

        List<String> argumentTypes = new ArrayList<>();
        for (Expression argument : methodCall.getArguments()) {
            argumentTypes.add(inferType(argument));
        }

        Receiver receiver = resolveReceiver(methodCall.getInstance(), methodCall.getCallee().getName(), argumentTypes,
                methodCall.getLine());
        MethodResolution resolution = receiver.methodResolution;
        StringBuilder code = new StringBuilder(receiver.code);
        for (int index = 0; index < methodCall.getArguments().size(); index++) {
            code.append(emitExpressionAs(methodCall.getArguments().get(index),
                    resolution.method.parameterDescriptors.get(index)));
        }
        code.append("    invokevirtual ").append(receiver.invocationClass).append('/')
                .append(resolution.method.name).append(resolution.method.descriptor()).append('\n');
        return code.toString();
    }

    @Override
    public String visit(ConstructorCall constructorCall) {
        return emitConstructorCall(constructorCall.getName().getName(), constructorCall.getArguments());
    }

    private String emitConstructorCall(String className, List<Expression> arguments) {
        List<String> argumentDescriptors = new ArrayList<>();
        for (Expression argument : arguments) {
            argumentDescriptors.add(inferType(argument));
        }
        String constructorDescriptor = methodDescriptor(argumentDescriptors, "V");
        requestedConstructors.computeIfAbsent(className, key -> new LinkedHashSet<>()).add(constructorDescriptor);

        StringBuilder code = new StringBuilder();
        code.append("    new ").append(className).append('\n');
        code.append("    dup\n");
        for (Expression argument : arguments) {
            code.append(nonNull(argument.accept(this)));
        }
        code.append("    invokespecial ").append(className).append("/<init>")
                .append(constructorDescriptor).append('\n');
        return code.toString();
    }

    private boolean isConstructorLike(MethodCall methodCall) {
        return methodCall.getInstance() == null && classes.containsKey(methodCall.getCallee().getName());
    }

    @Override
    public String visit(SimpleLoc simpleLoc) {
        return emitLocationLoad(simpleLoc);
    }

    @Override
    public String visit(MemberLoc memberLoc) {
        return emitLocationLoad(memberLoc);
    }

    @Override
    public String visit(ThisLoc thisLoc) {
        return emitLocationLoad(thisLoc);
    }

    @Override
    public String visit(MethodCallLoc methodCallLoc) {
        return methodCallLoc.getMethodCall().accept(this);
    }

    @Override
    public String visit(ParanthesisExpr paranthesisExpr) {
        return paranthesisExpr.getExpression().accept(this);
    }

    @Override
    public String visit(UnaryOpExpr unaryOpExpr) {
        StringBuilder code = new StringBuilder(nonNull(unaryOpExpr.getOperand().accept(this)));
        if (unaryOpExpr.getOperator() == UnaryOpExpr.Operator.MINUS) {
            code.append(negationInstruction(inferType(unaryOpExpr.getOperand())));
        } else {
            code.append("    ldc 1\n    ixor\n");
        }
        return code.toString();
    }

    @Override
    public String visit(UnaryExpression unaryExpression) {
        String descriptor = inferType(unaryExpression.getExpression());
        StringBuilder code = new StringBuilder(nonNull(unaryExpression.getExpression().accept(this)));
        switch (unaryExpression.getOperand()) {
            case MINUS:
                code.append(negationInstruction(descriptor));
                break;
            case NOT:
                code.append("    ldc 1\n    ixor\n");
                break;
            default:
                throw generationError("Unsupported unary operator", unaryExpression.getLine());
        }
        return code.toString();
    }

    @Override
    public String visit(BinaryExpression binaryExpression) {
        BinaryOperator operator = binaryExpression.getOperator();
        String leftType = inferType(binaryExpression.getLeftOperand());
        String rightType = inferType(binaryExpression.getRightOperand());
        StringBuilder code = new StringBuilder();

        if (operator == BinaryOperator.AND || operator == BinaryOperator.OR) {
            code.append(emitExpressionAs(binaryExpression.getLeftOperand(), "Z"));
            code.append(emitExpressionAs(binaryExpression.getRightOperand(), "Z"));
            code.append(operator == BinaryOperator.AND ? "    iand\n" : "    ior\n");
            return code.toString();
        }

        if (isComparison(operator)) {
            String trueLabel = newLabel("comparison_true");
            String endLabel = newLabel("comparison_end");
            if (isReference(leftType) || isReference(rightType)) {
                code.append(nonNull(binaryExpression.getLeftOperand().accept(this)));
                code.append(nonNull(binaryExpression.getRightOperand().accept(this)));
                if (operator == BinaryOperator.EQUALITY) {
                    code.append("    if_acmpeq ").append(trueLabel).append('\n');
                } else if (operator == BinaryOperator.INEQUALITY) {
                    code.append("    if_acmpne ").append(trueLabel).append('\n');
                } else {
                    throw generationError("Only == and != are valid for objects", binaryExpression.getLine());
                }
            } else {
                String numericType = promotedNumericType(leftType, rightType);
                code.append(emitExpressionAs(binaryExpression.getLeftOperand(), numericType));
                code.append(emitExpressionAs(binaryExpression.getRightOperand(), numericType));
                if ("F".equals(numericType)) {
                    code.append("    fcmpl\n");
                    code.append(singleValueComparisonInstruction(operator)).append(' ').append(trueLabel).append('\n');
                } else if ("D".equals(numericType)) {
                    code.append("    dcmpl\n");
                    code.append(singleValueComparisonInstruction(operator)).append(' ').append(trueLabel).append('\n');
                } else {
                    code.append(integerComparisonInstruction(operator)).append(' ').append(trueLabel).append('\n');
                }
            }
            code.append("    ldc 0\n");
            code.append("    goto ").append(endLabel).append('\n');
            code.append(trueLabel).append(":\n");
            code.append("    ldc 1\n");
            code.append(endLabel).append(":\n");
            return code.toString();
        }

        String numericType = promotedNumericType(leftType, rightType);
        code.append(emitExpressionAs(binaryExpression.getLeftOperand(), numericType));
        code.append(emitExpressionAs(binaryExpression.getRightOperand(), numericType));
        code.append(arithmeticInstruction(operator, numericType));
        return code.toString();
    }

    @Override
    public String visit(ConstantExpression constantExpression) {
        return constantCode(constantExpression.getValue());
    }

    @Override
    public String visit(IntLiteral intLiteral) {
        return "    ldc " + intLiteral.getValue() + "\n";
    }

    @Override
    public String visit(FloatLiteral floatLiteral) {
        return "    ldc " + Float.toString((float) floatLiteral.getValue()) + "\n";
    }

    @Override
    public String visit(DoubleLiteral doubleLiteral) {
        return "    ldc2_w " + Double.toString(doubleLiteral.getValue()) + "\n";
    }

    @Override
    public String visit(CharLiteral charLiteral) {
        return "    ldc " + (int) charLiteral.getValue() + "\n";
    }

    @Override
    public String visit(BoolLiteral boolLiteral) {
        return "    ldc " + (boolLiteral.getValue() ? 1 : 0) + "\n";
    }

    @Override
    public String visit(Identifier identifier) {
        LocalInfo local = locals.get(identifier.getName());
        if (local != null) {
            return loadInstruction(local.descriptor, local.slot);
        }
        return emitLocationLoad(new SimpleLoc(identifier));
    }

    @Override
    public String visit(PrimitiveType primitiveType) {
        return descriptorOfType(primitiveType);
    }

    @Override
    public String visit(UserDefinedType userDefinedType) {
        return descriptorOfType(userDefinedType);
    }

    private String emitLocationLoad(Location location) {
        if (location instanceof SimpleLoc) {
            String name = ((SimpleLoc) location).getId().getName();
            LocalInfo local = locals.get(name);
            if (local != null) {
                return loadInstruction(local.descriptor, local.slot);
            }
        }

        LocationPath path = flattenLocation(location);
        if (path.thisRoot && path.parts.isEmpty()) {
            return "    aload_0\n";
        }

        StringBuilder code = new StringBuilder();
        String ownerClass;
        int fieldIndex;
        if (path.thisRoot) {
            code.append("    aload_0\n");
            ownerClass = currentClass.name;
            fieldIndex = 0;
        } else {
            if (path.parts.isEmpty()) {
                throw generationError("Empty location", location.getLine());
            }
            String root = path.parts.get(0);
            LocalInfo rootLocal = locals.get(root);
            if (rootLocal != null) {
                code.append(loadInstruction(rootLocal.descriptor, rootLocal.slot));
                ownerClass = classNameOf(rootLocal.descriptor);
                fieldIndex = 1;
                if (fieldIndex == path.parts.size()) {
                    return code.toString();
                }
            } else {
                code.append("    aload_0\n");
                ownerClass = currentClass.name;
                fieldIndex = 0;
            }
        }

        for (int index = fieldIndex; index < path.parts.size(); index++) {
            String fieldName = path.parts.get(index);
            FieldResolution resolution = resolveField(ownerClass, fieldName);
            if (resolution == null) {
                throw generationError("Unknown field " + ownerClass + "." + fieldName, location.getLine());
            }
            ownerClass = prepareFieldReceiver(code, ownerClass, resolution.ownerClass, location.getLine());
            code.append("    getfield ").append(resolution.ownerClass).append('/')
                    .append(fieldName).append(' ').append(resolution.field.descriptor).append('\n');
            if (index + 1 < path.parts.size()) {
                ownerClass = classNameOf(resolution.field.descriptor);
                if (ownerClass == null) {
                    throw generationError("Cannot access a member of primitive field " + fieldName, location.getLine());
                }
            }
        }
        return code.toString();
    }

    private FieldTarget resolveFieldTarget(Location location) {
        LocationPath path = flattenLocation(location);
        if (path.parts.isEmpty()) {
            throw generationError("Cannot assign to this", location.getLine());
        }

        StringBuilder ownerCode = new StringBuilder();
        String ownerClass;
        int index;
        if (path.thisRoot) {
            ownerCode.append("    aload_0\n");
            ownerClass = currentClass.name;
            index = 0;
        } else {
            String root = path.parts.get(0);
            LocalInfo rootLocal = locals.get(root);
            if (rootLocal != null && path.parts.size() > 1) {
                ownerCode.append(loadInstruction(rootLocal.descriptor, rootLocal.slot));
                ownerClass = classNameOf(rootLocal.descriptor);
                index = 1;
            } else {
                ownerCode.append("    aload_0\n");
                ownerClass = currentClass.name;
                index = 0;
            }
        }

        for (; index < path.parts.size() - 1; index++) {
            String fieldName = path.parts.get(index);
            FieldResolution resolution = resolveField(ownerClass, fieldName);
            if (resolution == null) {
                throw generationError("Unknown field " + ownerClass + "." + fieldName, location.getLine());
            }
            ownerClass = prepareFieldReceiver(ownerCode, ownerClass, resolution.ownerClass, location.getLine());
            ownerCode.append("    getfield ").append(resolution.ownerClass).append('/')
                    .append(fieldName).append(' ').append(resolution.field.descriptor).append('\n');
            ownerClass = classNameOf(resolution.field.descriptor);
            if (ownerClass == null) {
                throw generationError("Cannot access a member of primitive field " + fieldName, location.getLine());
            }
        }

        String finalName = path.parts.get(path.parts.size() - 1);
        FieldResolution finalField = resolveField(ownerClass, finalName);
        if (finalField == null) {
            throw generationError("Unknown field " + ownerClass + "." + finalName, location.getLine());
        }
        prepareFieldReceiver(ownerCode, ownerClass, finalField.ownerClass, location.getLine());
        return new FieldTarget(ownerCode.toString(), finalField.ownerClass, finalField.field);
    }

    private String prepareFieldReceiver(StringBuilder code, String receiverClass, String declaringClass, int line) {
        if (isInSuperChain(receiverClass, declaringClass)) {
            return receiverClass;
        }
        ClassInfo receiverInfo = classes.get(receiverClass);
        String include = receiverInfo == null ? null : findAdditionalIncludeProviding(receiverInfo, declaringClass);
        if (include == null) {
            throw generationError("Field receiver " + receiverClass + " is not compatible with " + declaringClass, line);
        }
        code.append("    getfield ").append(receiverClass).append("/$include$")
                .append(include).append(" L").append(include).append(";\n");
        return include;
    }

    private Receiver resolveReceiver(Location instance, String methodName, List<String> argumentTypes, int line) {
        if (instance == null || instance instanceof ThisLoc && ((ThisLoc) instance).getLoc() == null) {
            MethodResolution resolution = resolveMethod(currentClass.name, methodName, argumentTypes, new HashSet<>());
            if (resolution == null) {
                throw generationError("Unknown method " + currentClass.name + "." + methodName, line);
            }
            if (isInSuperChain(currentClass.name, resolution.ownerClass)) {
                return new Receiver("    aload_0\n", currentClass.name, resolution);
            }
            String includeField = findAdditionalIncludeProviding(currentClass, resolution.ownerClass);
            if (includeField != null) {
                String code = "    aload_0\n    getfield " + currentClass.name + "/$include$" + includeField
                        + " L" + includeField + ";\n";
                return new Receiver(code, includeField, resolution);
            }
            return new Receiver("    aload_0\n", currentClass.name, resolution);
        }

        String receiverDescriptor = inferType(instance);
        String receiverClass = classNameOf(receiverDescriptor);
        if (receiverClass == null) {
            throw generationError("Method receiver is not an object", line);
        }
        MethodResolution resolution = resolveMethod(receiverClass, methodName, argumentTypes, new HashSet<>());
        if (resolution == null) {
            throw generationError("Unknown method " + receiverClass + "." + methodName, line);
        }
        StringBuilder receiverCode = new StringBuilder(nonNull(instance.accept(this)));
        String invocationClass = receiverClass;
        if (!isInSuperChain(receiverClass, resolution.ownerClass)) {
            ClassInfo receiverInfo = classes.get(receiverClass);
            String include = receiverInfo == null ? null : findAdditionalIncludeProviding(receiverInfo, resolution.ownerClass);
            if (include == null) {
                throw generationError("Method receiver " + receiverClass + " is not compatible with "
                        + resolution.ownerClass, line);
            }
            receiverCode.append("    getfield ").append(receiverClass).append("/$include$")
                    .append(include).append(" L").append(include).append(";\n");
            invocationClass = include;
        }
        return new Receiver(receiverCode.toString(), invocationClass, resolution);
    }

    private String findAdditionalIncludeProviding(ClassInfo info, String ownerClass) {
        for (String include : additionalIncludes(info)) {
            if (include.equals(ownerClass) || isInSuperChain(include, ownerClass)) {
                return include;
            }
        }
        return null;
    }

    private boolean isInSuperChain(String className, String possibleAncestor) {
        String cursor = className;
        Set<String> seen = new HashSet<>();
        while (cursor != null && seen.add(cursor)) {
            if (cursor.equals(possibleAncestor)) {
                return true;
            }
            ClassInfo info = classes.get(cursor);
            if (info == null || info.includes.isEmpty()) {
                return false;
            }
            cursor = info.includes.get(0);
        }
        return false;
    }

    private String emitExpressionAs(Expression expression, String targetDescriptor) {
        String sourceDescriptor = inferType(expression);
        return nonNull(expression.accept(this)) + conversion(sourceDescriptor, targetDescriptor);
    }

    private String inferType(Expression expression) {
        if (expression instanceof ConstantExpression) {
            return descriptorOfConstant(((ConstantExpression) expression).getValue());
        }
        if (expression instanceof IntLiteral) {
            return "I";
        }
        if (expression instanceof FloatLiteral) {
            return "F";
        }
        if (expression instanceof DoubleLiteral) {
            return "D";
        }
        if (expression instanceof CharLiteral) {
            return "C";
        }
        if (expression instanceof BoolLiteral) {
            return "Z";
        }
        if (expression instanceof ConstructorCall) {
            return "L" + ((ConstructorCall) expression).getName().getName() + ";";
        }
        if (expression instanceof MethodCall) {
            MethodCall call = (MethodCall) expression;
            if (isConstructorLike(call)) {
                return "L" + call.getCallee().getName() + ";";
            }
            List<String> arguments = new ArrayList<>();
            for (Expression argument : call.getArguments()) {
                arguments.add(inferType(argument));
            }
            String receiverClass = currentClass.name;
            if (call.getInstance() != null) {
                String descriptor = inferType(call.getInstance());
                receiverClass = classNameOf(descriptor);
            }
            MethodResolution resolution = resolveMethod(receiverClass, call.getCallee().getName(), arguments, new HashSet<>());
            if (resolution == null) {
                throw generationError("Unknown method " + receiverClass + "." + call.getCallee().getName(), call.getLine());
            }
            return resolution.method.returnDescriptor;
        }
        if (expression instanceof MethodCallLoc) {
            return inferType(((MethodCallLoc) expression).getMethodCall());
        }
        if (expression instanceof ParanthesisExpr) {
            return inferType(((ParanthesisExpr) expression).getExpression());
        }
        if (expression instanceof UnaryExpression) {
            UnaryExpression unary = (UnaryExpression) expression;
            return unary.getOperand() == main.ast.expressions.operators.UnaryOperator.NOT
                    ? "Z" : inferType(unary.getExpression());
        }
        if (expression instanceof UnaryOpExpr) {
            UnaryOpExpr unary = (UnaryOpExpr) expression;
            return unary.getOperator() == UnaryOpExpr.Operator.NOT ? "Z" : inferType(unary.getOperand());
        }
        if (expression instanceof BinaryExpression) {
            BinaryExpression binary = (BinaryExpression) expression;
            if (isComparison(binary.getOperator())
                    || binary.getOperator() == BinaryOperator.AND
                    || binary.getOperator() == BinaryOperator.OR) {
                return "Z";
            }
            return promotedNumericType(inferType(binary.getLeftOperand()), inferType(binary.getRightOperand()));
        }
        if (expression instanceof Location) {
            return inferLocationType((Location) expression);
        }
        throw generationError("Cannot infer expression type " + expression.getClass().getSimpleName(), expression.getLine());
    }

    private String inferLocationType(Location location) {
        if (location instanceof MethodCallLoc) {
            return inferType(((MethodCallLoc) location).getMethodCall());
        }
        if (location instanceof SimpleLoc) {
            String name = ((SimpleLoc) location).getId().getName();
            LocalInfo local = locals.get(name);
            if (local != null) {
                return local.descriptor;
            }
        }
        LocationPath path = flattenLocation(location);
        if (path.thisRoot && path.parts.isEmpty()) {
            return "L" + currentClass.name + ";";
        }

        String ownerClass;
        int index;
        if (path.thisRoot) {
            ownerClass = currentClass.name;
            index = 0;
        } else {
            String root = path.parts.get(0);
            LocalInfo local = locals.get(root);
            if (local != null) {
                if (path.parts.size() == 1) {
                    return local.descriptor;
                }
                ownerClass = classNameOf(local.descriptor);
                index = 1;
            } else {
                ownerClass = currentClass.name;
                index = 0;
            }
        }

        String result = "L" + ownerClass + ";";
        for (; index < path.parts.size(); index++) {
            FieldResolution field = resolveField(ownerClass, path.parts.get(index));
            if (field == null) {
                throw generationError("Unknown field " + ownerClass + "." + path.parts.get(index), location.getLine());
            }
            result = field.field.descriptor;
            if (index + 1 < path.parts.size()) {
                ownerClass = classNameOf(result);
                if (ownerClass == null) {
                    throw generationError("Cannot access member of primitive value", location.getLine());
                }
            }
        }
        return result;
    }

    private LocationPath flattenLocation(Location location) {
        LocationPath result = new LocationPath();
        appendLocation(location, result);
        return result;
    }

    private void appendLocation(Location location, LocationPath result) {
        if (location instanceof SimpleLoc) {
            result.parts.add(((SimpleLoc) location).getId().getName());
        } else if (location instanceof MemberLoc) {
            MemberLoc memberLoc = (MemberLoc) location;
            result.parts.add(memberLoc.getMemberName().getName());
            appendLocation(memberLoc.getLoc(), result);
        } else if (location instanceof ThisLoc) {
            result.thisRoot = true;
            ThisLoc thisLoc = (ThisLoc) location;
            if (thisLoc.getLoc() != null) {
                appendLocation(thisLoc.getLoc(), result);
            }
        } else if (location instanceof MethodCallLoc) {
            throw generationError("A method call cannot be assigned as a location", location.getLine());
        } else {
            throw generationError("Unknown location " + location.getClass().getSimpleName(), location.getLine());
        }
    }

    private FieldResolution resolveField(String className, String fieldName) {
        return resolveField(className, fieldName, new HashSet<>());
    }

    private FieldResolution resolveField(String className, String fieldName, Set<String> visited) {
        if (className == null || !visited.add(className)) {
            return null;
        }
        ClassInfo info = classes.get(className);
        if (info == null) {
            return null;
        }
        FieldInfo own = info.fields.get(fieldName);
        if (own != null) {
            return new FieldResolution(className, own);
        }
        for (String include : info.includes) {
            FieldResolution inherited = resolveField(include, fieldName, visited);
            if (inherited != null) {
                return inherited;
            }
        }
        return null;
    }

    private MethodResolution resolveMethod(String className, String methodName, List<String> argumentTypes,
                                           Set<String> visited) {
        if (className == null || !visited.add(className)) {
            return null;
        }
        ClassInfo info = classes.get(className);
        if (info == null) {
            return null;
        }
        List<MethodInfo> candidates = info.methods.get(methodName);
        MethodInfo compatible = selectMethod(candidates, argumentTypes);
        if (compatible != null) {
            return new MethodResolution(className, compatible);
        }
        for (String include : info.includes) {
            MethodResolution inherited = resolveMethod(include, methodName, argumentTypes, visited);
            if (inherited != null) {
                return inherited;
            }
        }
        return null;
    }

    private MethodInfo selectMethod(List<MethodInfo> candidates, List<String> argumentTypes) {
        if (candidates == null) {
            return null;
        }
        for (MethodInfo candidate : candidates) {
            if (candidate.parameterDescriptors.equals(argumentTypes)) {
                return candidate;
            }
        }
        for (MethodInfo candidate : candidates) {
            if (candidate.parameterDescriptors.size() != argumentTypes.size()) {
                continue;
            }
            boolean compatible = true;
            for (int index = 0; index < argumentTypes.size(); index++) {
                if (!canConvert(argumentTypes.get(index), candidate.parameterDescriptors.get(index))) {
                    compatible = false;
                    break;
                }
            }
            if (compatible) {
                return candidate;
            }
        }
        return null;
    }

    private MethodInfo findOwnMethodInfo(ClassInfo classInfo, Method method) {
        List<MethodInfo> methods = classInfo.methods.get(method.getName().getName());
        if (methods != null) {
            for (MethodInfo info : methods) {
                if (info.method == method) {
                    return info;
                }
            }
        }
        return null;
    }

    private int declareLocal(String name, String descriptor) {
        LocalInfo existing = locals.get(name);
        if (existing != null) {
            return existing.slot;
        }
        int slot = currentSlot;
        locals.put(name, new LocalInfo(slot, descriptor));
        currentSlot += slotWidth(descriptor);
        return slot;
    }

    private String descriptorOfVar(Var var) {
        if (var.getType() != null) {
            return descriptorOfType(var.getType());
        }
        if (var.getConstructorCall() != null) {
            return "L" + var.getConstructorCall().getName().getName() + ";";
        }
        throw generationError("Variable has no type", var.getLine());
    }

    private String descriptorOfType(Type type) {
        if (type instanceof UserDefinedType) {
            return "L" + ((UserDefinedType) type).getId().getName() + ";";
        }
        if (!(type instanceof PrimitiveType)) {
            throw new IllegalArgumentException("Unknown type " + type);
        }
        switch (((PrimitiveType) type).getPrimitive()) {
            case INT:
                return "I";
            case FLOAT:
                return "F";
            case DOUBLE:
                return "D";
            case CHAR:
                return "C";
            case BOOL:
                return "Z";
            case VOID:
                return "V";
            default:
                throw new IllegalArgumentException("Unknown primitive type");
        }
    }

    private String descriptorOfConstant(Object value) {
        if (value instanceof Integer) {
            return "I";
        }
        if (value instanceof Float) {
            return "F";
        }
        if (value instanceof Double) {
            return "D";
        }
        if (value instanceof Character) {
            return "C";
        }
        if (value instanceof Boolean) {
            return "Z";
        }
        throw new IllegalArgumentException("Unsupported constant: " + value);
    }

    private String constantCode(Object value) {
        if (value instanceof Integer) {
            return "    ldc " + value + "\n";
        }
        if (value instanceof Float) {
            return "    ldc " + Float.toString((Float) value) + "\n";
        }
        if (value instanceof Double) {
            return "    ldc2_w " + Double.toString((Double) value) + "\n";
        }
        if (value instanceof Character) {
            return "    ldc " + (int) ((Character) value) + "\n";
        }
        if (value instanceof Boolean) {
            return "    ldc " + ((Boolean) value ? 1 : 0) + "\n";
        }
        throw new IllegalArgumentException("Unsupported constant: " + value);
    }

    private String methodDescriptor(List<String> parameters, String returnDescriptor) {
        StringBuilder descriptor = new StringBuilder("(");
        for (String parameter : parameters) {
            descriptor.append(parameter);
        }
        return descriptor.append(')').append(returnDescriptor).toString();
    }

    private List<String> parseArgumentDescriptors(String methodDescriptor) {
        List<String> descriptors = new ArrayList<>();
        int index = 1;
        while (methodDescriptor.charAt(index) != ')') {
            int start = index;
            while (methodDescriptor.charAt(index) == '[') {
                index++;
            }
            if (methodDescriptor.charAt(index) == 'L') {
                index = methodDescriptor.indexOf(';', index) + 1;
            } else {
                index++;
            }
            descriptors.add(methodDescriptor.substring(start, index));
        }
        return descriptors;
    }

    private String loadInstruction(String descriptor, int slot) {
        if (isIntLike(descriptor)) {
            return "    iload " + slot + "\n";
        }
        if ("F".equals(descriptor)) {
            return "    fload " + slot + "\n";
        }
        if ("D".equals(descriptor)) {
            return "    dload " + slot + "\n";
        }
        return "    aload " + slot + "\n";
    }

    private String storeInstruction(String descriptor, int slot) {
        if (isIntLike(descriptor)) {
            return "    istore " + slot + "\n";
        }
        if ("F".equals(descriptor)) {
            return "    fstore " + slot + "\n";
        }
        if ("D".equals(descriptor)) {
            return "    dstore " + slot + "\n";
        }
        return "    astore " + slot + "\n";
    }

    private String returnInstruction(String descriptor) {
        if ("V".equals(descriptor)) {
            return "    return\n";
        }
        if (isIntLike(descriptor)) {
            return "    ireturn\n";
        }
        if ("F".equals(descriptor)) {
            return "    freturn\n";
        }
        if ("D".equals(descriptor)) {
            return "    dreturn\n";
        }
        return "    areturn\n";
    }

    private boolean statementAlwaysReturns(Statement statement) {
        if (statement == null) {
            return false;
        }
        if (statement instanceof ReturnStmt) {
            return true;
        }
        if (statement instanceof Block) {
            for (Statement child : ((Block) statement).getStatements()) {
                if (statementAlwaysReturns(child)) {
                    return true;
                }
            }
            return false;
        }
        if (statement instanceof IfStmt) {
            IfStmt ifStmt = (IfStmt) statement;
            return ifStmt.getElseBranch() != null
                    && statementAlwaysReturns(ifStmt.getThenBranch())
                    && statementAlwaysReturns(ifStmt.getElseBranch());
        }
        return false;
    }

    private String defaultReturn(String descriptor) {
        if ("V".equals(descriptor)) {
            return "    return\n";
        }
        return defaultValue(descriptor) + returnInstruction(descriptor);
    }

    private String defaultValue(String descriptor) {
        if (isIntLike(descriptor)) {
            return "    ldc 0\n";
        }
        if ("F".equals(descriptor)) {
            return "    ldc 0.0\n";
        }
        if ("D".equals(descriptor)) {
            return "    ldc2_w 0.0\n";
        }
        return "    aconst_null\n";
    }

    private String scannerRead(String descriptor) {
        StringBuilder code = new StringBuilder();
        code.append("    new java/util/Scanner\n");
        code.append("    dup\n");
        code.append("    getstatic java/lang/System/in Ljava/io/InputStream;\n");
        code.append("    invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V\n");
        if ("I".equals(descriptor)) {
            code.append("    invokevirtual java/util/Scanner/nextInt()I\n");
        } else if ("F".equals(descriptor)) {
            code.append("    invokevirtual java/util/Scanner/nextFloat()F\n");
        } else if ("D".equals(descriptor)) {
            code.append("    invokevirtual java/util/Scanner/nextDouble()D\n");
        } else if ("Z".equals(descriptor)) {
            code.append("    invokevirtual java/util/Scanner/nextBoolean()Z\n");
        } else if ("C".equals(descriptor)) {
            code.append("    invokevirtual java/util/Scanner/next()Ljava/lang/String;\n");
            code.append("    ldc 0\n");
            code.append("    invokevirtual java/lang/String/charAt(I)C\n");
        } else {
            throw generationError("input is only supported for primitive values", -1);
        }
        return code.toString();
    }

    private String conversion(String source, String target) {
        if (source.equals(target) || isIntLike(source) && isIntLike(target)) {
            return "";
        }
        if (isIntLike(source) && "F".equals(target)) {
            return "    i2f\n";
        }
        if (isIntLike(source) && "D".equals(target)) {
            return "    i2d\n";
        }
        if ("F".equals(source) && "D".equals(target)) {
            return "    f2d\n";
        }
        if ("F".equals(source) && isIntLike(target)) {
            return "    f2i\n";
        }
        if ("D".equals(source) && "F".equals(target)) {
            return "    d2f\n";
        }
        if ("D".equals(source) && isIntLike(target)) {
            return "    d2i\n";
        }
        if (isReference(source) && isReference(target)) {
            return "";
        }
        throw generationError("Cannot convert " + source + " to " + target, -1);
    }

    private boolean canConvert(String source, String target) {
        if (source.equals(target) || isIntLike(source) && isIntLike(target)) {
            return true;
        }
        if (isNumeric(source) && isNumeric(target)) {
            return true;
        }
        return isReference(source) && isReference(target)
                && isAssignableReference(classNameOf(source), classNameOf(target));
    }

    private boolean isAssignableReference(String sourceClass, String targetClass) {
        if (sourceClass == null || targetClass == null) {
            return false;
        }
        if (sourceClass.equals(targetClass) || "java/lang/Object".equals(targetClass)) {
            return true;
        }
        return isInSuperChain(sourceClass, targetClass);
    }

    private String promotedNumericType(String left, String right) {
        if ("D".equals(left) || "D".equals(right)) {
            return "D";
        }
        if ("F".equals(left) || "F".equals(right)) {
            return "F";
        }
        return "I";
    }

    private String arithmeticInstruction(BinaryOperator operator, String descriptor) {
        String prefix = "D".equals(descriptor) ? "d" : "F".equals(descriptor) ? "f" : "i";
        switch (operator) {
            case ADDITION:
                return "    " + prefix + "add\n";
            case SUBTRACTION:
                return "    " + prefix + "sub\n";
            case MULTIPLICATION:
                return "    " + prefix + "mul\n";
            case DIVISION:
                return "    " + prefix + "div\n";
            default:
                throw new IllegalArgumentException("Not an arithmetic operator: " + operator);
        }
    }

    private String negationInstruction(String descriptor) {
        if ("D".equals(descriptor)) {
            return "    dneg\n";
        }
        if ("F".equals(descriptor)) {
            return "    fneg\n";
        }
        return "    ineg\n";
    }

    private String integerComparisonInstruction(BinaryOperator operator) {
        switch (operator) {
            case LESS_THAN:
                return "    if_icmplt";
            case GREATER_THAN:
                return "    if_icmpgt";
            case LESS_THAN_OR_EQUAL_TO:
                return "    if_icmple";
            case GREATER_THAN_OR_EQUAL_TO:
                return "    if_icmpge";
            case EQUALITY:
                return "    if_icmpeq";
            case INEQUALITY:
                return "    if_icmpne";
            default:
                throw new IllegalArgumentException("Not a comparison operator: " + operator);
        }
    }

    private String singleValueComparisonInstruction(BinaryOperator operator) {
        switch (operator) {
            case LESS_THAN:
                return "    iflt";
            case GREATER_THAN:
                return "    ifgt";
            case LESS_THAN_OR_EQUAL_TO:
                return "    ifle";
            case GREATER_THAN_OR_EQUAL_TO:
                return "    ifge";
            case EQUALITY:
                return "    ifeq";
            case INEQUALITY:
                return "    ifne";
            default:
                throw new IllegalArgumentException("Not a comparison operator: " + operator);
        }
    }

    private boolean isComparison(BinaryOperator operator) {
        return operator == BinaryOperator.LESS_THAN
                || operator == BinaryOperator.GREATER_THAN
                || operator == BinaryOperator.LESS_THAN_OR_EQUAL_TO
                || operator == BinaryOperator.GREATER_THAN_OR_EQUAL_TO
                || operator == BinaryOperator.EQUALITY
                || operator == BinaryOperator.INEQUALITY;
    }

    private boolean isIntLike(String descriptor) {
        return "I".equals(descriptor) || "Z".equals(descriptor) || "C".equals(descriptor)
                || "B".equals(descriptor) || "S".equals(descriptor);
    }

    private boolean isNumeric(String descriptor) {
        return isIntLike(descriptor) || "F".equals(descriptor) || "D".equals(descriptor);
    }

    private boolean isReference(String descriptor) {
        return descriptor != null && (descriptor.startsWith("L") || descriptor.startsWith("["));
    }

    private int slotWidth(String descriptor) {
        return "D".equals(descriptor) || "J".equals(descriptor) ? 2 : 1;
    }

    private String classNameOf(String descriptor) {
        if (descriptor == null || !descriptor.startsWith("L") || !descriptor.endsWith(";")) {
            return null;
        }
        return descriptor.substring(1, descriptor.length() - 1);
    }

    private String newLabel(String prefix) {
        return prefix + "_" + labelCount++;
    }

    private String nonNull(String value) {
        return value == null ? "" : value;
    }

    private IllegalStateException generationError(String message, int line) {
        String context = currentClass == null ? "" : " in " + currentClass.name;
        if (currentMethod != null) {
            context += "." + currentMethod.name;
        }
        if (line > 0) {
            context += " at line " + line;
        }
        return new IllegalStateException(message + context);
    }

    private final class MethodInfo {
        private final Method method;
        private final String name;
        private final String access;
        private final String returnDescriptor;
        private final List<String> parameterDescriptors = new ArrayList<>();

        private MethodInfo(Method method, String access) {
            this.method = method;
            this.name = method.getName().getName();
            this.access = access;
            this.returnDescriptor = descriptorOfType(method.getReturnType());
            for (Parameter parameter : method.getParameters()) {
                parameterDescriptors.add(descriptorOfType(parameter.getType()));
            }
        }

        private String descriptor() {
            return methodDescriptor(parameterDescriptors, returnDescriptor);
        }
    }

    private static final class ClassInfo {
        private final String name;
        private final boolean isModule;
        private final Module module;
        private final Struct struct;
        private final List<String> includes = new ArrayList<>();
        private final Map<String, FieldInfo> fields = new LinkedHashMap<>();
        private final List<FieldInfo> fieldOrder = new ArrayList<>();
        private final Map<String, List<MethodInfo>> methods = new LinkedHashMap<>();

        private ClassInfo(String name, boolean isModule, Module module, Struct struct) {
            this.name = name;
            this.isModule = isModule;
            this.module = module;
            this.struct = struct;
        }
    }

    private static final class FieldInfo {
        private final String name;
        private final String descriptor;
        private final Var var;
        private final String access;

        private FieldInfo(String name, String descriptor, Var var, String access) {
            this.name = name;
            this.descriptor = descriptor;
            this.var = var;
            this.access = access;
        }
    }

    private static final class LocalInfo {
        private final int slot;
        private final String descriptor;

        private LocalInfo(int slot, String descriptor) {
            this.slot = slot;
            this.descriptor = descriptor;
        }
    }

    private static final class FieldResolution {
        private final String ownerClass;
        private final FieldInfo field;

        private FieldResolution(String ownerClass, FieldInfo field) {
            this.ownerClass = ownerClass;
            this.field = field;
        }
    }

    private static final class FieldTarget {
        private final String ownerCode;
        private final String declaringClass;
        private final FieldInfo field;

        private FieldTarget(String ownerCode, String declaringClass, FieldInfo field) {
            this.ownerCode = ownerCode;
            this.declaringClass = declaringClass;
            this.field = field;
        }
    }

    private static final class MethodResolution {
        private final String ownerClass;
        private final MethodInfo method;

        private MethodResolution(String ownerClass, MethodInfo method) {
            this.ownerClass = ownerClass;
            this.method = method;
        }
    }

    private static final class Receiver {
        private final String code;
        private final String invocationClass;
        private final MethodResolution methodResolution;

        private Receiver(String code, String invocationClass, MethodResolution methodResolution) {
            this.code = code;
            this.invocationClass = invocationClass;
            this.methodResolution = methodResolution;
        }
    }

    private static final class LocationPath {
        private boolean thisRoot;
        private final List<String> parts = new ArrayList<>();
    }
}