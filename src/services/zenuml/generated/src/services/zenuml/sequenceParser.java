// Generated from src/services/zenuml/sequenceParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class sequenceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, CONSTANT=2, READONLY=3, STATIC=4, AWAIT=5, TITLE=6, COL=7, SOPEN=8, 
		SCLOSE=9, ARROW=10, COLOR=11, OR=12, AND=13, EQ=14, NEQ=15, GT=16, LT=17, 
		GTEQ=18, LTEQ=19, PLUS=20, MINUS=21, MULT=22, DIV=23, MOD=24, POW=25, 
		NOT=26, SCOL=27, COMMA=28, ASSIGN=29, OPAR=30, CPAR=31, OBRACE=32, CBRACE=33, 
		TRUE=34, FALSE=35, NIL=36, IF=37, ELSE=38, WHILE=39, RETURN=40, NEW=41, 
		PAR=42, GROUP=43, OPT=44, CRITICAL=45, SECTION=46, REF=47, AS=48, TRY=49, 
		CATCH=50, FINALLY=51, IN=52, STARTER_LXR=53, ANNOTATION_RET=54, ANNOTATION=55, 
		DOT=56, ID=57, INT=58, FLOAT=59, MONEY=60, NUMBER_UNIT=61, STRING=62, 
		CR=63, COMMENT=64, OTHER=65, DIVIDER=66, EVENT_PAYLOAD_LXR=67, EVENT_END=68, 
		TITLE_CONTENT=69, TITLE_END=70;
	public static final int
		RULE_prog = 0, RULE_title = 1, RULE_head = 2, RULE_group = 3, RULE_starterExp = 4, 
		RULE_starter = 5, RULE_participant = 6, RULE_stereotype = 7, RULE_label = 8, 
		RULE_participantType = 9, RULE_name = 10, RULE_width = 11, RULE_block = 12, 
		RULE_ret = 13, RULE_divider = 14, RULE_dividerNote = 15, RULE_stat = 16, 
		RULE_par = 17, RULE_opt = 18, RULE_critical = 19, RULE_section = 20, RULE_creation = 21, 
		RULE_ref = 22, RULE_creationBody = 23, RULE_message = 24, RULE_messageBody = 25, 
		RULE_func = 26, RULE_from = 27, RULE_to = 28, RULE_signature = 29, RULE_invocation = 30, 
		RULE_assignment = 31, RULE_asyncMessage = 32, RULE_content = 33, RULE_construct = 34, 
		RULE_type = 35, RULE_assignee = 36, RULE_methodName = 37, RULE_parameters = 38, 
		RULE_parameter = 39, RULE_namedParameter = 40, RULE_declaration = 41, 
		RULE_tcf = 42, RULE_tryBlock = 43, RULE_catchBlock = 44, RULE_finallyBlock = 45, 
		RULE_alt = 46, RULE_ifBlock = 47, RULE_elseIfBlock = 48, RULE_elseBlock = 49, 
		RULE_braceBlock = 50, RULE_loop = 51, RULE_expr = 52, RULE_atom = 53, 
		RULE_parExpr = 54, RULE_condition = 55, RULE_inExpr = 56;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "title", "head", "group", "starterExp", "starter", "participant", 
			"stereotype", "label", "participantType", "name", "width", "block", "ret", 
			"divider", "dividerNote", "stat", "par", "opt", "critical", "section", 
			"creation", "ref", "creationBody", "message", "messageBody", "func", 
			"from", "to", "signature", "invocation", "assignment", "asyncMessage", 
			"content", "construct", "type", "assignee", "methodName", "parameters", 
			"parameter", "namedParameter", "declaration", "tcf", "tryBlock", "catchBlock", 
			"finallyBlock", "alt", "ifBlock", "elseIfBlock", "elseBlock", "braceBlock", 
			"loop", "expr", "atom", "parExpr", "condition", "inExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'const'", "'readonly'", "'static'", "'await'", "'title'", 
			"':'", "'<<'", "'>>'", "'->'", null, "'||'", "'&&'", "'=='", "'!='", 
			"'>'", "'<'", "'>='", "'<='", "'+'", "'-'", "'*'", "'/'", "'%'", "'^'", 
			"'!'", "';'", "','", "'='", "'('", "')'", "'{'", "'}'", "'true'", "'false'", 
			null, "'if'", "'else'", null, "'return'", "'new'", "'par'", "'group'", 
			"'opt'", "'critical'", null, "'ref'", "'as'", "'try'", "'catch'", "'finally'", 
			"'in'", null, null, null, "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "CONSTANT", "READONLY", "STATIC", "AWAIT", "TITLE", "COL", 
			"SOPEN", "SCLOSE", "ARROW", "COLOR", "OR", "AND", "EQ", "NEQ", "GT", 
			"LT", "GTEQ", "LTEQ", "PLUS", "MINUS", "MULT", "DIV", "MOD", "POW", "NOT", 
			"SCOL", "COMMA", "ASSIGN", "OPAR", "CPAR", "OBRACE", "CBRACE", "TRUE", 
			"FALSE", "NIL", "IF", "ELSE", "WHILE", "RETURN", "NEW", "PAR", "GROUP", 
			"OPT", "CRITICAL", "SECTION", "REF", "AS", "TRY", "CATCH", "FINALLY", 
			"IN", "STARTER_LXR", "ANNOTATION_RET", "ANNOTATION", "DOT", "ID", "INT", 
			"FLOAT", "MONEY", "NUMBER_UNIT", "STRING", "CR", "COMMENT", "OTHER", 
			"DIVIDER", "EVENT_PAYLOAD_LXR", "EVENT_END", "TITLE_CONTENT", "TITLE_END"
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
	public String getGrammarFileName() { return "sequenceParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public sequenceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(sequenceParser.EOF, 0); }
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TITLE) {
					{
					setState(114);
					title();
					}
				}

				setState(117);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TITLE) {
					{
					setState(118);
					title();
					}
				}

				setState(121);
				head();
				setState(122);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TITLE) {
					{
					setState(124);
					title();
					}
				}

				setState(128);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(127);
					head();
					}
					break;
				}
				setState(130);
				block();
				setState(131);
				match(EOF);
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
	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TITLE() { return getToken(sequenceParser.TITLE, 0); }
		public TerminalNode TITLE_CONTENT() { return getToken(sequenceParser.TITLE_CONTENT, 0); }
		public TerminalNode TITLE_END() { return getToken(sequenceParser.TITLE_END, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_title);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(TITLE);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TITLE_CONTENT) {
				{
				setState(136);
				match(TITLE_CONTENT);
				}
			}

			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TITLE_END) {
				{
				setState(139);
				match(TITLE_END);
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
	public static class HeadContext extends ParserRuleContext {
		public List<GroupContext> group() {
			return getRuleContexts(GroupContext.class);
		}
		public GroupContext group(int i) {
			return getRuleContext(GroupContext.class,i);
		}
		public List<ParticipantContext> participant() {
			return getRuleContexts(ParticipantContext.class);
		}
		public ParticipantContext participant(int i) {
			return getRuleContext(ParticipantContext.class,i);
		}
		public StarterExpContext starterExp() {
			return getRuleContext(StarterExpContext.class,0);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_head);
		try {
			int _alt;
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(144);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case GROUP:
							{
							setState(142);
							group();
							}
							break;
						case SOPEN:
						case LT:
						case ANNOTATION:
						case ID:
						case STRING:
							{
							setState(143);
							participant();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(146); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(150);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case GROUP:
							{
							setState(148);
							group();
							}
							break;
						case SOPEN:
						case LT:
						case ANNOTATION:
						case ID:
						case STRING:
							{
							setState(149);
							participant();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(154);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(155);
				starterExp();
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
	public static class GroupContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(sequenceParser.GROUP, 0); }
		public TerminalNode OBRACE() { return getToken(sequenceParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(sequenceParser.CBRACE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<ParticipantContext> participant() {
			return getRuleContexts(ParticipantContext.class);
		}
		public ParticipantContext participant(int i) {
			return getRuleContext(ParticipantContext.class,i);
		}
		public GroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupContext group() throws RecognitionException {
		GroupContext _localctx = new GroupContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_group);
		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(GROUP);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID || _la==STRING) {
					{
					setState(159);
					name();
					}
				}

				setState(162);
				match(OBRACE);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4791830003522339072L) != 0)) {
					{
					{
					setState(163);
					participant();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(169);
				match(CBRACE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(GROUP);
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID || _la==STRING) {
					{
					setState(171);
					name();
					}
				}

				setState(174);
				match(OBRACE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(175);
				match(GROUP);
				setState(177);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(176);
					name();
					}
					break;
				}
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
	public static class StarterExpContext extends ParserRuleContext {
		public TerminalNode STARTER_LXR() { return getToken(sequenceParser.STARTER_LXR, 0); }
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public StarterContext starter() {
			return getRuleContext(StarterContext.class,0);
		}
		public TerminalNode ANNOTATION() { return getToken(sequenceParser.ANNOTATION, 0); }
		public StarterExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_starterExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterStarterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitStarterExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitStarterExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StarterExpContext starterExp() throws RecognitionException {
		StarterExpContext _localctx = new StarterExpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_starterExp);
		int _la;
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STARTER_LXR:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(STARTER_LXR);
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPAR) {
					{
					setState(182);
					match(OPAR);
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID || _la==STRING) {
						{
						setState(183);
						starter();
						}
					}

					setState(186);
					match(CPAR);
					}
				}

				}
				break;
			case ANNOTATION:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				match(ANNOTATION);
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
	public static class StarterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public StarterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_starter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterStarter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitStarter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitStarter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StarterContext starter() throws RecognitionException {
		StarterContext _localctx = new StarterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_starter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class ParticipantContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ParticipantTypeContext participantType() {
			return getRuleContext(ParticipantTypeContext.class,0);
		}
		public StereotypeContext stereotype() {
			return getRuleContext(StereotypeContext.class,0);
		}
		public WidthContext width() {
			return getRuleContext(WidthContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode COLOR() { return getToken(sequenceParser.COLOR, 0); }
		public ParticipantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_participant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParticipant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParticipant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParticipant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParticipantContext participant() throws RecognitionException {
		ParticipantContext _localctx = new ParticipantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_participant);
		int _la;
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ANNOTATION) {
					{
					setState(194);
					participantType();
					}
				}

				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SOPEN || _la==LT) {
					{
					setState(197);
					stereotype();
					}
				}

				setState(200);
				name();
				setState(202);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(201);
					width();
					}
					break;
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(204);
					label();
					}
				}

				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLOR) {
					{
					setState(207);
					match(COLOR);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				stereotype();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				participantType();
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
	public static class StereotypeContext extends ParserRuleContext {
		public TerminalNode SOPEN() { return getToken(sequenceParser.SOPEN, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode SCLOSE() { return getToken(sequenceParser.SCLOSE, 0); }
		public TerminalNode GT() { return getToken(sequenceParser.GT, 0); }
		public TerminalNode LT() { return getToken(sequenceParser.LT, 0); }
		public StereotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereotype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterStereotype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitStereotype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitStereotype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StereotypeContext stereotype() throws RecognitionException {
		StereotypeContext _localctx = new StereotypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stereotype);
		int _la;
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(SOPEN);
				setState(215);
				name();
				setState(216);
				match(SCLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(SOPEN);
				setState(219);
				name();
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GT) {
					{
					setState(220);
					match(GT);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				_la = _input.LA(1);
				if ( !(_la==SOPEN || _la==LT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SCLOSE || _la==GT) {
					{
					setState(224);
					_la = _input.LA(1);
					if ( !(_la==SCLOSE || _la==GT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

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
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(sequenceParser.AS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_label);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				match(AS);
				setState(230);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(AS);
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
	public static class ParticipantTypeContext extends ParserRuleContext {
		public TerminalNode ANNOTATION() { return getToken(sequenceParser.ANNOTATION, 0); }
		public ParticipantTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_participantType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParticipantType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParticipantType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParticipantType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParticipantTypeContext participantType() throws RecognitionException {
		ParticipantTypeContext _localctx = new ParticipantTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_participantType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(ANNOTATION);
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
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class WidthContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(sequenceParser.INT, 0); }
		public WidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_width; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitWidth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitWidth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidthContext width() throws RecognitionException {
		WidthContext _localctx = new WidthContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_width);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(INT);
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
	public static class BlockContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(240);
				stat();
				}
				}
				setState(243); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 27888121789L) != 0) );
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
	public static class RetContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(sequenceParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SCOL() { return getToken(sequenceParser.SCOL, 0); }
		public TerminalNode ANNOTATION_RET() { return getToken(sequenceParser.ANNOTATION_RET, 0); }
		public AsyncMessageContext asyncMessage() {
			return getRuleContext(AsyncMessageContext.class,0);
		}
		public TerminalNode EVENT_END() { return getToken(sequenceParser.EVENT_END, 0); }
		public RetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterRet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitRet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitRet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetContext ret() throws RecognitionException {
		RetContext _localctx = new RetContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ret);
		int _la;
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				match(RETURN);
				setState(247);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(246);
					expr(0);
					}
					break;
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SCOL) {
					{
					setState(249);
					match(SCOL);
					}
				}

				}
				break;
			case ANNOTATION_RET:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				match(ANNOTATION_RET);
				setState(253);
				asyncMessage();
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EVENT_END) {
					{
					setState(254);
					match(EVENT_END);
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
	public static class DividerContext extends ParserRuleContext {
		public DividerNoteContext dividerNote() {
			return getRuleContext(DividerNoteContext.class,0);
		}
		public DividerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divider; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterDivider(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitDivider(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitDivider(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DividerContext divider() throws RecognitionException {
		DividerContext _localctx = new DividerContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_divider);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			dividerNote();
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
	public static class DividerNoteContext extends ParserRuleContext {
		public TerminalNode DIVIDER() { return getToken(sequenceParser.DIVIDER, 0); }
		public DividerNoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dividerNote; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterDividerNote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitDividerNote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitDividerNote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DividerNoteContext dividerNote() throws RecognitionException {
		DividerNoteContext _localctx = new DividerNoteContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_dividerNote);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(DIVIDER);
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
	public static class StatContext extends ParserRuleContext {
		public Token OTHER;
		public AltContext alt() {
			return getRuleContext(AltContext.class,0);
		}
		public ParContext par() {
			return getRuleContext(ParContext.class,0);
		}
		public OptContext opt() {
			return getRuleContext(OptContext.class,0);
		}
		public CriticalContext critical() {
			return getRuleContext(CriticalContext.class,0);
		}
		public SectionContext section() {
			return getRuleContext(SectionContext.class,0);
		}
		public RefContext ref() {
			return getRuleContext(RefContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public CreationContext creation() {
			return getRuleContext(CreationContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public AsyncMessageContext asyncMessage() {
			return getRuleContext(AsyncMessageContext.class,0);
		}
		public TerminalNode EVENT_END() { return getToken(sequenceParser.EVENT_END, 0); }
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public DividerContext divider() {
			return getRuleContext(DividerContext.class,0);
		}
		public TcfContext tcf() {
			return getRuleContext(TcfContext.class,0);
		}
		public TerminalNode OTHER() { return getToken(sequenceParser.OTHER, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stat);
		int _la;
		try {
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				alt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				par();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(265);
				opt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(266);
				critical();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(267);
				section();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(268);
				ref();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(269);
				loop();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(270);
				creation();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(271);
				message();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(272);
				asyncMessage();
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EVENT_END) {
					{
					setState(273);
					match(EVENT_END);
					}
				}

				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(276);
				ret();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(277);
				divider();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(278);
				tcf();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(279);
				((StatContext)_localctx).OTHER = match(OTHER);
				console.log("unknown char: " + (((StatContext)_localctx).OTHER!=null?((StatContext)_localctx).OTHER.getText():null));
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
	public static class ParContext extends ParserRuleContext {
		public TerminalNode PAR() { return getToken(sequenceParser.PAR, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public ParContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_par; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitPar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParContext par() throws RecognitionException {
		ParContext _localctx = new ParContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_par);
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				match(PAR);
				setState(284);
				braceBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(PAR);
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
	public static class OptContext extends ParserRuleContext {
		public TerminalNode OPT() { return getToken(sequenceParser.OPT, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public OptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitOpt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitOpt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptContext opt() throws RecognitionException {
		OptContext _localctx = new OptContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_opt);
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(OPT);
				setState(289);
				braceBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(OPT);
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
	public static class CriticalContext extends ParserRuleContext {
		public TerminalNode CRITICAL() { return getToken(sequenceParser.CRITICAL, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public CriticalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_critical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCritical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCritical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCritical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriticalContext critical() throws RecognitionException {
		CriticalContext _localctx = new CriticalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_critical);
		int _la;
		try {
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(CRITICAL);
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPAR) {
					{
					setState(294);
					match(OPAR);
					setState(296);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079256969038004224L) != 0)) {
						{
						setState(295);
						atom();
						}
					}

					setState(298);
					match(CPAR);
					}
				}

				setState(301);
				braceBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				match(CRITICAL);
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
	public static class SectionContext extends ParserRuleContext {
		public TerminalNode SECTION() { return getToken(sequenceParser.SECTION, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_section);
		int _la;
		try {
			setState(316);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				match(SECTION);
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPAR) {
					{
					setState(306);
					match(OPAR);
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079256969038004224L) != 0)) {
						{
						setState(307);
						atom();
						}
					}

					setState(310);
					match(CPAR);
					}
				}

				setState(313);
				braceBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				braceBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(315);
				match(SECTION);
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
	public static class CreationContext extends ParserRuleContext {
		public CreationBodyContext creationBody() {
			return getRuleContext(CreationBodyContext.class,0);
		}
		public TerminalNode SCOL() { return getToken(sequenceParser.SCOL, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public CreationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCreation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreationContext creation() throws RecognitionException {
		CreationContext _localctx = new CreationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_creation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			creationBody();
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(319);
				match(SCOL);
				}
				break;
			case 2:
				{
				setState(320);
				braceBlock();
				}
				break;
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
	public static class RefContext extends ParserRuleContext {
		public TerminalNode REF() { return getToken(sequenceParser.REF, 0); }
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode SCOL() { return getToken(sequenceParser.SCOL, 0); }
		public List<TerminalNode> COMMA() { return getTokens(sequenceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sequenceParser.COMMA, i);
		}
		public RefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RefContext ref() throws RecognitionException {
		RefContext _localctx = new RefContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(REF);
			setState(324);
			match(OPAR);
			{
			setState(325);
			name();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(326);
				match(COMMA);
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID || _la==STRING) {
					{
					{
					setState(327);
					name();
					}
					}
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(338);
			match(CPAR);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCOL) {
				{
				setState(339);
				match(SCOL);
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
	public static class CreationBodyContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(sequenceParser.NEW, 0); }
		public ConstructContext construct() {
			return getRuleContext(ConstructContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public CreationBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creationBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCreationBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCreationBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCreationBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreationBodyContext creationBody() throws RecognitionException {
		CreationBodyContext _localctx = new CreationBodyContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_creationBody);
		int _la;
		try {
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(342);
					assignment();
					}
					break;
				}
				setState(345);
				match(NEW);
				setState(346);
				construct();
				setState(352);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(347);
					match(OPAR);
					setState(349);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079259169204207616L) != 0)) {
						{
						setState(348);
						parameters();
						}
					}

					setState(351);
					match(CPAR);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(354);
					assignment();
					}
					break;
				}
				setState(357);
				match(NEW);
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
	public static class MessageContext extends ParserRuleContext {
		public MessageBodyContext messageBody() {
			return getRuleContext(MessageBodyContext.class,0);
		}
		public TerminalNode SCOL() { return getToken(sequenceParser.SCOL, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			messageBody();
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(361);
				match(SCOL);
				}
				break;
			case 2:
				{
				setState(362);
				braceBlock();
				}
				break;
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
	public static class MessageBodyContext extends ParserRuleContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ToContext to() {
			return getRuleContext(ToContext.class,0);
		}
		public TerminalNode DOT() { return getToken(sequenceParser.DOT, 0); }
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(sequenceParser.ARROW, 0); }
		public MessageBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_messageBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterMessageBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitMessageBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitMessageBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageBodyContext messageBody() throws RecognitionException {
		MessageBodyContext _localctx = new MessageBodyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_messageBody);
		try {
			setState(388);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
				case 1:
					{
					setState(365);
					assignment();
					}
					break;
				}
				setState(376);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(371);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
					case 1:
						{
						setState(368);
						from();
						setState(369);
						match(ARROW);
						}
						break;
					}
					setState(373);
					to();
					setState(374);
					match(DOT);
					}
					break;
				}
				setState(378);
				func();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(383);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(380);
					from();
					setState(381);
					match(ARROW);
					}
					break;
				}
				setState(385);
				to();
				setState(386);
				match(DOT);
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
	public static class FuncContext extends ParserRuleContext {
		public List<SignatureContext> signature() {
			return getRuleContexts(SignatureContext.class);
		}
		public SignatureContext signature(int i) {
			return getRuleContext(SignatureContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(sequenceParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(sequenceParser.DOT, i);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_func);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			signature();
			setState(395);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(391);
					match(DOT);
					setState(392);
					signature();
					}
					} 
				}
				setState(397);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
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
	public static class FromContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitFrom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitFrom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class ToContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public ToContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_to; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterTo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitTo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitTo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ToContext to() throws RecognitionException {
		ToContext _localctx = new ToContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_to);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class SignatureContext extends ParserRuleContext {
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public SignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			methodName();
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(403);
				invocation();
				}
				break;
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
	public static class InvocationContext extends ParserRuleContext {
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public InvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvocationContext invocation() throws RecognitionException {
		InvocationContext _localctx = new InvocationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_invocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(OPAR);
			setState(408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079259169204207616L) != 0)) {
				{
				setState(407);
				parameters();
				}
			}

			setState(410);
			match(CPAR);
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
	public static class AssignmentContext extends ParserRuleContext {
		public AssigneeContext assignee() {
			return getRuleContext(AssigneeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(sequenceParser.ASSIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(413);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				{
				setState(412);
				type();
				}
				break;
			}
			setState(415);
			assignee();
			setState(416);
			match(ASSIGN);
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
	public static class AsyncMessageContext extends ParserRuleContext {
		public ToContext to() {
			return getRuleContext(ToContext.class,0);
		}
		public TerminalNode COL() { return getToken(sequenceParser.COL, 0); }
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(sequenceParser.ARROW, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(sequenceParser.MINUS, 0); }
		public AsyncMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asyncMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAsyncMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAsyncMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAsyncMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsyncMessageContext asyncMessage() throws RecognitionException {
		AsyncMessageContext _localctx = new AsyncMessageContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_asyncMessage);
		int _la;
		try {
			setState(433);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(418);
					from();
					setState(419);
					match(ARROW);
					}
					break;
				}
				setState(423);
				to();
				setState(424);
				match(COL);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EVENT_PAYLOAD_LXR) {
					{
					setState(425);
					content();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(428);
				from();
				setState(429);
				_la = _input.LA(1);
				if ( !(_la==ARROW || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(431);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(430);
					to();
					}
					break;
				}
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
	public static class ContentContext extends ParserRuleContext {
		public TerminalNode EVENT_PAYLOAD_LXR() { return getToken(sequenceParser.EVENT_PAYLOAD_LXR, 0); }
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(EVENT_PAYLOAD_LXR);
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
	public static class ConstructContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public ConstructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_construct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterConstruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitConstruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitConstruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructContext construct() throws RecognitionException {
		ConstructContext _localctx = new ConstructContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_construct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class AssigneeContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(sequenceParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(sequenceParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(sequenceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sequenceParser.COMMA, i);
		}
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public TerminalNode NEW() { return getToken(sequenceParser.NEW, 0); }
		public AssigneeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignee; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAssignee(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAssignee(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAssignee(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssigneeContext assignee() throws RecognitionException {
		AssigneeContext _localctx = new AssigneeContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_assignee);
		int _la;
		try {
			setState(452);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(441);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(442);
				match(ID);
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(443);
					match(COMMA);
					setState(444);
					match(ID);
					}
					}
					setState(449);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(450);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(451);
				match(NEW);
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
	public static class MethodNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public MethodNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterMethodName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitMethodName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitMethodName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_methodName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==STRING) ) {
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
	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(sequenceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sequenceParser.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_parameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			parameter();
			setState(461);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(457);
					match(COMMA);
					setState(458);
					parameter();
					}
					} 
				}
				setState(463);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			}
			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(464);
				match(COMMA);
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
	public static class ParameterContext extends ParserRuleContext {
		public NamedParameterContext namedParameter() {
			return getRuleContext(NamedParameterContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_parameter);
		try {
			setState(470);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(467);
				namedParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(468);
				declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(469);
				expr(0);
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
	public static class NamedParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(sequenceParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NamedParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterNamedParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitNamedParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitNamedParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamedParameterContext namedParameter() throws RecognitionException {
		NamedParameterContext _localctx = new NamedParameterContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_namedParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			match(ID);
			setState(473);
			match(ASSIGN);
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079259169204207616L) != 0)) {
				{
				setState(474);
				expr(0);
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
	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			type();
			setState(478);
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
	public static class TcfContext extends ParserRuleContext {
		public TryBlockContext tryBlock() {
			return getRuleContext(TryBlockContext.class,0);
		}
		public List<CatchBlockContext> catchBlock() {
			return getRuleContexts(CatchBlockContext.class);
		}
		public CatchBlockContext catchBlock(int i) {
			return getRuleContext(CatchBlockContext.class,i);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public TcfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tcf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterTcf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitTcf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitTcf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TcfContext tcf() throws RecognitionException {
		TcfContext _localctx = new TcfContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tcf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			tryBlock();
			setState(484);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CATCH) {
				{
				{
				setState(481);
				catchBlock();
				}
				}
				setState(486);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(488);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(487);
				finallyBlock();
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
	public static class TryBlockContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(sequenceParser.TRY, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public TryBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterTryBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitTryBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitTryBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryBlockContext tryBlock() throws RecognitionException {
		TryBlockContext _localctx = new TryBlockContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_tryBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			match(TRY);
			setState(491);
			braceBlock();
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
	public static class CatchBlockContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(sequenceParser.CATCH, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public CatchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCatchBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCatchBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCatchBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchBlockContext catchBlock() throws RecognitionException {
		CatchBlockContext _localctx = new CatchBlockContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_catchBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			match(CATCH);
			setState(495);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPAR) {
				{
				setState(494);
				invocation();
				}
			}

			setState(497);
			braceBlock();
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
	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(sequenceParser.FINALLY, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitFinallyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitFinallyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			match(FINALLY);
			setState(500);
			braceBlock();
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
	public static class AltContext extends ParserRuleContext {
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public List<ElseIfBlockContext> elseIfBlock() {
			return getRuleContexts(ElseIfBlockContext.class);
		}
		public ElseIfBlockContext elseIfBlock(int i) {
			return getRuleContext(ElseIfBlockContext.class,i);
		}
		public ElseBlockContext elseBlock() {
			return getRuleContext(ElseBlockContext.class,0);
		}
		public AltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAlt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAlt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AltContext alt() throws RecognitionException {
		AltContext _localctx = new AltContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_alt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			ifBlock();
			setState(506);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(503);
					elseIfBlock();
					}
					} 
				}
				setState(508);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			}
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(509);
				elseBlock();
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
	public static class IfBlockContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(sequenceParser.IF, 0); }
		public ParExprContext parExpr() {
			return getRuleContext(ParExprContext.class,0);
		}
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterIfBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitIfBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitIfBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_ifBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			match(IF);
			setState(513);
			parExpr();
			setState(514);
			braceBlock();
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
	public static class ElseIfBlockContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(sequenceParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(sequenceParser.IF, 0); }
		public ParExprContext parExpr() {
			return getRuleContext(ParExprContext.class,0);
		}
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public ElseIfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterElseIfBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitElseIfBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitElseIfBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfBlockContext elseIfBlock() throws RecognitionException {
		ElseIfBlockContext _localctx = new ElseIfBlockContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_elseIfBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			match(ELSE);
			setState(517);
			match(IF);
			setState(518);
			parExpr();
			setState(519);
			braceBlock();
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
	public static class ElseBlockContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(sequenceParser.ELSE, 0); }
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public ElseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterElseBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitElseBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitElseBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseBlockContext elseBlock() throws RecognitionException {
		ElseBlockContext _localctx = new ElseBlockContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_elseBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			match(ELSE);
			setState(522);
			braceBlock();
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
	public static class BraceBlockContext extends ParserRuleContext {
		public TerminalNode OBRACE() { return getToken(sequenceParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(sequenceParser.CBRACE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BraceBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braceBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterBraceBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitBraceBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitBraceBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BraceBlockContext braceBlock() throws RecognitionException {
		BraceBlockContext _localctx = new BraceBlockContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_braceBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			match(OBRACE);
			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 27888121789L) != 0)) {
				{
				setState(525);
				block();
				}
			}

			setState(528);
			match(CBRACE);
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
	public static class LoopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(sequenceParser.WHILE, 0); }
		public ParExprContext parExpr() {
			return getRuleContext(ParExprContext.class,0);
		}
		public BraceBlockContext braceBlock() {
			return getRuleContext(BraceBlockContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_loop);
		try {
			setState(537);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(530);
				match(WHILE);
				setState(531);
				parExpr();
				setState(532);
				braceBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(534);
				match(WHILE);
				setState(535);
				parExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(536);
				match(WHILE);
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
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExprContext extends ExprContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAssignmentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAssignmentExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAssignmentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncExprContext extends ExprContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public ToContext to() {
			return getRuleContext(ToContext.class,0);
		}
		public TerminalNode DOT() { return getToken(sequenceParser.DOT, 0); }
		public FuncExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExprContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(sequenceParser.OR, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(sequenceParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(sequenceParser.MINUS, 0); }
		public AdditiveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LTEQ() { return getToken(sequenceParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(sequenceParser.GTEQ, 0); }
		public TerminalNode LT() { return getToken(sequenceParser.LT, 0); }
		public TerminalNode GT() { return getToken(sequenceParser.GT, 0); }
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(sequenceParser.PLUS, 0); }
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterPlusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitPlusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitPlusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(sequenceParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusExprContext extends ExprContext {
		public TerminalNode MINUS() { return getToken(sequenceParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryMinusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterUnaryMinusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitUnaryMinusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitUnaryMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CreationExprContext extends ExprContext {
		public CreationContext creation() {
			return getRuleContext(CreationContext.class,0);
		}
		public CreationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCreationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCreationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCreationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExprContext extends ExprContext {
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public ParenthesizedExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParenthesizedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParenthesizedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParenthesizedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(sequenceParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(sequenceParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(sequenceParser.MOD, 0); }
		public MultiplicationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterMultiplicationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitMultiplicationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(sequenceParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(sequenceParser.NEQ, 0); }
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(sequenceParser.AND, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 104;
		enterRecursionRule(_localctx, 104, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(540);
				atom();
				}
				break;
			case 2:
				{
				_localctx = new UnaryMinusExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(541);
				match(MINUS);
				setState(542);
				expr(13);
				}
				break;
			case 3:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(543);
				match(NOT);
				setState(544);
				expr(12);
				}
				break;
			case 4:
				{
				_localctx = new FuncExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(548);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(545);
					to();
					setState(546);
					match(DOT);
					}
					break;
				}
				setState(550);
				func();
				}
				break;
			case 5:
				{
				_localctx = new CreationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(551);
				creation();
				}
				break;
			case 6:
				{
				_localctx = new ParenthesizedExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(552);
				match(OPAR);
				setState(553);
				expr(0);
				setState(554);
				match(CPAR);
				}
				break;
			case 7:
				{
				_localctx = new AssignmentExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(556);
				assignment();
				setState(557);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(584);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(582);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(561);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(562);
						((MultiplicationExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 29360128L) != 0)) ) {
							((MultiplicationExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(563);
						expr(12);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(564);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(565);
						((AdditiveExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AdditiveExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(566);
						expr(11);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(567);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(568);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(569);
						expr(10);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(570);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(571);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(572);
						expr(9);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(573);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(574);
						match(AND);
						setState(575);
						expr(8);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(576);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(577);
						match(OR);
						setState(578);
						expr(7);
						}
						break;
					case 7:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(579);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(580);
						match(PLUS);
						setState(581);
						expr(6);
						}
						break;
					}
					} 
				}
				setState(586);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanAtomContext extends AtomContext {
		public TerminalNode TRUE() { return getToken(sequenceParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(sequenceParser.FALSE, 0); }
		public BooleanAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterBooleanAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitBooleanAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitBooleanAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdAtomContext extends AtomContext {
		public TerminalNode ID() { return getToken(sequenceParser.ID, 0); }
		public IdAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterIdAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitIdAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitIdAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MoneyAtomContext extends AtomContext {
		public TerminalNode MONEY() { return getToken(sequenceParser.MONEY, 0); }
		public MoneyAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterMoneyAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitMoneyAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitMoneyAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringAtomContext extends AtomContext {
		public TerminalNode STRING() { return getToken(sequenceParser.STRING, 0); }
		public StringAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterStringAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitStringAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitStringAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NilAtomContext extends AtomContext {
		public TerminalNode NIL() { return getToken(sequenceParser.NIL, 0); }
		public NilAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterNilAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitNilAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitNilAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberAtomContext extends AtomContext {
		public TerminalNode INT() { return getToken(sequenceParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(sequenceParser.FLOAT, 0); }
		public NumberAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterNumberAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitNumberAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitNumberAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberUnitAtomContext extends AtomContext {
		public TerminalNode NUMBER_UNIT() { return getToken(sequenceParser.NUMBER_UNIT, 0); }
		public NumberUnitAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterNumberUnitAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitNumberUnitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitNumberUnitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_atom);
		int _la;
		try {
			setState(594);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case FLOAT:
				_localctx = new NumberAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(587);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==FLOAT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case NUMBER_UNIT:
				_localctx = new NumberUnitAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(588);
				match(NUMBER_UNIT);
				}
				break;
			case MONEY:
				_localctx = new MoneyAtomContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(589);
				match(MONEY);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BooleanAtomContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(590);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case ID:
				_localctx = new IdAtomContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(591);
				match(ID);
				}
				break;
			case STRING:
				_localctx = new StringAtomContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(592);
				match(STRING);
				}
				break;
			case NIL:
				_localctx = new NilAtomContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(593);
				match(NIL);
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
	public static class ParExprContext extends ParserRuleContext {
		public TerminalNode OPAR() { return getToken(sequenceParser.OPAR, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode CPAR() { return getToken(sequenceParser.CPAR, 0); }
		public ParExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterParExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitParExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitParExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParExprContext parExpr() throws RecognitionException {
		ParExprContext _localctx = new ParExprContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_parExpr);
		try {
			setState(605);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(596);
				match(OPAR);
				setState(597);
				condition();
				setState(598);
				match(CPAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(600);
				match(OPAR);
				setState(601);
				condition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(602);
				match(OPAR);
				setState(603);
				match(CPAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(604);
				match(OPAR);
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
	public static class ConditionContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InExprContext inExpr() {
			return getRuleContext(InExprContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_condition);
		try {
			setState(610);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(607);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(608);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(609);
				inExpr();
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
	public static class InExprContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(sequenceParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(sequenceParser.ID, i);
		}
		public TerminalNode IN() { return getToken(sequenceParser.IN, 0); }
		public InExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).enterInExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sequenceParserListener ) ((sequenceParserListener)listener).exitInExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sequenceParserVisitor ) return ((sequenceParserVisitor<? extends T>)visitor).visitInExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InExprContext inExpr() throws RecognitionException {
		InExprContext _localctx = new InExprContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_inExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			match(ID);
			setState(613);
			match(IN);
			setState(614);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 52:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001F\u0269\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u0001\u0000\u0003\u0000t\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000x\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000~\b\u0000\u0001\u0000\u0003\u0000\u0081\b\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0086\b\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u008a\b\u0001\u0001\u0001\u0003\u0001\u008d\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0004\u0002\u0091\b\u0002\u000b\u0002\f\u0002"+
		"\u0092\u0001\u0002\u0001\u0002\u0005\u0002\u0097\b\u0002\n\u0002\f\u0002"+
		"\u009a\t\u0002\u0001\u0002\u0003\u0002\u009d\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u00a1\b\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u00a5"+
		"\b\u0003\n\u0003\f\u0003\u00a8\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u00ad\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"\u00b2\b\u0003\u0003\u0003\u00b4\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00b9\b\u0004\u0001\u0004\u0003\u0004\u00bc\b\u0004"+
		"\u0001\u0004\u0003\u0004\u00bf\b\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0003\u0006\u00c4\b\u0006\u0001\u0006\u0003\u0006\u00c7\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00cb\b\u0006\u0001\u0006\u0003\u0006\u00ce"+
		"\b\u0006\u0001\u0006\u0003\u0006\u00d1\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u00d5\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00de\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00e2\b\u0007\u0003\u0007\u00e4\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u00e9\b\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0004\f\u00f2\b\f\u000b\f\f\f\u00f3\u0001\r"+
		"\u0001\r\u0003\r\u00f8\b\r\u0001\r\u0003\r\u00fb\b\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u0100\b\r\u0003\r\u0102\b\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u0113\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u011a\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u011f\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0124\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0129\b"+
		"\u0013\u0001\u0013\u0003\u0013\u012c\b\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0130\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0135"+
		"\b\u0014\u0001\u0014\u0003\u0014\u0138\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u013d\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u0142\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u0149\b\u0016\n\u0016\f\u0016\u014c\t\u0016\u0005"+
		"\u0016\u014e\b\u0016\n\u0016\f\u0016\u0151\t\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u0155\b\u0016\u0001\u0017\u0003\u0017\u0158\b\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u015e\b\u0017\u0001"+
		"\u0017\u0003\u0017\u0161\b\u0017\u0001\u0017\u0003\u0017\u0164\b\u0017"+
		"\u0001\u0017\u0003\u0017\u0167\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u016c\b\u0018\u0001\u0019\u0003\u0019\u016f\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0174\b\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0179\b\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0180\b\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0185\b\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0005\u001a\u018a\b\u001a\n\u001a\f\u001a\u018d\t\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u0195\b\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u0199\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001f\u0003\u001f\u019e\b\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0003 \u01a6\b \u0001 \u0001 "+
		"\u0001 \u0003 \u01ab\b \u0001 \u0001 \u0001 \u0003 \u01b0\b \u0003 \u01b2"+
		"\b \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001$\u0001"+
		"$\u0005$\u01be\b$\n$\f$\u01c1\t$\u0001$\u0001$\u0003$\u01c5\b$\u0001%"+
		"\u0001%\u0001&\u0001&\u0001&\u0005&\u01cc\b&\n&\f&\u01cf\t&\u0001&\u0003"+
		"&\u01d2\b&\u0001\'\u0001\'\u0001\'\u0003\'\u01d7\b\'\u0001(\u0001(\u0001"+
		"(\u0003(\u01dc\b(\u0001)\u0001)\u0001)\u0001*\u0001*\u0005*\u01e3\b*\n"+
		"*\f*\u01e6\t*\u0001*\u0003*\u01e9\b*\u0001+\u0001+\u0001+\u0001,\u0001"+
		",\u0003,\u01f0\b,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001.\u0001.\u0005"+
		".\u01f9\b.\n.\f.\u01fc\t.\u0001.\u0003.\u01ff\b.\u0001/\u0001/\u0001/"+
		"\u0001/\u00010\u00010\u00010\u00010\u00010\u00011\u00011\u00011\u0001"+
		"2\u00012\u00032\u020f\b2\u00012\u00012\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00033\u021a\b3\u00014\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00014\u00034\u0225\b4\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00014\u00014\u00034\u0230\b4\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u00054\u0247\b4\n4"+
		"\f4\u024a\t4\u00015\u00015\u00015\u00015\u00015\u00015\u00015\u00035\u0253"+
		"\b5\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0003"+
		"6\u025e\b6\u00017\u00017\u00017\u00037\u0263\b7\u00018\u00018\u00018\u0001"+
		"8\u00018\u0000\u0001h9\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfhjlnp\u0000\n\u0002\u000099>>\u0002\u0000\b\b\u0011\u0011\u0002\u0000"+
		"\t\t\u0010\u0010\u0002\u0000\n\n\u0015\u0015\u0001\u0000\u0016\u0018\u0001"+
		"\u0000\u0014\u0015\u0001\u0000\u0010\u0013\u0001\u0000\u000e\u000f\u0001"+
		"\u0000:;\u0001\u0000\"#\u02b1\u0000\u0085\u0001\u0000\u0000\u0000\u0002"+
		"\u0087\u0001\u0000\u0000\u0000\u0004\u009c\u0001\u0000\u0000\u0000\u0006"+
		"\u00b3\u0001\u0000\u0000\u0000\b\u00be\u0001\u0000\u0000\u0000\n\u00c0"+
		"\u0001\u0000\u0000\u0000\f\u00d4\u0001\u0000\u0000\u0000\u000e\u00e3\u0001"+
		"\u0000\u0000\u0000\u0010\u00e8\u0001\u0000\u0000\u0000\u0012\u00ea\u0001"+
		"\u0000\u0000\u0000\u0014\u00ec\u0001\u0000\u0000\u0000\u0016\u00ee\u0001"+
		"\u0000\u0000\u0000\u0018\u00f1\u0001\u0000\u0000\u0000\u001a\u0101\u0001"+
		"\u0000\u0000\u0000\u001c\u0103\u0001\u0000\u0000\u0000\u001e\u0105\u0001"+
		"\u0000\u0000\u0000 \u0119\u0001\u0000\u0000\u0000\"\u011e\u0001\u0000"+
		"\u0000\u0000$\u0123\u0001\u0000\u0000\u0000&\u012f\u0001\u0000\u0000\u0000"+
		"(\u013c\u0001\u0000\u0000\u0000*\u013e\u0001\u0000\u0000\u0000,\u0143"+
		"\u0001\u0000\u0000\u0000.\u0166\u0001\u0000\u0000\u00000\u0168\u0001\u0000"+
		"\u0000\u00002\u0184\u0001\u0000\u0000\u00004\u0186\u0001\u0000\u0000\u0000"+
		"6\u018e\u0001\u0000\u0000\u00008\u0190\u0001\u0000\u0000\u0000:\u0192"+
		"\u0001\u0000\u0000\u0000<\u0196\u0001\u0000\u0000\u0000>\u019d\u0001\u0000"+
		"\u0000\u0000@\u01b1\u0001\u0000\u0000\u0000B\u01b3\u0001\u0000\u0000\u0000"+
		"D\u01b5\u0001\u0000\u0000\u0000F\u01b7\u0001\u0000\u0000\u0000H\u01c4"+
		"\u0001\u0000\u0000\u0000J\u01c6\u0001\u0000\u0000\u0000L\u01c8\u0001\u0000"+
		"\u0000\u0000N\u01d6\u0001\u0000\u0000\u0000P\u01d8\u0001\u0000\u0000\u0000"+
		"R\u01dd\u0001\u0000\u0000\u0000T\u01e0\u0001\u0000\u0000\u0000V\u01ea"+
		"\u0001\u0000\u0000\u0000X\u01ed\u0001\u0000\u0000\u0000Z\u01f3\u0001\u0000"+
		"\u0000\u0000\\\u01f6\u0001\u0000\u0000\u0000^\u0200\u0001\u0000\u0000"+
		"\u0000`\u0204\u0001\u0000\u0000\u0000b\u0209\u0001\u0000\u0000\u0000d"+
		"\u020c\u0001\u0000\u0000\u0000f\u0219\u0001\u0000\u0000\u0000h\u022f\u0001"+
		"\u0000\u0000\u0000j\u0252\u0001\u0000\u0000\u0000l\u025d\u0001\u0000\u0000"+
		"\u0000n\u0262\u0001\u0000\u0000\u0000p\u0264\u0001\u0000\u0000\u0000r"+
		"t\u0003\u0002\u0001\u0000sr\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000tu\u0001\u0000\u0000\u0000u\u0086\u0005\u0000\u0000\u0001vx\u0003"+
		"\u0002\u0001\u0000wv\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000"+
		"xy\u0001\u0000\u0000\u0000yz\u0003\u0004\u0002\u0000z{\u0005\u0000\u0000"+
		"\u0001{\u0086\u0001\u0000\u0000\u0000|~\u0003\u0002\u0001\u0000}|\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0080\u0001\u0000\u0000"+
		"\u0000\u007f\u0081\u0003\u0004\u0002\u0000\u0080\u007f\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0003\u0018\f\u0000\u0083\u0084\u0005\u0000\u0000\u0001"+
		"\u0084\u0086\u0001\u0000\u0000\u0000\u0085s\u0001\u0000\u0000\u0000\u0085"+
		"w\u0001\u0000\u0000\u0000\u0085}\u0001\u0000\u0000\u0000\u0086\u0001\u0001"+
		"\u0000\u0000\u0000\u0087\u0089\u0005\u0006\u0000\u0000\u0088\u008a\u0005"+
		"E\u0000\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000"+
		"\u0000\u0000\u008a\u008c\u0001\u0000\u0000\u0000\u008b\u008d\u0005F\u0000"+
		"\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u0003\u0001\u0000\u0000\u0000\u008e\u0091\u0003\u0006\u0003"+
		"\u0000\u008f\u0091\u0003\f\u0006\u0000\u0090\u008e\u0001\u0000\u0000\u0000"+
		"\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000"+
		"\u0093\u009d\u0001\u0000\u0000\u0000\u0094\u0097\u0003\u0006\u0003\u0000"+
		"\u0095\u0097\u0003\f\u0006\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096"+
		"\u0095\u0001\u0000\u0000\u0000\u0097\u009a\u0001\u0000\u0000\u0000\u0098"+
		"\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099"+
		"\u009b\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009b"+
		"\u009d\u0003\b\u0004\u0000\u009c\u0090\u0001\u0000\u0000\u0000\u009c\u0098"+
		"\u0001\u0000\u0000\u0000\u009d\u0005\u0001\u0000\u0000\u0000\u009e\u00a0"+
		"\u0005+\u0000\u0000\u009f\u00a1\u0003\u0014\n\u0000\u00a0\u009f\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a6\u0005 \u0000\u0000\u00a3\u00a5\u0003\f"+
		"\u0006\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a9\u00b4\u0005!\u0000\u0000\u00aa\u00ac\u0005+\u0000\u0000"+
		"\u00ab\u00ad\u0003\u0014\n\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\u00b4\u0005 \u0000\u0000\u00af\u00b1\u0005+\u0000\u0000\u00b0\u00b2\u0003"+
		"\u0014\n\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3\u009e\u0001\u0000"+
		"\u0000\u0000\u00b3\u00aa\u0001\u0000\u0000\u0000\u00b3\u00af\u0001\u0000"+
		"\u0000\u0000\u00b4\u0007\u0001\u0000\u0000\u0000\u00b5\u00bb\u00055\u0000"+
		"\u0000\u00b6\u00b8\u0005\u001e\u0000\u0000\u00b7\u00b9\u0003\n\u0005\u0000"+
		"\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bc\u0005\u001f\u0000\u0000"+
		"\u00bb\u00b6\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bf\u00057\u0000\u0000\u00be"+
		"\u00b5\u0001\u0000\u0000\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00bf"+
		"\t\u0001\u0000\u0000\u0000\u00c0\u00c1\u0007\u0000\u0000\u0000\u00c1\u000b"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c4\u0003\u0012\t\u0000\u00c3\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c7\u0003\u000e\u0007\u0000\u00c6\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001"+
		"\u0000\u0000\u0000\u00c8\u00ca\u0003\u0014\n\u0000\u00c9\u00cb\u0003\u0016"+
		"\u000b\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00ce\u0003\u0010"+
		"\b\u0000\u00cd\u00cc\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000"+
		"\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf\u00d1\u0005\u000b\u0000"+
		"\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d5\u0001\u0000\u0000\u0000\u00d2\u00d5\u0003\u000e\u0007"+
		"\u0000\u00d3\u00d5\u0003\u0012\t\u0000\u00d4\u00c3\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d5\r\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005\b\u0000\u0000\u00d7"+
		"\u00d8\u0003\u0014\n\u0000\u00d8\u00d9\u0005\t\u0000\u0000\u00d9\u00e4"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0005\b\u0000\u0000\u00db\u00dd\u0003"+
		"\u0014\n\u0000\u00dc\u00de\u0005\u0010\u0000\u0000\u00dd\u00dc\u0001\u0000"+
		"\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00e4\u0001\u0000"+
		"\u0000\u0000\u00df\u00e1\u0007\u0001\u0000\u0000\u00e0\u00e2\u0007\u0002"+
		"\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e4\u0001\u0000\u0000\u0000\u00e3\u00d6\u0001\u0000"+
		"\u0000\u0000\u00e3\u00da\u0001\u0000\u0000\u0000\u00e3\u00df\u0001\u0000"+
		"\u0000\u0000\u00e4\u000f\u0001\u0000\u0000\u0000\u00e5\u00e6\u00050\u0000"+
		"\u0000\u00e6\u00e9\u0003\u0014\n\u0000\u00e7\u00e9\u00050\u0000\u0000"+
		"\u00e8\u00e5\u0001\u0000\u0000\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e9\u0011\u0001\u0000\u0000\u0000\u00ea\u00eb\u00057\u0000\u0000\u00eb"+
		"\u0013\u0001\u0000\u0000\u0000\u00ec\u00ed\u0007\u0000\u0000\u0000\u00ed"+
		"\u0015\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005:\u0000\u0000\u00ef\u0017"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f2\u0003 \u0010\u0000\u00f1\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u0019\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f7\u0005(\u0000\u0000\u00f6\u00f8\u0003h4"+
		"\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f8\u00fa\u0001\u0000\u0000\u0000\u00f9\u00fb\u0005\u001b\u0000"+
		"\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000"+
		"\u0000\u00fb\u0102\u0001\u0000\u0000\u0000\u00fc\u00fd\u00056\u0000\u0000"+
		"\u00fd\u00ff\u0003@ \u0000\u00fe\u0100\u0005D\u0000\u0000\u00ff\u00fe"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0102"+
		"\u0001\u0000\u0000\u0000\u0101\u00f5\u0001\u0000\u0000\u0000\u0101\u00fc"+
		"\u0001\u0000\u0000\u0000\u0102\u001b\u0001\u0000\u0000\u0000\u0103\u0104"+
		"\u0003\u001e\u000f\u0000\u0104\u001d\u0001\u0000\u0000\u0000\u0105\u0106"+
		"\u0005B\u0000\u0000\u0106\u001f\u0001\u0000\u0000\u0000\u0107\u011a\u0003"+
		"\\.\u0000\u0108\u011a\u0003\"\u0011\u0000\u0109\u011a\u0003$\u0012\u0000"+
		"\u010a\u011a\u0003&\u0013\u0000\u010b\u011a\u0003(\u0014\u0000\u010c\u011a"+
		"\u0003,\u0016\u0000\u010d\u011a\u0003f3\u0000\u010e\u011a\u0003*\u0015"+
		"\u0000\u010f\u011a\u00030\u0018\u0000\u0110\u0112\u0003@ \u0000\u0111"+
		"\u0113\u0005D\u0000\u0000\u0112\u0111\u0001\u0000\u0000\u0000\u0112\u0113"+
		"\u0001\u0000\u0000\u0000\u0113\u011a\u0001\u0000\u0000\u0000\u0114\u011a"+
		"\u0003\u001a\r\u0000\u0115\u011a\u0003\u001c\u000e\u0000\u0116\u011a\u0003"+
		"T*\u0000\u0117\u0118\u0005A\u0000\u0000\u0118\u011a\u0006\u0010\uffff"+
		"\uffff\u0000\u0119\u0107\u0001\u0000\u0000\u0000\u0119\u0108\u0001\u0000"+
		"\u0000\u0000\u0119\u0109\u0001\u0000\u0000\u0000\u0119\u010a\u0001\u0000"+
		"\u0000\u0000\u0119\u010b\u0001\u0000\u0000\u0000\u0119\u010c\u0001\u0000"+
		"\u0000\u0000\u0119\u010d\u0001\u0000\u0000\u0000\u0119\u010e\u0001\u0000"+
		"\u0000\u0000\u0119\u010f\u0001\u0000\u0000\u0000\u0119\u0110\u0001\u0000"+
		"\u0000\u0000\u0119\u0114\u0001\u0000\u0000\u0000\u0119\u0115\u0001\u0000"+
		"\u0000\u0000\u0119\u0116\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000"+
		"\u0000\u0000\u011a!\u0001\u0000\u0000\u0000\u011b\u011c\u0005*\u0000\u0000"+
		"\u011c\u011f\u0003d2\u0000\u011d\u011f\u0005*\u0000\u0000\u011e\u011b"+
		"\u0001\u0000\u0000\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011f#\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0005,\u0000\u0000\u0121\u0124\u0003d2"+
		"\u0000\u0122\u0124\u0005,\u0000\u0000\u0123\u0120\u0001\u0000\u0000\u0000"+
		"\u0123\u0122\u0001\u0000\u0000\u0000\u0124%\u0001\u0000\u0000\u0000\u0125"+
		"\u012b\u0005-\u0000\u0000\u0126\u0128\u0005\u001e\u0000\u0000\u0127\u0129"+
		"\u0003j5\u0000\u0128\u0127\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012c\u0005\u001f"+
		"\u0000\u0000\u012b\u0126\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u0130\u0003d2\u0000"+
		"\u012e\u0130\u0005-\u0000\u0000\u012f\u0125\u0001\u0000\u0000\u0000\u012f"+
		"\u012e\u0001\u0000\u0000\u0000\u0130\'\u0001\u0000\u0000\u0000\u0131\u0137"+
		"\u0005.\u0000\u0000\u0132\u0134\u0005\u001e\u0000\u0000\u0133\u0135\u0003"+
		"j5\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0134\u0135\u0001\u0000\u0000"+
		"\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0138\u0005\u001f\u0000"+
		"\u0000\u0137\u0132\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013d\u0003d2\u0000\u013a"+
		"\u013d\u0003d2\u0000\u013b\u013d\u0005.\u0000\u0000\u013c\u0131\u0001"+
		"\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013b\u0001"+
		"\u0000\u0000\u0000\u013d)\u0001\u0000\u0000\u0000\u013e\u0141\u0003.\u0017"+
		"\u0000\u013f\u0142\u0005\u001b\u0000\u0000\u0140\u0142\u0003d2\u0000\u0141"+
		"\u013f\u0001\u0000\u0000\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0141"+
		"\u0142\u0001\u0000\u0000\u0000\u0142+\u0001\u0000\u0000\u0000\u0143\u0144"+
		"\u0005/\u0000\u0000\u0144\u0145\u0005\u001e\u0000\u0000\u0145\u014f\u0003"+
		"\u0014\n\u0000\u0146\u014a\u0005\u001c\u0000\u0000\u0147\u0149\u0003\u0014"+
		"\n\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0149\u014c\u0001\u0000\u0000"+
		"\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000"+
		"\u0000\u014b\u014e\u0001\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000"+
		"\u0000\u014d\u0146\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000"+
		"\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000"+
		"\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000"+
		"\u0000\u0152\u0154\u0005\u001f\u0000\u0000\u0153\u0155\u0005\u001b\u0000"+
		"\u0000\u0154\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000"+
		"\u0000\u0155-\u0001\u0000\u0000\u0000\u0156\u0158\u0003>\u001f\u0000\u0157"+
		"\u0156\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0001\u0000\u0000\u0000\u0159\u015a\u0005)\u0000\u0000\u015a\u0160"+
		"\u0003D\"\u0000\u015b\u015d\u0005\u001e\u0000\u0000\u015c\u015e\u0003"+
		"L&\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000"+
		"\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0161\u0005\u001f\u0000"+
		"\u0000\u0160\u015b\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000"+
		"\u0000\u0161\u0167\u0001\u0000\u0000\u0000\u0162\u0164\u0003>\u001f\u0000"+
		"\u0163\u0162\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000"+
		"\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u0167\u0005)\u0000\u0000\u0166"+
		"\u0157\u0001\u0000\u0000\u0000\u0166\u0163\u0001\u0000\u0000\u0000\u0167"+
		"/\u0001\u0000\u0000\u0000\u0168\u016b\u00032\u0019\u0000\u0169\u016c\u0005"+
		"\u001b\u0000\u0000\u016a\u016c\u0003d2\u0000\u016b\u0169\u0001\u0000\u0000"+
		"\u0000\u016b\u016a\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000"+
		"\u0000\u016c1\u0001\u0000\u0000\u0000\u016d\u016f\u0003>\u001f\u0000\u016e"+
		"\u016d\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f"+
		"\u0178\u0001\u0000\u0000\u0000\u0170\u0171\u00036\u001b\u0000\u0171\u0172"+
		"\u0005\n\u0000\u0000\u0172\u0174\u0001\u0000\u0000\u0000\u0173\u0170\u0001"+
		"\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0175\u0001"+
		"\u0000\u0000\u0000\u0175\u0176\u00038\u001c\u0000\u0176\u0177\u00058\u0000"+
		"\u0000\u0177\u0179\u0001\u0000\u0000\u0000\u0178\u0173\u0001\u0000\u0000"+
		"\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000"+
		"\u0000\u017a\u0185\u00034\u001a\u0000\u017b\u0185\u0003>\u001f\u0000\u017c"+
		"\u017d\u00036\u001b\u0000\u017d\u017e\u0005\n\u0000\u0000\u017e\u0180"+
		"\u0001\u0000\u0000\u0000\u017f\u017c\u0001\u0000\u0000\u0000\u017f\u0180"+
		"\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0182"+
		"\u00038\u001c\u0000\u0182\u0183\u00058\u0000\u0000\u0183\u0185\u0001\u0000"+
		"\u0000\u0000\u0184\u016e\u0001\u0000\u0000\u0000\u0184\u017b\u0001\u0000"+
		"\u0000\u0000\u0184\u017f\u0001\u0000\u0000\u0000\u01853\u0001\u0000\u0000"+
		"\u0000\u0186\u018b\u0003:\u001d\u0000\u0187\u0188\u00058\u0000\u0000\u0188"+
		"\u018a\u0003:\u001d\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u018a\u018d"+
		"\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018b\u018c"+
		"\u0001\u0000\u0000\u0000\u018c5\u0001\u0000\u0000\u0000\u018d\u018b\u0001"+
		"\u0000\u0000\u0000\u018e\u018f\u0007\u0000\u0000\u0000\u018f7\u0001\u0000"+
		"\u0000\u0000\u0190\u0191\u0007\u0000\u0000\u0000\u01919\u0001\u0000\u0000"+
		"\u0000\u0192\u0194\u0003J%\u0000\u0193\u0195\u0003<\u001e\u0000\u0194"+
		"\u0193\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000\u0000\u0000\u0195"+
		";\u0001\u0000\u0000\u0000\u0196\u0198\u0005\u001e\u0000\u0000\u0197\u0199"+
		"\u0003L&\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198\u0199\u0001\u0000"+
		"\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0005\u001f"+
		"\u0000\u0000\u019b=\u0001\u0000\u0000\u0000\u019c\u019e\u0003F#\u0000"+
		"\u019d\u019c\u0001\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000"+
		"\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01a0\u0003H$\u0000\u01a0\u01a1"+
		"\u0005\u001d\u0000\u0000\u01a1?\u0001\u0000\u0000\u0000\u01a2\u01a3\u0003"+
		"6\u001b\u0000\u01a3\u01a4\u0005\n\u0000\u0000\u01a4\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a5\u01a2\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7\u01a8\u00038\u001c"+
		"\u0000\u01a8\u01aa\u0005\u0007\u0000\u0000\u01a9\u01ab\u0003B!\u0000\u01aa"+
		"\u01a9\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab"+
		"\u01b2\u0001\u0000\u0000\u0000\u01ac\u01ad\u00036\u001b\u0000\u01ad\u01af"+
		"\u0007\u0003\u0000\u0000\u01ae\u01b0\u00038\u001c\u0000\u01af\u01ae\u0001"+
		"\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b1\u01a5\u0001\u0000\u0000\u0000\u01b1\u01ac\u0001"+
		"\u0000\u0000\u0000\u01b2A\u0001\u0000\u0000\u0000\u01b3\u01b4\u0005C\u0000"+
		"\u0000\u01b4C\u0001\u0000\u0000\u0000\u01b5\u01b6\u0007\u0000\u0000\u0000"+
		"\u01b6E\u0001\u0000\u0000\u0000\u01b7\u01b8\u0007\u0000\u0000\u0000\u01b8"+
		"G\u0001\u0000\u0000\u0000\u01b9\u01c5\u0003j5\u0000\u01ba\u01bf\u0005"+
		"9\u0000\u0000\u01bb\u01bc\u0005\u001c\u0000\u0000\u01bc\u01be\u00059\u0000"+
		"\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01be\u01c1\u0001\u0000\u0000"+
		"\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000"+
		"\u0000\u01c0\u01c5\u0001\u0000\u0000\u0000\u01c1\u01bf\u0001\u0000\u0000"+
		"\u0000\u01c2\u01c5\u0005>\u0000\u0000\u01c3\u01c5\u0005)\u0000\u0000\u01c4"+
		"\u01b9\u0001\u0000\u0000\u0000\u01c4\u01ba\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c3\u0001\u0000\u0000\u0000\u01c5"+
		"I\u0001\u0000\u0000\u0000\u01c6\u01c7\u0007\u0000\u0000\u0000\u01c7K\u0001"+
		"\u0000\u0000\u0000\u01c8\u01cd\u0003N\'\u0000\u01c9\u01ca\u0005\u001c"+
		"\u0000\u0000\u01ca\u01cc\u0003N\'\u0000\u01cb\u01c9\u0001\u0000\u0000"+
		"\u0000\u01cc\u01cf\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01d1\u0001\u0000\u0000"+
		"\u0000\u01cf\u01cd\u0001\u0000\u0000\u0000\u01d0\u01d2\u0005\u001c\u0000"+
		"\u0000\u01d1\u01d0\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000\u0000"+
		"\u0000\u01d2M\u0001\u0000\u0000\u0000\u01d3\u01d7\u0003P(\u0000\u01d4"+
		"\u01d7\u0003R)\u0000\u01d5\u01d7\u0003h4\u0000\u01d6\u01d3\u0001\u0000"+
		"\u0000\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d5\u0001\u0000"+
		"\u0000\u0000\u01d7O\u0001\u0000\u0000\u0000\u01d8\u01d9\u00059\u0000\u0000"+
		"\u01d9\u01db\u0005\u001d\u0000\u0000\u01da\u01dc\u0003h4\u0000\u01db\u01da"+
		"\u0001\u0000\u0000\u0000\u01db\u01dc\u0001\u0000\u0000\u0000\u01dcQ\u0001"+
		"\u0000\u0000\u0000\u01dd\u01de\u0003F#\u0000\u01de\u01df\u00059\u0000"+
		"\u0000\u01dfS\u0001\u0000\u0000\u0000\u01e0\u01e4\u0003V+\u0000\u01e1"+
		"\u01e3\u0003X,\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000\u01e3\u01e6\u0001"+
		"\u0000\u0000\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000\u01e4\u01e5\u0001"+
		"\u0000\u0000\u0000\u01e5\u01e8\u0001\u0000\u0000\u0000\u01e6\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e9\u0003Z-\u0000\u01e8\u01e7\u0001\u0000\u0000"+
		"\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000\u01e9U\u0001\u0000\u0000\u0000"+
		"\u01ea\u01eb\u00051\u0000\u0000\u01eb\u01ec\u0003d2\u0000\u01ecW\u0001"+
		"\u0000\u0000\u0000\u01ed\u01ef\u00052\u0000\u0000\u01ee\u01f0\u0003<\u001e"+
		"\u0000\u01ef\u01ee\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1\u01f2\u0003d2\u0000\u01f2"+
		"Y\u0001\u0000\u0000\u0000\u01f3\u01f4\u00053\u0000\u0000\u01f4\u01f5\u0003"+
		"d2\u0000\u01f5[\u0001\u0000\u0000\u0000\u01f6\u01fa\u0003^/\u0000\u01f7"+
		"\u01f9\u0003`0\u0000\u01f8\u01f7\u0001\u0000\u0000\u0000\u01f9\u01fc\u0001"+
		"\u0000\u0000\u0000\u01fa\u01f8\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fe\u0001\u0000\u0000\u0000\u01fc\u01fa\u0001"+
		"\u0000\u0000\u0000\u01fd\u01ff\u0003b1\u0000\u01fe\u01fd\u0001\u0000\u0000"+
		"\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff]\u0001\u0000\u0000\u0000"+
		"\u0200\u0201\u0005%\u0000\u0000\u0201\u0202\u0003l6\u0000\u0202\u0203"+
		"\u0003d2\u0000\u0203_\u0001\u0000\u0000\u0000\u0204\u0205\u0005&\u0000"+
		"\u0000\u0205\u0206\u0005%\u0000\u0000\u0206\u0207\u0003l6\u0000\u0207"+
		"\u0208\u0003d2\u0000\u0208a\u0001\u0000\u0000\u0000\u0209\u020a\u0005"+
		"&\u0000\u0000\u020a\u020b\u0003d2\u0000\u020bc\u0001\u0000\u0000\u0000"+
		"\u020c\u020e\u0005 \u0000\u0000\u020d\u020f\u0003\u0018\f\u0000\u020e"+
		"\u020d\u0001\u0000\u0000\u0000\u020e\u020f\u0001\u0000\u0000\u0000\u020f"+
		"\u0210\u0001\u0000\u0000\u0000\u0210\u0211\u0005!\u0000\u0000\u0211e\u0001"+
		"\u0000\u0000\u0000\u0212\u0213\u0005\'\u0000\u0000\u0213\u0214\u0003l"+
		"6\u0000\u0214\u0215\u0003d2\u0000\u0215\u021a\u0001\u0000\u0000\u0000"+
		"\u0216\u0217\u0005\'\u0000\u0000\u0217\u021a\u0003l6\u0000\u0218\u021a"+
		"\u0005\'\u0000\u0000\u0219\u0212\u0001\u0000\u0000\u0000\u0219\u0216\u0001"+
		"\u0000\u0000\u0000\u0219\u0218\u0001\u0000\u0000\u0000\u021ag\u0001\u0000"+
		"\u0000\u0000\u021b\u021c\u00064\uffff\uffff\u0000\u021c\u0230\u0003j5"+
		"\u0000\u021d\u021e\u0005\u0015\u0000\u0000\u021e\u0230\u0003h4\r\u021f"+
		"\u0220\u0005\u001a\u0000\u0000\u0220\u0230\u0003h4\f\u0221\u0222\u0003"+
		"8\u001c\u0000\u0222\u0223\u00058\u0000\u0000\u0223\u0225\u0001\u0000\u0000"+
		"\u0000\u0224\u0221\u0001\u0000\u0000\u0000\u0224\u0225\u0001\u0000\u0000"+
		"\u0000\u0225\u0226\u0001\u0000\u0000\u0000\u0226\u0230\u00034\u001a\u0000"+
		"\u0227\u0230\u0003*\u0015\u0000\u0228\u0229\u0005\u001e\u0000\u0000\u0229"+
		"\u022a\u0003h4\u0000\u022a\u022b\u0005\u001f\u0000\u0000\u022b\u0230\u0001"+
		"\u0000\u0000\u0000\u022c\u022d\u0003>\u001f\u0000\u022d\u022e\u0003h4"+
		"\u0001\u022e\u0230\u0001\u0000\u0000\u0000\u022f\u021b\u0001\u0000\u0000"+
		"\u0000\u022f\u021d\u0001\u0000\u0000\u0000\u022f\u021f\u0001\u0000\u0000"+
		"\u0000\u022f\u0224\u0001\u0000\u0000\u0000\u022f\u0227\u0001\u0000\u0000"+
		"\u0000\u022f\u0228\u0001\u0000\u0000\u0000\u022f\u022c\u0001\u0000\u0000"+
		"\u0000\u0230\u0248\u0001\u0000\u0000\u0000\u0231\u0232\n\u000b\u0000\u0000"+
		"\u0232\u0233\u0007\u0004\u0000\u0000\u0233\u0247\u0003h4\f\u0234\u0235"+
		"\n\n\u0000\u0000\u0235\u0236\u0007\u0005\u0000\u0000\u0236\u0247\u0003"+
		"h4\u000b\u0237\u0238\n\t\u0000\u0000\u0238\u0239\u0007\u0006\u0000\u0000"+
		"\u0239\u0247\u0003h4\n\u023a\u023b\n\b\u0000\u0000\u023b\u023c\u0007\u0007"+
		"\u0000\u0000\u023c\u0247\u0003h4\t\u023d\u023e\n\u0007\u0000\u0000\u023e"+
		"\u023f\u0005\r\u0000\u0000\u023f\u0247\u0003h4\b\u0240\u0241\n\u0006\u0000"+
		"\u0000\u0241\u0242\u0005\f\u0000\u0000\u0242\u0247\u0003h4\u0007\u0243"+
		"\u0244\n\u0005\u0000\u0000\u0244\u0245\u0005\u0014\u0000\u0000\u0245\u0247"+
		"\u0003h4\u0006\u0246\u0231\u0001\u0000\u0000\u0000\u0246\u0234\u0001\u0000"+
		"\u0000\u0000\u0246\u0237\u0001\u0000\u0000\u0000\u0246\u023a\u0001\u0000"+
		"\u0000\u0000\u0246\u023d\u0001\u0000\u0000\u0000\u0246\u0240\u0001\u0000"+
		"\u0000\u0000\u0246\u0243\u0001\u0000\u0000\u0000\u0247\u024a\u0001\u0000"+
		"\u0000\u0000\u0248\u0246\u0001\u0000\u0000\u0000\u0248\u0249\u0001\u0000"+
		"\u0000\u0000\u0249i\u0001\u0000\u0000\u0000\u024a\u0248\u0001\u0000\u0000"+
		"\u0000\u024b\u0253\u0007\b\u0000\u0000\u024c\u0253\u0005=\u0000\u0000"+
		"\u024d\u0253\u0005<\u0000\u0000\u024e\u0253\u0007\t\u0000\u0000\u024f"+
		"\u0253\u00059\u0000\u0000\u0250\u0253\u0005>\u0000\u0000\u0251\u0253\u0005"+
		"$\u0000\u0000\u0252\u024b\u0001\u0000\u0000\u0000\u0252\u024c\u0001\u0000"+
		"\u0000\u0000\u0252\u024d\u0001\u0000\u0000\u0000\u0252\u024e\u0001\u0000"+
		"\u0000\u0000\u0252\u024f\u0001\u0000\u0000\u0000\u0252\u0250\u0001\u0000"+
		"\u0000\u0000\u0252\u0251\u0001\u0000\u0000\u0000\u0253k\u0001\u0000\u0000"+
		"\u0000\u0254\u0255\u0005\u001e\u0000\u0000\u0255\u0256\u0003n7\u0000\u0256"+
		"\u0257\u0005\u001f\u0000\u0000\u0257\u025e\u0001\u0000\u0000\u0000\u0258"+
		"\u0259\u0005\u001e\u0000\u0000\u0259\u025e\u0003n7\u0000\u025a\u025b\u0005"+
		"\u001e\u0000\u0000\u025b\u025e\u0005\u001f\u0000\u0000\u025c\u025e\u0005"+
		"\u001e\u0000\u0000\u025d\u0254\u0001\u0000\u0000\u0000\u025d\u0258\u0001"+
		"\u0000\u0000\u0000\u025d\u025a\u0001\u0000\u0000\u0000\u025d\u025c\u0001"+
		"\u0000\u0000\u0000\u025em\u0001\u0000\u0000\u0000\u025f\u0263\u0003j5"+
		"\u0000\u0260\u0263\u0003h4\u0000\u0261\u0263\u0003p8\u0000\u0262\u025f"+
		"\u0001\u0000\u0000\u0000\u0262\u0260\u0001\u0000\u0000\u0000\u0262\u0261"+
		"\u0001\u0000\u0000\u0000\u0263o\u0001\u0000\u0000\u0000\u0264\u0265\u0005"+
		"9\u0000\u0000\u0265\u0266\u00054\u0000\u0000\u0266\u0267\u00059\u0000"+
		"\u0000\u0267q\u0001\u0000\u0000\u0000Xsw}\u0080\u0085\u0089\u008c\u0090"+
		"\u0092\u0096\u0098\u009c\u00a0\u00a6\u00ac\u00b1\u00b3\u00b8\u00bb\u00be"+
		"\u00c3\u00c6\u00ca\u00cd\u00d0\u00d4\u00dd\u00e1\u00e3\u00e8\u00f3\u00f7"+
		"\u00fa\u00ff\u0101\u0112\u0119\u011e\u0123\u0128\u012b\u012f\u0134\u0137"+
		"\u013c\u0141\u014a\u014f\u0154\u0157\u015d\u0160\u0163\u0166\u016b\u016e"+
		"\u0173\u0178\u017f\u0184\u018b\u0194\u0198\u019d\u01a5\u01aa\u01af\u01b1"+
		"\u01bf\u01c4\u01cd\u01d1\u01d6\u01db\u01e4\u01e8\u01ef\u01fa\u01fe\u020e"+
		"\u0219\u0224\u022f\u0246\u0248\u0252\u025d\u0262";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}