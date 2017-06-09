// Generated from MonetaryLanguage.g4 by ANTLR 4.5.3

    package lapr4.red.s2.lang.n1150690.formula.compiler;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MonetaryLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		START_CHAR=1, DOLLAR=2, EURO=3, POUND=4, NUMBER_FOR_COIN=5, NUMBER=6, 
		PLUS=7, MINUS=8, MULTI=9, DIV=10, COMMA=11, LPAR=12, RPAR=13, L_CURLY_BRACKET=14, 
		R_CURLY_BRACKET=15, WS=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"START_CHAR", "DOLLAR", "EURO", "POUND", "NUMBER_FOR_COIN", "NUMBER", 
		"PLUS", "MINUS", "MULTI", "DIV", "COMMA", "LPAR", "RPAR", "L_CURLY_BRACKET", 
		"R_CURLY_BRACKET", "WS"
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


	public MonetaryLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MonetaryLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22\u008f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\38\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4F\n\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5W\n\5"+
		"\3\6\6\6Z\n\6\r\6\16\6[\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6g\n\6\3"+
		"\7\5\7j\n\7\3\7\6\7m\n\7\r\7\16\7n\3\7\3\7\3\7\5\7t\n\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\5\21\u008c\n\21\3\21\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22\3\2\3\3\2\62"+
		";\u009c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\3#\3\2\2\2\5\67\3\2\2\2\7E\3\2\2\2\tV\3\2\2\2\13Y\3\2\2\2\ri\3\2\2\2"+
		"\17u\3\2\2\2\21w\3\2\2\2\23y\3\2\2\2\25{\3\2\2\2\27}\3\2\2\2\31\177\3"+
		"\2\2\2\33\u0081\3\2\2\2\35\u0083\3\2\2\2\37\u0085\3\2\2\2!\u008b\3\2\2"+
		"\2#$\7%\2\2$\4\3\2\2\2%&\7f\2\2&\'\7q\2\2\'(\7n\2\2()\7n\2\2)*\7c\2\2"+
		"*8\7t\2\2+,\7F\2\2,-\7q\2\2-.\7n\2\2./\7n\2\2/\60\7c\2\2\608\7t\2\2\61"+
		"\62\7F\2\2\62\63\7Q\2\2\63\64\7N\2\2\64\65\7N\2\2\65\66\7C\2\2\668\7T"+
		"\2\2\67%\3\2\2\2\67+\3\2\2\2\67\61\3\2\2\28\6\3\2\2\29:\7g\2\2:;\7w\2"+
		"\2;<\7t\2\2<F\7q\2\2=>\7G\2\2>?\7w\2\2?@\7t\2\2@F\7q\2\2AB\7G\2\2BC\7"+
		"W\2\2CD\7T\2\2DF\7Q\2\2E9\3\2\2\2E=\3\2\2\2EA\3\2\2\2F\b\3\2\2\2GH\7r"+
		"\2\2HI\7q\2\2IJ\7w\2\2JK\7p\2\2KW\7f\2\2LM\7R\2\2MN\7q\2\2NO\7w\2\2OP"+
		"\7p\2\2PW\7f\2\2QR\7R\2\2RS\7Q\2\2ST\7W\2\2TU\7P\2\2UW\7F\2\2VG\3\2\2"+
		"\2VL\3\2\2\2VQ\3\2\2\2W\n\3\2\2\2XZ\t\2\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2"+
		"\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7\60\2\2^_\t\2\2\2_f\t\2\2\2`a\7\u00e4\2"+
		"\2ab\7\u201c\2\2bg\7\u00ae\2\2cg\7&\2\2de\7\u00c4\2\2eg\7\u00a5\2\2f`"+
		"\3\2\2\2fc\3\2\2\2fd\3\2\2\2g\f\3\2\2\2hj\7/\2\2ih\3\2\2\2ij\3\2\2\2j"+
		"l\3\2\2\2km\t\2\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2os\3\2\2\2"+
		"pq\7\60\2\2qr\t\2\2\2rt\t\2\2\2sp\3\2\2\2st\3\2\2\2t\16\3\2\2\2uv\7-\2"+
		"\2v\20\3\2\2\2wx\7/\2\2x\22\3\2\2\2yz\7,\2\2z\24\3\2\2\2{|\7\61\2\2|\26"+
		"\3\2\2\2}~\7.\2\2~\30\3\2\2\2\177\u0080\7*\2\2\u0080\32\3\2\2\2\u0081"+
		"\u0082\7+\2\2\u0082\34\3\2\2\2\u0083\u0084\7}\2\2\u0084\36\3\2\2\2\u0085"+
		"\u0086\7\177\2\2\u0086 \3\2\2\2\u0087\u008c\7\"\2\2\u0088\u0089\7\17\2"+
		"\2\u0089\u008c\7\f\2\2\u008a\u008c\4\13\f\2\u008b\u0087\3\2\2\2\u008b"+
		"\u0088\3\2\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\21"+
		"\2\2\u008e\"\3\2\2\2\f\2\67EV[fins\u008b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}