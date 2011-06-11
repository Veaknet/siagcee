// $ANTLR 3.2 Sep 23, 2009 12:02:23 Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g 2011-06-11 16:08:56

package com.siagcee.logic;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class SigeceneGramParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "NEWLINE", "BOOLEAN", "STRING", "DOUBLE", "WS", "'='", "'obtener'", "'si'", "'('", "';'", "')'", "'Y'", "'O'", "'NO'", "'>'", "'>='", "'<'", "'<='", "'=='", "'<>'", "'+'", "'-'", "'*'", "'/'", "'promedio'", "'sumatoria'", "'contar'", "'max'", "'min'"
    };
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int ID=4;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int WS=9;
    public static final int BOOLEAN=6;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int NEWLINE=5;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int DOUBLE=8;
    public static final int STRING=7;

    // delegates
    // delegators


        public SigeceneGramParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public SigeceneGramParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[44+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return SigeceneGramParser.tokenNames; }
    public String getGrammarFileName() { return "Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g"; }


    	public void displayRecognitionError(String[] tokenNames, RecognitionException e){
    		String hdr = getErrorHeader(e);
    		String msg = getErrorMessage(e, tokenNames);
    		EstudioPerso.getInstance().addError("Error encontrado: "+hdr);
    	}

    	protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
    		throw new MismatchedTokenException(ttype, input);
    	}

    	public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
    		throw e;
    	}


    public static class prog_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "prog"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:57:1: prog : ( stat )+ ;
    public final SigeceneGramParser.prog_return prog() throws RecognitionException {
        SigeceneGramParser.prog_return retval = new SigeceneGramParser.prog_return();
        retval.start = input.LT(1);
        int prog_StartIndex = input.index();
        CommonTree root_0 = null;

        SigeceneGramParser.stat_return stat1 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:57:6: ( ( stat )+ )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:57:8: ( stat )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:57:8: ( stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=ID && LA1_0<=NEWLINE)||(LA1_0>=11 && LA1_0<=12)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:0:0: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_prog73);
            	    stat1=stat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, prog_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "prog"

    public static class stat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stat"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:1: stat : ( ID '=' proposicion -> ^( '=' ID proposicion ) | condicional | 'obtener' proposicion -> ^( 'obtener' proposicion ) | NEWLINE ->);
    public final SigeceneGramParser.stat_return stat() throws RecognitionException {
        SigeceneGramParser.stat_return retval = new SigeceneGramParser.stat_return();
        retval.start = input.LT(1);
        int stat_StartIndex = input.index();
        CommonTree root_0 = null;

        Token ID2=null;
        Token char_literal3=null;
        Token string_literal6=null;
        Token NEWLINE8=null;
        SigeceneGramParser.proposicion_return proposicion4 = null;

        SigeceneGramParser.condicional_return condicional5 = null;

        SigeceneGramParser.proposicion_return proposicion7 = null;


        CommonTree ID2_tree=null;
        CommonTree char_literal3_tree=null;
        CommonTree string_literal6_tree=null;
        CommonTree NEWLINE8_tree=null;
        RewriteRuleTokenStream stream_10=new RewriteRuleTokenStream(adaptor,"token 10");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_11=new RewriteRuleTokenStream(adaptor,"token 11");
        RewriteRuleSubtreeStream stream_proposicion=new RewriteRuleSubtreeStream(adaptor,"rule proposicion");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:6: ( ID '=' proposicion -> ^( '=' ID proposicion ) | condicional | 'obtener' proposicion -> ^( 'obtener' proposicion ) | NEWLINE ->)
            int alt2=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 11:
                {
                alt2=3;
                }
                break;
            case NEWLINE:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:8: ID '=' proposicion
                    {
                    ID2=(Token)match(input,ID,FOLLOW_ID_in_stat85); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(ID2);

                    char_literal3=(Token)match(input,10,FOLLOW_10_in_stat87); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_10.add(char_literal3);

                    pushFollow(FOLLOW_proposicion_in_stat89);
                    proposicion4=proposicion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_proposicion.add(proposicion4.getTree());


                    // AST REWRITE
                    // elements: ID, 10, proposicion
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 60:27: -> ^( '=' ID proposicion )
                    {
                        // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:30: ^( '=' ID proposicion )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_10.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_proposicion.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:61:4: condicional
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_condicional_in_stat104);
                    condicional5=condicional();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, condicional5.getTree());

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:62:4: 'obtener' proposicion
                    {
                    string_literal6=(Token)match(input,11,FOLLOW_11_in_stat109); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_11.add(string_literal6);

                    pushFollow(FOLLOW_proposicion_in_stat111);
                    proposicion7=proposicion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_proposicion.add(proposicion7.getTree());


                    // AST REWRITE
                    // elements: proposicion, 11
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 62:26: -> ^( 'obtener' proposicion )
                    {
                        // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:62:29: ^( 'obtener' proposicion )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_11.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_proposicion.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:63:4: NEWLINE
                    {
                    NEWLINE8=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat124); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE8);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 63:14: ->
                    {
                        root_0 = null;
                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, stat_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "stat"

    public static class condicional_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "condicional"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:66:1: condicional : 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')' ;
    public final SigeceneGramParser.condicional_return condicional() throws RecognitionException {
        SigeceneGramParser.condicional_return retval = new SigeceneGramParser.condicional_return();
        retval.start = input.LT(1);
        int condicional_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal9=null;
        Token char_literal10=null;
        Token char_literal11=null;
        Token char_literal13=null;
        SigeceneGramParser.proposicion_return cond = null;

        SigeceneGramParser.stat_return stat12 = null;


        CommonTree string_literal9_tree=null;
        CommonTree char_literal10_tree=null;
        CommonTree char_literal11_tree=null;
        CommonTree char_literal13_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:67:2: ( 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:67:4: 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal9=(Token)match(input,12,FOLLOW_12_in_condicional139); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal9_tree = (CommonTree)adaptor.create(string_literal9);
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal9_tree, root_0);
            }
            char_literal10=(Token)match(input,13,FOLLOW_13_in_condicional142); if (state.failed) return retval;
            pushFollow(FOLLOW_proposicion_in_condicional147);
            cond=proposicion();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());
            char_literal11=(Token)match(input,14,FOLLOW_14_in_condicional149); if (state.failed) return retval;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:67:37: ( ( stat )+ )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:67:38: ( stat )+
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:67:38: ( stat )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=ID && LA3_0<=NEWLINE)||(LA3_0>=11 && LA3_0<=12)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:0:0: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_condicional153);
            	    stat12=stat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat12.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            char_literal13=(Token)match(input,15,FOLLOW_15_in_condicional156); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, condicional_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "condicional"

    public static class proposicion_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "proposicion"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:70:1: proposicion : ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )* ;
    public final SigeceneGramParser.proposicion_return proposicion() throws RecognitionException {
        SigeceneGramParser.proposicion_return retval = new SigeceneGramParser.proposicion_return();
        retval.start = input.LT(1);
        int proposicion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token NEWLINE14=null;
        Token char_literal16=null;
        Token char_literal17=null;
        SigeceneGramParser.negacion_return negacion15 = null;

        SigeceneGramParser.negacion_return negacion18 = null;


        CommonTree NEWLINE14_tree=null;
        CommonTree char_literal16_tree=null;
        CommonTree char_literal17_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:2: ( ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:4: ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )*
            {
            root_0 = (CommonTree)adaptor.nil();

            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:4: ( ( NEWLINE )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:5: ( NEWLINE )*
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:5: ( NEWLINE )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==NEWLINE) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:6: NEWLINE
            	    {
            	    NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_proposicion170); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE14_tree = (CommonTree)adaptor.create(NEWLINE14);
            	    adaptor.addChild(root_0, NEWLINE14_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            pushFollow(FOLLOW_negacion_in_proposicion176);
            negacion15=negacion();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, negacion15.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:27: ( ( 'Y' | 'O' ) negacion )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=16 && LA6_0<=17)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:28: ( 'Y' | 'O' ) negacion
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:28: ( 'Y' | 'O' )
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==16) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0==17) ) {
            	        alt5=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 5, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:29: 'Y'
            	            {
            	            char_literal16=(Token)match(input,16,FOLLOW_16_in_proposicion180); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal16_tree = (CommonTree)adaptor.create(char_literal16);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal16_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:71:34: 'O'
            	            {
            	            char_literal17=(Token)match(input,17,FOLLOW_17_in_proposicion183); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal17_tree = (CommonTree)adaptor.create(char_literal17);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal17_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_negacion_in_proposicion187);
            	    negacion18=negacion();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, negacion18.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, proposicion_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "proposicion"

    public static class negacion_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "negacion"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:74:1: negacion : ( 'NO' comparacion | comparacion );
    public final SigeceneGramParser.negacion_return negacion() throws RecognitionException {
        SigeceneGramParser.negacion_return retval = new SigeceneGramParser.negacion_return();
        retval.start = input.LT(1);
        int negacion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal19=null;
        SigeceneGramParser.comparacion_return comparacion20 = null;

        SigeceneGramParser.comparacion_return comparacion21 = null;


        CommonTree string_literal19_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:74:9: ( 'NO' comparacion | comparacion )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            else if ( (LA7_0==ID||(LA7_0>=BOOLEAN && LA7_0<=DOUBLE)||LA7_0==13||(LA7_0>=29 && LA7_0<=33)) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:74:11: 'NO' comparacion
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal19=(Token)match(input,18,FOLLOW_18_in_negacion198); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal19_tree = (CommonTree)adaptor.create(string_literal19);
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal19_tree, root_0);
                    }
                    pushFollow(FOLLOW_comparacion_in_negacion201);
                    comparacion20=comparacion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparacion20.getTree());

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:75:4: comparacion
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_comparacion_in_negacion206);
                    comparacion21=comparacion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparacion21.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, negacion_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "negacion"

    public static class comparacion_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comparacion"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:78:1: comparacion : ( BOOLEAN | factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )? );
    public final SigeceneGramParser.comparacion_return comparacion() throws RecognitionException {
        SigeceneGramParser.comparacion_return retval = new SigeceneGramParser.comparacion_return();
        retval.start = input.LT(1);
        int comparacion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token BOOLEAN22=null;
        Token char_literal24=null;
        Token string_literal25=null;
        Token char_literal26=null;
        Token string_literal27=null;
        Token string_literal28=null;
        Token string_literal29=null;
        SigeceneGramParser.factorStr_return factorStr23 = null;

        SigeceneGramParser.factorStr_return factorStr30 = null;


        CommonTree BOOLEAN22_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree string_literal25_tree=null;
        CommonTree char_literal26_tree=null;
        CommonTree string_literal27_tree=null;
        CommonTree string_literal28_tree=null;
        CommonTree string_literal29_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:79:2: ( BOOLEAN | factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )? )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==BOOLEAN) ) {
                alt10=1;
            }
            else if ( (LA10_0==ID||(LA10_0>=STRING && LA10_0<=DOUBLE)||LA10_0==13||(LA10_0>=29 && LA10_0<=33)) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:79:4: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    BOOLEAN22=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_comparacion217); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLEAN22_tree = (CommonTree)adaptor.create(BOOLEAN22);
                    adaptor.addChild(root_0, BOOLEAN22_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:4: factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_factorStr_in_comparacion222);
                    factorStr23=factorStr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, factorStr23.getTree());
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:14: ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=19 && LA9_0<=24)) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:15: ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr
                            {
                            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:15: ( '>' | '>=' | '<' | '<=' | '==' | '<>' )
                            int alt8=6;
                            switch ( input.LA(1) ) {
                            case 19:
                                {
                                alt8=1;
                                }
                                break;
                            case 20:
                                {
                                alt8=2;
                                }
                                break;
                            case 21:
                                {
                                alt8=3;
                                }
                                break;
                            case 22:
                                {
                                alt8=4;
                                }
                                break;
                            case 23:
                                {
                                alt8=5;
                                }
                                break;
                            case 24:
                                {
                                alt8=6;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 0, input);

                                throw nvae;
                            }

                            switch (alt8) {
                                case 1 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:16: '>'
                                    {
                                    char_literal24=(Token)match(input,19,FOLLOW_19_in_comparacion226); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal24_tree = (CommonTree)adaptor.create(char_literal24);
                                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal24_tree, root_0);
                                    }

                                    }
                                    break;
                                case 2 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:21: '>='
                                    {
                                    string_literal25=(Token)match(input,20,FOLLOW_20_in_comparacion229); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal25_tree = (CommonTree)adaptor.create(string_literal25);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal25_tree, root_0);
                                    }

                                    }
                                    break;
                                case 3 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:27: '<'
                                    {
                                    char_literal26=(Token)match(input,21,FOLLOW_21_in_comparacion232); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal26_tree = (CommonTree)adaptor.create(char_literal26);
                                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal26_tree, root_0);
                                    }

                                    }
                                    break;
                                case 4 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:32: '<='
                                    {
                                    string_literal27=(Token)match(input,22,FOLLOW_22_in_comparacion235); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal27_tree = (CommonTree)adaptor.create(string_literal27);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal27_tree, root_0);
                                    }

                                    }
                                    break;
                                case 5 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:38: '=='
                                    {
                                    string_literal28=(Token)match(input,23,FOLLOW_23_in_comparacion238); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal28_tree = (CommonTree)adaptor.create(string_literal28);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal28_tree, root_0);
                                    }

                                    }
                                    break;
                                case 6 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:44: '<>'
                                    {
                                    string_literal29=(Token)match(input,24,FOLLOW_24_in_comparacion241); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal29_tree = (CommonTree)adaptor.create(string_literal29);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal29_tree, root_0);
                                    }

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_factorStr_in_comparacion245);
                            factorStr30=factorStr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, factorStr30.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, comparacion_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "comparacion"

    public static class factorStr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factorStr"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:83:1: factorStr : ( STRING | expr );
    public final SigeceneGramParser.factorStr_return factorStr() throws RecognitionException {
        SigeceneGramParser.factorStr_return retval = new SigeceneGramParser.factorStr_return();
        retval.start = input.LT(1);
        int factorStr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token STRING31=null;
        SigeceneGramParser.expr_return expr32 = null;


        CommonTree STRING31_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:84:2: ( STRING | expr )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==STRING) ) {
                alt11=1;
            }
            else if ( (LA11_0==ID||LA11_0==DOUBLE||LA11_0==13||(LA11_0>=29 && LA11_0<=33)) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:84:4: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING31=(Token)match(input,STRING,FOLLOW_STRING_in_factorStr258); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING31_tree = (CommonTree)adaptor.create(STRING31);
                    adaptor.addChild(root_0, STRING31_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:85:4: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_factorStr263);
                    expr32=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr32.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, factorStr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "factorStr"

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:1: expr : producto ( ( '+' | '-' ) producto )* ;
    public final SigeceneGramParser.expr_return expr() throws RecognitionException {
        SigeceneGramParser.expr_return retval = new SigeceneGramParser.expr_return();
        retval.start = input.LT(1);
        int expr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token char_literal34=null;
        Token char_literal35=null;
        SigeceneGramParser.producto_return producto33 = null;

        SigeceneGramParser.producto_return producto36 = null;


        CommonTree char_literal34_tree=null;
        CommonTree char_literal35_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:6: ( producto ( ( '+' | '-' ) producto )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:8: producto ( ( '+' | '-' ) producto )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_producto_in_expr273);
            producto33=producto();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, producto33.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:17: ( ( '+' | '-' ) producto )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=25 && LA13_0<=26)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:18: ( '+' | '-' ) producto
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:18: ( '+' | '-' )
            	    int alt12=2;
            	    int LA12_0 = input.LA(1);

            	    if ( (LA12_0==25) ) {
            	        alt12=1;
            	    }
            	    else if ( (LA12_0==26) ) {
            	        alt12=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 12, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt12) {
            	        case 1 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:19: '+'
            	            {
            	            char_literal34=(Token)match(input,25,FOLLOW_25_in_expr277); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal34_tree = (CommonTree)adaptor.create(char_literal34);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal34_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:88:24: '-'
            	            {
            	            char_literal35=(Token)match(input,26,FOLLOW_26_in_expr280); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal35_tree = (CommonTree)adaptor.create(char_literal35);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal35_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_producto_in_expr284);
            	    producto36=producto();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, producto36.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, expr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class producto_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "producto"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:1: producto : factor ( ( '*' | '/' ) factor )* ;
    public final SigeceneGramParser.producto_return producto() throws RecognitionException {
        SigeceneGramParser.producto_return retval = new SigeceneGramParser.producto_return();
        retval.start = input.LT(1);
        int producto_StartIndex = input.index();
        CommonTree root_0 = null;

        Token char_literal38=null;
        Token char_literal39=null;
        SigeceneGramParser.factor_return factor37 = null;

        SigeceneGramParser.factor_return factor40 = null;


        CommonTree char_literal38_tree=null;
        CommonTree char_literal39_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:9: ( factor ( ( '*' | '/' ) factor )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:11: factor ( ( '*' | '/' ) factor )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_factor_in_producto295);
            factor37=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, factor37.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:18: ( ( '*' | '/' ) factor )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=27 && LA15_0<=28)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:19: ( '*' | '/' ) factor
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:19: ( '*' | '/' )
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==27) ) {
            	        alt14=1;
            	    }
            	    else if ( (LA14_0==28) ) {
            	        alt14=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 14, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:20: '*'
            	            {
            	            char_literal38=(Token)match(input,27,FOLLOW_27_in_producto299); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal38_tree = (CommonTree)adaptor.create(char_literal38);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal38_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:91:25: '/'
            	            {
            	            char_literal39=(Token)match(input,28,FOLLOW_28_in_producto302); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal39_tree = (CommonTree)adaptor.create(char_literal39);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal39_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_factor_in_producto306);
            	    factor40=factor();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, factor40.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, producto_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "producto"

    public static class factor_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:94:1: factor : ( DOUBLE | ID | funcPredefinidas | '(' proposicion ')' );
    public final SigeceneGramParser.factor_return factor() throws RecognitionException {
        SigeceneGramParser.factor_return retval = new SigeceneGramParser.factor_return();
        retval.start = input.LT(1);
        int factor_StartIndex = input.index();
        CommonTree root_0 = null;

        Token DOUBLE41=null;
        Token ID42=null;
        Token char_literal44=null;
        Token char_literal46=null;
        SigeceneGramParser.funcPredefinidas_return funcPredefinidas43 = null;

        SigeceneGramParser.proposicion_return proposicion45 = null;


        CommonTree DOUBLE41_tree=null;
        CommonTree ID42_tree=null;
        CommonTree char_literal44_tree=null;
        CommonTree char_literal46_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:94:8: ( DOUBLE | ID | funcPredefinidas | '(' proposicion ')' )
            int alt16=4;
            switch ( input.LA(1) ) {
            case DOUBLE:
                {
                alt16=1;
                }
                break;
            case ID:
                {
                alt16=2;
                }
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
                {
                alt16=3;
                }
                break;
            case 13:
                {
                alt16=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:94:10: DOUBLE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    DOUBLE41=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_factor318); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLE41_tree = (CommonTree)adaptor.create(DOUBLE41);
                    adaptor.addChild(root_0, DOUBLE41_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:95:4: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ID42=(Token)match(input,ID,FOLLOW_ID_in_factor323); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID42_tree = (CommonTree)adaptor.create(ID42);
                    adaptor.addChild(root_0, ID42_tree);
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:96:4: funcPredefinidas
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_funcPredefinidas_in_factor328);
                    funcPredefinidas43=funcPredefinidas();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcPredefinidas43.getTree());

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:97:4: '(' proposicion ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal44=(Token)match(input,13,FOLLOW_13_in_factor333); if (state.failed) return retval;
                    pushFollow(FOLLOW_proposicion_in_factor336);
                    proposicion45=proposicion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, proposicion45.getTree());
                    char_literal46=(Token)match(input,15,FOLLOW_15_in_factor338); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, factor_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class funcPredefinidas_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "funcPredefinidas"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:101:1: funcPredefinidas : ( promedio | sumatoria | contar | max | min );
    public final SigeceneGramParser.funcPredefinidas_return funcPredefinidas() throws RecognitionException {
        SigeceneGramParser.funcPredefinidas_return retval = new SigeceneGramParser.funcPredefinidas_return();
        retval.start = input.LT(1);
        int funcPredefinidas_StartIndex = input.index();
        CommonTree root_0 = null;

        SigeceneGramParser.promedio_return promedio47 = null;

        SigeceneGramParser.sumatoria_return sumatoria48 = null;

        SigeceneGramParser.contar_return contar49 = null;

        SigeceneGramParser.max_return max50 = null;

        SigeceneGramParser.min_return min51 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:102:2: ( promedio | sumatoria | contar | max | min )
            int alt17=5;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt17=1;
                }
                break;
            case 30:
                {
                alt17=2;
                }
                break;
            case 31:
                {
                alt17=3;
                }
                break;
            case 32:
                {
                alt17=4;
                }
                break;
            case 33:
                {
                alt17=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:102:4: promedio
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_promedio_in_funcPredefinidas351);
                    promedio47=promedio();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, promedio47.getTree());

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:103:4: sumatoria
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_sumatoria_in_funcPredefinidas356);
                    sumatoria48=sumatoria();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sumatoria48.getTree());

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:104:4: contar
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_contar_in_funcPredefinidas361);
                    contar49=contar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, contar49.getTree());

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:105:4: max
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_max_in_funcPredefinidas366);
                    max50=max();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, max50.getTree());

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:106:4: min
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_min_in_funcPredefinidas371);
                    min51=min();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, min51.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, funcPredefinidas_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "funcPredefinidas"

    public static class promedio_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "promedio"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:109:1: promedio : 'promedio' '(' ID ')' -> ^( 'promedio' ID ) ;
    public final SigeceneGramParser.promedio_return promedio() throws RecognitionException {
        SigeceneGramParser.promedio_return retval = new SigeceneGramParser.promedio_return();
        retval.start = input.LT(1);
        int promedio_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal52=null;
        Token char_literal53=null;
        Token ID54=null;
        Token char_literal55=null;

        CommonTree string_literal52_tree=null;
        CommonTree char_literal53_tree=null;
        CommonTree ID54_tree=null;
        CommonTree char_literal55_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");
        RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:109:9: ( 'promedio' '(' ID ')' -> ^( 'promedio' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:109:11: 'promedio' '(' ID ')'
            {
            string_literal52=(Token)match(input,29,FOLLOW_29_in_promedio381); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_29.add(string_literal52);

            char_literal53=(Token)match(input,13,FOLLOW_13_in_promedio383); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_13.add(char_literal53);

            ID54=(Token)match(input,ID,FOLLOW_ID_in_promedio385); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID54);

            char_literal55=(Token)match(input,15,FOLLOW_15_in_promedio387); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal55);



            // AST REWRITE
            // elements: ID, 29
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 109:33: -> ^( 'promedio' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:109:36: ^( 'promedio' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_29.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, promedio_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "promedio"

    public static class sumatoria_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sumatoria"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:112:1: sumatoria : 'sumatoria' '(' ID ')' -> ^( 'sumatoria' ID ) ;
    public final SigeceneGramParser.sumatoria_return sumatoria() throws RecognitionException {
        SigeceneGramParser.sumatoria_return retval = new SigeceneGramParser.sumatoria_return();
        retval.start = input.LT(1);
        int sumatoria_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal56=null;
        Token char_literal57=null;
        Token ID58=null;
        Token char_literal59=null;

        CommonTree string_literal56_tree=null;
        CommonTree char_literal57_tree=null;
        CommonTree ID58_tree=null;
        CommonTree char_literal59_tree=null;
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:113:2: ( 'sumatoria' '(' ID ')' -> ^( 'sumatoria' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:113:4: 'sumatoria' '(' ID ')'
            {
            string_literal56=(Token)match(input,30,FOLLOW_30_in_sumatoria406); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_30.add(string_literal56);

            char_literal57=(Token)match(input,13,FOLLOW_13_in_sumatoria408); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_13.add(char_literal57);

            ID58=(Token)match(input,ID,FOLLOW_ID_in_sumatoria410); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID58);

            char_literal59=(Token)match(input,15,FOLLOW_15_in_sumatoria412); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal59);



            // AST REWRITE
            // elements: 30, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 113:27: -> ^( 'sumatoria' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:113:30: ^( 'sumatoria' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_30.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, sumatoria_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "sumatoria"

    public static class contar_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "contar"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:116:1: contar : 'contar' '(' ID ')' -> ^( 'contar' ID ) ;
    public final SigeceneGramParser.contar_return contar() throws RecognitionException {
        SigeceneGramParser.contar_return retval = new SigeceneGramParser.contar_return();
        retval.start = input.LT(1);
        int contar_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal60=null;
        Token char_literal61=null;
        Token ID62=null;
        Token char_literal63=null;

        CommonTree string_literal60_tree=null;
        CommonTree char_literal61_tree=null;
        CommonTree ID62_tree=null;
        CommonTree char_literal63_tree=null;
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:116:8: ( 'contar' '(' ID ')' -> ^( 'contar' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:116:10: 'contar' '(' ID ')'
            {
            string_literal60=(Token)match(input,31,FOLLOW_31_in_contar430); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_31.add(string_literal60);

            char_literal61=(Token)match(input,13,FOLLOW_13_in_contar432); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_13.add(char_literal61);

            ID62=(Token)match(input,ID,FOLLOW_ID_in_contar434); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID62);

            char_literal63=(Token)match(input,15,FOLLOW_15_in_contar436); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal63);



            // AST REWRITE
            // elements: 31, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 116:30: -> ^( 'contar' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:116:33: ^( 'contar' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_31.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, contar_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "contar"

    public static class max_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "max"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:119:1: max : 'max' '(' ID ')' -> ^( 'max' ID ) ;
    public final SigeceneGramParser.max_return max() throws RecognitionException {
        SigeceneGramParser.max_return retval = new SigeceneGramParser.max_return();
        retval.start = input.LT(1);
        int max_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal64=null;
        Token char_literal65=null;
        Token ID66=null;
        Token char_literal67=null;

        CommonTree string_literal64_tree=null;
        CommonTree char_literal65_tree=null;
        CommonTree ID66_tree=null;
        CommonTree char_literal67_tree=null;
        RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:119:5: ( 'max' '(' ID ')' -> ^( 'max' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:119:7: 'max' '(' ID ')'
            {
            string_literal64=(Token)match(input,32,FOLLOW_32_in_max454); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_32.add(string_literal64);

            char_literal65=(Token)match(input,13,FOLLOW_13_in_max456); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_13.add(char_literal65);

            ID66=(Token)match(input,ID,FOLLOW_ID_in_max458); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID66);

            char_literal67=(Token)match(input,15,FOLLOW_15_in_max460); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal67);



            // AST REWRITE
            // elements: ID, 32
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 119:24: -> ^( 'max' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:119:27: ^( 'max' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_32.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, max_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "max"

    public static class min_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "min"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:122:1: min : 'min' '(' ID ')' -> ^( 'min' ID ) ;
    public final SigeceneGramParser.min_return min() throws RecognitionException {
        SigeceneGramParser.min_return retval = new SigeceneGramParser.min_return();
        retval.start = input.LT(1);
        int min_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal68=null;
        Token char_literal69=null;
        Token ID70=null;
        Token char_literal71=null;

        CommonTree string_literal68_tree=null;
        CommonTree char_literal69_tree=null;
        CommonTree ID70_tree=null;
        CommonTree char_literal71_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_33=new RewriteRuleTokenStream(adaptor,"token 33");
        RewriteRuleTokenStream stream_13=new RewriteRuleTokenStream(adaptor,"token 13");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:122:5: ( 'min' '(' ID ')' -> ^( 'min' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:122:7: 'min' '(' ID ')'
            {
            string_literal68=(Token)match(input,33,FOLLOW_33_in_min478); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_33.add(string_literal68);

            char_literal69=(Token)match(input,13,FOLLOW_13_in_min480); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_13.add(char_literal69);

            ID70=(Token)match(input,ID,FOLLOW_ID_in_min482); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID70);

            char_literal71=(Token)match(input,15,FOLLOW_15_in_min484); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal71);



            // AST REWRITE
            // elements: 33, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 122:24: -> ^( 'min' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:122:27: ^( 'min' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_33.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        	catch (RecognitionException e) {
        		throw e;
        	}
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, min_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "min"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog73 = new BitSet(new long[]{0x0000000000001832L});
    public static final BitSet FOLLOW_ID_in_stat85 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_stat87 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_proposicion_in_stat89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condicional_in_stat104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat109 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_proposicion_in_stat111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_condicional139 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_condicional142 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_proposicion_in_condicional147 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_condicional149 = new BitSet(new long[]{0x0000000000009830L});
    public static final BitSet FOLLOW_stat_in_condicional153 = new BitSet(new long[]{0x0000000000009830L});
    public static final BitSet FOLLOW_15_in_condicional156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_proposicion170 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_negacion_in_proposicion176 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_16_in_proposicion180 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_17_in_proposicion183 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_negacion_in_proposicion187 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_18_in_negacion198 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_comparacion_in_negacion201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparacion_in_negacion206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_comparacion217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factorStr_in_comparacion222 = new BitSet(new long[]{0x0000000001F80002L});
    public static final BitSet FOLLOW_19_in_comparacion226 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_20_in_comparacion229 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_21_in_comparacion232 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_22_in_comparacion235 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_23_in_comparacion238 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_24_in_comparacion241 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_factorStr_in_comparacion245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_factorStr258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_factorStr263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_producto_in_expr273 = new BitSet(new long[]{0x0000000006000002L});
    public static final BitSet FOLLOW_25_in_expr277 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_26_in_expr280 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_producto_in_expr284 = new BitSet(new long[]{0x0000000006000002L});
    public static final BitSet FOLLOW_factor_in_producto295 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_27_in_producto299 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_28_in_producto302 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_factor_in_producto306 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_DOUBLE_in_factor318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcPredefinidas_in_factor328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_factor333 = new BitSet(new long[]{0x00000003E00421F0L});
    public static final BitSet FOLLOW_proposicion_in_factor336 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_factor338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_promedio_in_funcPredefinidas351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sumatoria_in_funcPredefinidas356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contar_in_funcPredefinidas361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_max_in_funcPredefinidas366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_min_in_funcPredefinidas371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_promedio381 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_promedio383 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_promedio385 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_promedio387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_sumatoria406 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_sumatoria408 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_sumatoria410 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_sumatoria412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_contar430 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_contar432 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_contar434 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_contar436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_max454 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_max456 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_max458 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_max460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_min478 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_min480 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_min482 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_min484 = new BitSet(new long[]{0x0000000000000002L});

}