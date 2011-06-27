// $ANTLR 3.2 Sep 23, 2009 12:02:23 Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g 2011-06-26 19:31:33

package com.siagcee.logic;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Date;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class SigeceneTree extends TreeParser {
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
    public static final int T__16=16;
    public static final int T__33=33;
    public static final int BOOLEAN=6;
    public static final int WS=9;
    public static final int T__15=15;
    public static final int T__34=34;
    public static final int T__18=18;
    public static final int T__35=35;
    public static final int NEWLINE=5;
    public static final int T__17=17;
    public static final int T__36=36;
    public static final int T__12=12;
    public static final int T__37=37;
    public static final int T__11=11;
    public static final int T__38=38;
    public static final int T__14=14;
    public static final int T__39=39;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int DOUBLE=8;
    public static final int STRING=7;

    // delegates
    // delegators


        public SigeceneTree(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public SigeceneTree(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[46+1];
             
             
        }
        

    public String[] getTokenNames() { return SigeceneTree.tokenNames; }
    public String getGrammarFileName() { return "Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g"; }


    HashMap memory = EstudioPerso.getInstance()._memoriaInternaEstudio;



    // $ANTLR start "prog"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:24:1: prog : ( stat[true] )+ ;
    public final void prog() throws RecognitionException {
        int prog_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return ; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:24:6: ( ( stat[true] )+ )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:24:8: ( stat[true] )+
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:24:8: ( stat[true] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=10 && LA1_0<=12)||(LA1_0>=15 && LA1_0<=16)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:0:0: stat[true]
            	    {
            	    pushFollow(FOLLOW_stat_in_prog59);
            	    stat(true);

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, prog_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "prog"


    // $ANTLR start "stat"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:26:1: stat[boolean accionar] : ( condicional[accionar] | ^( 'obtener' proposicion ) | ^( '=' ID proposicion ) | ^( 'pre' ( stat[accionar] )+ ) | ^( 'post' ( stat[accionar] )+ ) );
    public final void stat(boolean accionar) throws RecognitionException {
        int stat_StartIndex = input.index();
        CommonTree ID2=null;
        String proposicion1 = null;

        String proposicion3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:27:2: ( condicional[accionar] | ^( 'obtener' proposicion ) | ^( '=' ID proposicion ) | ^( 'pre' ( stat[accionar] )+ ) | ^( 'post' ( stat[accionar] )+ ) )
            int alt4=5;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt4=1;
                }
                break;
            case 11:
                {
                alt4=2;
                }
                break;
            case 10:
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
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:27:4: condicional[accionar]
                    {
                    pushFollow(FOLLOW_condicional_in_stat71);
                    condicional(accionar);

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:28:4: ^( 'obtener' proposicion )
                    {
                    match(input,11,FOLLOW_11_in_stat78); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_proposicion_in_stat80);
                    proposicion1=proposicion();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;
                    if ( state.backtracking==0 ) {

                      				if(accionar){
                      					EstudioPerso.getInstance().addResultado(proposicion1);
                      				}
                      			
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:34:4: ^( '=' ID proposicion )
                    {
                    match(input,10,FOLLOW_10_in_stat93); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_stat95); if (state.failed) return ;
                    pushFollow(FOLLOW_proposicion_in_stat97);
                    proposicion3=proposicion();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;
                    if ( state.backtracking==0 ) {

                      				if(accionar){
                      					memory.put((ID2!=null?ID2.getText():null), proposicion3);
                      				}
                      			
                    }

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:40:4: ^( 'pre' ( stat[accionar] )+ )
                    {
                    match(input,12,FOLLOW_12_in_stat109); if (state.failed) return ;

                    if ( state.backtracking==0 ) {

                      				if(EstudioPerso.getInstance().is_initial()){
                      					accionar = true;
                      				}else{
                      					accionar = false;
                      				}
                      			
                    }

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:48:4: ( stat[accionar] )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>=10 && LA2_0<=12)||(LA2_0>=15 && LA2_0<=16)) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:48:5: stat[accionar]
                    	    {
                    	    pushFollow(FOLLOW_stat_in_stat121);
                    	    stat(accionar);

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);


                    match(input, Token.UP, null); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:50:4: ^( 'post' ( stat[accionar] )+ )
                    {
                    match(input,15,FOLLOW_15_in_stat134); if (state.failed) return ;

                    if ( state.backtracking==0 ) {

                      				if(EstudioPerso.getInstance().is_final()){
                      					accionar = true;
                      				}else{
                      					accionar = false;
                      				}
                      			
                    }

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:58:4: ( stat[accionar] )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>=10 && LA3_0<=12)||(LA3_0>=15 && LA3_0<=16)) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:58:5: stat[accionar]
                    	    {
                    	    pushFollow(FOLLOW_stat_in_stat146);
                    	    stat(accionar);

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);


                    match(input, Token.UP, null); if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, stat_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "stat"


    // $ANTLR start "condicional"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:62:1: condicional[boolean accionar] : ^( 'si' cond= proposicion ( stat[accionar] )+ ) ;
    public final void condicional(boolean accionar) throws RecognitionException {
        int condicional_StartIndex = input.index();
        String cond = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:63:2: ( ^( 'si' cond= proposicion ( stat[accionar] )+ ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:63:4: ^( 'si' cond= proposicion ( stat[accionar] )+ )
            {
            match(input,16,FOLLOW_16_in_condicional166); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_proposicion_in_condicional170);
            cond=proposicion();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {

              				if((cond).equals("1")){
              					accionar = true;
              				}else{
              					accionar = false;
              				}
              			
            }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:71:4: ( stat[accionar] )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=10 && LA5_0<=12)||(LA5_0>=15 && LA5_0<=16)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:71:5: stat[accionar]
            	    {
            	    pushFollow(FOLLOW_stat_in_condicional182);
            	    stat(accionar);

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, condicional_StartIndex); }
        }
        return ;
    }
    // $ANTLR end "condicional"


    // $ANTLR start "proposicion"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:75:1: proposicion returns [String valor] : ( ^( '<' a= expr b= expr ) | ^( '<=' a= expr b= expr ) | ^( '>' a= expr b= expr ) | ^( '>=' a= expr b= expr ) | ^( '==' a= expr b= expr ) | ^( '<>' a= expr b= expr ) | ^( 'Y' a= proposicion b= proposicion ) | ^( 'O' a= proposicion b= proposicion ) | ^( 'NO' a= proposicion ) | BOOLEAN | expr );
    public final String proposicion() throws RecognitionException {
        String valor = null;
        int proposicion_StartIndex = input.index();
        CommonTree BOOLEAN4=null;
        String a = null;

        String b = null;

        String expr5 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:76:2: ( ^( '<' a= expr b= expr ) | ^( '<=' a= expr b= expr ) | ^( '>' a= expr b= expr ) | ^( '>=' a= expr b= expr ) | ^( '==' a= expr b= expr ) | ^( '<>' a= expr b= expr ) | ^( 'Y' a= proposicion b= proposicion ) | ^( 'O' a= proposicion b= proposicion ) | ^( 'NO' a= proposicion ) | BOOLEAN | expr )
            int alt6=11;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt6=1;
                }
                break;
            case 26:
                {
                alt6=2;
                }
                break;
            case 23:
                {
                alt6=3;
                }
                break;
            case 24:
                {
                alt6=4;
                }
                break;
            case 27:
                {
                alt6=5;
                }
                break;
            case 28:
                {
                alt6=6;
                }
                break;
            case 20:
                {
                alt6=7;
                }
                break;
            case 21:
                {
                alt6=8;
                }
                break;
            case 22:
                {
                alt6=9;
                }
                break;
            case BOOLEAN:
                {
                alt6=10;
                }
                break;
            case ID:
            case STRING:
            case DOUBLE:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
                {
                alt6=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return valor;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:76:4: ^( '<' a= expr b= expr )
                    {
                    match(input,25,FOLLOW_25_in_proposicion205); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion209);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion213);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				try{
                      					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                      					Date _aDate = df.parse(a);
                      					Date _bDate = df.parse(b);
                      					valor = (_bDate.compareTo(_aDate) > 0) ? "1":"0";
                      				}catch(Exception fechas){
                      					try{
                      						valor = (Double.parseDouble(a) < Double.parseDouble(b))?"1":"0";
                      					}catch(Exception numerica){
                      						valor = (b.toLowerCase().compareTo(a.toLowerCase()) > 0)?"1":"0";
                      					}
                      				}
                      			
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:91:4: ^( '<=' a= expr b= expr )
                    {
                    match(input,26,FOLLOW_26_in_proposicion225); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion229);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion233);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				try{
                      					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                      					Date _aDate = df.parse(a);
                      					Date _bDate = df.parse(b);
                      					valor = (_bDate.compareTo(_aDate) > 0 || _aDate.compareTo(_bDate) == 0 ) ? "1":"0";
                      				}catch(Exception fechas){
                      					try{
                      						valor = (Double.parseDouble(a) <= Double.parseDouble(b))?"1":"0";
                      					}catch(Exception numerica){
                      						valor = ((b.toLowerCase().compareTo(a.toLowerCase()) > 0) || (b.toLowerCase().compareTo(a.toLowerCase()) == 0))?"1":"0";
                      					}
                      				}
                      			
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:106:4: ^( '>' a= expr b= expr )
                    {
                    match(input,23,FOLLOW_23_in_proposicion245); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion249);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion253);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				try{
                      					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                      					Date _aDate = df.parse(a);
                      					Date _bDate = df.parse(b);
                      					valor = (_aDate.compareTo(_bDate) > 0) ? "1":"0";
                      				}catch(Exception fechas){
                      					try{
                      						valor = (Double.parseDouble(a) > Double.parseDouble(b))?"1":"0";
                      					}catch(Exception numerica){
                      						valor = (a.toLowerCase().compareTo(b.toLowerCase()) > 0)?"1":"0";
                      					}
                      				}
                      			
                    }

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:121:4: ^( '>=' a= expr b= expr )
                    {
                    match(input,24,FOLLOW_24_in_proposicion265); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion269);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion273);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				try{
                      					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                      					Date _aDate = df.parse(a);
                      					Date _bDate = df.parse(b);
                      					valor = (_aDate.compareTo(_bDate) > 0 || _bDate.compareTo(_aDate) == 0 ) ? "1":"0";
                      				}catch(Exception fechas){
                      					try{
                      						valor = (Double.parseDouble(a) >= Double.parseDouble(b))?"1":"0";
                      					}catch(Exception numerica){
                      						valor = ((a.toLowerCase().compareTo(b.toLowerCase()) > 0) || (a.toLowerCase().compareTo(b.toLowerCase()) == 0))?"1":"0";
                      					}
                      				}
                      			
                    }

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:136:4: ^( '==' a= expr b= expr )
                    {
                    match(input,27,FOLLOW_27_in_proposicion285); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion289);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion293);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = ((a.toLowerCase()).equals(b.toLowerCase()))?"1":"0";
                      			
                    }

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:140:4: ^( '<>' a= expr b= expr )
                    {
                    match(input,28,FOLLOW_28_in_proposicion305); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion309);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion313);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = ((a.toLowerCase()).equals(b.toLowerCase()))?"0":"1";
                      			
                    }

                    }
                    break;
                case 7 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:144:4: ^( 'Y' a= proposicion b= proposicion )
                    {
                    match(input,20,FOLLOW_20_in_proposicion325); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion329);
                    a=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion333);
                    b=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = ((a).equals("1") && (b).equals("1"))?"1":"0";
                      			
                    }

                    }
                    break;
                case 8 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:148:4: ^( 'O' a= proposicion b= proposicion )
                    {
                    match(input,21,FOLLOW_21_in_proposicion345); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion349);
                    a=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion353);
                    b=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = ((a).equals("1") || (b).equals("1"))?"1":"0";
                      			
                    }

                    }
                    break;
                case 9 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:152:4: ^( 'NO' a= proposicion )
                    {
                    match(input,22,FOLLOW_22_in_proposicion365); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion369);
                    a=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				if(a.equals("1")){
                      					valor = "0";
                      				}else{
                      					valor = "1";
                      				}
                      			
                    }

                    }
                    break;
                case 10 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:160:4: BOOLEAN
                    {
                    BOOLEAN4=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_proposicion380); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = "0";
                      				if(((BOOLEAN4!=null?BOOLEAN4.getText():null)).equals("verdad")){
                      					valor = "1";
                      				}
                      			
                    }

                    }
                    break;
                case 11 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:167:4: expr
                    {
                    pushFollow(FOLLOW_expr_in_proposicion391);
                    expr5=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = expr5;
                      			
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, proposicion_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "proposicion"


    // $ANTLR start "expr"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:173:1: expr returns [String valor] : ( ^( '+' a= expr b= expr ) | ^( '-' a= expr b= expr ) | ^( '*' a= expr b= expr ) | ^( '/' a= expr b= expr ) | funcPredefinidas | ID | DOUBLE | STRING );
    public final String expr() throws RecognitionException {
        String valor = null;
        int expr_StartIndex = input.index();
        CommonTree ID7=null;
        CommonTree DOUBLE8=null;
        CommonTree STRING9=null;
        String a = null;

        String b = null;

        String funcPredefinidas6 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:174:2: ( ^( '+' a= expr b= expr ) | ^( '-' a= expr b= expr ) | ^( '*' a= expr b= expr ) | ^( '/' a= expr b= expr ) | funcPredefinidas | ID | DOUBLE | STRING )
            int alt7=8;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt7=1;
                }
                break;
            case 30:
                {
                alt7=2;
                }
                break;
            case 31:
                {
                alt7=3;
                }
                break;
            case 32:
                {
                alt7=4;
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
                alt7=5;
                }
                break;
            case ID:
                {
                alt7=6;
                }
                break;
            case DOUBLE:
                {
                alt7=7;
                }
                break;
            case STRING:
                {
                alt7=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return valor;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:174:4: ^( '+' a= expr b= expr )
                    {
                    match(input,29,FOLLOW_29_in_expr413); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr417);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr421);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
                      			
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:178:4: ^( '-' a= expr b= expr )
                    {
                    match(input,30,FOLLOW_30_in_expr433); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr437);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr441);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
                      			
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:182:4: ^( '*' a= expr b= expr )
                    {
                    match(input,31,FOLLOW_31_in_expr453); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr457);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr461);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
                      			
                    }

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:186:4: ^( '/' a= expr b= expr )
                    {
                    match(input,32,FOLLOW_32_in_expr473); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr477);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr481);
                    b=expr();

                    state._fsp--;
                    if (state.failed) return valor;

                    match(input, Token.UP, null); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = String.valueOf(Double.parseDouble(a) / Double.parseDouble(b));
                      			
                    }

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:190:4: funcPredefinidas
                    {
                    pushFollow(FOLLOW_funcPredefinidas_in_expr492);
                    funcPredefinidas6=funcPredefinidas();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = funcPredefinidas6;
                      			
                    }

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:194:4: ID
                    {
                    ID7=(CommonTree)match(input,ID,FOLLOW_ID_in_expr502); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				String v = (String)memory.get((ID7!=null?ID7.getText():null));
                      				if (v != null){
                      					valor = v;
                      				}else{
                      					memory.put((ID7!=null?ID7.getText():null), "");
                      				}
                      			
                    }

                    }
                    break;
                case 7 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:203:4: DOUBLE
                    {
                    DOUBLE8=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_expr513); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = new String((DOUBLE8!=null?DOUBLE8.getText():null));
                      			
                    }

                    }
                    break;
                case 8 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:207:4: STRING
                    {
                    STRING9=(CommonTree)match(input,STRING,FOLLOW_STRING_in_expr524); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				//Elimino las comillas dobles del texto del String
                      				valor = new String((STRING9!=null?STRING9.getText():null));
                      				valor = valor.substring(1,valor.length()-1);
                      			
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, expr_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "expr"


    // $ANTLR start "funcPredefinidas"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:216:1: funcPredefinidas returns [String valor] : ( promedio | sumatoria | contar | max | min | redondea | diff_fechas | concatenar );
    public final String funcPredefinidas() throws RecognitionException {
        String valor = null;
        int funcPredefinidas_StartIndex = input.index();
        String promedio10 = null;

        String sumatoria11 = null;

        String contar12 = null;

        String max13 = null;

        String min14 = null;

        String redondea15 = null;

        String diff_fechas16 = null;

        String concatenar17 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:217:2: ( promedio | sumatoria | contar | max | min | redondea | diff_fechas | concatenar )
            int alt8=8;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt8=1;
                }
                break;
            case 34:
                {
                alt8=2;
                }
                break;
            case 35:
                {
                alt8=3;
                }
                break;
            case 36:
                {
                alt8=4;
                }
                break;
            case 37:
                {
                alt8=5;
                }
                break;
            case 38:
                {
                alt8=6;
                }
                break;
            case 39:
                {
                alt8=7;
                }
                break;
            case 41:
                {
                alt8=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return valor;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:217:4: promedio
                    {
                    pushFollow(FOLLOW_promedio_in_funcPredefinidas546);
                    promedio10=promedio();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = promedio10;
                      			
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:221:4: sumatoria
                    {
                    pushFollow(FOLLOW_sumatoria_in_funcPredefinidas556);
                    sumatoria11=sumatoria();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = sumatoria11;
                      			
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:225:4: contar
                    {
                    pushFollow(FOLLOW_contar_in_funcPredefinidas566);
                    contar12=contar();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = contar12;
                      			
                    }

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:229:4: max
                    {
                    pushFollow(FOLLOW_max_in_funcPredefinidas576);
                    max13=max();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = max13;
                      			
                    }

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:233:4: min
                    {
                    pushFollow(FOLLOW_min_in_funcPredefinidas586);
                    min14=min();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = min14;
                      			
                    }

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:237:4: redondea
                    {
                    pushFollow(FOLLOW_redondea_in_funcPredefinidas596);
                    redondea15=redondea();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = redondea15;
                      			
                    }

                    }
                    break;
                case 7 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:241:4: diff_fechas
                    {
                    pushFollow(FOLLOW_diff_fechas_in_funcPredefinidas606);
                    diff_fechas16=diff_fechas();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = diff_fechas16;
                      			
                    }

                    }
                    break;
                case 8 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:245:4: concatenar
                    {
                    pushFollow(FOLLOW_concatenar_in_funcPredefinidas616);
                    concatenar17=concatenar();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = concatenar17;
                      			
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, funcPredefinidas_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "funcPredefinidas"


    // $ANTLR start "promedio"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:251:1: promedio returns [String valor] : ^( 'promedio' ID ) ;
    public final String promedio() throws RecognitionException {
        String valor = null;
        int promedio_StartIndex = input.index();
        CommonTree ID18=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:252:2: ( ^( 'promedio' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:252:4: ^( 'promedio' ID )
            {
            match(input,33,FOLLOW_33_in_promedio637); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID18=(CommonTree)match(input,ID,FOLLOW_ID_in_promedio639); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			//para permitir que se compartan las variables entre los usuarios
              			/*String v = (String)memory.get("promedio(["+(ID18!=null?ID18.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getPromedioField((ID18!=null?ID18.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("promedio(["+(ID18!=null?ID18.getText():null)+"])", valor);
              			}*/
              			try{
              				valor = EstudioPerso.getInstance().getPromedioField((ID18!=null?ID18.getText():null));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, promedio_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "promedio"


    // $ANTLR start "sumatoria"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:274:1: sumatoria returns [String valor] : ^( 'sumatoria' ID ) ;
    public final String sumatoria() throws RecognitionException {
        String valor = null;
        int sumatoria_StartIndex = input.index();
        CommonTree ID19=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:275:2: ( ^( 'sumatoria' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:275:4: ^( 'sumatoria' ID )
            {
            match(input,34,FOLLOW_34_in_sumatoria660); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_sumatoria662); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			//para permitir que se compartan las variables entre los usuarios
              			/*String v = (String)memory.get("sumatoria(["+(ID19!=null?ID19.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getSumatoriaField((ID19!=null?ID19.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("sumatoria(["+(ID19!=null?ID19.getText():null)+"])", valor);
              			}*/
              			try{
              				valor = EstudioPerso.getInstance().getSumatoriaField((ID19!=null?ID19.getText():null));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, sumatoria_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "sumatoria"


    // $ANTLR start "contar"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:297:1: contar returns [String valor] : ^( 'contar' ID ) ;
    public final String contar() throws RecognitionException {
        String valor = null;
        int contar_StartIndex = input.index();
        CommonTree ID20=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:298:2: ( ^( 'contar' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:298:4: ^( 'contar' ID )
            {
            match(input,35,FOLLOW_35_in_contar683); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID20=(CommonTree)match(input,ID,FOLLOW_ID_in_contar685); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			//para permitir que se compartan las variables entre los usuarios
              			/*String v = (String)memory.get("contar(["+(ID20!=null?ID20.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getContarField((ID20!=null?ID20.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("contar(["+(ID20!=null?ID20.getText():null)+"])", valor);
              			}*/
              			try{
              				valor = EstudioPerso.getInstance().getContarField((ID20!=null?ID20.getText():null));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, contar_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "contar"


    // $ANTLR start "max"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:320:1: max returns [String valor] : ^( 'max' ID ) ;
    public final String max() throws RecognitionException {
        String valor = null;
        int max_StartIndex = input.index();
        CommonTree ID21=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:321:2: ( ^( 'max' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:321:4: ^( 'max' ID )
            {
            match(input,36,FOLLOW_36_in_max706); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID21=(CommonTree)match(input,ID,FOLLOW_ID_in_max708); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			//para permitir que se compartan las variables entre los usuarios
              			/*String v = (String)memory.get("max(["+(ID21!=null?ID21.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getMaxField((ID21!=null?ID21.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("max(["+(ID21!=null?ID21.getText():null)+"])", valor);
              			}*/
              			try{
              				valor = EstudioPerso.getInstance().getMaxField((ID21!=null?ID21.getText():null));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, max_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "max"


    // $ANTLR start "min"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:343:1: min returns [String valor] : ^( 'min' ID ) ;
    public final String min() throws RecognitionException {
        String valor = null;
        int min_StartIndex = input.index();
        CommonTree ID22=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:344:2: ( ^( 'min' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:344:4: ^( 'min' ID )
            {
            match(input,37,FOLLOW_37_in_min729); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID22=(CommonTree)match(input,ID,FOLLOW_ID_in_min731); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			//para permitir que se compartan las variables entre los usuarios
              			/*String v = (String)memory.get("min(["+(ID22!=null?ID22.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getMinField((ID22!=null?ID22.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("min(["+(ID22!=null?ID22.getText():null)+"])", valor);
              			}*/
              			try{
              				valor = EstudioPerso.getInstance().getMinField((ID22!=null?ID22.getText():null));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, min_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "min"


    // $ANTLR start "redondea"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:366:1: redondea returns [String valor] : ^( 'redondea' expr ) ;
    public final String redondea() throws RecognitionException {
        String valor = null;
        int redondea_StartIndex = input.index();
        String expr23 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:367:2: ( ^( 'redondea' expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:367:4: ^( 'redondea' expr )
            {
            match(input,38,FOLLOW_38_in_redondea752); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_redondea754);
            expr23=expr();

            state._fsp--;
            if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			try{
              				valor = String.valueOf(Math.round(Double.parseDouble(expr23)));
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, redondea_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "redondea"


    // $ANTLR start "diff_fechas"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:377:1: diff_fechas returns [String valor] : ^( 'diff_fechas' a= expr b= expr ) ;
    public final String diff_fechas() throws RecognitionException {
        String valor = null;
        int diff_fechas_StartIndex = input.index();
        String a = null;

        String b = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:378:2: ( ^( 'diff_fechas' a= expr b= expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:378:4: ^( 'diff_fechas' a= expr b= expr )
            {
            match(input,39,FOLLOW_39_in_diff_fechas775); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_diff_fechas779);
            a=expr();

            state._fsp--;
            if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_diff_fechas783);
            b=expr();

            state._fsp--;
            if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			try{
              				valor = EstudioPerso.getInstance().diff_fechas(a, b);
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, diff_fechas_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "diff_fechas"


    // $ANTLR start "concatenar"
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:388:1: concatenar returns [String valor] : ^( 'concatenar' a= expr b= expr ) ;
    public final String concatenar() throws RecognitionException {
        String valor = null;
        int concatenar_StartIndex = input.index();
        String a = null;

        String b = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:389:2: ( ^( 'concatenar' a= expr b= expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:389:4: ^( 'concatenar' a= expr b= expr )
            {
            match(input,41,FOLLOW_41_in_concatenar804); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_concatenar808);
            a=expr();

            state._fsp--;
            if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_concatenar812);
            b=expr();

            state._fsp--;
            if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			try{
              				valor = a+b;
              			}catch(Exception proEx){
              				throw new RecognitionException();
              			}
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, concatenar_StartIndex); }
        }
        return valor;
    }
    // $ANTLR end "concatenar"

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog59 = new BitSet(new long[]{0x0000000000019C02L});
    public static final BitSet FOLLOW_condicional_in_stat71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_stat80 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_10_in_stat93 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_stat95 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_proposicion_in_stat97 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_12_in_stat109 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_stat121 = new BitSet(new long[]{0x0000000000019C08L});
    public static final BitSet FOLLOW_15_in_stat134 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_stat146 = new BitSet(new long[]{0x0000000000019C08L});
    public static final BitSet FOLLOW_16_in_condicional166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_condicional170 = new BitSet(new long[]{0x0000000000019C08L});
    public static final BitSet FOLLOW_stat_in_condicional182 = new BitSet(new long[]{0x0000000000019C08L});
    public static final BitSet FOLLOW_25_in_proposicion205 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion209 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion213 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_proposicion225 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion229 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion233 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_23_in_proposicion245 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion249 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion253 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_24_in_proposicion265 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion269 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_27_in_proposicion285 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion289 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion293 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_28_in_proposicion305 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion309 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_proposicion313 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_20_in_proposicion325 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion329 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_proposicion_in_proposicion333 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_21_in_proposicion345 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion349 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_proposicion_in_proposicion353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_proposicion365 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion369 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOLEAN_in_proposicion380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_proposicion391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_expr413 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr417 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_expr421 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_30_in_expr433 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr437 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_expr441 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_31_in_expr453 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr457 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_expr461 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expr473 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr477 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_expr481 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_funcPredefinidas_in_expr492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_expr513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expr524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_promedio_in_funcPredefinidas546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sumatoria_in_funcPredefinidas556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contar_in_funcPredefinidas566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_max_in_funcPredefinidas576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_min_in_funcPredefinidas586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_redondea_in_funcPredefinidas596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_diff_fechas_in_funcPredefinidas606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_concatenar_in_funcPredefinidas616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_promedio637 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_promedio639 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_34_in_sumatoria660 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_sumatoria662 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_contar683 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_contar685 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_max706 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_max708 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_37_in_min729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_min731 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_redondea752 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_redondea754 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_diff_fechas775 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_diff_fechas779 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_diff_fechas783 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_41_in_concatenar804 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_concatenar808 = new BitSet(new long[]{0x000002FFFFF001D0L});
    public static final BitSet FOLLOW_expr_in_concatenar812 = new BitSet(new long[]{0x0000000000000008L});

}