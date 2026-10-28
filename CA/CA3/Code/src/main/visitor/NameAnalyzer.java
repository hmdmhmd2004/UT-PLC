package main.visitor;

import main.ast.core.Program;
import main.ast.declarations.Module;
import main.ast.declarations.*;
import main.ast.statements.*;
import main.ast.expressions.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.*;

import java.util.*;

public class NameAnalyzer extends Visitor<Void> {

    private Set<SymbolTableItem> initializedVars = new HashSet<>();
    private boolean isLValue = false;
    private String currentModuleName = null;
    private Module currentModule = null;

    private Map<String, Set<String>> dependencies = new HashMap<>();
    private Map<String, TopLevelDecl> declarationNodes = new HashMap<>();

    @Override
    public Void visit(Program program) {
        SymbolTable.root = new SymbolTable();
        SymbolTable.push(SymbolTable.root);

        for (TopLevelDecl decl : program.getTopLevelDeclarations()) {
            if (decl instanceof ModuleDecl) {
                String name = ((ModuleDecl) decl).getModule().getName().getName();
                declarationNodes.put(name, decl);
                dependencies.put(name, new HashSet<>());
            } else if (decl instanceof StructDecl) {
                String name = ((StructDecl) decl).getStruct().getName().getName();
                declarationNodes.put(name, decl);
                dependencies.put(name, new HashSet<>());
            }
            decl.accept(this);
        }
        
        Set<String> reachable = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        if (declarationNodes.containsKey("Main")) {
            queue.add("Main");
            reachable.add("Main");
        }
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            Set<String> deps = dependencies.get(current);
            if (deps != null) {
                for (String dep : deps) {
                    if (declarationNodes.containsKey(dep) && !reachable.contains(dep)) {
                        reachable.add(dep);
                        queue.add(dep);
                    }
                }
            }
        }
        
        List<TopLevelDecl> toRemove = new ArrayList<>();
        for (TopLevelDecl decl : program.getTopLevelDeclarations()) {
            String name = "";
            int line = 0;
            
            if (decl instanceof ModuleDecl) {
                name = ((ModuleDecl) decl).getModule().getName().getName();
                line = ((ModuleDecl) decl).getModule().getName().getLine();
            } else if (decl instanceof StructDecl) {
                name = ((StructDecl) decl).getStruct().getName().getName();
                line = ((StructDecl) decl).getStruct().getName().getLine();
            }
            
            if (!name.isEmpty() && !reachable.contains(name)) {
                System.out.println("Warning Line " + line + ": " + name + " is unreachable");
                toRemove.add(decl);
            }
        }
        
        // program.getTopLevelDeclarations().removeAll(toRemove); 
        // -> Must delete/comment previous line for type check, since it deletes 
        // simple functions that are inaccesable

        return null;
    }

    @Override
    public Void visit(ModuleDecl moduleDecl) {
        Module module = moduleDecl.getModule();
        if (module == null) return null;

        String moduleName = module.getName().getName();
        ModuleItem moduleItem = new ModuleItem(moduleName); 
        
        try {
            SymbolTable.top.put(moduleItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Line " + module.getName().getLine() + ": " + moduleName + " already defined");
        }

        SymbolTable.push(moduleItem.getModuleSymbolTable());

        currentModuleName = moduleName;
        currentModule = module;

        for (main.ast.types.Identifier includeId : module.getIncludes()) {
            dependencies.get(currentModuleName).add(includeId.getName());
        }

        for (Member member : module.getMembers()) {
            member.accept(this);
        }

        currentModuleName = null;
        currentModule = null;
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(StructDecl structDecl) {
        Struct struct = structDecl.getStruct();
        if (struct == null) return null;

        String structName = struct.getName().getName();
        
        SymbolTable structScope = new SymbolTable(SymbolTable.top);
        SymbolTable.push(structScope);

        currentModuleName = structName;

        for (Member member : struct.getMembers()) {
            member.accept(this);
        }

        currentModuleName = null;
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(MethodDecl methodDecl) {
        Method method = methodDecl.getMethod();
        if (method == null) return null;

        String methodName = method.getName().getName();
        main.ast.types.Type returnType = method.getReturnType();
        
        trackDependency(returnType);
        
        String visibility = methodDecl.getAccessModifier() != null ? 
                    methodDecl.getAccessModifier().toString().toLowerCase() : "public";

        MethodItem methodItem = new MethodItem(methodName, returnType, visibility);
        
        try {
            SymbolTable.top.put(methodItem);
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Line " + method.getName().getLine() + ": " + methodName + " already defined");
        }

        SymbolTable newScope = new SymbolTable(SymbolTable.top);
        SymbolTable.push(newScope);

        for (Parameter param : method.getParameters()) {
            methodItem.addParameterType(param.getType());
            param.accept(this);
        }

        if (method.getBody() != null) {
            method.getBody().accept(this);
        }

        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(Parameter parameter) {
        String paramName = parameter.getName().getName();
        main.ast.types.Type paramType = parameter.getType();
        
        trackDependency(paramType);

        VarItem varItem = new VarItem(paramName, paramType, false, "public");
        
        try {
            SymbolTable.top.put(varItem);
            initializedVars.add(varItem); 
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Line " + parameter.getName().getLine() + ": " + paramName + " already defined");
        }
        
        return null;
    }

    @Override
    public Void visit(VarDecl varDecl) {
        Var var = varDecl.getVar();
        if (var == null) return null;

        String varName = var.getName().getName();
        main.ast.types.Type varType = var.getType();
        
        trackDependency(varType);
        if (var.getConstructorCall() != null) var.getConstructorCall().accept(this);

        boolean isMut = var.getIsMutable(); 
        
        String visibility = varDecl.getAccessModifier() != null ? 
                    varDecl.getAccessModifier().toString().toLowerCase() : "private";

        VarItem varItem = new VarItem(varName, varType, isMut, visibility);
        
        try {
            SymbolTable.top.put(varItem);
            initializedVars.add(varItem); 
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Line " + var.getName().getLine() + ": " + varName + " already defined");
        }
        
        return null;
    }

    @Override
    public Void visit(Block block) {
        SymbolTable blockScope = new SymbolTable(SymbolTable.top);
        SymbolTable.push(blockScope);

        for (Statement stmt : block.getStatements()) {
            stmt.accept(this);
        }

        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(VarDeclStmt varDeclStmt) {
        Var var = varDeclStmt.getVar();
        if (var == null) return null;

        String varName = var.getName().getName();
        main.ast.types.Type varType = var.getType();
        
        trackDependency(varType);
        if (var.getConstructorCall() != null) var.getConstructorCall().accept(this);

        boolean isMut = var.getIsMutable();
        VarItem varItem = new VarItem(varName, varType, isMut, "local");

        try {
            SymbolTable.top.put(varItem);
            
            boolean isUserDefined = (varType instanceof main.ast.types.UserDefinedType);
            
            if (varDeclStmt.getInitial() != null || isUserDefined) {
                if (varDeclStmt.getInitial() != null) {
                    varDeclStmt.getInitial().accept(this);
                }
                initializedVars.add(varItem);
            }
        } catch (ItemAlreadyExistsException e) {
            System.out.println("Line " + var.getName().getLine() + ": " + varName + " already defined");
        }
        
        return null;
    }

    @Override
    public Void visit(AssignStmt assignStmt) {
        if (assignStmt.getRight() != null) assignStmt.getRight().accept(this);

        isLValue = true;
        if (assignStmt.getLeft() != null) assignStmt.getLeft().accept(this);
        isLValue = false;

        return null;
    }

    @Override
    public Void visit(SimpleLoc simpleLoc) {
        String name = simpleLoc.getId().getName();
        if (!isDeclaredLocallyOrInIncludes(name)) {
            System.out.println("Line " + simpleLoc.getId().getLine() + ": " + name + " not declared");
        } else {
            try {
                SymbolTableItem item = SymbolTable.top.get(name);
                if (item instanceof ModuleItem || item.getClass().getSimpleName().equals("StructItem")) {
                    if (currentModuleName != null) dependencies.get(currentModuleName).add(name);
                } else {
                    if (isLValue) {
                        initializedVars.add(item);
                    } else if (!initializedVars.contains(item)) {
                        System.out.println("Line " + simpleLoc.getId().getLine() + ": " + name + " is uninitialized");
                    }
                }
            } catch (ItemNotFoundException e) {}
        }
        return null;
    }

    @Override
    public Void visit(MemberLoc memberLoc) {
        String instanceName = memberLoc.getMemberName().getName();
        
        if (!isDeclaredLocallyOrInIncludes(instanceName)) {
            System.out.println("Line " + memberLoc.getMemberName().getLine() + ": " + instanceName + " not declared");
        } else {
            try {
                SymbolTableItem item = SymbolTable.top.get(instanceName);
                if (item instanceof ModuleItem || item.getClass().getSimpleName().equals("StructItem")) {
                    if (currentModuleName != null) dependencies.get(currentModuleName).add(instanceName);
                } else {
                    if (isLValue) {
                        initializedVars.add(item);
                    } else if (!initializedVars.contains(item)) {
                        System.out.println("Line " + memberLoc.getMemberName().getLine() + ": " + instanceName + " is uninitialized");
                    }
                }
            } catch (ItemNotFoundException e) {}
        }

        String fieldName = "";
        int fieldLine = memberLoc.getMemberName().getLine();
        
        if (memberLoc.getLoc() instanceof SimpleLoc) {
            fieldName = ((SimpleLoc) memberLoc.getLoc()).getId().getName();
            fieldLine = ((SimpleLoc) memberLoc.getLoc()).getId().getLine();
        }

        if (!fieldName.isEmpty()) {
            SimpleLoc dummyInstance = new SimpleLoc(memberLoc.getMemberName());
            checkMemberAccess(dummyInstance, fieldName, fieldLine);
        }

        return null;
    }
    
    @Override
    public Void visit(ConstructorCall constructorCall) {
        if (currentModuleName != null) {
            dependencies.get(currentModuleName).add(constructorCall.getName().getName());
        }
        for (Expression arg : constructorCall.getArguments()) {
            if (arg != null) arg.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ThisLoc thisLoc) {
        if (thisLoc.getLoc() != null) {
            String fieldName = "";
            int line = thisLoc.getLine();
            if (thisLoc.getLoc() instanceof SimpleLoc) {
                fieldName = ((SimpleLoc) thisLoc.getLoc()).getId().getName();
                line = ((SimpleLoc) thisLoc.getLoc()).getId().getLine();
            }
            if (!fieldName.isEmpty()) {
                checkMemberAccess(thisLoc, fieldName, line);
            }
        }
        return null;
    }

    @Override
    public Void visit(MethodCall methodCall) {
        String methodName = methodCall.getCallee().getName();
        
        if (methodCall.getInstance() == null) {
            if (!isDeclaredLocallyOrInIncludes(methodName)) {
                System.out.println("Line " + methodCall.getCallee().getLine() + ": " + methodName + " not declared");
            }
        } else {
            methodCall.getInstance().accept(this);
            checkMemberAccess(methodCall.getInstance(), methodName, methodCall.getCallee().getLine());
        }

        for (Expression arg : methodCall.getArguments()) {
            arg.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(OutputStmt outputStmt) {
        if (outputStmt.getValue() != null) outputStmt.getValue().accept(this);
        return null;
    }

    @Override
    public Void visit(IfStmt ifStmt) {
        if (ifStmt.getCondition() != null) ifStmt.getCondition().accept(this);
        if (ifStmt.getThenBranch() != null) ifStmt.getThenBranch().accept(this);
        if (ifStmt.getElseBranch() != null) ifStmt.getElseBranch().accept(this);
        return null;
    }

    @Override
    public Void visit(WhileStmt whileStmt) {
        if (whileStmt.getCondition() != null) whileStmt.getCondition().accept(this);
        if (whileStmt.getBody() != null) whileStmt.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpr) {
        if (binaryExpr.getLeftOperand() != null) binaryExpr.getLeftOperand().accept(this);
        if (binaryExpr.getRightOperand() != null) binaryExpr.getRightOperand().accept(this);
        return null;
    }

    // --- HELPER METHODS ---
    
    private void trackDependency(main.ast.types.Type type) {
        if (type instanceof main.ast.types.UserDefinedType && currentModuleName != null) {
            dependencies.get(currentModuleName).add(((main.ast.types.UserDefinedType) type).getId().getName());
        }
    }

    private boolean isDeclaredLocallyOrInIncludes(String name) {
        try {
            SymbolTable.top.get(name);
            return true;
        } catch (ItemNotFoundException e) {
            if (currentModule != null) {
                for (main.ast.types.Identifier includeId : currentModule.getIncludes()) {
                    try {
                        SymbolTableItem incModule = SymbolTable.root.get(includeId.getName());
                        if (incModule instanceof ModuleItem) {
                            try {
                                ((ModuleItem) incModule).getModuleSymbolTable().get(name);
                                return true;
                            } catch (ItemNotFoundException ignored) {}
                        }
                    } catch (ItemNotFoundException ignored) {}
                }
            }
            return false;
        }
    }

    private void checkMemberAccess(Location instance, String memberName, int line) {
        String instanceTypeName = null;

        if (instance instanceof ThisLoc) {
            instanceTypeName = currentModuleName;
        } else if (instance instanceof SimpleLoc) {
            String varName = ((SimpleLoc) instance).getId().getName();
            try {
                SymbolTableItem item = SymbolTable.top.get(varName);
                if (item instanceof VarItem) {
                    main.ast.types.Type type = ((VarItem) item).getType();
                    if (type instanceof main.ast.types.UserDefinedType) {
                        instanceTypeName = ((main.ast.types.UserDefinedType) type).getId().getName();
                    }
                }
            } catch (ItemNotFoundException e) {
                return;
            }
        }

        if (instanceTypeName != null) {
            boolean found = false;
            String visibility = "public";

            try {
                SymbolTableItem moduleOrStruct = SymbolTable.root.get(instanceTypeName);
                if (moduleOrStruct instanceof ModuleItem) {
                    SymbolTable memberScope = ((ModuleItem) moduleOrStruct).getModuleSymbolTable();
                    try {
                        SymbolTableItem memberItem = memberScope.get(memberName);
                        found = true;
                        if (memberItem instanceof VarItem) visibility = ((VarItem) memberItem).getVisibility();
                        else if (memberItem instanceof MethodItem) visibility = ((MethodItem) memberItem).getVisibility();
                    } catch (ItemNotFoundException e) {}
                }
            } catch (ItemNotFoundException e) {}

            // 2. If not found, check its included modules
            if (!found && instanceTypeName.equals(currentModuleName) && currentModule != null) {
                for (main.ast.types.Identifier includeId : currentModule.getIncludes()) {
                    try {
                        SymbolTableItem incModule = SymbolTable.root.get(includeId.getName());
                        if (incModule instanceof ModuleItem) {
                            try {
                                SymbolTableItem memberItem = ((ModuleItem) incModule).getModuleSymbolTable().get(memberName);
                                found = true;
                                if (memberItem instanceof VarItem) visibility = ((VarItem) memberItem).getVisibility();
                                else if (memberItem instanceof MethodItem) visibility = ((MethodItem) memberItem).getVisibility();
                                break;
                            } catch (ItemNotFoundException ignored) {}
                        }
                    } catch (ItemNotFoundException ignored) {}
                }
            }

            if (found) {
                if ("private".equalsIgnoreCase(visibility) && !instanceTypeName.equals(currentModuleName)) {
                    System.out.println("Line " + line + ": " + memberName + " is private");
                }
            } else {
                System.out.println("Line " + line + ": " + memberName + " not declared");
            }
        }
    }
}