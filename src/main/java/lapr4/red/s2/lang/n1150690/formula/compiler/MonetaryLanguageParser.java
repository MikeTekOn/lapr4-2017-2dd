// Generated from MonetaryLanguage.g4 by ANTLR 4.5.3
package lapr4.red.s2.lang.n1150690.formula.compiler;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MonetaryLanguageParser extends Parser {

    static {
        RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int START_CHAR = 1, DOLLAR = 2, EURO = 3, POUND = 4, NUMBER_FOR_COIN = 5, NUMBER = 6,
            PLUS = 7, MINUS = 8, MULTI = 9, DIV = 10, COMMA = 11, LPAR = 12, RPAR = 13, L_CURLY_BRACKET = 14,
            R_CURLY_BRACKET = 15, WS = 16;
    public static final int RULE_formula = 0, RULE_currency = 1, RULE_expression = 2;
    public static final String[] ruleNames = {
        "formula", "currency", "expression"
    };

    private static final String[] _LITERAL_NAMES = {
        null, "'#'", null, null, null, null, null, "'+'", "'-'", "'*'", "'/'",
        "','", "'('", "')'", "'{'", "'}'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
        null, "START_CHAR", "DOLLAR", "EURO", "POUND", "NUMBER_FOR_COIN", "NUMBER",
        "PLUS", "MINUS", "MULTI", "DIV", "COMMA", "LPAR", "RPAR", "L_CURLY_BRACKET",
        "R_CURLY_BRACKET", "WS"
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
    public String getGrammarFileName() {
        return "MonetaryLanguage.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public MonetaryLanguageParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class FormulaContext extends ParserRuleContext {

        public TerminalNode START_CHAR() {
            return getToken(MonetaryLanguageParser.START_CHAR, 0);
        }

        public CurrencyContext currency() {
            return getRuleContext(CurrencyContext.class, 0);
        }

        public TerminalNode L_CURLY_BRACKET() {
            return getToken(MonetaryLanguageParser.L_CURLY_BRACKET, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TerminalNode R_CURLY_BRACKET() {
            return getToken(MonetaryLanguageParser.R_CURLY_BRACKET, 0);
        }

        public TerminalNode EOF() {
            return getToken(MonetaryLanguageParser.EOF, 0);
        }

        public FormulaContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formula;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).enterFormula(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).exitFormula(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MonetaryLanguageVisitor) {
                return ((MonetaryLanguageVisitor<? extends T>) visitor).visitFormula(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final FormulaContext formula() throws RecognitionException {
        FormulaContext _localctx = new FormulaContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_formula);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(6);
                match(START_CHAR);
                setState(7);
                currency();
                setState(8);
                match(L_CURLY_BRACKET);
                setState(9);
                expression(0);
                setState(10);
                match(R_CURLY_BRACKET);
                setState(11);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CurrencyContext extends ParserRuleContext {

        public TerminalNode DOLLAR() {
            return getToken(MonetaryLanguageParser.DOLLAR, 0);
        }

        public TerminalNode EURO() {
            return getToken(MonetaryLanguageParser.EURO, 0);
        }

        public TerminalNode POUND() {
            return getToken(MonetaryLanguageParser.POUND, 0);
        }

        public CurrencyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_currency;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).enterCurrency(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).exitCurrency(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MonetaryLanguageVisitor) {
                return ((MonetaryLanguageVisitor<? extends T>) visitor).visitCurrency(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final CurrencyContext currency() throws RecognitionException {
        CurrencyContext _localctx = new CurrencyContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_currency);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(13);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOLLAR) | (1L << EURO) | (1L << POUND))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExpressionContext extends ParserRuleContext {

        public ExpressionContext left;
        public Token value;
        public Token op;
        public ExpressionContext right;

        public TerminalNode LPAR() {
            return getToken(MonetaryLanguageParser.LPAR, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public TerminalNode RPAR() {
            return getToken(MonetaryLanguageParser.RPAR, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(MonetaryLanguageParser.NUMBER, 0);
        }

        public TerminalNode NUMBER_FOR_COIN() {
            return getToken(MonetaryLanguageParser.NUMBER_FOR_COIN, 0);
        }

        public TerminalNode PLUS() {
            return getToken(MonetaryLanguageParser.PLUS, 0);
        }

        public TerminalNode MINUS() {
            return getToken(MonetaryLanguageParser.MINUS, 0);
        }

        public TerminalNode MULTI() {
            return getToken(MonetaryLanguageParser.MULTI, 0);
        }

        public TerminalNode DIV() {
            return getToken(MonetaryLanguageParser.DIV, 0);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).enterExpression(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof MonetaryLanguageListener) {
                ((MonetaryLanguageListener) listener).exitExpression(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof MonetaryLanguageVisitor) {
                return ((MonetaryLanguageVisitor<? extends T>) visitor).visitExpression(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ExpressionContext expression() throws RecognitionException {
        return expression(0);
    }

    private ExpressionContext expression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
        ExpressionContext _prevctx = _localctx;
        int _startState = 4;
        enterRecursionRule(_localctx, 4, RULE_expression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(22);
                switch (_input.LA(1)) {
                    case LPAR: {
                        setState(16);
                        match(LPAR);
                        setState(17);
                        expression(0);
                        setState(18);
                        match(RPAR);
                    }
                    break;
                    case NUMBER: {
                        setState(20);
                        ((ExpressionContext) _localctx).value = match(NUMBER);
                    }
                    break;
                    case NUMBER_FOR_COIN: {
                        setState(21);
                        ((ExpressionContext) _localctx).value = match(NUMBER_FOR_COIN);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(35);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            setState(33);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
                                case 1: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState);
                                    _localctx.left = _prevctx;
                                    _localctx.left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(24);
                                    if (!(precpred(_ctx, 4))) {
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    }
                                    setState(25);
                                    ((ExpressionContext) _localctx).op = _input.LT(1);
                                    _la = _input.LA(1);
                                    if (!(_la == PLUS || _la == MINUS)) {
                                        ((ExpressionContext) _localctx).op = (Token) _errHandler.recoverInline(this);
                                    } else {
                                        consume();
                                    }
                                    setState(26);
                                    ((ExpressionContext) _localctx).right = expression(5);
                                }
                                break;
                                case 2: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState);
                                    _localctx.left = _prevctx;
                                    _localctx.left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(27);
                                    if (!(precpred(_ctx, 6))) {
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    }
                                    setState(28);
                                    ((ExpressionContext) _localctx).op = match(MULTI);
                                    setState(29);
                                    _la = _input.LA(1);
                                    if (!(_la == NUMBER_FOR_COIN || _la == NUMBER)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        consume();
                                    }
                                }
                                break;
                                case 3: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState);
                                    _localctx.left = _prevctx;
                                    _localctx.left = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(30);
                                    if (!(precpred(_ctx, 5))) {
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    }
                                    setState(31);
                                    ((ExpressionContext) _localctx).op = match(DIV);
                                    setState(32);
                                    match(NUMBER);
                                }
                                break;
                            }
                        }
                    }
                    setState(37);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 2:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return precpred(_ctx, 4);
            case 1:
                return precpred(_ctx, 6);
            case 2:
                return precpred(_ctx, 5);
        }
        return true;
    }

    public static final String _serializedATN
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22)\4\2\t\2\4\3\t"
            + "\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"
            + "\3\4\5\4\31\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4$\n\4\f\4\16\4"
            + "\'\13\4\3\4\2\3\6\5\2\4\6\2\5\3\2\4\6\3\2\t\n\3\2\7\b*\2\b\3\2\2\2\4\17"
            + "\3\2\2\2\6\30\3\2\2\2\b\t\7\3\2\2\t\n\5\4\3\2\n\13\7\20\2\2\13\f\5\6\4"
            + "\2\f\r\7\21\2\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20\t\2\2\2\20\5\3\2\2\2\21"
            + "\22\b\4\1\2\22\23\7\16\2\2\23\24\5\6\4\2\24\25\7\17\2\2\25\31\3\2\2\2"
            + "\26\31\7\b\2\2\27\31\7\7\2\2\30\21\3\2\2\2\30\26\3\2\2\2\30\27\3\2\2\2"
            + "\31%\3\2\2\2\32\33\f\6\2\2\33\34\t\3\2\2\34$\5\6\4\7\35\36\f\b\2\2\36"
            + "\37\7\13\2\2\37$\t\4\2\2 !\f\7\2\2!\"\7\f\2\2\"$\7\b\2\2#\32\3\2\2\2#"
            + "\35\3\2\2\2# \3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\7\3\2\2\2\'%\3\2"
            + "\2\2\5\30#%";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
