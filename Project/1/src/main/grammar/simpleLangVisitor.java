// Generated from /media/hmdmhmd/New Volume/University/Term-6/PLC/Project/1/src/main/grammar/simpleLang.g4 by ANTLR 4.13.1

    package main.grammar;
    import main.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link simpleLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface simpleLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(simpleLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#mol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMol(simpleLangParser.MolContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(simpleLangParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct(simpleLangParser.StructContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#m_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitM_dec(simpleLangParser.M_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#s_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS_dec(simpleLangParser.S_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclude(simpleLangParser.IncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(simpleLangParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(simpleLangParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(simpleLangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#module_sample_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule_sample_stmt(simpleLangParser.Module_sample_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#dec_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec_stmt(simpleLangParser.Dec_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(simpleLangParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#output_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput_stmt(simpleLangParser.Output_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stmt(simpleLangParser.Assign_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(simpleLangParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(simpleLangParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#elif_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElif_stmt(simpleLangParser.Elif_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#else_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_stmt(simpleLangParser.Else_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(simpleLangParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#for_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stmt(simpleLangParser.For_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#for_changes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_changes(simpleLangParser.For_changesContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#passing_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassing_args(simpleLangParser.Passing_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#passing_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassing_arg(simpleLangParser.Passing_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(simpleLangParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(simpleLangParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(simpleLangParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#expr_rest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_rest(simpleLangParser.Expr_restContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#return_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_type(simpleLangParser.Return_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(simpleLangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(simpleLangParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_type(simpleLangParser.Simple_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive(simpleLangParser.PrimitiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#true}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(simpleLangParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(simpleLangParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#uint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUint(simpleLangParser.UintContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(simpleLangParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(simpleLangParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath(simpleLangParser.MathContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(simpleLangParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#num_sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum_sign(simpleLangParser.Num_signContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(simpleLangParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(simpleLangParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link simpleLangParser#access_lvl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess_lvl(simpleLangParser.Access_lvlContext ctx);
}