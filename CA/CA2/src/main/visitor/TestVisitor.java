package main.visitor;

import main.ast.core.*;
import main.ast.declarations.Module;
import main.ast.declarations.*;
import main.ast.types.*;
import main.ast.statements.*;
import main.ast.expressions.*;
import main.ast.expressions.literals.*;

import java.util.List;

public class TestVisitor extends Visitor<Void> {   
    private int depth = 0;

    private void printIndented(String text) {
        for (int i = 0; i < depth; i++) {
            System.out.print("        "); 
        }
        System.out.println(text);
    }

    private String getTypeString(Type t) {
        if (t == null) return "void";
        if (t instanceof PrimitiveType) {
            return ((PrimitiveType) t).getPrimitive().toString().toLowerCase();
        }
        if (t instanceof UserDefinedType) {
            return ((UserDefinedType) t).getName().getName();
        }
        return "unknown";
    }

    private String getParamTypesString(List<Parameter> params) {
        if (params == null || params.isEmpty()) {
            return "void";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(getTypeString(params.get(i).getType()));
            if (i < params.size() - 1) {
                sb.append("*");
            }
        }
        return sb.toString();
    }

    @Override
    public Void visit(Program program) {
        int modules = 0;
        int structs = 0;
        
        for (TopLevelDecl tld : program.getTopLevelDeclarations()) {
            if (tld instanceof ModuleDecl) modules++;
            if (tld instanceof StructDecl) structs++;
        }
        
        printIndented("program [modules:" + modules + " structs:" + structs + "]");
        
        depth++;
        for (TopLevelDecl tld : program.getTopLevelDeclarations()) {
            tld.accept(this);
        }
        depth--;
        
        return null;
    }

    @Override
    public Void visit(ModuleDecl moduleDecl) {
        if (moduleDecl.getModule() != null) {
            moduleDecl.getModule().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Module module) {
        int methods = 0;
        int fields = 0;
        
        // Count methods and fields
        for (Member m : module.getMembers()) {
            if (m instanceof MethodDecl) methods++;
            if (m instanceof VarDecl) fields++;
        }
        
        printIndented("module " + module.getName().getName() + " [methods:" + methods + " fields:" + fields + "]");
        
        depth++;
        for (Member m : module.getMembers()) {
            m.accept(this);
        }
        depth--;
        
        return null;
    }

    @Override
    public Void visit(StructDecl structDecl) {
        if (structDecl.getStruct() != null) {
            structDecl.getStruct().accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Struct struct) {
        int fields = 0;
        
        for (Member m : struct.getMembers()) {
            if (m instanceof VarDecl) fields++;
        }
        
        printIndented("struct " + struct.getName().getName() + " [fields:" + fields + "]");
        
        depth++;
        for (Member m : struct.getMembers()) {
            m.accept(this);
        }
        depth--;
        
        return null;
    }

    @Override
    public Void visit(MethodDecl methodDecl) {
        Method m = methodDecl.getMethod();
        if (m != null) {
            // Build the modifier string conditionally. If null, leave it entirely empty.
            String accModStr = methodDecl.getAccessModifier() != null ? 
                ":" + methodDecl.getAccessModifier().toString().toLowerCase() : "";
                
            String retType = getTypeString(m.getReturnType());
            String paramTypes = getParamTypesString(m.getParameters());
            int stmts = m.getBody() != null ? m.getBody().getStatements().size() : 0;
            
            // Note: Removed the hardcoded ":" before accModStr
            printIndented("method " + m.getName().getName() + " (" + paramTypes + " -> " + retType + ")" + accModStr + " [statements:" + stmts + "]");
        }
        return null;
    }

    @Override
    public Void visit(VarDecl varDecl) {
        // Build the modifier string conditionally. If null, leave it entirely empty.
        String accModStr = varDecl.getAccessModifier() != null ? 
            ":" + varDecl.getAccessModifier().toString().toLowerCase() : "";
            
        Var var = varDecl.getVar();
        
        if (var != null) {
            String typeStr = getTypeString(var.getType());
            String name = var.getName().getName();
            
            // Note: Removed the hardcoded ":" before accModStr
            printIndented("field " + name + " " + typeStr + accModStr);
        }
        return null;
    }
}