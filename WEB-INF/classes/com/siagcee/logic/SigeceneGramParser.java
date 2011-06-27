// $ANTLR 3.2 Sep 23, 2009 12:02:23 Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g 2011-06-26 19:31:23

package com.siagcee.logic;

/**
 * Creado por Fabio Pereira.
 * Bajo la tutoria del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computacion.
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "NEWLINE", "BOOLEAN", "STRING", "DOUBLE", "WS", "'='", "'obtener'", "'pre'", "'{'", "'}'", "'post'", "'si'", "'('", "';'", "')'", "'Y'", "'O'", "'NO'", "'>'", "'>='", "'<'", "'<='", "'=='", "'<>'", "'+'", "'-'", "'*'", "'/'", "'promedio'", "'sumatoria'", "'contar'", "'max'", "'min'", "'redondea'", "'diff_fechas'", "','", "'concatenar'"
    };
    public static final int T__40=40;
    public static final int T__41=41;
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
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int NEWLINE=5;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__12=12;
    public static final int T__38=38;
    public static final int T__11=11;
    public static final int T__39=39;
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
            this.state.ruleMemo = new HashMap[54+1];
             
             
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

                if ( ((LA1_0>=ID && LA1_0<=NEWLINE)||(LA1_0>=11 && LA1_0<=12)||(LA1_0>=15 && LA1_0<=16)) ) {
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:1: stat : ( ID '=' proposicion -> ^( '=' ID proposicion ) | condicional | 'obtener' proposicion -> ^( 'obtener' proposicion ) | 'pre' '{' ( ( stat )+ ) '}' | 'post' '{' ( ( stat )+ ) '}' | NEWLINE ->);
    public final SigeceneGramParser.stat_return stat() throws RecognitionException {
        SigeceneGramParser.stat_return retval = new SigeceneGramParser.stat_return();
        retval.start = input.LT(1);
        int stat_StartIndex = input.index();
        CommonTree root_0 = null;

        Token ID2=null;
        Token char_literal3=null;
        Token string_literal6=null;
        Token string_literal8=null;
        Token char_literal9=null;
        Token char_literal11=null;
        Token string_literal12=null;
        Token char_literal13=null;
        Token char_literal15=null;
        Token NEWLINE16=null;
        SigeceneGramParser.proposicion_return proposicion4 = null;

        SigeceneGramParser.condicional_return condicional5 = null;

        SigeceneGramParser.proposicion_return proposicion7 = null;

        SigeceneGramParser.stat_return stat10 = null;

        SigeceneGramParser.stat_return stat14 = null;


        CommonTree ID2_tree=null;
        CommonTree char_literal3_tree=null;
        CommonTree string_literal6_tree=null;
        CommonTree string_literal8_tree=null;
        CommonTree char_literal9_tree=null;
        CommonTree char_literal11_tree=null;
        CommonTree string_literal12_tree=null;
        CommonTree char_literal13_tree=null;
        CommonTree char_literal15_tree=null;
        CommonTree NEWLINE16_tree=null;
        RewriteRuleTokenStream stream_10=new RewriteRuleTokenStream(adaptor,"token 10");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_11=new RewriteRuleTokenStream(adaptor,"token 11");
        RewriteRuleSubtreeStream stream_proposicion=new RewriteRuleSubtreeStream(adaptor,"rule proposicion");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:60:6: ( ID '=' proposicion -> ^( '=' ID proposicion ) | condicional | 'obtener' proposicion -> ^( 'obtener' proposicion ) | 'pre' '{' ( ( stat )+ ) '}' | 'post' '{' ( ( stat )+ ) '}' | NEWLINE ->)
            int alt4=6;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt4=1;
                }
                break;
            case 16:
                {
                alt4=2;
                }
                break;
            case 11:
                {
                alt4=3;
                }
                break;
            case 12:
                {
                alt4=4;
                }
                break;
            case 15:
                {
                alt4=5;
                }
                break;
            case NEWLINE:
                {
                alt4=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
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
                    // elements: 11, proposicion
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:63:4: 'pre' '{' ( ( stat )+ ) '}'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal8=(Token)match(input,12,FOLLOW_12_in_stat124); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal8_tree = (CommonTree)adaptor.create(string_literal8);
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal8_tree, root_0);
                    }
                    char_literal9=(Token)match(input,13,FOLLOW_13_in_stat127); if (state.failed) return retval;
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:63:16: ( ( stat )+ )
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:63:17: ( stat )+
                    {
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:63:17: ( stat )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>=ID && LA2_0<=NEWLINE)||(LA2_0>=11 && LA2_0<=12)||(LA2_0>=15 && LA2_0<=16)) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:0:0: stat
                    	    {
                    	    pushFollow(FOLLOW_stat_in_stat131);
                    	    stat10=stat();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat10.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);


                    }

                    char_literal11=(Token)match(input,14,FOLLOW_14_in_stat135); if (state.failed) return retval;

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:64:4: 'post' '{' ( ( stat )+ ) '}'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal12=(Token)match(input,15,FOLLOW_15_in_stat141); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal12_tree = (CommonTree)adaptor.create(string_literal12);
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal12_tree, root_0);
                    }
                    char_literal13=(Token)match(input,13,FOLLOW_13_in_stat144); if (state.failed) return retval;
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:64:17: ( ( stat )+ )
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:64:18: ( stat )+
                    {
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:64:18: ( stat )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>=ID && LA3_0<=NEWLINE)||(LA3_0>=11 && LA3_0<=12)||(LA3_0>=15 && LA3_0<=16)) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:0:0: stat
                    	    {
                    	    pushFollow(FOLLOW_stat_in_stat148);
                    	    stat14=stat();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat14.getTree());

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

                    char_literal15=(Token)match(input,14,FOLLOW_14_in_stat152); if (state.failed) return retval;

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:65:4: NEWLINE
                    {
                    NEWLINE16=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_stat158); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE16);



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
                    // 65:14: ->
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:68:1: condicional : 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')' ;
    public final SigeceneGramParser.condicional_return condicional() throws RecognitionException {
        SigeceneGramParser.condicional_return retval = new SigeceneGramParser.condicional_return();
        retval.start = input.LT(1);
        int condicional_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal17=null;
        Token char_literal18=null;
        Token char_literal19=null;
        Token char_literal21=null;
        SigeceneGramParser.proposicion_return cond = null;

        SigeceneGramParser.stat_return stat20 = null;


        CommonTree string_literal17_tree=null;
        CommonTree char_literal18_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree char_literal21_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:69:2: ( 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:69:4: 'si' '(' cond= proposicion ';' ( ( stat )+ ) ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal17=(Token)match(input,16,FOLLOW_16_in_condicional173); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal17_tree = (CommonTree)adaptor.create(string_literal17);
            root_0 = (CommonTree)adaptor.becomeRoot(string_literal17_tree, root_0);
            }
            char_literal18=(Token)match(input,17,FOLLOW_17_in_condicional176); if (state.failed) return retval;
            pushFollow(FOLLOW_proposicion_in_condicional181);
            cond=proposicion();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());
            char_literal19=(Token)match(input,18,FOLLOW_18_in_condicional183); if (state.failed) return retval;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:69:37: ( ( stat )+ )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:69:38: ( stat )+
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:69:38: ( stat )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=ID && LA5_0<=NEWLINE)||(LA5_0>=11 && LA5_0<=12)||(LA5_0>=15 && LA5_0<=16)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:0:0: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_condicional187);
            	    stat20=stat();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat20.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            char_literal21=(Token)match(input,19,FOLLOW_19_in_condicional190); if (state.failed) return retval;

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:72:1: proposicion : ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )* ;
    public final SigeceneGramParser.proposicion_return proposicion() throws RecognitionException {
        SigeceneGramParser.proposicion_return retval = new SigeceneGramParser.proposicion_return();
        retval.start = input.LT(1);
        int proposicion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token NEWLINE22=null;
        Token char_literal24=null;
        Token char_literal25=null;
        SigeceneGramParser.negacion_return negacion23 = null;

        SigeceneGramParser.negacion_return negacion26 = null;


        CommonTree NEWLINE22_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree char_literal25_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:2: ( ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:4: ( ( NEWLINE )* ) negacion ( ( 'Y' | 'O' ) negacion )*
            {
            root_0 = (CommonTree)adaptor.nil();

            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:4: ( ( NEWLINE )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:5: ( NEWLINE )*
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:5: ( NEWLINE )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==NEWLINE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:6: NEWLINE
            	    {
            	    NEWLINE22=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_proposicion204); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE22_tree = (CommonTree)adaptor.create(NEWLINE22);
            	    adaptor.addChild(root_0, NEWLINE22_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            pushFollow(FOLLOW_negacion_in_proposicion210);
            negacion23=negacion();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, negacion23.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:27: ( ( 'Y' | 'O' ) negacion )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=20 && LA8_0<=21)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:28: ( 'Y' | 'O' ) negacion
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:28: ( 'Y' | 'O' )
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==20) ) {
            	        alt7=1;
            	    }
            	    else if ( (LA7_0==21) ) {
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
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:29: 'Y'
            	            {
            	            char_literal24=(Token)match(input,20,FOLLOW_20_in_proposicion214); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal24_tree = (CommonTree)adaptor.create(char_literal24);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal24_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:73:34: 'O'
            	            {
            	            char_literal25=(Token)match(input,21,FOLLOW_21_in_proposicion217); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal25_tree = (CommonTree)adaptor.create(char_literal25);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal25_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_negacion_in_proposicion221);
            	    negacion26=negacion();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, negacion26.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:76:1: negacion : ( 'NO' comparacion | comparacion );
    public final SigeceneGramParser.negacion_return negacion() throws RecognitionException {
        SigeceneGramParser.negacion_return retval = new SigeceneGramParser.negacion_return();
        retval.start = input.LT(1);
        int negacion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal27=null;
        SigeceneGramParser.comparacion_return comparacion28 = null;

        SigeceneGramParser.comparacion_return comparacion29 = null;


        CommonTree string_literal27_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:76:9: ( 'NO' comparacion | comparacion )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==22) ) {
                alt9=1;
            }
            else if ( (LA9_0==ID||(LA9_0>=BOOLEAN && LA9_0<=DOUBLE)||LA9_0==17||(LA9_0>=33 && LA9_0<=39)||LA9_0==41) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:76:11: 'NO' comparacion
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal27=(Token)match(input,22,FOLLOW_22_in_negacion232); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal27_tree = (CommonTree)adaptor.create(string_literal27);
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal27_tree, root_0);
                    }
                    pushFollow(FOLLOW_comparacion_in_negacion235);
                    comparacion28=comparacion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparacion28.getTree());

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:77:4: comparacion
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_comparacion_in_negacion240);
                    comparacion29=comparacion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparacion29.getTree());

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:80:1: comparacion : ( BOOLEAN | factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )? );
    public final SigeceneGramParser.comparacion_return comparacion() throws RecognitionException {
        SigeceneGramParser.comparacion_return retval = new SigeceneGramParser.comparacion_return();
        retval.start = input.LT(1);
        int comparacion_StartIndex = input.index();
        CommonTree root_0 = null;

        Token BOOLEAN30=null;
        Token char_literal32=null;
        Token string_literal33=null;
        Token char_literal34=null;
        Token string_literal35=null;
        Token string_literal36=null;
        Token string_literal37=null;
        SigeceneGramParser.factorStr_return factorStr31 = null;

        SigeceneGramParser.factorStr_return factorStr38 = null;


        CommonTree BOOLEAN30_tree=null;
        CommonTree char_literal32_tree=null;
        CommonTree string_literal33_tree=null;
        CommonTree char_literal34_tree=null;
        CommonTree string_literal35_tree=null;
        CommonTree string_literal36_tree=null;
        CommonTree string_literal37_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:81:2: ( BOOLEAN | factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )? )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==BOOLEAN) ) {
                alt12=1;
            }
            else if ( (LA12_0==ID||(LA12_0>=STRING && LA12_0<=DOUBLE)||LA12_0==17||(LA12_0>=33 && LA12_0<=39)||LA12_0==41) ) {
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:81:4: BOOLEAN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    BOOLEAN30=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_comparacion251); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLEAN30_tree = (CommonTree)adaptor.create(BOOLEAN30);
                    adaptor.addChild(root_0, BOOLEAN30_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:4: factorStr ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_factorStr_in_comparacion256);
                    factorStr31=factorStr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, factorStr31.getTree());
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:14: ( ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0>=23 && LA11_0<=28)) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:15: ( '>' | '>=' | '<' | '<=' | '==' | '<>' ) factorStr
                            {
                            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:15: ( '>' | '>=' | '<' | '<=' | '==' | '<>' )
                            int alt10=6;
                            switch ( input.LA(1) ) {
                            case 23:
                                {
                                alt10=1;
                                }
                                break;
                            case 24:
                                {
                                alt10=2;
                                }
                                break;
                            case 25:
                                {
                                alt10=3;
                                }
                                break;
                            case 26:
                                {
                                alt10=4;
                                }
                                break;
                            case 27:
                                {
                                alt10=5;
                                }
                                break;
                            case 28:
                                {
                                alt10=6;
                                }
                                break;
                            default:
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 10, 0, input);

                                throw nvae;
                            }

                            switch (alt10) {
                                case 1 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:16: '>'
                                    {
                                    char_literal32=(Token)match(input,23,FOLLOW_23_in_comparacion260); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal32_tree = (CommonTree)adaptor.create(char_literal32);
                                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal32_tree, root_0);
                                    }

                                    }
                                    break;
                                case 2 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:21: '>='
                                    {
                                    string_literal33=(Token)match(input,24,FOLLOW_24_in_comparacion263); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal33_tree = (CommonTree)adaptor.create(string_literal33);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal33_tree, root_0);
                                    }

                                    }
                                    break;
                                case 3 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:27: '<'
                                    {
                                    char_literal34=(Token)match(input,25,FOLLOW_25_in_comparacion266); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    char_literal34_tree = (CommonTree)adaptor.create(char_literal34);
                                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal34_tree, root_0);
                                    }

                                    }
                                    break;
                                case 4 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:32: '<='
                                    {
                                    string_literal35=(Token)match(input,26,FOLLOW_26_in_comparacion269); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal35_tree = (CommonTree)adaptor.create(string_literal35);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal35_tree, root_0);
                                    }

                                    }
                                    break;
                                case 5 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:38: '=='
                                    {
                                    string_literal36=(Token)match(input,27,FOLLOW_27_in_comparacion272); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal36_tree = (CommonTree)adaptor.create(string_literal36);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal36_tree, root_0);
                                    }

                                    }
                                    break;
                                case 6 :
                                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:82:44: '<>'
                                    {
                                    string_literal37=(Token)match(input,28,FOLLOW_28_in_comparacion275); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    string_literal37_tree = (CommonTree)adaptor.create(string_literal37);
                                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal37_tree, root_0);
                                    }

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_factorStr_in_comparacion279);
                            factorStr38=factorStr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, factorStr38.getTree());

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:85:1: factorStr : ( STRING | expr );
    public final SigeceneGramParser.factorStr_return factorStr() throws RecognitionException {
        SigeceneGramParser.factorStr_return retval = new SigeceneGramParser.factorStr_return();
        retval.start = input.LT(1);
        int factorStr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token STRING39=null;
        SigeceneGramParser.expr_return expr40 = null;


        CommonTree STRING39_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:86:2: ( STRING | expr )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==STRING) ) {
                alt13=1;
            }
            else if ( (LA13_0==ID||LA13_0==DOUBLE||LA13_0==17||(LA13_0>=33 && LA13_0<=39)||LA13_0==41) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:86:4: STRING
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING39=(Token)match(input,STRING,FOLLOW_STRING_in_factorStr292); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING39_tree = (CommonTree)adaptor.create(STRING39);
                    adaptor.addChild(root_0, STRING39_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:87:4: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_factorStr297);
                    expr40=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr40.getTree());

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:1: expr : producto ( ( '+' | '-' ) producto )* ;
    public final SigeceneGramParser.expr_return expr() throws RecognitionException {
        SigeceneGramParser.expr_return retval = new SigeceneGramParser.expr_return();
        retval.start = input.LT(1);
        int expr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token char_literal42=null;
        Token char_literal43=null;
        SigeceneGramParser.producto_return producto41 = null;

        SigeceneGramParser.producto_return producto44 = null;


        CommonTree char_literal42_tree=null;
        CommonTree char_literal43_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:6: ( producto ( ( '+' | '-' ) producto )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:8: producto ( ( '+' | '-' ) producto )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_producto_in_expr307);
            producto41=producto();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, producto41.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:17: ( ( '+' | '-' ) producto )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=29 && LA15_0<=30)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:18: ( '+' | '-' ) producto
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:18: ( '+' | '-' )
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==29) ) {
            	        alt14=1;
            	    }
            	    else if ( (LA14_0==30) ) {
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
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:19: '+'
            	            {
            	            char_literal42=(Token)match(input,29,FOLLOW_29_in_expr311); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal42_tree = (CommonTree)adaptor.create(char_literal42);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal42_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:90:24: '-'
            	            {
            	            char_literal43=(Token)match(input,30,FOLLOW_30_in_expr314); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal43_tree = (CommonTree)adaptor.create(char_literal43);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal43_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_producto_in_expr318);
            	    producto44=producto();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, producto44.getTree());

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:1: producto : factor ( ( '*' | '/' ) factor )* ;
    public final SigeceneGramParser.producto_return producto() throws RecognitionException {
        SigeceneGramParser.producto_return retval = new SigeceneGramParser.producto_return();
        retval.start = input.LT(1);
        int producto_StartIndex = input.index();
        CommonTree root_0 = null;

        Token char_literal46=null;
        Token char_literal47=null;
        SigeceneGramParser.factor_return factor45 = null;

        SigeceneGramParser.factor_return factor48 = null;


        CommonTree char_literal46_tree=null;
        CommonTree char_literal47_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:9: ( factor ( ( '*' | '/' ) factor )* )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:11: factor ( ( '*' | '/' ) factor )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_factor_in_producto329);
            factor45=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, factor45.getTree());
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:18: ( ( '*' | '/' ) factor )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=31 && LA17_0<=32)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:19: ( '*' | '/' ) factor
            	    {
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:19: ( '*' | '/' )
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==31) ) {
            	        alt16=1;
            	    }
            	    else if ( (LA16_0==32) ) {
            	        alt16=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 16, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:20: '*'
            	            {
            	            char_literal46=(Token)match(input,31,FOLLOW_31_in_producto333); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal46_tree = (CommonTree)adaptor.create(char_literal46);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal46_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:93:25: '/'
            	            {
            	            char_literal47=(Token)match(input,32,FOLLOW_32_in_producto336); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal47_tree = (CommonTree)adaptor.create(char_literal47);
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal47_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_factor_in_producto340);
            	    factor48=factor();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, factor48.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:96:1: factor : ( DOUBLE | ID | funcPredefinidas | '(' proposicion ')' );
    public final SigeceneGramParser.factor_return factor() throws RecognitionException {
        SigeceneGramParser.factor_return retval = new SigeceneGramParser.factor_return();
        retval.start = input.LT(1);
        int factor_StartIndex = input.index();
        CommonTree root_0 = null;

        Token DOUBLE49=null;
        Token ID50=null;
        Token char_literal52=null;
        Token char_literal54=null;
        SigeceneGramParser.funcPredefinidas_return funcPredefinidas51 = null;

        SigeceneGramParser.proposicion_return proposicion53 = null;


        CommonTree DOUBLE49_tree=null;
        CommonTree ID50_tree=null;
        CommonTree char_literal52_tree=null;
        CommonTree char_literal54_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:96:8: ( DOUBLE | ID | funcPredefinidas | '(' proposicion ')' )
            int alt18=4;
            switch ( input.LA(1) ) {
            case DOUBLE:
                {
                alt18=1;
                }
                break;
            case ID:
                {
                alt18=2;
                }
                break;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
                {
                alt18=3;
                }
                break;
            case 17:
                {
                alt18=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:96:10: DOUBLE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    DOUBLE49=(Token)match(input,DOUBLE,FOLLOW_DOUBLE_in_factor352); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLE49_tree = (CommonTree)adaptor.create(DOUBLE49);
                    adaptor.addChild(root_0, DOUBLE49_tree);
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:97:4: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ID50=(Token)match(input,ID,FOLLOW_ID_in_factor357); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID50_tree = (CommonTree)adaptor.create(ID50);
                    adaptor.addChild(root_0, ID50_tree);
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:98:4: funcPredefinidas
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_funcPredefinidas_in_factor362);
                    funcPredefinidas51=funcPredefinidas();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcPredefinidas51.getTree());

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:99:4: '(' proposicion ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal52=(Token)match(input,17,FOLLOW_17_in_factor367); if (state.failed) return retval;
                    pushFollow(FOLLOW_proposicion_in_factor370);
                    proposicion53=proposicion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, proposicion53.getTree());
                    char_literal54=(Token)match(input,19,FOLLOW_19_in_factor372); if (state.failed) return retval;

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:103:1: funcPredefinidas : ( promedio | sumatoria | contar | max | min | redondea | diff_fechas | concatenar );
    public final SigeceneGramParser.funcPredefinidas_return funcPredefinidas() throws RecognitionException {
        SigeceneGramParser.funcPredefinidas_return retval = new SigeceneGramParser.funcPredefinidas_return();
        retval.start = input.LT(1);
        int funcPredefinidas_StartIndex = input.index();
        CommonTree root_0 = null;

        SigeceneGramParser.promedio_return promedio55 = null;

        SigeceneGramParser.sumatoria_return sumatoria56 = null;

        SigeceneGramParser.contar_return contar57 = null;

        SigeceneGramParser.max_return max58 = null;

        SigeceneGramParser.min_return min59 = null;

        SigeceneGramParser.redondea_return redondea60 = null;

        SigeceneGramParser.diff_fechas_return diff_fechas61 = null;

        SigeceneGramParser.concatenar_return concatenar62 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:104:2: ( promedio | sumatoria | contar | max | min | redondea | diff_fechas | concatenar )
            int alt19=8;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt19=1;
                }
                break;
            case 34:
                {
                alt19=2;
                }
                break;
            case 35:
                {
                alt19=3;
                }
                break;
            case 36:
                {
                alt19=4;
                }
                break;
            case 37:
                {
                alt19=5;
                }
                break;
            case 38:
                {
                alt19=6;
                }
                break;
            case 39:
                {
                alt19=7;
                }
                break;
            case 41:
                {
                alt19=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:104:4: promedio
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_promedio_in_funcPredefinidas385);
                    promedio55=promedio();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, promedio55.getTree());

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:105:4: sumatoria
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_sumatoria_in_funcPredefinidas390);
                    sumatoria56=sumatoria();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sumatoria56.getTree());

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:106:4: contar
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_contar_in_funcPredefinidas395);
                    contar57=contar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, contar57.getTree());

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:107:4: max
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_max_in_funcPredefinidas400);
                    max58=max();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, max58.getTree());

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:108:4: min
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_min_in_funcPredefinidas405);
                    min59=min();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, min59.getTree());

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:109:4: redondea
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_redondea_in_funcPredefinidas410);
                    redondea60=redondea();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, redondea60.getTree());

                    }
                    break;
                case 7 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:110:4: diff_fechas
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_diff_fechas_in_funcPredefinidas415);
                    diff_fechas61=diff_fechas();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, diff_fechas61.getTree());

                    }
                    break;
                case 8 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:111:4: concatenar
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_concatenar_in_funcPredefinidas420);
                    concatenar62=concatenar();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, concatenar62.getTree());

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:114:1: promedio : 'promedio' '(' ID ')' -> ^( 'promedio' ID ) ;
    public final SigeceneGramParser.promedio_return promedio() throws RecognitionException {
        SigeceneGramParser.promedio_return retval = new SigeceneGramParser.promedio_return();
        retval.start = input.LT(1);
        int promedio_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal63=null;
        Token char_literal64=null;
        Token ID65=null;
        Token char_literal66=null;

        CommonTree string_literal63_tree=null;
        CommonTree char_literal64_tree=null;
        CommonTree ID65_tree=null;
        CommonTree char_literal66_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_33=new RewriteRuleTokenStream(adaptor,"token 33");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:114:9: ( 'promedio' '(' ID ')' -> ^( 'promedio' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:114:11: 'promedio' '(' ID ')'
            {
            string_literal63=(Token)match(input,33,FOLLOW_33_in_promedio430); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_33.add(string_literal63);

            char_literal64=(Token)match(input,17,FOLLOW_17_in_promedio432); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal64);

            ID65=(Token)match(input,ID,FOLLOW_ID_in_promedio434); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID65);

            char_literal66=(Token)match(input,19,FOLLOW_19_in_promedio436); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal66);



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
            // 114:33: -> ^( 'promedio' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:114:36: ^( 'promedio' ID )
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:117:1: sumatoria : 'sumatoria' '(' ID ')' -> ^( 'sumatoria' ID ) ;
    public final SigeceneGramParser.sumatoria_return sumatoria() throws RecognitionException {
        SigeceneGramParser.sumatoria_return retval = new SigeceneGramParser.sumatoria_return();
        retval.start = input.LT(1);
        int sumatoria_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal67=null;
        Token char_literal68=null;
        Token ID69=null;
        Token char_literal70=null;

        CommonTree string_literal67_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree ID69_tree=null;
        CommonTree char_literal70_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:118:2: ( 'sumatoria' '(' ID ')' -> ^( 'sumatoria' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:118:4: 'sumatoria' '(' ID ')'
            {
            string_literal67=(Token)match(input,34,FOLLOW_34_in_sumatoria455); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_34.add(string_literal67);

            char_literal68=(Token)match(input,17,FOLLOW_17_in_sumatoria457); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal68);

            ID69=(Token)match(input,ID,FOLLOW_ID_in_sumatoria459); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID69);

            char_literal70=(Token)match(input,19,FOLLOW_19_in_sumatoria461); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal70);



            // AST REWRITE
            // elements: 34, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 118:27: -> ^( 'sumatoria' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:118:30: ^( 'sumatoria' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_34.nextNode(), root_1);

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:121:1: contar : 'contar' '(' ID ')' -> ^( 'contar' ID ) ;
    public final SigeceneGramParser.contar_return contar() throws RecognitionException {
        SigeceneGramParser.contar_return retval = new SigeceneGramParser.contar_return();
        retval.start = input.LT(1);
        int contar_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal71=null;
        Token char_literal72=null;
        Token ID73=null;
        Token char_literal74=null;

        CommonTree string_literal71_tree=null;
        CommonTree char_literal72_tree=null;
        CommonTree ID73_tree=null;
        CommonTree char_literal74_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_35=new RewriteRuleTokenStream(adaptor,"token 35");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:121:8: ( 'contar' '(' ID ')' -> ^( 'contar' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:121:10: 'contar' '(' ID ')'
            {
            string_literal71=(Token)match(input,35,FOLLOW_35_in_contar479); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_35.add(string_literal71);

            char_literal72=(Token)match(input,17,FOLLOW_17_in_contar481); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal72);

            ID73=(Token)match(input,ID,FOLLOW_ID_in_contar483); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID73);

            char_literal74=(Token)match(input,19,FOLLOW_19_in_contar485); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal74);



            // AST REWRITE
            // elements: 35, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 121:30: -> ^( 'contar' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:121:33: ^( 'contar' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_35.nextNode(), root_1);

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:124:1: max : 'max' '(' ID ')' -> ^( 'max' ID ) ;
    public final SigeceneGramParser.max_return max() throws RecognitionException {
        SigeceneGramParser.max_return retval = new SigeceneGramParser.max_return();
        retval.start = input.LT(1);
        int max_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal75=null;
        Token char_literal76=null;
        Token ID77=null;
        Token char_literal78=null;

        CommonTree string_literal75_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree ID77_tree=null;
        CommonTree char_literal78_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:124:5: ( 'max' '(' ID ')' -> ^( 'max' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:124:7: 'max' '(' ID ')'
            {
            string_literal75=(Token)match(input,36,FOLLOW_36_in_max503); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_36.add(string_literal75);

            char_literal76=(Token)match(input,17,FOLLOW_17_in_max505); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal76);

            ID77=(Token)match(input,ID,FOLLOW_ID_in_max507); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID77);

            char_literal78=(Token)match(input,19,FOLLOW_19_in_max509); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal78);



            // AST REWRITE
            // elements: ID, 36
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 124:24: -> ^( 'max' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:124:27: ^( 'max' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_36.nextNode(), root_1);

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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:127:1: min : 'min' '(' ID ')' -> ^( 'min' ID ) ;
    public final SigeceneGramParser.min_return min() throws RecognitionException {
        SigeceneGramParser.min_return retval = new SigeceneGramParser.min_return();
        retval.start = input.LT(1);
        int min_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal79=null;
        Token char_literal80=null;
        Token ID81=null;
        Token char_literal82=null;

        CommonTree string_literal79_tree=null;
        CommonTree char_literal80_tree=null;
        CommonTree ID81_tree=null;
        CommonTree char_literal82_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:127:5: ( 'min' '(' ID ')' -> ^( 'min' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:127:7: 'min' '(' ID ')'
            {
            string_literal79=(Token)match(input,37,FOLLOW_37_in_min527); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_37.add(string_literal79);

            char_literal80=(Token)match(input,17,FOLLOW_17_in_min529); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal80);

            ID81=(Token)match(input,ID,FOLLOW_ID_in_min531); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID81);

            char_literal82=(Token)match(input,19,FOLLOW_19_in_min533); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal82);



            // AST REWRITE
            // elements: ID, 37
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 127:24: -> ^( 'min' ID )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:127:27: ^( 'min' ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_37.nextNode(), root_1);

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

    public static class redondea_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "redondea"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:130:1: redondea : 'redondea' '(' expr ')' -> ^( 'redondea' expr ) ;
    public final SigeceneGramParser.redondea_return redondea() throws RecognitionException {
        SigeceneGramParser.redondea_return retval = new SigeceneGramParser.redondea_return();
        retval.start = input.LT(1);
        int redondea_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal83=null;
        Token char_literal84=null;
        Token char_literal86=null;
        SigeceneGramParser.expr_return expr85 = null;


        CommonTree string_literal83_tree=null;
        CommonTree char_literal84_tree=null;
        CommonTree char_literal86_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:130:9: ( 'redondea' '(' expr ')' -> ^( 'redondea' expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:130:11: 'redondea' '(' expr ')'
            {
            string_literal83=(Token)match(input,38,FOLLOW_38_in_redondea550); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_38.add(string_literal83);

            char_literal84=(Token)match(input,17,FOLLOW_17_in_redondea552); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal84);

            pushFollow(FOLLOW_expr_in_redondea554);
            expr85=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr85.getTree());
            char_literal86=(Token)match(input,19,FOLLOW_19_in_redondea556); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal86);



            // AST REWRITE
            // elements: expr, 38
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 130:35: -> ^( 'redondea' expr )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:130:38: ^( 'redondea' expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_38.nextNode(), root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

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
            if ( state.backtracking>0 ) { memoize(input, 17, redondea_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "redondea"

    public static class diff_fechas_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "diff_fechas"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:133:1: diff_fechas : 'diff_fechas' '(' factorStr ',' factorStr ')' -> ^( 'diff_fechas' factorStr factorStr ) ;
    public final SigeceneGramParser.diff_fechas_return diff_fechas() throws RecognitionException {
        SigeceneGramParser.diff_fechas_return retval = new SigeceneGramParser.diff_fechas_return();
        retval.start = input.LT(1);
        int diff_fechas_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal87=null;
        Token char_literal88=null;
        Token char_literal90=null;
        Token char_literal92=null;
        SigeceneGramParser.factorStr_return factorStr89 = null;

        SigeceneGramParser.factorStr_return factorStr91 = null;


        CommonTree string_literal87_tree=null;
        CommonTree char_literal88_tree=null;
        CommonTree char_literal90_tree=null;
        CommonTree char_literal92_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleSubtreeStream stream_factorStr=new RewriteRuleSubtreeStream(adaptor,"rule factorStr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:134:2: ( 'diff_fechas' '(' factorStr ',' factorStr ')' -> ^( 'diff_fechas' factorStr factorStr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:134:4: 'diff_fechas' '(' factorStr ',' factorStr ')'
            {
            string_literal87=(Token)match(input,39,FOLLOW_39_in_diff_fechas575); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(string_literal87);

            char_literal88=(Token)match(input,17,FOLLOW_17_in_diff_fechas577); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal88);

            pushFollow(FOLLOW_factorStr_in_diff_fechas579);
            factorStr89=factorStr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factorStr.add(factorStr89.getTree());
            char_literal90=(Token)match(input,40,FOLLOW_40_in_diff_fechas581); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal90);

            pushFollow(FOLLOW_factorStr_in_diff_fechas583);
            factorStr91=factorStr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factorStr.add(factorStr91.getTree());
            char_literal92=(Token)match(input,19,FOLLOW_19_in_diff_fechas585); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal92);



            // AST REWRITE
            // elements: 39, factorStr, factorStr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 134:50: -> ^( 'diff_fechas' factorStr factorStr )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:134:53: ^( 'diff_fechas' factorStr factorStr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_39.nextNode(), root_1);

                adaptor.addChild(root_1, stream_factorStr.nextTree());
                adaptor.addChild(root_1, stream_factorStr.nextTree());

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
            if ( state.backtracking>0 ) { memoize(input, 18, diff_fechas_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "diff_fechas"

    public static class concatenar_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "concatenar"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:137:1: concatenar : 'concatenar' '(' factorStr ',' factorStr ')' -> ^( 'concatenar' factorStr factorStr ) ;
    public final SigeceneGramParser.concatenar_return concatenar() throws RecognitionException {
        SigeceneGramParser.concatenar_return retval = new SigeceneGramParser.concatenar_return();
        retval.start = input.LT(1);
        int concatenar_StartIndex = input.index();
        CommonTree root_0 = null;

        Token string_literal93=null;
        Token char_literal94=null;
        Token char_literal96=null;
        Token char_literal98=null;
        SigeceneGramParser.factorStr_return factorStr95 = null;

        SigeceneGramParser.factorStr_return factorStr97 = null;


        CommonTree string_literal93_tree=null;
        CommonTree char_literal94_tree=null;
        CommonTree char_literal96_tree=null;
        CommonTree char_literal98_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleTokenStream stream_17=new RewriteRuleTokenStream(adaptor,"token 17");
        RewriteRuleSubtreeStream stream_factorStr=new RewriteRuleSubtreeStream(adaptor,"rule factorStr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:138:2: ( 'concatenar' '(' factorStr ',' factorStr ')' -> ^( 'concatenar' factorStr factorStr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:138:4: 'concatenar' '(' factorStr ',' factorStr ')'
            {
            string_literal93=(Token)match(input,41,FOLLOW_41_in_concatenar607); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_41.add(string_literal93);

            char_literal94=(Token)match(input,17,FOLLOW_17_in_concatenar609); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_17.add(char_literal94);

            pushFollow(FOLLOW_factorStr_in_concatenar611);
            factorStr95=factorStr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factorStr.add(factorStr95.getTree());
            char_literal96=(Token)match(input,40,FOLLOW_40_in_concatenar613); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_40.add(char_literal96);

            pushFollow(FOLLOW_factorStr_in_concatenar615);
            factorStr97=factorStr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factorStr.add(factorStr97.getTree());
            char_literal98=(Token)match(input,19,FOLLOW_19_in_concatenar617); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_19.add(char_literal98);



            // AST REWRITE
            // elements: 41, factorStr, factorStr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 138:49: -> ^( 'concatenar' factorStr factorStr )
            {
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:138:52: ^( 'concatenar' factorStr factorStr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_41.nextNode(), root_1);

                adaptor.addChild(root_1, stream_factorStr.nextTree());
                adaptor.addChild(root_1, stream_factorStr.nextTree());

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
            if ( state.backtracking>0 ) { memoize(input, 19, concatenar_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "concatenar"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog73 = new BitSet(new long[]{0x0000000000019832L});
    public static final BitSet FOLLOW_ID_in_stat85 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_stat87 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_proposicion_in_stat89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condicional_in_stat104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat109 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_proposicion_in_stat111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_stat124 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat127 = new BitSet(new long[]{0x000000000001D830L});
    public static final BitSet FOLLOW_stat_in_stat131 = new BitSet(new long[]{0x000000000001D830L});
    public static final BitSet FOLLOW_14_in_stat135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_stat141 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_stat144 = new BitSet(new long[]{0x000000000001D830L});
    public static final BitSet FOLLOW_stat_in_stat148 = new BitSet(new long[]{0x000000000001D830L});
    public static final BitSet FOLLOW_14_in_stat152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_stat158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_condicional173 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_condicional176 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_proposicion_in_condicional181 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_condicional183 = new BitSet(new long[]{0x0000000000099830L});
    public static final BitSet FOLLOW_stat_in_condicional187 = new BitSet(new long[]{0x0000000000099830L});
    public static final BitSet FOLLOW_19_in_condicional190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_proposicion204 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_negacion_in_proposicion210 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_20_in_proposicion214 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_21_in_proposicion217 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_negacion_in_proposicion221 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_22_in_negacion232 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_comparacion_in_negacion235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparacion_in_negacion240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_comparacion251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factorStr_in_comparacion256 = new BitSet(new long[]{0x000000001F800002L});
    public static final BitSet FOLLOW_23_in_comparacion260 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_24_in_comparacion263 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_25_in_comparacion266 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_26_in_comparacion269 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_27_in_comparacion272 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_28_in_comparacion275 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factorStr_in_comparacion279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_factorStr292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_factorStr297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_producto_in_expr307 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_29_in_expr311 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_30_in_expr314 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_producto_in_expr318 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_factor_in_producto329 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_31_in_producto333 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_32_in_producto336 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factor_in_producto340 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_DOUBLE_in_factor352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcPredefinidas_in_factor362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_factor367 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_proposicion_in_factor370 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_factor372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_promedio_in_funcPredefinidas385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sumatoria_in_funcPredefinidas390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contar_in_funcPredefinidas395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_max_in_funcPredefinidas400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_min_in_funcPredefinidas405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_redondea_in_funcPredefinidas410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_diff_fechas_in_funcPredefinidas415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_concatenar_in_funcPredefinidas420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_promedio430 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_promedio432 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_promedio434 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_promedio436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_sumatoria455 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_sumatoria457 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_sumatoria459 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_sumatoria461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_contar479 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_contar481 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_contar483 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_contar485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_max503 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_max505 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_max507 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_max509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_min527 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_min529 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ID_in_min531 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_min533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_redondea550 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_redondea552 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_expr_in_redondea554 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_redondea556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_diff_fechas575 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_diff_fechas577 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factorStr_in_diff_fechas579 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_diff_fechas581 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factorStr_in_diff_fechas583 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_diff_fechas585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_concatenar607 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_concatenar609 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factorStr_in_concatenar611 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_concatenar613 = new BitSet(new long[]{0x000002FE004201F0L});
    public static final BitSet FOLLOW_factorStr_in_concatenar615 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_concatenar617 = new BitSet(new long[]{0x0000000000000002L});

}