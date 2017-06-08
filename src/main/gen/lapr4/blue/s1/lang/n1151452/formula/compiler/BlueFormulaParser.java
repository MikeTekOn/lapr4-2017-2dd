// Generated from /home/k4rd050/Documents/LAPR4/lapr4-2017-2dd/src/main/antlr4/lapr4/blue/s1/lang/n1151452/formula/compiler/BlueFormula.g4 by ANTLR 4.7
package lapr4.blue.s1.lang.n1151452.formula.compiler;

    package lapr4.blue.s1.lang.n1151452.formula.compiler;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BlueFormulaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FOR=1, FUNCTION=2, CELL_REF=3, CELL=4, VARIABLE_NAME=5, STRING=6, QUOT=7, 
		NUMBER=8, EQ=9, NEQ=10, LTEQ=11, GTEQ=12, GT=13, LT=14, AMP=15, PLUS=16, 
		MINUS=17, MULTI=18, DIV=19, POWER=20, PERCENT=21, COLON=22, COMMA=23, 
		SEMI=24, LPAR=25, RPAR=26, L_CURLY_BRACKET=27, R_CURLY_BRACKET=28, ASSIGN=29, 
		WS=30;
	public static final int
		RULE_expression = 0, RULE_comparison = 1, RULE_concatenation = 2, RULE_atom = 3, 
		RULE_for_loop = 4, RULE_block = 5, RULE_assignment = 6, RULE_function_call = 7, 
		RULE_reference = 8, RULE_literal = 9;
	public static final String[] ruleNames = {
		"expression", "comparison", "concatenation", "atom", "for_loop", "block", 
		"assignment", "function_call", "reference", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'\"'", null, "'='", "'<>'", 
		"'<='", "'>='", "'>'", "'<'", "'&'", "'+'", "'-'", "'*'", "'/'", "'^'", 
		"'%'", "':'", "','", "';'", "'('", "')'", "'{'", "'}'", "':='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FOR", "FUNCTION", "CELL_REF", "CELL", "VARIABLE_NAME", "STRING", 
		"QUOT", "NUMBER", "EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", "PLUS", 
		"MINUS", "MULTI", "DIV", "POWER", "PERCENT", "COLON", "COMMA", "SEMI", 
		"LPAR", "RPAR", "L_CURLY_BRACKET", "R_CURLY_BRACKET", "ASSIGN", "WS"
	};
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
	public String getGrammarFileName() { return "BlueFormula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BlueFormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(BlueFormulaParser.EQ, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BlueFormulaParser.EOF, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(EQ);
			setState(21);
			comparison();
			setState(22);
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

	public static class ComparisonContext extends ParserRuleContext {
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode EQ() { return getToken(BlueFormulaParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(BlueFormulaParser.NEQ, 0); }
		public TerminalNode LTEQ() { return getToken(BlueFormulaParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(BlueFormulaParser.GTEQ, 0); }
		public TerminalNode GT() { return getToken(BlueFormulaParser.GT, 0); }
		public TerminalNode LT() { return getToken(BlueFormulaParser.LT, 0); }
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_comparison);
		int _la;
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
			case CELL_REF:
			case CELL:
			case VARIABLE_NAME:
			case STRING:
			case NUMBER:
			case MINUS:
			case LPAR:
			case L_CURLY_BRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				concatenation(0);
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) {
					{
					setState(25);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LTEQ) | (1L << GTEQ) | (1L << GT) | (1L << LT))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(26);
					concatenation(0);
					}
				}

				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				for_loop();
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

	public static class ConcatenationContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(BlueFormulaParser.MINUS, 0); }
		public List<ConcatenationContext> concatenation() {
			return getRuleContexts(ConcatenationContext.class);
		}
		public ConcatenationContext concatenation(int i) {
			return getRuleContext(ConcatenationContext.class,i);
		}
		public TerminalNode POWER() { return getToken(BlueFormulaParser.POWER, 0); }
		public TerminalNode MULTI() { return getToken(BlueFormulaParser.MULTI, 0); }
		public TerminalNode DIV() { return getToken(BlueFormulaParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(BlueFormulaParser.PLUS, 0); }
		public TerminalNode AMP() { return getToken(BlueFormulaParser.AMP, 0); }
		public TerminalNode PERCENT() { return getToken(BlueFormulaParser.PERCENT, 0); }
		public ConcatenationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concatenation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterConcatenation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitConcatenation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitConcatenation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConcatenationContext concatenation() throws RecognitionException {
		return concatenation(0);
	}

	private ConcatenationContext concatenation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConcatenationContext _localctx = new ConcatenationContext(_ctx, _parentState);
		ConcatenationContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_concatenation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(33);
				match(MINUS);
				}
			}

			setState(36);
			atom();
			}
			_ctx.stop = _input.LT(-1);
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(52);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(38);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(39);
						match(POWER);
						setState(40);
						concatenation(4);
						}
						break;
					case 2:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(41);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(42);
						_la = _input.LA(1);
						if ( !(_la==MULTI || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(43);
						concatenation(4);
						}
						break;
					case 3:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(44);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(45);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(46);
						concatenation(3);
						}
						break;
					case 4:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(47);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(48);
						match(AMP);
						setState(49);
						concatenation(2);
						}
						break;
					case 5:
						{
						_localctx = new ConcatenationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_concatenation);
						setState(50);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(51);
						match(PERCENT);
						}
						break;
					}
					} 
				}
				setState(56);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(BlueFormulaParser.LPAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(BlueFormulaParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode VARIABLE_NAME() { return getToken(BlueFormulaParser.VARIABLE_NAME, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				reference();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				match(LPAR);
				setState(61);
				comparison();
				setState(62);
				match(RPAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(64);
				block();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(65);
				assignment();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(66);
				match(VARIABLE_NAME);
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

	public static class For_loopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(BlueFormulaParser.FOR, 0); }
		public TerminalNode L_CURLY_BRACKET() { return getToken(BlueFormulaParser.L_CURLY_BRACKET, 0); }
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(BlueFormulaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(BlueFormulaParser.SEMI, i);
		}
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode R_CURLY_BRACKET() { return getToken(BlueFormulaParser.R_CURLY_BRACKET, 0); }
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitFor_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitFor_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_for_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(FOR);
			setState(70);
			match(L_CURLY_BRACKET);
			setState(71);
			assignment();
			setState(72);
			match(SEMI);
			setState(73);
			comparison();
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				match(SEMI);
				setState(75);
				comparison();
				}
				}
				setState(78); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SEMI );
			setState(80);
			match(R_CURLY_BRACKET);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode L_CURLY_BRACKET() { return getToken(BlueFormulaParser.L_CURLY_BRACKET, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public TerminalNode R_CURLY_BRACKET() { return getToken(BlueFormulaParser.R_CURLY_BRACKET, 0); }
		public List<TerminalNode> SEMI() { return getTokens(BlueFormulaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(BlueFormulaParser.SEMI, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(L_CURLY_BRACKET);
			setState(83);
			comparison();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(84);
				match(SEMI);
				setState(85);
				comparison();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(R_CURLY_BRACKET);
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

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(BlueFormulaParser.LPAR, 0); }
		public TerminalNode ASSIGN() { return getToken(BlueFormulaParser.ASSIGN, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(BlueFormulaParser.RPAR, 0); }
		public ReferenceContext reference() {
			return getRuleContext(ReferenceContext.class,0);
		}
		public TerminalNode VARIABLE_NAME() { return getToken(BlueFormulaParser.VARIABLE_NAME, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(LPAR);
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CELL_REF:
			case CELL:
				{
				setState(94);
				reference();
				}
				break;
			case VARIABLE_NAME:
				{
				{
				setState(95);
				match(VARIABLE_NAME);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(98);
			match(ASSIGN);
			setState(99);
			comparison();
			setState(100);
			match(RPAR);
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

	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(BlueFormulaParser.FUNCTION, 0); }
		public TerminalNode LPAR() { return getToken(BlueFormulaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(BlueFormulaParser.RPAR, 0); }
		public List<ComparisonContext> comparison() {
			return getRuleContexts(ComparisonContext.class);
		}
		public ComparisonContext comparison(int i) {
			return getRuleContext(ComparisonContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(BlueFormulaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(BlueFormulaParser.SEMI, i);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(FUNCTION);
			setState(103);
			match(LPAR);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FOR) | (1L << FUNCTION) | (1L << CELL_REF) | (1L << CELL) | (1L << VARIABLE_NAME) | (1L << STRING) | (1L << NUMBER) | (1L << MINUS) | (1L << LPAR) | (1L << L_CURLY_BRACKET))) != 0)) {
				{
				setState(104);
				comparison();
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI) {
					{
					{
					setState(105);
					match(SEMI);
					setState(106);
					comparison();
					}
					}
					setState(111);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(114);
			match(RPAR);
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

	public static class ReferenceContext extends ParserRuleContext {
		public List<TerminalNode> CELL_REF() { return getTokens(BlueFormulaParser.CELL_REF); }
		public TerminalNode CELL_REF(int i) {
			return getToken(BlueFormulaParser.CELL_REF, i);
		}
		public TerminalNode COLON() { return getToken(BlueFormulaParser.COLON, 0); }
		public TerminalNode CELL() { return getToken(BlueFormulaParser.CELL, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_reference);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CELL_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				match(CELL_REF);
				setState(119);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					{
					setState(117);
					match(COLON);
					}
					setState(118);
					match(CELL_REF);
					}
					break;
				}
				}
				break;
			case CELL:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				match(CELL);
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(BlueFormulaParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(BlueFormulaParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BlueFormulaListener ) ((BlueFormulaListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BlueFormulaVisitor ) return ((BlueFormulaVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==NUMBER) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return concatenation_sempred((ConcatenationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean concatenation_sempred(ConcatenationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		case 4:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u0081\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\3\3\3\3\3\5\3\36\n\3\3\3\5\3!\n\3\3\4\3\4\5\4%\n"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"\67\n\4\f\4\16\4:\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5F\n"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6O\n\6\r\6\16\6P\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\7\7Y\n\7\f\7\16\7\\\13\7\3\7\3\7\3\b\3\b\3\b\5\bc\n\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\7\tn\n\t\f\t\16\tq\13\t\5\ts\n\t\3\t\3\t\3\n"+
		"\3\n\3\n\5\nz\n\n\3\n\5\n}\n\n\3\13\3\13\3\13\2\3\6\f\2\4\6\b\n\f\16\20"+
		"\22\24\2\6\3\2\13\20\3\2\24\25\3\2\22\23\4\2\b\b\n\n\2\u008b\2\26\3\2"+
		"\2\2\4 \3\2\2\2\6\"\3\2\2\2\bE\3\2\2\2\nG\3\2\2\2\fT\3\2\2\2\16_\3\2\2"+
		"\2\20h\3\2\2\2\22|\3\2\2\2\24~\3\2\2\2\26\27\7\13\2\2\27\30\5\4\3\2\30"+
		"\31\7\2\2\3\31\3\3\2\2\2\32\35\5\6\4\2\33\34\t\2\2\2\34\36\5\6\4\2\35"+
		"\33\3\2\2\2\35\36\3\2\2\2\36!\3\2\2\2\37!\5\n\6\2 \32\3\2\2\2 \37\3\2"+
		"\2\2!\5\3\2\2\2\"$\b\4\1\2#%\7\23\2\2$#\3\2\2\2$%\3\2\2\2%&\3\2\2\2&\'"+
		"\5\b\5\2\'8\3\2\2\2()\f\6\2\2)*\7\26\2\2*\67\5\6\4\6+,\f\5\2\2,-\t\3\2"+
		"\2-\67\5\6\4\6./\f\4\2\2/\60\t\4\2\2\60\67\5\6\4\5\61\62\f\3\2\2\62\63"+
		"\7\21\2\2\63\67\5\6\4\4\64\65\f\7\2\2\65\67\7\27\2\2\66(\3\2\2\2\66+\3"+
		"\2\2\2\66.\3\2\2\2\66\61\3\2\2\2\66\64\3\2\2\2\67:\3\2\2\28\66\3\2\2\2"+
		"89\3\2\2\29\7\3\2\2\2:8\3\2\2\2;F\5\20\t\2<F\5\22\n\2=F\5\24\13\2>?\7"+
		"\33\2\2?@\5\4\3\2@A\7\34\2\2AF\3\2\2\2BF\5\f\7\2CF\5\16\b\2DF\7\7\2\2"+
		"E;\3\2\2\2E<\3\2\2\2E=\3\2\2\2E>\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2"+
		"F\t\3\2\2\2GH\7\3\2\2HI\7\35\2\2IJ\5\16\b\2JK\7\32\2\2KN\5\4\3\2LM\7\32"+
		"\2\2MO\5\4\3\2NL\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\36"+
		"\2\2S\13\3\2\2\2TU\7\35\2\2UZ\5\4\3\2VW\7\32\2\2WY\5\4\3\2XV\3\2\2\2Y"+
		"\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7\36\2\2^\r\3\2"+
		"\2\2_b\7\33\2\2`c\5\22\n\2ac\7\7\2\2b`\3\2\2\2ba\3\2\2\2cd\3\2\2\2de\7"+
		"\37\2\2ef\5\4\3\2fg\7\34\2\2g\17\3\2\2\2hi\7\4\2\2ir\7\33\2\2jo\5\4\3"+
		"\2kl\7\32\2\2ln\5\4\3\2mk\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2ps\3\2"+
		"\2\2qo\3\2\2\2rj\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\7\34\2\2u\21\3\2\2\2vy"+
		"\7\5\2\2wx\7\30\2\2xz\7\5\2\2yw\3\2\2\2yz\3\2\2\2z}\3\2\2\2{}\7\6\2\2"+
		"|v\3\2\2\2|{\3\2\2\2}\23\3\2\2\2~\177\t\5\2\2\177\25\3\2\2\2\17\35 $\66"+
		"8EPZbory|";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}