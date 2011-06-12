// $ANTLR 3.2 Sep 23, 2009 12:02:23 Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g 2011-06-12 12:25:22

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "NEWLINE", "BOOLEAN", "STRING", "DOUBLE", "WS", "'='", "'obtener'", "'si'", "'('", "';'", "')'", "'Y'", "'O'", "'NO'", "'>'", "'>='", "'<'", "'<='", "'=='", "'<>'", "'+'", "'-'", "'*'", "'/'", "'promedio'", "'sumatoria'", "'contar'", "'max'", "'min'", "'redondea'", "'diff_fechas'", "','"
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
    public static final int T__11=11;
    public static final int T__14=14;
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
            this.state.ruleMemo = new HashMap[40+1];
             
             
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

                if ( ((LA1_0>=10 && LA1_0<=12)) ) {
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:26:1: stat[boolean accionar] : ( condicional[accionar] | ^( 'obtener' proposicion ) | ^( '=' ID proposicion ) );
    public final void stat(boolean accionar) throws RecognitionException {
        int stat_StartIndex = input.index();
        CommonTree ID2=null;
        String proposicion1 = null;

        String proposicion3 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return ; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:27:2: ( condicional[accionar] | ^( 'obtener' proposicion ) | ^( '=' ID proposicion ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt2=1;
                }
                break;
            case 11:
                {
                alt2=2;
                }
                break;
            case 10:
                {
                alt2=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:42:1: condicional[boolean accionar] : ^( 'si' cond= proposicion ( stat[accionar] )+ ) ;
    public final void condicional(boolean accionar) throws RecognitionException {
        int condicional_StartIndex = input.index();
        String cond = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return ; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:43:2: ( ^( 'si' cond= proposicion ( stat[accionar] )+ ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:43:4: ^( 'si' cond= proposicion ( stat[accionar] )+ )
            {
            match(input,12,FOLLOW_12_in_condicional116); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_proposicion_in_condicional120);
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
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:51:4: ( stat[accionar] )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=10 && LA3_0<=12)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:51:5: stat[accionar]
            	    {
            	    pushFollow(FOLLOW_stat_in_condicional132);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:55:1: proposicion returns [String valor] : ( ^( '<' a= expr b= expr ) | ^( '<=' a= expr b= expr ) | ^( '>' a= expr b= expr ) | ^( '>=' a= expr b= expr ) | ^( '==' a= expr b= expr ) | ^( '<>' a= expr b= expr ) | ^( 'Y' a= proposicion b= proposicion ) | ^( 'O' a= proposicion b= proposicion ) | ^( 'NO' a= proposicion ) | BOOLEAN | expr );
    public final String proposicion() throws RecognitionException {
        String valor = null;
        int proposicion_StartIndex = input.index();
        CommonTree BOOLEAN4=null;
        String a = null;

        String b = null;

        String expr5 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:56:2: ( ^( '<' a= expr b= expr ) | ^( '<=' a= expr b= expr ) | ^( '>' a= expr b= expr ) | ^( '>=' a= expr b= expr ) | ^( '==' a= expr b= expr ) | ^( '<>' a= expr b= expr ) | ^( 'Y' a= proposicion b= proposicion ) | ^( 'O' a= proposicion b= proposicion ) | ^( 'NO' a= proposicion ) | BOOLEAN | expr )
            int alt4=11;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt4=1;
                }
                break;
            case 22:
                {
                alt4=2;
                }
                break;
            case 19:
                {
                alt4=3;
                }
                break;
            case 20:
                {
                alt4=4;
                }
                break;
            case 23:
                {
                alt4=5;
                }
                break;
            case 24:
                {
                alt4=6;
                }
                break;
            case 16:
                {
                alt4=7;
                }
                break;
            case 17:
                {
                alt4=8;
                }
                break;
            case 18:
                {
                alt4=9;
                }
                break;
            case BOOLEAN:
                {
                alt4=10;
                }
                break;
            case ID:
            case STRING:
            case DOUBLE:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                {
                alt4=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return valor;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:56:4: ^( '<' a= expr b= expr )
                    {
                    match(input,21,FOLLOW_21_in_proposicion155); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion159);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion163);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:71:4: ^( '<=' a= expr b= expr )
                    {
                    match(input,22,FOLLOW_22_in_proposicion175); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion179);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion183);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:86:4: ^( '>' a= expr b= expr )
                    {
                    match(input,19,FOLLOW_19_in_proposicion195); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion199);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion203);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:101:4: ^( '>=' a= expr b= expr )
                    {
                    match(input,20,FOLLOW_20_in_proposicion215); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion219);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion223);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:116:4: ^( '==' a= expr b= expr )
                    {
                    match(input,23,FOLLOW_23_in_proposicion235); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion239);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion243);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:120:4: ^( '<>' a= expr b= expr )
                    {
                    match(input,24,FOLLOW_24_in_proposicion255); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion259);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_proposicion263);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:124:4: ^( 'Y' a= proposicion b= proposicion )
                    {
                    match(input,16,FOLLOW_16_in_proposicion275); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion279);
                    a=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion283);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:128:4: ^( 'O' a= proposicion b= proposicion )
                    {
                    match(input,17,FOLLOW_17_in_proposicion295); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion299);
                    a=proposicion();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion303);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:132:4: ^( 'NO' a= proposicion )
                    {
                    match(input,18,FOLLOW_18_in_proposicion315); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_proposicion_in_proposicion319);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:140:4: BOOLEAN
                    {
                    BOOLEAN4=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_proposicion330); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = "0";
                      				if(((BOOLEAN4!=null?BOOLEAN4.getText():null)).equals("verdad")){
                      					valor = "1";
                      				}
                      			
                    }

                    }
                    break;
                case 11 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:147:4: expr
                    {
                    pushFollow(FOLLOW_expr_in_proposicion341);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:153:1: expr returns [String valor] : ( ^( '+' a= expr b= expr ) | ^( '-' a= expr b= expr ) | ^( '*' a= expr b= expr ) | ^( '/' a= expr b= expr ) | funcPredefinidas | ID | DOUBLE | STRING );
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
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:154:2: ( ^( '+' a= expr b= expr ) | ^( '-' a= expr b= expr ) | ^( '*' a= expr b= expr ) | ^( '/' a= expr b= expr ) | funcPredefinidas | ID | DOUBLE | STRING )
            int alt5=8;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt5=1;
                }
                break;
            case 26:
                {
                alt5=2;
                }
                break;
            case 27:
                {
                alt5=3;
                }
                break;
            case 28:
                {
                alt5=4;
                }
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
                {
                alt5=5;
                }
                break;
            case ID:
                {
                alt5=6;
                }
                break;
            case DOUBLE:
                {
                alt5=7;
                }
                break;
            case STRING:
                {
                alt5=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return valor;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:154:4: ^( '+' a= expr b= expr )
                    {
                    match(input,25,FOLLOW_25_in_expr363); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr367);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr371);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:158:4: ^( '-' a= expr b= expr )
                    {
                    match(input,26,FOLLOW_26_in_expr383); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr387);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr391);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:162:4: ^( '*' a= expr b= expr )
                    {
                    match(input,27,FOLLOW_27_in_expr403); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr407);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr411);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:166:4: ^( '/' a= expr b= expr )
                    {
                    match(input,28,FOLLOW_28_in_expr423); if (state.failed) return valor;

                    match(input, Token.DOWN, null); if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr427);
                    a=expr();

                    state._fsp--;
                    if (state.failed) return valor;
                    pushFollow(FOLLOW_expr_in_expr431);
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:170:4: funcPredefinidas
                    {
                    pushFollow(FOLLOW_funcPredefinidas_in_expr442);
                    funcPredefinidas6=funcPredefinidas();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = funcPredefinidas6;
                      			
                    }

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:174:4: ID
                    {
                    ID7=(CommonTree)match(input,ID,FOLLOW_ID_in_expr452); if (state.failed) return valor;
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:183:4: DOUBLE
                    {
                    DOUBLE8=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_expr463); if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = new String((DOUBLE8!=null?DOUBLE8.getText():null));
                      			
                    }

                    }
                    break;
                case 8 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:187:4: STRING
                    {
                    STRING9=(CommonTree)match(input,STRING,FOLLOW_STRING_in_expr474); if (state.failed) return valor;
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:196:1: funcPredefinidas returns [String valor] : ( promedio | sumatoria | contar | max | min | redondea | diff_fechas );
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


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:197:2: ( promedio | sumatoria | contar | max | min | redondea | diff_fechas )
            int alt6=7;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt6=1;
                }
                break;
            case 30:
                {
                alt6=2;
                }
                break;
            case 31:
                {
                alt6=3;
                }
                break;
            case 32:
                {
                alt6=4;
                }
                break;
            case 33:
                {
                alt6=5;
                }
                break;
            case 34:
                {
                alt6=6;
                }
                break;
            case 35:
                {
                alt6=7;
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
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:197:4: promedio
                    {
                    pushFollow(FOLLOW_promedio_in_funcPredefinidas496);
                    promedio10=promedio();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = promedio10;
                      			
                    }

                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:201:4: sumatoria
                    {
                    pushFollow(FOLLOW_sumatoria_in_funcPredefinidas506);
                    sumatoria11=sumatoria();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = sumatoria11;
                      			
                    }

                    }
                    break;
                case 3 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:205:4: contar
                    {
                    pushFollow(FOLLOW_contar_in_funcPredefinidas516);
                    contar12=contar();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = contar12;
                      			
                    }

                    }
                    break;
                case 4 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:209:4: max
                    {
                    pushFollow(FOLLOW_max_in_funcPredefinidas526);
                    max13=max();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = max13;
                      			
                    }

                    }
                    break;
                case 5 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:213:4: min
                    {
                    pushFollow(FOLLOW_min_in_funcPredefinidas536);
                    min14=min();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = min14;
                      			
                    }

                    }
                    break;
                case 6 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:217:4: redondea
                    {
                    pushFollow(FOLLOW_redondea_in_funcPredefinidas546);
                    redondea15=redondea();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = redondea15;
                      			
                    }

                    }
                    break;
                case 7 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:221:4: diff_fechas
                    {
                    pushFollow(FOLLOW_diff_fechas_in_funcPredefinidas556);
                    diff_fechas16=diff_fechas();

                    state._fsp--;
                    if (state.failed) return valor;
                    if ( state.backtracking==0 ) {

                      				valor = diff_fechas16;
                      			
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:227:1: promedio returns [String valor] : ^( 'promedio' ID ) ;
    public final String promedio() throws RecognitionException {
        String valor = null;
        int promedio_StartIndex = input.index();
        CommonTree ID17=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:228:2: ( ^( 'promedio' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:228:4: ^( 'promedio' ID )
            {
            match(input,29,FOLLOW_29_in_promedio577); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID17=(CommonTree)match(input,ID,FOLLOW_ID_in_promedio579); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			String v = (String)memory.get("promedio(["+(ID17!=null?ID17.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getPromedioField((ID17!=null?ID17.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("promedio(["+(ID17!=null?ID17.getText():null)+"])", valor);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:244:1: sumatoria returns [String valor] : ^( 'sumatoria' ID ) ;
    public final String sumatoria() throws RecognitionException {
        String valor = null;
        int sumatoria_StartIndex = input.index();
        CommonTree ID18=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:245:2: ( ^( 'sumatoria' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:245:4: ^( 'sumatoria' ID )
            {
            match(input,30,FOLLOW_30_in_sumatoria600); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID18=(CommonTree)match(input,ID,FOLLOW_ID_in_sumatoria602); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			String v = (String)memory.get("sumatoria(["+(ID18!=null?ID18.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getSumatoriaField((ID18!=null?ID18.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("sumatoria(["+(ID18!=null?ID18.getText():null)+"])", valor);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:261:1: contar returns [String valor] : ^( 'contar' ID ) ;
    public final String contar() throws RecognitionException {
        String valor = null;
        int contar_StartIndex = input.index();
        CommonTree ID19=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:262:2: ( ^( 'contar' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:262:4: ^( 'contar' ID )
            {
            match(input,31,FOLLOW_31_in_contar623); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_contar625); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			String v = (String)memory.get("contar(["+(ID19!=null?ID19.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getContarField((ID19!=null?ID19.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("contar(["+(ID19!=null?ID19.getText():null)+"])", valor);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:278:1: max returns [String valor] : ^( 'max' ID ) ;
    public final String max() throws RecognitionException {
        String valor = null;
        int max_StartIndex = input.index();
        CommonTree ID20=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:279:2: ( ^( 'max' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:279:4: ^( 'max' ID )
            {
            match(input,32,FOLLOW_32_in_max646); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID20=(CommonTree)match(input,ID,FOLLOW_ID_in_max648); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			String v = (String)memory.get("max(["+(ID20!=null?ID20.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getMaxField((ID20!=null?ID20.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("max(["+(ID20!=null?ID20.getText():null)+"])", valor);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:295:1: min returns [String valor] : ^( 'min' ID ) ;
    public final String min() throws RecognitionException {
        String valor = null;
        int min_StartIndex = input.index();
        CommonTree ID21=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:296:2: ( ^( 'min' ID ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:296:4: ^( 'min' ID )
            {
            match(input,33,FOLLOW_33_in_min669); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            ID21=(CommonTree)match(input,ID,FOLLOW_ID_in_min671); if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			String v = (String)memory.get("min(["+(ID21!=null?ID21.getText():null)+"])");
              			if (v != null){
              				valor = v;
              			}else{
              				try{
              					valor = EstudioPerso.getInstance().getMinField((ID21!=null?ID21.getText():null));
              				}catch(Exception proEx){
              					throw new RecognitionException();
              				}
              				memory.put("min(["+(ID21!=null?ID21.getText():null)+"])", valor);
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:312:1: redondea returns [String valor] : ^( 'redondea' expr ) ;
    public final String redondea() throws RecognitionException {
        String valor = null;
        int redondea_StartIndex = input.index();
        String expr22 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:313:2: ( ^( 'redondea' expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:313:4: ^( 'redondea' expr )
            {
            match(input,34,FOLLOW_34_in_redondea692); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_redondea694);
            expr22=expr();

            state._fsp--;
            if (state.failed) return valor;

            match(input, Token.UP, null); if (state.failed) return valor;
            if ( state.backtracking==0 ) {

              			try{
              				valor = String.valueOf(Math.round(Double.parseDouble(expr22)));
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
    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:323:1: diff_fechas returns [String valor] : ^( 'diff_fechas' a= expr b= expr ) ;
    public final String diff_fechas() throws RecognitionException {
        String valor = null;
        int diff_fechas_StartIndex = input.index();
        String a = null;

        String b = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return valor; }
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:324:2: ( ^( 'diff_fechas' a= expr b= expr ) )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneTree.g:324:4: ^( 'diff_fechas' a= expr b= expr )
            {
            match(input,35,FOLLOW_35_in_diff_fechas715); if (state.failed) return valor;

            match(input, Token.DOWN, null); if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_diff_fechas719);
            a=expr();

            state._fsp--;
            if (state.failed) return valor;
            pushFollow(FOLLOW_expr_in_diff_fechas723);
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

    // Delegated rules


 

    public static final BitSet FOLLOW_stat_in_prog59 = new BitSet(new long[]{0x0000000000001C02L});
    public static final BitSet FOLLOW_condicional_in_stat71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_stat78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_stat80 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_10_in_stat93 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_stat95 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_proposicion_in_stat97 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_12_in_condicional116 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_condicional120 = new BitSet(new long[]{0x0000000000001C08L});
    public static final BitSet FOLLOW_stat_in_condicional132 = new BitSet(new long[]{0x0000000000001C08L});
    public static final BitSet FOLLOW_21_in_proposicion155 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion159 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion163 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_proposicion175 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion179 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion183 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_19_in_proposicion195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion199 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion203 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_20_in_proposicion215 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion219 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion223 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_23_in_proposicion235 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion239 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_24_in_proposicion255 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_proposicion259 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_proposicion263 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_16_in_proposicion275 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion279 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_proposicion_in_proposicion283 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_17_in_proposicion295 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion299 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_proposicion_in_proposicion303 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_18_in_proposicion315 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_proposicion_in_proposicion319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOLEAN_in_proposicion330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_proposicion341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_expr363 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr367 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_expr371 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_expr383 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr387 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_expr391 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_27_in_expr403 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr407 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_expr411 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_28_in_expr423 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr427 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_expr431 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_funcPredefinidas_in_expr442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_in_expr463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expr474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_promedio_in_funcPredefinidas496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sumatoria_in_funcPredefinidas506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contar_in_funcPredefinidas516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_max_in_funcPredefinidas526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_min_in_funcPredefinidas536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_redondea_in_funcPredefinidas546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_diff_fechas_in_funcPredefinidas556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_promedio577 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_promedio579 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_30_in_sumatoria600 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_sumatoria602 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_31_in_contar623 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_contar625 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_max646 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_max648 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_33_in_min669 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_min671 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_34_in_redondea692 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_redondea694 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_diff_fechas715 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_diff_fechas719 = new BitSet(new long[]{0x0000000FFFFF01D0L});
    public static final BitSet FOLLOW_expr_in_diff_fechas723 = new BitSet(new long[]{0x0000000000000008L});

}