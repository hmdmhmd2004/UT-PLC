package main.visitor;

import main.ast.core.Program;
import main.ast.declarations.*;
import main.ast.statements.*;
import main.ast.types.Type;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.*;

public class TypeChecker extends Visitor<Void> {
    
    private ExpressionTypeChecker expressionChecker = new ExpressionTypeChecker();
    private Type currentExpectedReturnType = null;

    @Override
    public Void visit(Program program) {
        SymbolTable.push(SymbolTable.root);
        for (TopLevelDecl decl : program.getTopLevelDeclarations()) {
            decl.accept(this);
        }
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(ModuleDecl moduleDecl) {
        expressionChecker.currentModule = moduleDecl.getModule();
        try {
            ModuleItem moduleItem = (ModuleItem) SymbolTable.top.get(moduleDecl.getModule().getName().getName());
            SymbolTable.push(moduleItem.getModuleSymbolTable());
            
            for (Member member : moduleDecl.getModule().getMembers()) {
                member.accept(this);
            }
            
            SymbolTable.pop();
        } catch (ItemNotFoundException e) {}
        return null;
    }

    @Override
    public Void visit(StructDecl structDecl) {
        try {
            for (Member member : structDecl.getStruct().getMembers()) {
                member.accept(this);
            }
        } catch (Exception e) {}
        return null;
    }

    @Override
    public Void visit(MethodDecl methodDecl) {
        try {
            SymbolTable newScope = new SymbolTable(SymbolTable.top);
            SymbolTable.push(newScope);

            for (Parameter param : methodDecl.getMethod().getParameters()) {
                SymbolTable.top.put(new VarItem(param.getName().getName(), param.getType(), false, "public"));
            }

            currentExpectedReturnType = methodDecl.getMethod().getReturnType();

            if (methodDecl.getMethod().getBody() != null) {
                methodDecl.getMethod().getBody().accept(this);
            }

            currentExpectedReturnType = null;
            SymbolTable.pop();
        } catch (Exception e) {}
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
        try {
            String name = varDeclStmt.getVar().getName().getName();
            Type type = varDeclStmt.getVar().getType();
            boolean isMut = varDeclStmt.getVar().getIsMutable();
            SymbolTable.top.put(new VarItem(name, type, isMut, "local"));
            
            if (varDeclStmt.getInitial() != null) {
                Type rightType = varDeclStmt.getInitial().accept(expressionChecker);
                if (rightType != null && !type.getStr().equals(rightType.getStr())) {
                    System.out.println("Line " + varDeclStmt.getLine() + ": Type mismatch in assignment. Cannot assign " + 
                                       rightType.getStr() + " to " + type.getStr());
                }
            }
        } catch (Exception e) {}
        return null;
    }

    @Override
    public Void visit(AssignStmt assignStmt) {
        if (assignStmt.getLeft() instanceof main.ast.expressions.SimpleLoc) {
            String varName = ((main.ast.expressions.SimpleLoc) assignStmt.getLeft()).getId().getName();
            try {
                VarItem item = (VarItem) SymbolTable.top.get(varName);
                if (!item.isMut()) {
                    System.out.println("Line " + assignStmt.getLine() + ": Cannot modify immutable variable " + varName);
                }
            } catch (ItemNotFoundException e) {}
        }

        Type leftType = assignStmt.getLeft().accept(expressionChecker);
        Type rightType = assignStmt.getRight().accept(expressionChecker);

        if (leftType != null && rightType != null) {
            if (!leftType.getStr().equals(rightType.getStr())) {
                System.out.println("Line " + assignStmt.getLine() + ": Type mismatch in assignment. Cannot assign " + 
                                   rightType.getStr() + " to " + leftType.getStr());
            }
        }
        return null;
    }

    @Override
    public Void visit(IfStmt ifStmt) {
        if (ifStmt.getCondition() != null) {
            Type condType = ifStmt.getCondition().accept(expressionChecker);
            if (condType != null && !condType.getStr().equals("bool")) {
                System.out.println("Line " + ifStmt.getLine() + ": Condition type must be bool");
            }
        }
        if (ifStmt.getThenBranch() != null) ifStmt.getThenBranch().accept(this);
        if (ifStmt.getElseBranch() != null) ifStmt.getElseBranch().accept(this);
        return null;
    }

    @Override
    public Void visit(WhileStmt whileStmt) {
        if (whileStmt.getCondition() != null) {
            Type condType = whileStmt.getCondition().accept(expressionChecker);
            if (condType != null && !condType.getStr().equals("bool")) {
                System.out.println("Line " + whileStmt.getLine() + ": Condition type must be bool");
            }
        }
        if (whileStmt.getBody() != null) whileStmt.getBody().accept(this);
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        if (returnStmt.getValue() != null && currentExpectedReturnType != null) {
            Type actualType = returnStmt.getValue().accept(expressionChecker);
            if (actualType != null && !actualType.getStr().equals(currentExpectedReturnType.getStr())) {
                System.out.println("Line " + returnStmt.getLine() + ": Return type mismatch. Expected " + 
                                   currentExpectedReturnType.getStr() + ", got " + actualType.getStr());
            }
        }
        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        if (methodCallStmt.getMethodCall() != null) {
            methodCallStmt.getMethodCall().accept(expressionChecker);
        }
        return null;
    }
}