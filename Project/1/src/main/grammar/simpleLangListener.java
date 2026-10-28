// Generated from /media/hmdmhmd/New Volume/University/Term-6/PLC/Project/1/src/main/grammar/simpleLang.g4 by ANTLR 4.13.1

    package main.grammar;
    import main.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link simpleLangParser}.
 */
public interface simpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(simpleLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(simpleLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#mol}.
	 * @param ctx the parse tree
	 */
	void enterMol(simpleLangParser.MolContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#mol}.
	 * @param ctx the parse tree
	 */
	void exitMol(simpleLangParser.MolContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(simpleLangParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(simpleLangParser.ModuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(simpleLangParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(simpleLangParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#m_dec}.
	 * @param ctx the parse tree
	 */
	void enterM_dec(simpleLangParser.M_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#m_dec}.
	 * @param ctx the parse tree
	 */
	void exitM_dec(simpleLangParser.M_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#s_dec}.
	 * @param ctx the parse tree
	 */
	void enterS_dec(simpleLangParser.S_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#s_dec}.
	 * @param ctx the parse tree
	 */
	void exitS_dec(simpleLangParser.S_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(simpleLangParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(simpleLangParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(simpleLangParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(simpleLangParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(simpleLangParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(simpleLangParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(simpleLangParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(simpleLangParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#module_sample_stmt}.
	 * @param ctx the parse tree
	 */
	void enterModule_sample_stmt(simpleLangParser.Module_sample_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#module_sample_stmt}.
	 * @param ctx the parse tree
	 */
	void exitModule_sample_stmt(simpleLangParser.Module_sample_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#dec_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDec_stmt(simpleLangParser.Dec_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#dec_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDec_stmt(simpleLangParser.Dec_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(simpleLangParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(simpleLangParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#output_stmt}.
	 * @param ctx the parse tree
	 */
	void enterOutput_stmt(simpleLangParser.Output_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#output_stmt}.
	 * @param ctx the parse tree
	 */
	void exitOutput_stmt(simpleLangParser.Output_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(simpleLangParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(simpleLangParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#access}.
	 * @param ctx the parse tree
	 */
	void enterAccess(simpleLangParser.AccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#access}.
	 * @param ctx the parse tree
	 */
	void exitAccess(simpleLangParser.AccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(simpleLangParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(simpleLangParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#elif_stmt}.
	 * @param ctx the parse tree
	 */
	void enterElif_stmt(simpleLangParser.Elif_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#elif_stmt}.
	 * @param ctx the parse tree
	 */
	void exitElif_stmt(simpleLangParser.Elif_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void enterElse_stmt(simpleLangParser.Else_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void exitElse_stmt(simpleLangParser.Else_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(simpleLangParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(simpleLangParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFor_stmt(simpleLangParser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFor_stmt(simpleLangParser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#for_changes}.
	 * @param ctx the parse tree
	 */
	void enterFor_changes(simpleLangParser.For_changesContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#for_changes}.
	 * @param ctx the parse tree
	 */
	void exitFor_changes(simpleLangParser.For_changesContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#passing_args}.
	 * @param ctx the parse tree
	 */
	void enterPassing_args(simpleLangParser.Passing_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#passing_args}.
	 * @param ctx the parse tree
	 */
	void exitPassing_args(simpleLangParser.Passing_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#passing_arg}.
	 * @param ctx the parse tree
	 */
	void enterPassing_arg(simpleLangParser.Passing_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#passing_arg}.
	 * @param ctx the parse tree
	 */
	void exitPassing_arg(simpleLangParser.Passing_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(simpleLangParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(simpleLangParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(simpleLangParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(simpleLangParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(simpleLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(simpleLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#expr_rest}.
	 * @param ctx the parse tree
	 */
	void enterExpr_rest(simpleLangParser.Expr_restContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#expr_rest}.
	 * @param ctx the parse tree
	 */
	void exitExpr_rest(simpleLangParser.Expr_restContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(simpleLangParser.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(simpleLangParser.Return_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(simpleLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(simpleLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(simpleLangParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(simpleLangParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void enterSimple_type(simpleLangParser.Simple_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#simple_type}.
	 * @param ctx the parse tree
	 */
	void exitSimple_type(simpleLangParser.Simple_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive(simpleLangParser.PrimitiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive(simpleLangParser.PrimitiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#true}.
	 * @param ctx the parse tree
	 */
	void enterTrue(simpleLangParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#true}.
	 * @param ctx the parse tree
	 */
	void exitTrue(simpleLangParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#false}.
	 * @param ctx the parse tree
	 */
	void enterFalse(simpleLangParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#false}.
	 * @param ctx the parse tree
	 */
	void exitFalse(simpleLangParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#uint}.
	 * @param ctx the parse tree
	 */
	void enterUint(simpleLangParser.UintContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#uint}.
	 * @param ctx the parse tree
	 */
	void exitUint(simpleLangParser.UintContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#int}.
	 * @param ctx the parse tree
	 */
	void enterInt(simpleLangParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#int}.
	 * @param ctx the parse tree
	 */
	void exitInt(simpleLangParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#float}.
	 * @param ctx the parse tree
	 */
	void enterFloat(simpleLangParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#float}.
	 * @param ctx the parse tree
	 */
	void exitFloat(simpleLangParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#math}.
	 * @param ctx the parse tree
	 */
	void enterMath(simpleLangParser.MathContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#math}.
	 * @param ctx the parse tree
	 */
	void exitMath(simpleLangParser.MathContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(simpleLangParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(simpleLangParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#num_sign}.
	 * @param ctx the parse tree
	 */
	void enterNum_sign(simpleLangParser.Num_signContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#num_sign}.
	 * @param ctx the parse tree
	 */
	void exitNum_sign(simpleLangParser.Num_signContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(simpleLangParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(simpleLangParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(simpleLangParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(simpleLangParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link simpleLangParser#access_lvl}.
	 * @param ctx the parse tree
	 */
	void enterAccess_lvl(simpleLangParser.Access_lvlContext ctx);
	/**
	 * Exit a parse tree produced by {@link simpleLangParser#access_lvl}.
	 * @param ctx the parse tree
	 */
	void exitAccess_lvl(simpleLangParser.Access_lvlContext ctx);
}