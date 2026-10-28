// Generated from /media/hmdmhmd/New Volume/University/Term-6/PLC/Project/1/src/main/grammar/simpleLang.g4 by ANTLR 4.13.1

    package main.grammar;
    import main.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class simpleLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		MODULE=18, STRUCT=19, INCLUDES=20, BEGIN=21, END=22, MUT=23, INT_TYPE=24, 
		BOOL_TYPE=25, FLOAT_TYPE=26, DOUBLE_TYPE=27, CHAR_TYPE=28, VOID=29, RETURN=30, 
		OUTPUT=31, WHILE=32, FOR=33, TRUE_VAL=34, FALSE_VAL=35, INT_VAL=36, IF=37, 
		ELIF=38, ELSE=39, PUBLIC=40, PRIVATE=41, ID=42, DIGITS=43, WHITE_SPACE=44, 
		LINE_COMMENT=45, BLOCK_COMMENT=46;
	public static final int
		RULE_program = 0, RULE_mol = 1, RULE_module = 2, RULE_struct = 3, RULE_m_dec = 4, 
		RULE_s_dec = 5, RULE_include = 6, RULE_method = 7, RULE_field = 8, RULE_stmt = 9, 
		RULE_module_sample_stmt = 10, RULE_dec_stmt = 11, RULE_return_stmt = 12, 
		RULE_output_stmt = 13, RULE_assign_stmt = 14, RULE_access = 15, RULE_if_stmt = 16, 
		RULE_elif_stmt = 17, RULE_else_stmt = 18, RULE_while_stmt = 19, RULE_for_stmt = 20, 
		RULE_for_changes = 21, RULE_passing_args = 22, RULE_passing_arg = 23, 
		RULE_args = 24, RULE_arg = 25, RULE_expr = 26, RULE_expr_rest = 27, RULE_return_type = 28, 
		RULE_type = 29, RULE_condition = 30, RULE_simple_type = 31, RULE_primitive = 32, 
		RULE_true = 33, RULE_false = 34, RULE_uint = 35, RULE_int = 36, RULE_float = 37, 
		RULE_math = 38, RULE_assign = 39, RULE_num_sign = 40, RULE_comparison = 41, 
		RULE_id = 42, RULE_access_lvl = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "mol", "module", "struct", "m_dec", "s_dec", "include", "method", 
			"field", "stmt", "module_sample_stmt", "dec_stmt", "return_stmt", "output_stmt", 
			"assign_stmt", "access", "if_stmt", "elif_stmt", "else_stmt", "while_stmt", 
			"for_stmt", "for_changes", "passing_args", "passing_arg", "args", "arg", 
			"expr", "expr_rest", "return_type", "type", "condition", "simple_type", 
			"primitive", "true", "false", "uint", "int", "float", "math", "assign", 
			"num_sign", "comparison", "id", "access_lvl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "';'", "'.'", "','", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'='", "'>'", "'<'", "'=='", "'>='", "'<='", "'!='", "'module'", 
			"'struct'", "'includes'", "'begin'", "'end'", "'mut'", "'int'", "'bool'", 
			"'float'", "'double'", "'char'", "'void'", "'return'", "'output'", "'while'", 
			"'for'", "'true'", "'false'", null, "'if'", "'elif'", "'else'", "'public'", 
			"'private'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "MODULE", "STRUCT", "INCLUDES", "BEGIN", 
			"END", "MUT", "INT_TYPE", "BOOL_TYPE", "FLOAT_TYPE", "DOUBLE_TYPE", "CHAR_TYPE", 
			"VOID", "RETURN", "OUTPUT", "WHILE", "FOR", "TRUE_VAL", "FALSE_VAL", 
			"INT_VAL", "IF", "ELIF", "ELSE", "PUBLIC", "PRIVATE", "ID", "DIGITS", 
			"WHITE_SPACE", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "simpleLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public simpleLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public Program programRet;
		public MolContext mol() {
			return getRuleContext(MolContext.class,0);
		}
		public TerminalNode EOF() { return getToken(simpleLangParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			mol();
			setState(89);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MolContext extends ParserRuleContext {
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public List<StructContext> struct() {
			return getRuleContexts(StructContext.class);
		}
		public StructContext struct(int i) {
			return getRuleContext(StructContext.class,i);
		}
		public MolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterMol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitMol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitMol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MolContext mol() throws RecognitionException {
		MolContext _localctx = new MolContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(93);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MODULE:
					{
					setState(91);
					module();
					}
					break;
				case STRUCT:
					{
					setState(92);
					struct();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MODULE || _la==STRUCT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleContext extends ParserRuleContext {
		public M_decContext m_dec() {
			return getRuleContext(M_decContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			m_dec();
			setState(98);
			match(BEGIN);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7697646747648L) != 0)) {
				{
				setState(101);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(99);
					field();
					}
					break;
				case 2:
					{
					setState(100);
					method();
					}
					break;
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StructContext extends ParserRuleContext {
		public S_decContext s_dec() {
			return getRuleContext(S_decContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_struct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			s_dec();
			setState(109);
			match(BEGIN);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7697109876736L) != 0)) {
				{
				{
				setState(110);
				field();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class M_decContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(simpleLangParser.MODULE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IncludeContext include() {
			return getRuleContext(IncludeContext.class,0);
		}
		public M_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_m_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterM_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitM_dec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitM_dec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final M_decContext m_dec() throws RecognitionException {
		M_decContext _localctx = new M_decContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_m_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(MODULE);
			setState(119);
			id();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INCLUDES) {
				{
				setState(120);
				include();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class S_decContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(simpleLangParser.STRUCT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IncludeContext include() {
			return getRuleContext(IncludeContext.class,0);
		}
		public S_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterS_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitS_dec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitS_dec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final S_decContext s_dec() throws RecognitionException {
		S_decContext _localctx = new S_decContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_s_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(STRUCT);
			setState(124);
			id();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INCLUDES) {
				{
				setState(125);
				include();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IncludeContext extends ParserRuleContext {
		public TerminalNode INCLUDES() { return getToken(simpleLangParser.INCLUDES, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitInclude(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitInclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_include);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(INCLUDES);
			setState(129);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public Access_lvlContext access_lvl() {
			return getRuleContext(Access_lvlContext.class,0);
		}
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC || _la==PRIVATE) {
				{
				setState(131);
				access_lvl();
				}
			}

			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(134);
				return_type();
				}
				break;
			}
			setState(137);
			id();
			setState(138);
			match(T__0);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4398574993408L) != 0)) {
				{
				setState(139);
				args();
				}
			}

			setState(142);
			match(T__1);
			setState(143);
			match(BEGIN);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4552120074240L) != 0)) {
				{
				{
				setState(144);
				stmt();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Access_lvlContext access_lvl() {
			return getRuleContext(Access_lvlContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC || _la==PRIVATE) {
				{
				setState(152);
				access_lvl();
				}
			}

			setState(155);
			type();
			setState(156);
			id();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(157);
				assign();
				setState(158);
				expr();
				}
			}

			setState(162);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Output_stmtContext output_stmt() {
			return getRuleContext(Output_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public Module_sample_stmtContext module_sample_stmt() {
			return getRuleContext(Module_sample_stmtContext.class,0);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public Dec_stmtContext dec_stmt() {
			return getRuleContext(Dec_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stmt);
		try {
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				output_stmt();
				setState(165);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				return_stmt();
				setState(168);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				assign_stmt();
				setState(171);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
				if_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(174);
				for_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(175);
				while_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(176);
				module_sample_stmt();
				setState(177);
				match(T__2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(179);
				access();
				setState(180);
				match(T__2);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(182);
				dec_stmt();
				setState(183);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Module_sample_stmtContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode MUT() { return getToken(simpleLangParser.MUT, 0); }
		public Passing_argsContext passing_args() {
			return getRuleContext(Passing_argsContext.class,0);
		}
		public Module_sample_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module_sample_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterModule_sample_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitModule_sample_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitModule_sample_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Module_sample_stmtContext module_sample_stmt() throws RecognitionException {
		Module_sample_stmtContext _localctx = new Module_sample_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_module_sample_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUT) {
				{
				setState(187);
				match(MUT);
				}
			}

			setState(190);
			id();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(191);
				match(T__0);
				setState(192);
				passing_args();
				setState(193);
				match(T__1);
				}
			}

			setState(197);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Dec_stmtContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode MUT() { return getToken(simpleLangParser.MUT, 0); }
		public Dec_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterDec_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitDec_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitDec_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dec_stmtContext dec_stmt() throws RecognitionException {
		Dec_stmtContext _localctx = new Dec_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dec_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(199);
				match(MUT);
				}
				break;
			}
			setState(202);
			type();
			setState(203);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(simpleLangParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitReturn_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(RETURN);
			setState(206);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Output_stmtContext extends ParserRuleContext {
		public TerminalNode OUTPUT() { return getToken(simpleLangParser.OUTPUT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Output_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterOutput_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitOutput_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitOutput_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Output_stmtContext output_stmt() throws RecognitionException {
		Output_stmtContext _localctx = new Output_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_output_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(OUTPUT);
			setState(209);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Assign_stmtContext extends ParserRuleContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Passing_argsContext passing_args() {
			return getRuleContext(Passing_argsContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitAssign_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitAssign_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assign_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(211);
				type();
				}
				break;
			}
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(214);
				id();
				}
				break;
			case 2:
				{
				setState(215);
				access();
				}
				break;
			}
			setState(218);
			assign();
			setState(219);
			expr();
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(220);
				match(T__0);
				setState(221);
				passing_args();
				setState(222);
				match(T__1);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccessContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public Passing_argsContext passing_args() {
			return getRuleContext(Passing_argsContext.class,0);
		}
		public AccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessContext access() throws RecognitionException {
		AccessContext _localctx = new AccessContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_access);
		int _la;
		try {
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				id();
				setState(227);
				match(T__3);
				setState(228);
				access();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				id();
				setState(231);
				match(T__3);
				setState(232);
				id();
				setState(233);
				match(T__0);
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4518834077906L) != 0)) {
					{
					setState(234);
					passing_args();
					}
				}

				setState(237);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(239);
				id();
				setState(240);
				match(T__3);
				setState(241);
				id();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(simpleLangParser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<Elif_stmtContext> elif_stmt() {
			return getRuleContexts(Elif_stmtContext.class);
		}
		public Elif_stmtContext elif_stmt(int i) {
			return getRuleContext(Elif_stmtContext.class,i);
		}
		public Else_stmtContext else_stmt() {
			return getRuleContext(Else_stmtContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_if_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(IF);
			setState(246);
			match(T__0);
			setState(247);
			condition();
			setState(248);
			match(T__1);
			setState(249);
			match(BEGIN);
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4552120074240L) != 0)) {
				{
				{
				setState(250);
				stmt();
				}
				}
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(256);
			match(END);
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(257);
				elif_stmt();
				}
				}
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(263);
				else_stmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Elif_stmtContext extends ParserRuleContext {
		public TerminalNode ELIF() { return getToken(simpleLangParser.ELIF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public Elif_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elif_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterElif_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitElif_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitElif_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elif_stmtContext elif_stmt() throws RecognitionException {
		Elif_stmtContext _localctx = new Elif_stmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_elif_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(ELIF);
			setState(267);
			match(T__0);
			setState(268);
			condition();
			setState(269);
			match(T__1);
			setState(270);
			match(BEGIN);
			setState(271);
			stmt();
			setState(272);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Else_stmtContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(simpleLangParser.ELSE, 0); }
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public Else_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterElse_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitElse_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitElse_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Else_stmtContext else_stmt() throws RecognitionException {
		Else_stmtContext _localctx = new Else_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_else_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(ELSE);
			setState(275);
			match(BEGIN);
			setState(276);
			stmt();
			setState(277);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(simpleLangParser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitWhile_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitWhile_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_while_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(WHILE);
			setState(280);
			match(T__0);
			setState(281);
			condition();
			setState(282);
			match(T__1);
			setState(283);
			match(BEGIN);
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4552120074240L) != 0)) {
				{
				{
				setState(284);
				stmt();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(290);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_stmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(simpleLangParser.FOR, 0); }
		public TerminalNode BEGIN() { return getToken(simpleLangParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(simpleLangParser.END, 0); }
		public Passing_argsContext passing_args() {
			return getRuleContext(Passing_argsContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public For_changesContext for_changes() {
			return getRuleContext(For_changesContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitFor_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_for_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(FOR);
			setState(293);
			match(T__0);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4518834077906L) != 0)) {
				{
				setState(294);
				passing_args();
				}
			}

			setState(297);
			match(T__2);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4518305595602L) != 0)) {
				{
				setState(298);
				condition();
				}
			}

			setState(301);
			match(T__2);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4398574993408L) != 0)) {
				{
				setState(302);
				for_changes();
				}
			}

			setState(305);
			match(T__1);
			setState(306);
			match(BEGIN);
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4552120074240L) != 0)) {
				{
				{
				setState(307);
				stmt();
				}
				}
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(313);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_changesContext extends ParserRuleContext {
		public List<Assign_stmtContext> assign_stmt() {
			return getRuleContexts(Assign_stmtContext.class);
		}
		public Assign_stmtContext assign_stmt(int i) {
			return getRuleContext(Assign_stmtContext.class,i);
		}
		public For_changesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_changes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterFor_changes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitFor_changes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitFor_changes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_changesContext for_changes() throws RecognitionException {
		For_changesContext _localctx = new For_changesContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_for_changes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			assign_stmt();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(316);
				match(T__4);
				setState(317);
				assign_stmt();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Passing_argsContext extends ParserRuleContext {
		public List<Passing_argContext> passing_arg() {
			return getRuleContexts(Passing_argContext.class);
		}
		public Passing_argContext passing_arg(int i) {
			return getRuleContext(Passing_argContext.class,i);
		}
		public Passing_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passing_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterPassing_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitPassing_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitPassing_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Passing_argsContext passing_args() throws RecognitionException {
		Passing_argsContext _localctx = new Passing_argsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_passing_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			passing_arg();
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(324);
				match(T__4);
				setState(325);
				passing_arg();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Passing_argContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Passing_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passing_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterPassing_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitPassing_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitPassing_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Passing_argContext passing_arg() throws RecognitionException {
		Passing_argContext _localctx = new Passing_argContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_passing_arg);
		try {
			setState(333);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				assign_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			arg();
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(336);
				match(T__4);
				setState(337);
				arg();
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode MUT() { return getToken(simpleLangParser.MUT, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUT) {
				{
				setState(343);
				match(MUT);
				}
			}

			setState(346);
			simple_type();
			setState(347);
			id();
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(348);
				assign();
				setState(349);
				primitive();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<Expr_restContext> expr_rest() {
			return getRuleContexts(Expr_restContext.class);
		}
		public Expr_restContext expr_rest(int i) {
			return getRuleContext(Expr_restContext.class,i);
		}
		public List<MathContext> math() {
			return getRuleContexts(MathContext.class);
		}
		public MathContext math(int i) {
			return getRuleContext(MathContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			expr_rest();
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1984L) != 0)) {
				{
				{
				setState(354);
				math();
				setState(355);
				expr_rest();
				}
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_restContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public Expr_restContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_rest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterExpr_rest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitExpr_rest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitExpr_rest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_restContext expr_rest() throws RecognitionException {
		Expr_restContext _localctx = new Expr_restContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expr_rest);
		try {
			setState(369);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(T__0);
				setState(363);
				expr();
				setState(364);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				id();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(367);
				primitive();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(368);
				access();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_typeContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(simpleLangParser.VOID, 0); }
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitReturn_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitReturn_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_return_type);
		try {
			setState(373);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
			case BOOL_TYPE:
			case FLOAT_TYPE:
			case DOUBLE_TYPE:
			case CHAR_TYPE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				simple_type();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public TerminalNode MUT() { return getToken(simpleLangParser.MUT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUT) {
				{
				setState(375);
				match(MUT);
				}
			}

			setState(378);
			simple_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_condition);
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				expr();
				setState(381);
				comparison();
				setState(382);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_typeContext extends ParserRuleContext {
		public TerminalNode INT_TYPE() { return getToken(simpleLangParser.INT_TYPE, 0); }
		public TerminalNode BOOL_TYPE() { return getToken(simpleLangParser.BOOL_TYPE, 0); }
		public TerminalNode FLOAT_TYPE() { return getToken(simpleLangParser.FLOAT_TYPE, 0); }
		public TerminalNode CHAR_TYPE() { return getToken(simpleLangParser.CHAR_TYPE, 0); }
		public TerminalNode DOUBLE_TYPE() { return getToken(simpleLangParser.DOUBLE_TYPE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Simple_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterSimple_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitSimple_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitSimple_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_typeContext simple_type() throws RecognitionException {
		Simple_typeContext _localctx = new Simple_typeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_simple_type);
		try {
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				match(INT_TYPE);
				}
				break;
			case BOOL_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				match(BOOL_TYPE);
				}
				break;
			case FLOAT_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(389);
				match(FLOAT_TYPE);
				}
				break;
			case CHAR_TYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(390);
				match(CHAR_TYPE);
				}
				break;
			case DOUBLE_TYPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(391);
				match(DOUBLE_TYPE);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 6);
				{
				setState(392);
				id();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveContext extends ParserRuleContext {
		public TrueContext true_() {
			return getRuleContext(TrueContext.class,0);
		}
		public FalseContext false_() {
			return getRuleContext(FalseContext.class,0);
		}
		public IntContext int_() {
			return getRuleContext(IntContext.class,0);
		}
		public FloatContext float_() {
			return getRuleContext(FloatContext.class,0);
		}
		public PrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterPrimitive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitPrimitive(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveContext primitive() throws RecognitionException {
		PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_primitive);
		try {
			setState(399);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				true_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				false_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(397);
				int_();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(398);
				float_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends ParserRuleContext {
		public TerminalNode TRUE_VAL() { return getToken(simpleLangParser.TRUE_VAL, 0); }
		public TrueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueContext true_() throws RecognitionException {
		TrueContext _localctx = new TrueContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_true);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			match(TRUE_VAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends ParserRuleContext {
		public TerminalNode FALSE_VAL() { return getToken(simpleLangParser.FALSE_VAL, 0); }
		public FalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_false; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FalseContext false_() throws RecognitionException {
		FalseContext _localctx = new FalseContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_false);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			match(FALSE_VAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UintContext extends ParserRuleContext {
		public TerminalNode INT_VAL() { return getToken(simpleLangParser.INT_VAL, 0); }
		public UintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterUint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitUint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitUint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UintContext uint() throws RecognitionException {
		UintContext _localctx = new UintContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_uint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			match(INT_VAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ParserRuleContext {
		public TerminalNode INT_VAL() { return getToken(simpleLangParser.INT_VAL, 0); }
		public Num_signContext num_sign() {
			return getRuleContext(Num_signContext.class,0);
		}
		public IntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntContext int_() throws RecognitionException {
		IntContext _localctx = new IntContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_int);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5 || _la==T__6) {
				{
				setState(407);
				num_sign();
				}
			}

			setState(410);
			match(INT_VAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ParserRuleContext {
		public IntContext int_() {
			return getRuleContext(IntContext.class,0);
		}
		public UintContext uint() {
			return getRuleContext(UintContext.class,0);
		}
		public FloatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatContext float_() throws RecognitionException {
		FloatContext _localctx = new FloatContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_float);
		int _la;
		try {
			setState(421);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__6:
			case INT_VAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(412);
				int_();
				setState(413);
				match(T__3);
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT_VAL) {
					{
					setState(414);
					uint();
					}
				}

				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(417);
				match(T__3);
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT_VAL) {
					{
					setState(418);
					uint();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MathContext extends ParserRuleContext {
		public MathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterMath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitMath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitMath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathContext math() throws RecognitionException {
		MathContext _localctx = new MathContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_math);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1984L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Num_signContext extends ParserRuleContext {
		public Num_signContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterNum_sign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitNum_sign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitNum_sign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Num_signContext num_sign() throws RecognitionException {
		Num_signContext _localctx = new Num_signContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_num_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 258048L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(simpleLangParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Access_lvlContext extends ParserRuleContext {
		public TerminalNode PRIVATE() { return getToken(simpleLangParser.PRIVATE, 0); }
		public TerminalNode PUBLIC() { return getToken(simpleLangParser.PUBLIC, 0); }
		public Access_lvlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access_lvl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).enterAccess_lvl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof simpleLangListener ) ((simpleLangListener)listener).exitAccess_lvl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof simpleLangVisitor ) return ((simpleLangVisitor<? extends T>)visitor).visitAccess_lvl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Access_lvlContext access_lvl() throws RecognitionException {
		Access_lvlContext _localctx = new Access_lvlContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_access_lvl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			_la = _input.LA(1);
			if ( !(_la==PUBLIC || _la==PRIVATE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001.\u01b4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0004\u0001^\b\u0001\u000b\u0001"+
		"\f\u0001_\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"f\b\u0002\n\u0002\f\u0002i\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003p\b\u0003\n\u0003\f\u0003s\t\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"z\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u007f\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007\u0085\b\u0007"+
		"\u0001\u0007\u0003\u0007\u0088\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u008d\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"\u0092\b\u0007\n\u0007\f\u0007\u0095\t\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0003\b\u009a\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00a1"+
		"\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00ba\b\t\u0001\n\u0003"+
		"\n\u00bd\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c4\b\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0003\u000b\u00c9\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0003\u000e\u00d5\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00d9"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00e1\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00ec\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u00f4\b\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00fc\b\u0010\n"+
		"\u0010\f\u0010\u00ff\t\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0103"+
		"\b\u0010\n\u0010\f\u0010\u0106\t\u0010\u0001\u0010\u0003\u0010\u0109\b"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0005\u0013\u011e\b\u0013\n\u0013\f\u0013\u0121\t\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u0128\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u012c\b\u0014\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u0130\b\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u0135\b\u0014\n\u0014\f\u0014\u0138\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u013f\b\u0015"+
		"\n\u0015\f\u0015\u0142\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005"+
		"\u0016\u0147\b\u0016\n\u0016\f\u0016\u014a\t\u0016\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u014e\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u0153\b\u0018\n\u0018\f\u0018\u0156\t\u0018\u0001\u0019\u0003\u0019\u0159"+
		"\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u0160\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u0166\b\u001a\n\u001a\f\u001a\u0169\t\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u0172\b\u001b\u0001\u001c\u0001\u001c\u0003\u001c\u0176\b\u001c\u0001"+
		"\u001d\u0003\u001d\u0179\b\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0182\b\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u018a\b\u001f\u0001 \u0001 \u0001 \u0001 \u0003 \u0190\b \u0001"+
		"!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0003$\u0199\b$\u0001$\u0001"+
		"$\u0001%\u0001%\u0001%\u0003%\u01a0\b%\u0001%\u0001%\u0003%\u01a4\b%\u0003"+
		"%\u01a6\b%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001)\u0001"+
		"*\u0001*\u0001+\u0001+\u0001+\u0000\u0000,\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8:<>@BDFHJLNPRTV\u0000\u0004\u0001\u0000\u0006\n\u0001\u0000\u0006\u0007"+
		"\u0001\u0000\f\u0011\u0001\u0000()\u01c6\u0000X\u0001\u0000\u0000\u0000"+
		"\u0002]\u0001\u0000\u0000\u0000\u0004a\u0001\u0000\u0000\u0000\u0006l"+
		"\u0001\u0000\u0000\u0000\bv\u0001\u0000\u0000\u0000\n{\u0001\u0000\u0000"+
		"\u0000\f\u0080\u0001\u0000\u0000\u0000\u000e\u0084\u0001\u0000\u0000\u0000"+
		"\u0010\u0099\u0001\u0000\u0000\u0000\u0012\u00b9\u0001\u0000\u0000\u0000"+
		"\u0014\u00bc\u0001\u0000\u0000\u0000\u0016\u00c8\u0001\u0000\u0000\u0000"+
		"\u0018\u00cd\u0001\u0000\u0000\u0000\u001a\u00d0\u0001\u0000\u0000\u0000"+
		"\u001c\u00d4\u0001\u0000\u0000\u0000\u001e\u00f3\u0001\u0000\u0000\u0000"+
		" \u00f5\u0001\u0000\u0000\u0000\"\u010a\u0001\u0000\u0000\u0000$\u0112"+
		"\u0001\u0000\u0000\u0000&\u0117\u0001\u0000\u0000\u0000(\u0124\u0001\u0000"+
		"\u0000\u0000*\u013b\u0001\u0000\u0000\u0000,\u0143\u0001\u0000\u0000\u0000"+
		".\u014d\u0001\u0000\u0000\u00000\u014f\u0001\u0000\u0000\u00002\u0158"+
		"\u0001\u0000\u0000\u00004\u0161\u0001\u0000\u0000\u00006\u0171\u0001\u0000"+
		"\u0000\u00008\u0175\u0001\u0000\u0000\u0000:\u0178\u0001\u0000\u0000\u0000"+
		"<\u0181\u0001\u0000\u0000\u0000>\u0189\u0001\u0000\u0000\u0000@\u018f"+
		"\u0001\u0000\u0000\u0000B\u0191\u0001\u0000\u0000\u0000D\u0193\u0001\u0000"+
		"\u0000\u0000F\u0195\u0001\u0000\u0000\u0000H\u0198\u0001\u0000\u0000\u0000"+
		"J\u01a5\u0001\u0000\u0000\u0000L\u01a7\u0001\u0000\u0000\u0000N\u01a9"+
		"\u0001\u0000\u0000\u0000P\u01ab\u0001\u0000\u0000\u0000R\u01ad\u0001\u0000"+
		"\u0000\u0000T\u01af\u0001\u0000\u0000\u0000V\u01b1\u0001\u0000\u0000\u0000"+
		"XY\u0003\u0002\u0001\u0000YZ\u0005\u0000\u0000\u0001Z\u0001\u0001\u0000"+
		"\u0000\u0000[^\u0003\u0004\u0002\u0000\\^\u0003\u0006\u0003\u0000][\u0001"+
		"\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000"+
		"_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`\u0003\u0001\u0000"+
		"\u0000\u0000ab\u0003\b\u0004\u0000bg\u0005\u0015\u0000\u0000cf\u0003\u0010"+
		"\b\u0000df\u0003\u000e\u0007\u0000ec\u0001\u0000\u0000\u0000ed\u0001\u0000"+
		"\u0000\u0000fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001"+
		"\u0000\u0000\u0000hj\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000"+
		"jk\u0005\u0016\u0000\u0000k\u0005\u0001\u0000\u0000\u0000lm\u0003\n\u0005"+
		"\u0000mq\u0005\u0015\u0000\u0000np\u0003\u0010\b\u0000on\u0001\u0000\u0000"+
		"\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000"+
		"\u0000\u0000rt\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000tu\u0005"+
		"\u0016\u0000\u0000u\u0007\u0001\u0000\u0000\u0000vw\u0005\u0012\u0000"+
		"\u0000wy\u0003T*\u0000xz\u0003\f\u0006\u0000yx\u0001\u0000\u0000\u0000"+
		"yz\u0001\u0000\u0000\u0000z\t\u0001\u0000\u0000\u0000{|\u0005\u0013\u0000"+
		"\u0000|~\u0003T*\u0000}\u007f\u0003\f\u0006\u0000~}\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u000b\u0001\u0000\u0000\u0000"+
		"\u0080\u0081\u0005\u0014\u0000\u0000\u0081\u0082\u0003T*\u0000\u0082\r"+
		"\u0001\u0000\u0000\u0000\u0083\u0085\u0003V+\u0000\u0084\u0083\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0087\u0001\u0000"+
		"\u0000\u0000\u0086\u0088\u00038\u001c\u0000\u0087\u0086\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0003T*\u0000\u008a\u008c\u0005\u0001\u0000\u0000\u008b"+
		"\u008d\u00030\u0018\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005\u0002\u0000\u0000\u008f\u0093\u0005\u0015\u0000\u0000\u0090\u0092"+
		"\u0003\u0012\t\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095\u0001"+
		"\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001"+
		"\u0000\u0000\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0005\u0016\u0000\u0000\u0097\u000f\u0001"+
		"\u0000\u0000\u0000\u0098\u009a\u0003V+\u0000\u0099\u0098\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0003:\u001d\u0000\u009c\u00a0\u0003T*\u0000\u009d"+
		"\u009e\u0003N\'\u0000\u009e\u009f\u00034\u001a\u0000\u009f\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a0\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"\u0003\u0000\u0000\u00a3\u0011\u0001\u0000\u0000\u0000\u00a4\u00a5\u0003"+
		"\u001a\r\u0000\u00a5\u00a6\u0005\u0003\u0000\u0000\u00a6\u00ba\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0003\u0018\f\u0000\u00a8\u00a9\u0005\u0003\u0000"+
		"\u0000\u00a9\u00ba\u0001\u0000\u0000\u0000\u00aa\u00ab\u0003\u001c\u000e"+
		"\u0000\u00ab\u00ac\u0005\u0003\u0000\u0000\u00ac\u00ba\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ba\u0003 \u0010\u0000\u00ae\u00ba\u0003(\u0014\u0000\u00af"+
		"\u00ba\u0003&\u0013\u0000\u00b0\u00b1\u0003\u0014\n\u0000\u00b1\u00b2"+
		"\u0005\u0003\u0000\u0000\u00b2\u00ba\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0003\u001e\u000f\u0000\u00b4\u00b5\u0005\u0003\u0000\u0000\u00b5\u00ba"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b7\u0003\u0016\u000b\u0000\u00b7\u00b8"+
		"\u0005\u0003\u0000\u0000\u00b8\u00ba\u0001\u0000\u0000\u0000\u00b9\u00a4"+
		"\u0001\u0000\u0000\u0000\u00b9\u00a7\u0001\u0000\u0000\u0000\u00b9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00b9\u00ad\u0001\u0000\u0000\u0000\u00b9\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b9\u00af\u0001\u0000\u0000\u0000\u00b9\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b9\u00b3\u0001\u0000\u0000\u0000\u00b9\u00b6"+
		"\u0001\u0000\u0000\u0000\u00ba\u0013\u0001\u0000\u0000\u0000\u00bb\u00bd"+
		"\u0005\u0017\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00c3"+
		"\u0003T*\u0000\u00bf\u00c0\u0005\u0001\u0000\u0000\u00c0\u00c1\u0003,"+
		"\u0016\u0000\u00c1\u00c2\u0005\u0002\u0000\u0000\u00c2\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c3\u00bf\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6\u0003T*\u0000"+
		"\u00c6\u0015\u0001\u0000\u0000\u0000\u00c7\u00c9\u0005\u0017\u0000\u0000"+
		"\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000"+
		"\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cb\u0003:\u001d\u0000\u00cb"+
		"\u00cc\u0003T*\u0000\u00cc\u0017\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005"+
		"\u001e\u0000\u0000\u00ce\u00cf\u00034\u001a\u0000\u00cf\u0019\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0005\u001f\u0000\u0000\u00d1\u00d2\u00034\u001a"+
		"\u0000\u00d2\u001b\u0001\u0000\u0000\u0000\u00d3\u00d5\u0003:\u001d\u0000"+
		"\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d8\u0001\u0000\u0000\u0000\u00d6\u00d9\u0003T*\u0000\u00d7\u00d9"+
		"\u0003\u001e\u000f\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\u0003N\'\u0000\u00db\u00e0\u00034\u001a\u0000\u00dc\u00dd\u0005\u0001"+
		"\u0000\u0000\u00dd\u00de\u0003,\u0016\u0000\u00de\u00df\u0005\u0002\u0000"+
		"\u0000\u00df\u00e1\u0001\u0000\u0000\u0000\u00e0\u00dc\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u001d\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0003T*\u0000\u00e3\u00e4\u0005\u0004\u0000\u0000\u00e4"+
		"\u00e5\u0003\u001e\u000f\u0000\u00e5\u00f4\u0001\u0000\u0000\u0000\u00e6"+
		"\u00e7\u0003T*\u0000\u00e7\u00e8\u0005\u0004\u0000\u0000\u00e8\u00e9\u0003"+
		"T*\u0000\u00e9\u00eb\u0005\u0001\u0000\u0000\u00ea\u00ec\u0003,\u0016"+
		"\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u0002\u0000"+
		"\u0000\u00ee\u00f4\u0001\u0000\u0000\u0000\u00ef\u00f0\u0003T*\u0000\u00f0"+
		"\u00f1\u0005\u0004\u0000\u0000\u00f1\u00f2\u0003T*\u0000\u00f2\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f3\u00e2\u0001\u0000\u0000\u0000\u00f3\u00e6\u0001"+
		"\u0000\u0000\u0000\u00f3\u00ef\u0001\u0000\u0000\u0000\u00f4\u001f\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0005%\u0000\u0000\u00f6\u00f7\u0005\u0001"+
		"\u0000\u0000\u00f7\u00f8\u0003<\u001e\u0000\u00f8\u00f9\u0005\u0002\u0000"+
		"\u0000\u00f9\u00fd\u0005\u0015\u0000\u0000\u00fa\u00fc\u0003\u0012\t\u0000"+
		"\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000"+
		"\u00fe\u0100\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000"+
		"\u0100\u0104\u0005\u0016\u0000\u0000\u0101\u0103\u0003\"\u0011\u0000\u0102"+
		"\u0101\u0001\u0000\u0000\u0000\u0103\u0106\u0001\u0000\u0000\u0000\u0104"+
		"\u0102\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105"+
		"\u0108\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107"+
		"\u0109\u0003$\u0012\u0000\u0108\u0107\u0001\u0000\u0000\u0000\u0108\u0109"+
		"\u0001\u0000\u0000\u0000\u0109!\u0001\u0000\u0000\u0000\u010a\u010b\u0005"+
		"&\u0000\u0000\u010b\u010c\u0005\u0001\u0000\u0000\u010c\u010d\u0003<\u001e"+
		"\u0000\u010d\u010e\u0005\u0002\u0000\u0000\u010e\u010f\u0005\u0015\u0000"+
		"\u0000\u010f\u0110\u0003\u0012\t\u0000\u0110\u0111\u0005\u0016\u0000\u0000"+
		"\u0111#\u0001\u0000\u0000\u0000\u0112\u0113\u0005\'\u0000\u0000\u0113"+
		"\u0114\u0005\u0015\u0000\u0000\u0114\u0115\u0003\u0012\t\u0000\u0115\u0116"+
		"\u0005\u0016\u0000\u0000\u0116%\u0001\u0000\u0000\u0000\u0117\u0118\u0005"+
		" \u0000\u0000\u0118\u0119\u0005\u0001\u0000\u0000\u0119\u011a\u0003<\u001e"+
		"\u0000\u011a\u011b\u0005\u0002\u0000\u0000\u011b\u011f\u0005\u0015\u0000"+
		"\u0000\u011c\u011e\u0003\u0012\t\u0000\u011d\u011c\u0001\u0000\u0000\u0000"+
		"\u011e\u0121\u0001\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0001\u0000\u0000\u0000\u0120\u0122\u0001\u0000\u0000\u0000"+
		"\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0123\u0005\u0016\u0000\u0000"+
		"\u0123\'\u0001\u0000\u0000\u0000\u0124\u0125\u0005!\u0000\u0000\u0125"+
		"\u0127\u0005\u0001\u0000\u0000\u0126\u0128\u0003,\u0016\u0000\u0127\u0126"+
		"\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129"+
		"\u0001\u0000\u0000\u0000\u0129\u012b\u0005\u0003\u0000\u0000\u012a\u012c"+
		"\u0003<\u001e\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012b\u012c\u0001"+
		"\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012f\u0005"+
		"\u0003\u0000\u0000\u012e\u0130\u0003*\u0015\u0000\u012f\u012e\u0001\u0000"+
		"\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000"+
		"\u0000\u0000\u0131\u0132\u0005\u0002\u0000\u0000\u0132\u0136\u0005\u0015"+
		"\u0000\u0000\u0133\u0135\u0003\u0012\t\u0000\u0134\u0133\u0001\u0000\u0000"+
		"\u0000\u0135\u0138\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000\u0000"+
		"\u0000\u0136\u0137\u0001\u0000\u0000\u0000\u0137\u0139\u0001\u0000\u0000"+
		"\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0139\u013a\u0005\u0016\u0000"+
		"\u0000\u013a)\u0001\u0000\u0000\u0000\u013b\u0140\u0003\u001c\u000e\u0000"+
		"\u013c\u013d\u0005\u0005\u0000\u0000\u013d\u013f\u0003\u001c\u000e\u0000"+
		"\u013e\u013c\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000\u0000\u0000"+
		"\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000"+
		"\u0141+\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143"+
		"\u0148\u0003.\u0017\u0000\u0144\u0145\u0005\u0005\u0000\u0000\u0145\u0147"+
		"\u0003.\u0017\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0147\u014a\u0001"+
		"\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0148\u0149\u0001"+
		"\u0000\u0000\u0000\u0149-\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000"+
		"\u0000\u0000\u014b\u014e\u00034\u001a\u0000\u014c\u014e\u0003\u001c\u000e"+
		"\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014d\u014c\u0001\u0000\u0000"+
		"\u0000\u014e/\u0001\u0000\u0000\u0000\u014f\u0154\u00032\u0019\u0000\u0150"+
		"\u0151\u0005\u0005\u0000\u0000\u0151\u0153\u00032\u0019\u0000\u0152\u0150"+
		"\u0001\u0000\u0000\u0000\u0153\u0156\u0001\u0000\u0000\u0000\u0154\u0152"+
		"\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u01551\u0001"+
		"\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000\u0000\u0157\u0159\u0005"+
		"\u0017\u0000\u0000\u0158\u0157\u0001\u0000\u0000\u0000\u0158\u0159\u0001"+
		"\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015b\u0003"+
		">\u001f\u0000\u015b\u015f\u0003T*\u0000\u015c\u015d\u0003N\'\u0000\u015d"+
		"\u015e\u0003@ \u0000\u015e\u0160\u0001\u0000\u0000\u0000\u015f\u015c\u0001"+
		"\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u01603\u0001\u0000"+
		"\u0000\u0000\u0161\u0167\u00036\u001b\u0000\u0162\u0163\u0003L&\u0000"+
		"\u0163\u0164\u00036\u001b\u0000\u0164\u0166\u0001\u0000\u0000\u0000\u0165"+
		"\u0162\u0001\u0000\u0000\u0000\u0166\u0169\u0001\u0000\u0000\u0000\u0167"+
		"\u0165\u0001\u0000\u0000\u0000\u0167\u0168\u0001\u0000\u0000\u0000\u0168"+
		"5\u0001\u0000\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000\u016a\u016b"+
		"\u0005\u0001\u0000\u0000\u016b\u016c\u00034\u001a\u0000\u016c\u016d\u0005"+
		"\u0002\u0000\u0000\u016d\u0172\u0001\u0000\u0000\u0000\u016e\u0172\u0003"+
		"T*\u0000\u016f\u0172\u0003@ \u0000\u0170\u0172\u0003\u001e\u000f\u0000"+
		"\u0171\u016a\u0001\u0000\u0000\u0000\u0171\u016e\u0001\u0000\u0000\u0000"+
		"\u0171\u016f\u0001\u0000\u0000\u0000\u0171\u0170\u0001\u0000\u0000\u0000"+
		"\u01727\u0001\u0000\u0000\u0000\u0173\u0176\u0003>\u001f\u0000\u0174\u0176"+
		"\u0005\u001d\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0175\u0174"+
		"\u0001\u0000\u0000\u0000\u01769\u0001\u0000\u0000\u0000\u0177\u0179\u0005"+
		"\u0017\u0000\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u017b\u0003"+
		">\u001f\u0000\u017b;\u0001\u0000\u0000\u0000\u017c\u017d\u00034\u001a"+
		"\u0000\u017d\u017e\u0003R)\u0000\u017e\u017f\u00034\u001a\u0000\u017f"+
		"\u0182\u0001\u0000\u0000\u0000\u0180\u0182\u00034\u001a\u0000\u0181\u017c"+
		"\u0001\u0000\u0000\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0182=\u0001"+
		"\u0000\u0000\u0000\u0183\u018a\u0005\u0018\u0000\u0000\u0184\u018a\u0005"+
		"\u0019\u0000\u0000\u0185\u018a\u0005\u001a\u0000\u0000\u0186\u018a\u0005"+
		"\u001c\u0000\u0000\u0187\u018a\u0005\u001b\u0000\u0000\u0188\u018a\u0003"+
		"T*\u0000\u0189\u0183\u0001\u0000\u0000\u0000\u0189\u0184\u0001\u0000\u0000"+
		"\u0000\u0189\u0185\u0001\u0000\u0000\u0000\u0189\u0186\u0001\u0000\u0000"+
		"\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u0188\u0001\u0000\u0000"+
		"\u0000\u018a?\u0001\u0000\u0000\u0000\u018b\u0190\u0003B!\u0000\u018c"+
		"\u0190\u0003D\"\u0000\u018d\u0190\u0003H$\u0000\u018e\u0190\u0003J%\u0000"+
		"\u018f\u018b\u0001\u0000\u0000\u0000\u018f\u018c\u0001\u0000\u0000\u0000"+
		"\u018f\u018d\u0001\u0000\u0000\u0000\u018f\u018e\u0001\u0000\u0000\u0000"+
		"\u0190A\u0001\u0000\u0000\u0000\u0191\u0192\u0005\"\u0000\u0000\u0192"+
		"C\u0001\u0000\u0000\u0000\u0193\u0194\u0005#\u0000\u0000\u0194E\u0001"+
		"\u0000\u0000\u0000\u0195\u0196\u0005$\u0000\u0000\u0196G\u0001\u0000\u0000"+
		"\u0000\u0197\u0199\u0003P(\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198"+
		"\u0199\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a"+
		"\u019b\u0005$\u0000\u0000\u019bI\u0001\u0000\u0000\u0000\u019c\u019d\u0003"+
		"H$\u0000\u019d\u019f\u0005\u0004\u0000\u0000\u019e\u01a0\u0003F#\u0000"+
		"\u019f\u019e\u0001\u0000\u0000\u0000\u019f\u01a0\u0001\u0000\u0000\u0000"+
		"\u01a0\u01a6\u0001\u0000\u0000\u0000\u01a1\u01a3\u0005\u0004\u0000\u0000"+
		"\u01a2\u01a4\u0003F#\u0000\u01a3\u01a2\u0001\u0000\u0000\u0000\u01a3\u01a4"+
		"\u0001\u0000\u0000\u0000\u01a4\u01a6\u0001\u0000\u0000\u0000\u01a5\u019c"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a1\u0001\u0000\u0000\u0000\u01a6K\u0001"+
		"\u0000\u0000\u0000\u01a7\u01a8\u0007\u0000\u0000\u0000\u01a8M\u0001\u0000"+
		"\u0000\u0000\u01a9\u01aa\u0005\u000b\u0000\u0000\u01aaO\u0001\u0000\u0000"+
		"\u0000\u01ab\u01ac\u0007\u0001\u0000\u0000\u01acQ\u0001\u0000\u0000\u0000"+
		"\u01ad\u01ae\u0007\u0002\u0000\u0000\u01aeS\u0001\u0000\u0000\u0000\u01af"+
		"\u01b0\u0005*\u0000\u0000\u01b0U\u0001\u0000\u0000\u0000\u01b1\u01b2\u0007"+
		"\u0003\u0000\u0000\u01b2W\u0001\u0000\u0000\u0000/]_egqy~\u0084\u0087"+
		"\u008c\u0093\u0099\u00a0\u00b9\u00bc\u00c3\u00c8\u00d4\u00d8\u00e0\u00eb"+
		"\u00f3\u00fd\u0104\u0108\u011f\u0127\u012b\u012f\u0136\u0140\u0148\u014d"+
		"\u0154\u0158\u015f\u0167\u0171\u0175\u0178\u0181\u0189\u018f\u0198\u019f"+
		"\u01a3\u01a5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}