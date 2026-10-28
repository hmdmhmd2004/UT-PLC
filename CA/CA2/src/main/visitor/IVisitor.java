package main.visitor;

import main.ast.declarations.Module;

import main.ast.core.*;
import main.ast.declarations.*;
import main.ast.statements.*;
import main.ast.expressions.*;
import main.ast.expressions.literals.*;
import main.ast.types.*;

public interface IVisitor<T> {
    // Core
    T visit(Program program);

    // Declarations
    T visit(Module module);
    T visit(ModuleDecl moduleDecl);
    T visit(Struct struct);
    T visit(StructDecl structDecl);
    T visit(Method method);
    T visit(MethodDecl methodDecl);
    T visit(Parameter parameter);
    T visit(Var var);
    T visit(VarDecl varDecl);

    // Statements
    T visit(AssignStmt assignStmt);
    T visit(Block block);
    T visit(BreakJump breakJump);
    T visit(ContinueJump continueJump);
    T visit(ForStmt forStmt);
    T visit(IfStmt ifStmt);
    T visit(InputStmt inputStmt);
    T visit(MethodCallStmt methodCallStmt);
    T visit(OutputStmt outputStmt);
    T visit(ReturnStmt returnStmt);
    T visit(VarDeclStmt varDeclStmt);
    T visit(WhileStmt whileStmt);

    // Expressions
    T visit(BinaryExpression binaryExpression);
    T visit(ConstructorCall constructorCall);
    T visit(MethodCall methodCall);
    T visit(ParanthesisExpr paranthesisExpr);
    T visit(UnaryExpression unaryExpression);
    T visit(UnaryOpExpr unaryOpExpr);

    // Locations
    T visit(MemberLoc memberLoc);
    T visit(MethodCallLoc methodCallLoc);
    T visit(SimpleLoc simpleLoc);
    T visit(ThisLoc thisLoc);

    // Literals
    T visit(BoolLiteral boolLiteral);
    T visit(CharLiteral charLiteral);
    T visit(DoubleLiteral doubleLiteral);
    T visit(FloatLiteral floatLiteral);
    T visit(IntLiteral intLiteral);

    // Types
    T visit(Identifier identifier);
    T visit(PrimitiveType primitiveType);
    T visit(UserDefinedType userDefinedType);
}