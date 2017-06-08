// Generated from /home/k4rd050/Documents/LAPR4/lapr4-2017-2dd/src/main/antlr4/lapr4/blue/s1/lang/n1151452/formula/compiler/BlueFormula.g4 by ANTLR 4.7
package lapr4.blue.s1.lang.n1151452.formula.compiler;

    package lapr4.blue.s1.lang.n1151452.formula.compiler;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BlueFormulaLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LETTER", "FOR", "FUNCTION", "CELL_REF", "CELL", "VARIABLE_NAME", "STRING", 
		"QUOT", "NUMBER", "DIGIT", "EQ", "NEQ", "LTEQ", "GTEQ", "GT", "LT", "AMP", 
		"PLUS", "MINUS", "MULTI", "DIV", "POWER", "PERCENT", "ABS", "EXCL", "COLON", 
		"COMMA", "SEMI", "LPAR", "RPAR", "L_CURLY_BRACKET", "R_CURLY_BRACKET", 
		"UNDERSCORE", "ASSIGN", "WS"
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


	public BlueFormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BlueFormula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00d1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3U\n\3\3\4\6\4X\n\4\r\4\16\4Y\3\5\5\5]\n\5\3\5\3\5\5\5a\n\5\3\5\5\5d"+
		"\n\5\3\5\6\5g\n\5\r\5\16\5h\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\7"+
		"\7u\n\7\f\7\16\7x\13\7\3\b\3\b\3\b\3\b\7\b~\n\b\f\b\16\b\u0081\13\b\3"+
		"\b\3\b\3\t\3\t\3\n\6\n\u0088\n\n\r\n\16\n\u0089\3\n\3\n\6\n\u008e\n\n"+
		"\r\n\16\n\u008f\5\n\u0092\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#"+
		"\3$\3$\3$\3$\5$\u00ce\n$\3$\3$\2\2%\3\2\5\3\7\4\t\5\13\6\r\7\17\b\21\t"+
		"\23\n\25\2\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27"+
		"\61\2\63\2\65\30\67\319\32;\33=\34?\35A\36C\2E\37G \3\2\4\4\2C\\c|\3\2"+
		"$$\2\u00db\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5T\3\2\2\2\7W\3\2\2\2\t\\\3\2\2\2\13j"+
		"\3\2\2\2\rp\3\2\2\2\17y\3\2\2\2\21\u0084\3\2\2\2\23\u0087\3\2\2\2\25\u0093"+
		"\3\2\2\2\27\u0095\3\2\2\2\31\u0097\3\2\2\2\33\u009a\3\2\2\2\35\u009d\3"+
		"\2\2\2\37\u00a0\3\2\2\2!\u00a2\3\2\2\2#\u00a4\3\2\2\2%\u00a6\3\2\2\2\'"+
		"\u00a8\3\2\2\2)\u00aa\3\2\2\2+\u00ac\3\2\2\2-\u00ae\3\2\2\2/\u00b0\3\2"+
		"\2\2\61\u00b2\3\2\2\2\63\u00b4\3\2\2\2\65\u00b6\3\2\2\2\67\u00b8\3\2\2"+
		"\29\u00ba\3\2\2\2;\u00bc\3\2\2\2=\u00be\3\2\2\2?\u00c0\3\2\2\2A\u00c2"+
		"\3\2\2\2C\u00c4\3\2\2\2E\u00c6\3\2\2\2G\u00cd\3\2\2\2IJ\t\2\2\2J\4\3\2"+
		"\2\2KL\7H\2\2LM\7Q\2\2MU\7T\2\2NO\7h\2\2OP\7q\2\2PU\7t\2\2QR\7H\2\2RS"+
		"\7q\2\2SU\7t\2\2TK\3\2\2\2TN\3\2\2\2TQ\3\2\2\2U\6\3\2\2\2VX\5\3\2\2WV"+
		"\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\b\3\2\2\2[]\5\61\31\2\\[\3\2\2"+
		"\2\\]\3\2\2\2]^\3\2\2\2^`\5\3\2\2_a\5\3\2\2`_\3\2\2\2`a\3\2\2\2ac\3\2"+
		"\2\2bd\5\61\31\2cb\3\2\2\2cd\3\2\2\2df\3\2\2\2eg\5\25\13\2fe\3\2\2\2g"+
		"h\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\n\3\2\2\2jk\7%\2\2kl\7E\2\2lm\7G\2\2mn"+
		"\7N\2\2no\7N\2\2o\f\3\2\2\2pq\5C\"\2qv\5\3\2\2ru\5\25\13\2su\5\3\2\2t"+
		"r\3\2\2\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\16\3\2\2\2xv\3\2\2"+
		"\2y\177\5\21\t\2z{\7^\2\2{~\7$\2\2|~\n\3\2\2}z\3\2\2\2}|\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177"+
		"\3\2\2\2\u0082\u0083\5\21\t\2\u0083\20\3\2\2\2\u0084\u0085\7$\2\2\u0085"+
		"\22\3\2\2\2\u0086\u0088\5\25\13\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0091\3\2\2\2\u008b"+
		"\u008d\5\67\34\2\u008c\u008e\5\25\13\2\u008d\u008c\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091"+
		"\u008b\3\2\2\2\u0091\u0092\3\2\2\2\u0092\24\3\2\2\2\u0093\u0094\4\62;"+
		"\2\u0094\26\3\2\2\2\u0095\u0096\7?\2\2\u0096\30\3\2\2\2\u0097\u0098\7"+
		">\2\2\u0098\u0099\7@\2\2\u0099\32\3\2\2\2\u009a\u009b\7>\2\2\u009b\u009c"+
		"\7?\2\2\u009c\34\3\2\2\2\u009d\u009e\7@\2\2\u009e\u009f\7?\2\2\u009f\36"+
		"\3\2\2\2\u00a0\u00a1\7@\2\2\u00a1 \3\2\2\2\u00a2\u00a3\7>\2\2\u00a3\""+
		"\3\2\2\2\u00a4\u00a5\7(\2\2\u00a5$\3\2\2\2\u00a6\u00a7\7-\2\2\u00a7&\3"+
		"\2\2\2\u00a8\u00a9\7/\2\2\u00a9(\3\2\2\2\u00aa\u00ab\7,\2\2\u00ab*\3\2"+
		"\2\2\u00ac\u00ad\7\61\2\2\u00ad,\3\2\2\2\u00ae\u00af\7`\2\2\u00af.\3\2"+
		"\2\2\u00b0\u00b1\7\'\2\2\u00b1\60\3\2\2\2\u00b2\u00b3\7&\2\2\u00b3\62"+
		"\3\2\2\2\u00b4\u00b5\7#\2\2\u00b5\64\3\2\2\2\u00b6\u00b7\7<\2\2\u00b7"+
		"\66\3\2\2\2\u00b8\u00b9\7.\2\2\u00b98\3\2\2\2\u00ba\u00bb\7=\2\2\u00bb"+
		":\3\2\2\2\u00bc\u00bd\7*\2\2\u00bd<\3\2\2\2\u00be\u00bf\7+\2\2\u00bf>"+
		"\3\2\2\2\u00c0\u00c1\7}\2\2\u00c1@\3\2\2\2\u00c2\u00c3\7\177\2\2\u00c3"+
		"B\3\2\2\2\u00c4\u00c5\7a\2\2\u00c5D\3\2\2\2\u00c6\u00c7\7<\2\2\u00c7\u00c8"+
		"\7?\2\2\u00c8F\3\2\2\2\u00c9\u00ce\7\"\2\2\u00ca\u00cb\7\17\2\2\u00cb"+
		"\u00ce\7\f\2\2\u00cc\u00ce\4\13\f\2\u00cd\u00c9\3\2\2\2\u00cd\u00ca\3"+
		"\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b$\2\2\u00d0"+
		"H\3\2\2\2\21\2TY\\`chtv}\177\u0089\u008f\u0091\u00cd\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}