package main.visitor;

import main.ast.declarations.Module;
import main.ast.expressions.*;
import main.ast.expressions.literals.*;
import main.ast.types.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.SymbolTableItem;
import main.symbolTable.items.VarItem;
import main.symbolTable.items.MethodItem;
import main.symbolTable.items.ModuleItem;

import java.util.List;

public class ExpressionTypeChecker extends Visitor<Type> {

    // We pass the current module from TypeChecker so we can check 'includes'
    public Module currentModule;

    @Override
    public Type visit(IntLiteral intLiteral) { return new PrimitiveType(PrimitiveType.Primitive.INT); }

    @Override
    public Type visit(FloatLiteral floatLiteral) { return new PrimitiveType(PrimitiveType.Primitive.FLOAT); }

    @Override
    public Type visit(DoubleLiteral doubleLiteral) { return new PrimitiveType(PrimitiveType.Primitive.DOUBLE); }

    @Override
    public Type visit(CharLiteral charLiteral) { return new PrimitiveType(PrimitiveType.Primitive.CHAR); }

    @Override
    public Type visit(BoolLiteral boolLiteral) { return new PrimitiveType(PrimitiveType.Primitive.BOOL); }

    @Override
    public Type visit(ConstantExpression constantExpr) {
        Object val = constantExpr.getValue();
        if (val instanceof Integer) return new PrimitiveType(PrimitiveType.Primitive.INT);
        if (val instanceof Float) return new PrimitiveType(PrimitiveType.Primitive.FLOAT);
        if (val instanceof Double) return new PrimitiveType(PrimitiveType.Primitive.DOUBLE);
        if (val instanceof Character) return new PrimitiveType(PrimitiveType.Primitive.CHAR);
        if (val instanceof Boolean) return new PrimitiveType(PrimitiveType.Primitive.BOOL);
        return new PrimitiveType(PrimitiveType.Primitive.VOID);
    }

    @Override
    public Type visit(SimpleLoc simpleLoc) {
        try {
            SymbolTableItem item = SymbolTable.top.get(simpleLoc.getId().getName());
            
            // If it's a standard variable, return its type
            if (item instanceof VarItem) {
                return ((VarItem) item).getType();
            } 
            // NEW: If it's a static module/struct call (e.g., Calculator.add), return it as a UserDefinedType
            else if (item instanceof ModuleItem || item.getClass().getSimpleName().equals("StructItem")) {
                return new UserDefinedType(simpleLoc.getId());
            }
            
        } catch (ItemNotFoundException e) {
            // Fallback (NameAnalyzer already threw the "not declared" error)
        }
        return new PrimitiveType(PrimitiveType.Primitive.VOID); 
    }

    @Override
    public Type visit(MemberLoc memberLoc) {
        Type instanceType = new PrimitiveType(PrimitiveType.Primitive.VOID);
        if (memberLoc.getMemberName() != null) {
            try {
                VarItem item = (VarItem) SymbolTable.top.get(memberLoc.getMemberName().getName());
                instanceType = item.getType();
            } catch (Exception e) {}
        }
        
        if (instanceType instanceof UserDefinedType) {
            String typeName = ((UserDefinedType) instanceType).getId().getName();
            try {
                ModuleItem mod = (ModuleItem) SymbolTable.root.get(typeName);
                String fieldName = "";
                if (memberLoc.getLoc() instanceof SimpleLoc) {
                    fieldName = ((SimpleLoc)memberLoc.getLoc()).getId().getName();
                }
                VarItem field = (VarItem) mod.getModuleSymbolTable().get(fieldName);
                return field.getType();
            } catch (Exception e) {}
        }
        return new PrimitiveType(PrimitiveType.Primitive.VOID);
    }

    @Override
    public Type visit(BinaryExpression binaryExpr) {
        Type leftType = null;
        if (binaryExpr.getLeftOperand() != null) leftType = binaryExpr.getLeftOperand().accept(this);
        Type rightType = null;
        if (binaryExpr.getRightOperand() != null) rightType = binaryExpr.getRightOperand().accept(this);
        
        String op = binaryExpr.getOperator() != null ? binaryExpr.getOperator().toString().toLowerCase() : "";
        
        // Relational and logical operators always return bool
        if (op.equals("less") || op.equals("greater") || op.equals("less_eq") || op.equals("greater_eq") || 
            op.equals("equal") || op.equals("not_equal") || op.equals("and") || op.equals("or")) {
            return new PrimitiveType(PrimitiveType.Primitive.BOOL);
        }
        
        // Mathematical operations (Simple type promotion)
        if (leftType != null && rightType != null) {
            if (leftType.getStr().equals("double") || rightType.getStr().equals("double")) 
                return new PrimitiveType(PrimitiveType.Primitive.DOUBLE);
            if (leftType.getStr().equals("float") || rightType.getStr().equals("float")) 
                return new PrimitiveType(PrimitiveType.Primitive.FLOAT);
        }
        return new PrimitiveType(PrimitiveType.Primitive.INT);
    }

    @Override
    public Type visit(MethodCall methodCall) {
        MethodItem methodItem = findMethodItem(methodCall);
        if (methodItem != null) {
            List<Type> expectedTypes = methodItem.getParameterTypes();
            List<Expression> args = methodCall.getArguments();
            
            // ERROR 5a: Argument count mismatch
            if (args.size() != expectedTypes.size()) {
                System.out.println("Line " + methodCall.getCallee().getLine() + ": Argument count mismatch for method " + 
                                   methodCall.getCallee().getName() + ". Expected " + expectedTypes.size() + ", got " + args.size());
            } else {
                // ERROR 5b: Argument type mismatch
                for (int i = 0; i < args.size(); i++) {
                    Type actualType = args.get(i).accept(this);
                    Type expectedType = expectedTypes.get(i);
                    if (actualType != null && expectedType != null) {
                        if (!actualType.getStr().equals(expectedType.getStr())) {
                            System.out.println("Line " + methodCall.getCallee().getLine() + ": Argument type mismatch for method " + 
                                               methodCall.getCallee().getName() + ", parameter " + (i + 1) + ". Expected " + 
                                               expectedType.getStr() + ", got " + actualType.getStr());
                        }
                    }
                }
            }
            return methodItem.getReturnType();
        }
        return new PrimitiveType(PrimitiveType.Primitive.VOID);
    }

    // Helper to find methods (Supports local, includes, and struct.method() calls)
    private MethodItem findMethodItem(MethodCall methodCall) {
        String methodName = methodCall.getCallee().getName();
        if (methodCall.getInstance() == null) {
            try {
                SymbolTableItem item = SymbolTable.top.get(methodName);
                if (item instanceof MethodItem) return (MethodItem) item;
            } catch (ItemNotFoundException e) {
                if (currentModule != null) {
                    for (main.ast.types.Identifier inc : currentModule.getIncludes()) {
                        try {
                            SymbolTableItem mod = SymbolTable.root.get(inc.getName());
                            if (mod instanceof ModuleItem) {
                                SymbolTableItem item = ((ModuleItem)mod).getModuleSymbolTable().get(methodName);
                                if (item instanceof MethodItem) return (MethodItem) item;
                            }
                        } catch (Exception ignored) {}
                    }
                }
            }
        } else {
            Type instanceType = methodCall.getInstance().accept(this);
            if (instanceType instanceof UserDefinedType) {
                String typeName = ((UserDefinedType) instanceType).getId().getName();
                try {
                    SymbolTableItem mod = SymbolTable.root.get(typeName);
                    if (mod instanceof ModuleItem) {
                        SymbolTableItem item = ((ModuleItem)mod).getModuleSymbolTable().get(methodName);
                        if (item instanceof MethodItem) return (MethodItem) item;
                    }
                } catch (Exception e) {}
            }
        }
        return null;
    }
}