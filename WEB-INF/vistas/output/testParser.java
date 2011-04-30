// $ANTLR 3.2 Sep 23, 2009 12:02:23 \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g 2011-04-29 21:58:55

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
public class testParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NUMERO", "IDENTIFICADOR", "TEXTO", "CARACTER", "FINLINEA", "'inicio'", "'fin'", "'proceso'", "'('", "')'", "','", "'='", "'imprimir'", "';'", "'pedir'", "'variable'", "'real'", "'caracter'", "'booleana'", "'entera'", "'+'", "'-'", "'*'", "'/'", "'&&'", "'||'", "'!'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'si'", "'entonces'", "'sino'", "'{'", "'}'", "'mientras'", "'haga'", "'para'", "'seleccion'", "'caso'", "'salir'", "'continuar'", "'retornar'"
    };
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int NUMERO=4;
    public static final int T__25=25;
    public static final int FINLINEA=8;
    public static final int TEXTO=6;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int IDENTIFICADOR=5;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int CARACTER=7;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "expresion", "iContinuar", "suma", "iPara", "funcion", 
        "producto", "bloqueCasos", "sino", "iCrearVariable", "bloque", "iPedir", 
        "iRetornar", "tipo", "iMientras", "listaparametros", "comparacion", 
        "iSalir", "iHaga", "programa", "iAsignar", "iImprimir", "iSeleccion", 
        "parametro", "factor", "instruccion", "caso", "proposicion", "iSi"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public testParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public testParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this, port, null);
            setDebugListener(proxy);
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
        }
    public testParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg, new RecognizerSharedState());

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return testParser.tokenNames; }
    public String getGrammarFileName() { return "\\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g"; }



    // $ANTLR start "programa"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:1: programa : ( funcion )* 'inicio' ( instruccion )* 'fin' ;
    public final void programa() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "programa");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(11, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:9: ( ( funcion )* 'inicio' ( instruccion )* 'fin' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:11: ( funcion )* 'inicio' ( instruccion )* 'fin'
            {
            dbg.location(11,11);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:11: ( funcion )*
            try { dbg.enterSubRule(1);

            loop1:
            do {
                int alt1=2;
                try { dbg.enterDecision(1);

                int LA1_0 = input.LA(1);

                if ( (LA1_0==11||(LA1_0>=20 && LA1_0<=23)) ) {
                    alt1=1;
                }


                } finally {dbg.exitDecision(1);}

                switch (alt1) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:11: funcion
            	    {
            	    dbg.location(11,11);
            	    pushFollow(FOLLOW_funcion_in_programa148);
            	    funcion();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);
            } finally {dbg.exitSubRule(1);}

            dbg.location(11,20);
            match(input,9,FOLLOW_9_in_programa151); 
            dbg.location(11,31);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:31: ( instruccion )*
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2);

                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENTIFICADOR||LA2_0==16||(LA2_0>=18 && LA2_0<=19)||LA2_0==37||(LA2_0>=42 && LA2_0<=45)||(LA2_0>=47 && LA2_0<=49)) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:31: instruccion
            	    {
            	    dbg.location(11,31);
            	    pushFollow(FOLLOW_instruccion_in_programa155);
            	    instruccion();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);
            } finally {dbg.exitSubRule(2);}

            dbg.location(11,44);
            match(input,10,FOLLOW_10_in_programa158); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(11, 49);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "programa");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "programa"


    // $ANTLR start "funcion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:12:1: funcion : ( tipo | 'proceso' ) IDENTIFICADOR '(' ( listaparametros )? ')' bloque ;
    public final void funcion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "funcion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(12, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:2: ( ( tipo | 'proceso' ) IDENTIFICADOR '(' ( listaparametros )? ')' bloque )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:4: ( tipo | 'proceso' ) IDENTIFICADOR '(' ( listaparametros )? ')' bloque
            {
            dbg.location(13,4);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:4: ( tipo | 'proceso' )
            int alt3=2;
            try { dbg.enterSubRule(3);
            try { dbg.enterDecision(3);

            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=20 && LA3_0<=23)) ) {
                alt3=1;
            }
            else if ( (LA3_0==11) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:5: tipo
                    {
                    dbg.location(13,5);
                    pushFollow(FOLLOW_tipo_in_funcion167);
                    tipo();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:10: 'proceso'
                    {
                    dbg.location(13,10);
                    match(input,11,FOLLOW_11_in_funcion169); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(3);}

            dbg.location(13,21);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_funcion172); 
            dbg.location(13,35);
            match(input,12,FOLLOW_12_in_funcion174); 
            dbg.location(13,39);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:39: ( listaparametros )?
            int alt4=2;
            try { dbg.enterSubRule(4);
            try { dbg.enterDecision(4);

            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=20 && LA4_0<=23)) ) {
                alt4=1;
            }
            } finally {dbg.exitDecision(4);}

            switch (alt4) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:39: listaparametros
                    {
                    dbg.location(13,39);
                    pushFollow(FOLLOW_listaparametros_in_funcion176);
                    listaparametros();

                    state._fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(4);}

            dbg.location(13,56);
            match(input,13,FOLLOW_13_in_funcion179); 
            dbg.location(13,62);
            pushFollow(FOLLOW_bloque_in_funcion183);
            bloque();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(13, 68);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "funcion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "funcion"


    // $ANTLR start "listaparametros"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:1: listaparametros : parametro ( ',' parametro )* ;
    public final void listaparametros() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "listaparametros");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(14, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:16: ( parametro ( ',' parametro )* )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:18: parametro ( ',' parametro )*
            {
            dbg.location(14,18);
            pushFollow(FOLLOW_parametro_in_listaparametros189);
            parametro();

            state._fsp--;

            dbg.location(14,28);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:28: ( ',' parametro )*
            try { dbg.enterSubRule(5);

            loop5:
            do {
                int alt5=2;
                try { dbg.enterDecision(5);

                int LA5_0 = input.LA(1);

                if ( (LA5_0==14) ) {
                    alt5=1;
                }


                } finally {dbg.exitDecision(5);}

                switch (alt5) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:29: ',' parametro
            	    {
            	    dbg.location(14,29);
            	    match(input,14,FOLLOW_14_in_listaparametros192); 
            	    dbg.location(14,33);
            	    pushFollow(FOLLOW_parametro_in_listaparametros194);
            	    parametro();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);
            } finally {dbg.exitSubRule(5);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(14, 44);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "listaparametros");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "listaparametros"


    // $ANTLR start "parametro"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:15:1: parametro : tipo IDENTIFICADOR ( '=' ( NUMERO | CARACTER | TEXTO ) )? ;
    public final void parametro() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "parametro");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(15, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:2: ( tipo IDENTIFICADOR ( '=' ( NUMERO | CARACTER | TEXTO ) )? )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:4: tipo IDENTIFICADOR ( '=' ( NUMERO | CARACTER | TEXTO ) )?
            {
            dbg.location(16,4);
            pushFollow(FOLLOW_tipo_in_parametro204);
            tipo();

            state._fsp--;

            dbg.location(16,9);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_parametro206); 
            dbg.location(16,23);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:23: ( '=' ( NUMERO | CARACTER | TEXTO ) )?
            int alt6=2;
            try { dbg.enterSubRule(6);
            try { dbg.enterDecision(6);

            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:24: '=' ( NUMERO | CARACTER | TEXTO )
                    {
                    dbg.location(16,24);
                    match(input,15,FOLLOW_15_in_parametro209); 
                    dbg.location(16,28);
                    if ( input.LA(1)==NUMERO||(input.LA(1)>=TEXTO && input.LA(1)<=CARACTER) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(6);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(16, 53);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "parametro");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "parametro"


    // $ANTLR start "instruccion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:17:1: instruccion : ( iImprimir | iPedir | iAsignar | iCrearVariable | iSi | iMientras | iPara | iHaga | iSeleccion | iSalir | iContinuar | iRetornar );
    public final void instruccion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "instruccion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(17, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:2: ( iImprimir | iPedir | iAsignar | iCrearVariable | iSi | iMientras | iPara | iHaga | iSeleccion | iSalir | iContinuar | iRetornar )
            int alt7=12;
            try { dbg.enterDecision(7);

            switch ( input.LA(1) ) {
            case 16:
                {
                alt7=1;
                }
                break;
            case 18:
                {
                alt7=2;
                }
                break;
            case IDENTIFICADOR:
                {
                alt7=3;
                }
                break;
            case 19:
                {
                alt7=4;
                }
                break;
            case 37:
                {
                alt7=5;
                }
                break;
            case 42:
                {
                alt7=6;
                }
                break;
            case 44:
                {
                alt7=7;
                }
                break;
            case 43:
                {
                alt7=8;
                }
                break;
            case 45:
                {
                alt7=9;
                }
                break;
            case 47:
                {
                alt7=10;
                }
                break;
            case 48:
                {
                alt7=11;
                }
                break;
            case 49:
                {
                alt7=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(7);}

            switch (alt7) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:4: iImprimir
                    {
                    dbg.location(18,4);
                    pushFollow(FOLLOW_iImprimir_in_instruccion227);
                    iImprimir();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:14: iPedir
                    {
                    dbg.location(18,14);
                    pushFollow(FOLLOW_iPedir_in_instruccion229);
                    iPedir();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:21: iAsignar
                    {
                    dbg.location(18,21);
                    pushFollow(FOLLOW_iAsignar_in_instruccion231);
                    iAsignar();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:30: iCrearVariable
                    {
                    dbg.location(18,30);
                    pushFollow(FOLLOW_iCrearVariable_in_instruccion233);
                    iCrearVariable();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:45: iSi
                    {
                    dbg.location(18,45);
                    pushFollow(FOLLOW_iSi_in_instruccion235);
                    iSi();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:49: iMientras
                    {
                    dbg.location(18,49);
                    pushFollow(FOLLOW_iMientras_in_instruccion237);
                    iMientras();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:59: iPara
                    {
                    dbg.location(18,59);
                    pushFollow(FOLLOW_iPara_in_instruccion239);
                    iPara();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:65: iHaga
                    {
                    dbg.location(18,65);
                    pushFollow(FOLLOW_iHaga_in_instruccion241);
                    iHaga();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    dbg.enterAlt(9);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:71: iSeleccion
                    {
                    dbg.location(18,71);
                    pushFollow(FOLLOW_iSeleccion_in_instruccion243);
                    iSeleccion();

                    state._fsp--;


                    }
                    break;
                case 10 :
                    dbg.enterAlt(10);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:82: iSalir
                    {
                    dbg.location(18,82);
                    pushFollow(FOLLOW_iSalir_in_instruccion245);
                    iSalir();

                    state._fsp--;


                    }
                    break;
                case 11 :
                    dbg.enterAlt(11);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:89: iContinuar
                    {
                    dbg.location(18,89);
                    pushFollow(FOLLOW_iContinuar_in_instruccion247);
                    iContinuar();

                    state._fsp--;


                    }
                    break;
                case 12 :
                    dbg.enterAlt(12);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:100: iRetornar
                    {
                    dbg.location(18,100);
                    pushFollow(FOLLOW_iRetornar_in_instruccion249);
                    iRetornar();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(18, 109);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "instruccion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "instruccion"


    // $ANTLR start "iImprimir"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:19:1: iImprimir : 'imprimir' ( TEXTO | CARACTER | NUMERO | IDENTIFICADOR ) ';' ;
    public final void iImprimir() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iImprimir");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(19, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:20:2: ( 'imprimir' ( TEXTO | CARACTER | NUMERO | IDENTIFICADOR ) ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:20:4: 'imprimir' ( TEXTO | CARACTER | NUMERO | IDENTIFICADOR ) ';'
            {
            dbg.location(20,4);
            match(input,16,FOLLOW_16_in_iImprimir257); 
            dbg.location(20,15);
            if ( (input.LA(1)>=NUMERO && input.LA(1)<=CARACTER) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(20,53);
            match(input,17,FOLLOW_17_in_iImprimir269); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(20, 57);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iImprimir");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iImprimir"


    // $ANTLR start "iPedir"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:21:1: iPedir : 'pedir' IDENTIFICADOR ';' ;
    public final void iPedir() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iPedir");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(21, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:21:8: ( 'pedir' IDENTIFICADOR ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:21:10: 'pedir' IDENTIFICADOR ';'
            {
            dbg.location(21,10);
            match(input,18,FOLLOW_18_in_iPedir277); 
            dbg.location(21,18);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_iPedir279); 
            dbg.location(21,32);
            match(input,17,FOLLOW_17_in_iPedir281); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(21, 35);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iPedir");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iPedir"


    // $ANTLR start "iCrearVariable"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:22:1: iCrearVariable : 'variable' tipo IDENTIFICADOR ( '=' ( TEXTO | NUMERO | CARACTER ) )? ';' ;
    public final void iCrearVariable() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iCrearVariable");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(22, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:2: ( 'variable' tipo IDENTIFICADOR ( '=' ( TEXTO | NUMERO | CARACTER ) )? ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:4: 'variable' tipo IDENTIFICADOR ( '=' ( TEXTO | NUMERO | CARACTER ) )? ';'
            {
            dbg.location(23,4);
            match(input,19,FOLLOW_19_in_iCrearVariable289); 
            dbg.location(23,15);
            pushFollow(FOLLOW_tipo_in_iCrearVariable291);
            tipo();

            state._fsp--;

            dbg.location(23,20);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_iCrearVariable293); 
            dbg.location(23,34);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:34: ( '=' ( TEXTO | NUMERO | CARACTER ) )?
            int alt8=2;
            try { dbg.enterSubRule(8);
            try { dbg.enterDecision(8);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==15) ) {
                alt8=1;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:35: '=' ( TEXTO | NUMERO | CARACTER )
                    {
                    dbg.location(23,35);
                    match(input,15,FOLLOW_15_in_iCrearVariable296); 
                    dbg.location(23,39);
                    if ( input.LA(1)==NUMERO||(input.LA(1)>=TEXTO && input.LA(1)<=CARACTER) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(23,65);
            match(input,17,FOLLOW_17_in_iCrearVariable308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(23, 68);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iCrearVariable");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iCrearVariable"


    // $ANTLR start "tipo"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:24:1: tipo : ( 'real' | 'caracter' | 'booleana' | 'entera' ) ;
    public final void tipo() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tipo");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(24, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:24:6: ( ( 'real' | 'caracter' | 'booleana' | 'entera' ) )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:24:8: ( 'real' | 'caracter' | 'booleana' | 'entera' )
            {
            dbg.location(24,8);
            if ( (input.LA(1)>=20 && input.LA(1)<=23) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(24, 47);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "tipo");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tipo"


    // $ANTLR start "iAsignar"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:25:1: iAsignar : IDENTIFICADOR '=' expresion ';' ;
    public final void iAsignar() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iAsignar");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(25, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:25:9: ( IDENTIFICADOR '=' expresion ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:25:11: IDENTIFICADOR '=' expresion ';'
            {
            dbg.location(25,11);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_iAsignar329); 
            dbg.location(25,25);
            match(input,15,FOLLOW_15_in_iAsignar331); 
            dbg.location(25,29);
            pushFollow(FOLLOW_expresion_in_iAsignar333);
            expresion();

            state._fsp--;

            dbg.location(25,39);
            match(input,17,FOLLOW_17_in_iAsignar335); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(25, 42);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iAsignar");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iAsignar"


    // $ANTLR start "expresion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:26:1: expresion : suma ( ( '+' | '-' ) suma )* ;
    public final void expresion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expresion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(26, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:2: ( suma ( ( '+' | '-' ) suma )* )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:4: suma ( ( '+' | '-' ) suma )*
            {
            dbg.location(27,4);
            pushFollow(FOLLOW_suma_in_expresion343);
            suma();

            state._fsp--;

            dbg.location(27,9);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:9: ( ( '+' | '-' ) suma )*
            try { dbg.enterSubRule(9);

            loop9:
            do {
                int alt9=2;
                try { dbg.enterDecision(9);

                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=24 && LA9_0<=25)) ) {
                    alt9=1;
                }


                } finally {dbg.exitDecision(9);}

                switch (alt9) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:10: ( '+' | '-' ) suma
            	    {
            	    dbg.location(27,10);
            	    if ( (input.LA(1)>=24 && input.LA(1)<=25) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(27,20);
            	    pushFollow(FOLLOW_suma_in_expresion352);
            	    suma();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);
            } finally {dbg.exitSubRule(9);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(27, 26);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expresion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "expresion"


    // $ANTLR start "suma"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:1: suma : producto ( ( '*' | '/' ) producto )* ;
    public final void suma() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "suma");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(28, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:6: ( producto ( ( '*' | '/' ) producto )* )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:8: producto ( ( '*' | '/' ) producto )*
            {
            dbg.location(28,8);
            pushFollow(FOLLOW_producto_in_suma361);
            producto();

            state._fsp--;

            dbg.location(28,17);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:17: ( ( '*' | '/' ) producto )*
            try { dbg.enterSubRule(10);

            loop10:
            do {
                int alt10=2;
                try { dbg.enterDecision(10);

                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=26 && LA10_0<=27)) ) {
                    alt10=1;
                }


                } finally {dbg.exitDecision(10);}

                switch (alt10) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:18: ( '*' | '/' ) producto
            	    {
            	    dbg.location(28,18);
            	    if ( (input.LA(1)>=26 && input.LA(1)<=27) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(28,28);
            	    pushFollow(FOLLOW_producto_in_suma370);
            	    producto();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);
            } finally {dbg.exitSubRule(10);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(28, 38);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "suma");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "suma"


    // $ANTLR start "producto"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:1: producto : proposicion ( ( '&&' | '||' ) proposicion )* ;
    public final void producto() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "producto");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(29, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:9: ( proposicion ( ( '&&' | '||' ) proposicion )* )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:11: proposicion ( ( '&&' | '||' ) proposicion )*
            {
            dbg.location(29,11);
            pushFollow(FOLLOW_proposicion_in_producto378);
            proposicion();

            state._fsp--;

            dbg.location(29,23);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:23: ( ( '&&' | '||' ) proposicion )*
            try { dbg.enterSubRule(11);

            loop11:
            do {
                int alt11=2;
                try { dbg.enterDecision(11);

                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=28 && LA11_0<=29)) ) {
                    alt11=1;
                }


                } finally {dbg.exitDecision(11);}

                switch (alt11) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:24: ( '&&' | '||' ) proposicion
            	    {
            	    dbg.location(29,24);
            	    if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(29,36);
            	    pushFollow(FOLLOW_proposicion_in_producto387);
            	    proposicion();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);
            } finally {dbg.exitSubRule(11);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(29, 49);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "producto");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "producto"


    // $ANTLR start "proposicion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:30:1: proposicion : ( '!' )? comparacion ;
    public final void proposicion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "proposicion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(30, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:2: ( ( '!' )? comparacion )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:4: ( '!' )? comparacion
            {
            dbg.location(31,4);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:4: ( '!' )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==30) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:4: '!'
                    {
                    dbg.location(31,4);
                    match(input,30,FOLLOW_30_in_proposicion397); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(31,9);
            pushFollow(FOLLOW_comparacion_in_proposicion400);
            comparacion();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(31, 20);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "proposicion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "proposicion"


    // $ANTLR start "comparacion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:1: comparacion : factor ( ( '>' | '>=' | '<' | '<=' | '==' | '!=' ) factor )? ;
    public final void comparacion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparacion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(32, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:12: ( factor ( ( '>' | '>=' | '<' | '<=' | '==' | '!=' ) factor )? )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:14: factor ( ( '>' | '>=' | '<' | '<=' | '==' | '!=' ) factor )?
            {
            dbg.location(32,14);
            pushFollow(FOLLOW_factor_in_comparacion406);
            factor();

            state._fsp--;

            dbg.location(32,21);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:21: ( ( '>' | '>=' | '<' | '<=' | '==' | '!=' ) factor )?
            int alt13=2;
            try { dbg.enterSubRule(13);
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=31 && LA13_0<=36)) ) {
                alt13=1;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:22: ( '>' | '>=' | '<' | '<=' | '==' | '!=' ) factor
                    {
                    dbg.location(32,22);
                    if ( (input.LA(1)>=31 && input.LA(1)<=36) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }

                    dbg.location(32,52);
                    pushFollow(FOLLOW_factor_in_comparacion423);
                    factor();

                    state._fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(32, 61);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparacion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "comparacion"


    // $ANTLR start "factor"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:33:1: factor : ( CARACTER | NUMERO | IDENTIFICADOR ) ;
    public final void factor() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "factor");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(33, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:33:8: ( ( CARACTER | NUMERO | IDENTIFICADOR ) )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:33:10: ( CARACTER | NUMERO | IDENTIFICADOR )
            {
            dbg.location(33,10);
            if ( (input.LA(1)>=NUMERO && input.LA(1)<=IDENTIFICADOR)||input.LA(1)==CARACTER ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(33, 41);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "factor");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "factor"


    // $ANTLR start "iSi"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:34:1: iSi : 'si' '(' expresion ')' 'entonces' bloque sino ;
    public final void iSi() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iSi");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(34, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:34:5: ( 'si' '(' expresion ')' 'entonces' bloque sino )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:34:7: 'si' '(' expresion ')' 'entonces' bloque sino
            {
            dbg.location(34,7);
            match(input,37,FOLLOW_37_in_iSi446); 
            dbg.location(34,12);
            match(input,12,FOLLOW_12_in_iSi448); 
            dbg.location(34,16);
            pushFollow(FOLLOW_expresion_in_iSi450);
            expresion();

            state._fsp--;

            dbg.location(34,26);
            match(input,13,FOLLOW_13_in_iSi452); 
            dbg.location(34,30);
            match(input,38,FOLLOW_38_in_iSi454); 
            dbg.location(34,41);
            pushFollow(FOLLOW_bloque_in_iSi456);
            bloque();

            state._fsp--;

            dbg.location(34,48);
            pushFollow(FOLLOW_sino_in_iSi458);
            sino();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(34, 52);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iSi");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iSi"


    // $ANTLR start "sino"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:1: sino : ( 'sino' bloque )? ;
    public final void sino() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "sino");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(35, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:6: ( ( 'sino' bloque )? )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:8: ( 'sino' bloque )?
            {
            dbg.location(35,8);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:8: ( 'sino' bloque )?
            int alt14=2;
            try { dbg.enterSubRule(14);
            try { dbg.enterDecision(14);

            int LA14_0 = input.LA(1);

            if ( (LA14_0==39) ) {
                alt14=1;
            }
            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:9: 'sino' bloque
                    {
                    dbg.location(35,9);
                    match(input,39,FOLLOW_39_in_sino466); 
                    dbg.location(35,16);
                    pushFollow(FOLLOW_bloque_in_sino468);
                    bloque();

                    state._fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(14);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(35, 24);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "sino");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "sino"


    // $ANTLR start "bloque"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:1: bloque : ( instruccion | ( '{' ( instruccion )* '}' ) ) ;
    public final void bloque() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "bloque");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(36, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:8: ( ( instruccion | ( '{' ( instruccion )* '}' ) ) )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:10: ( instruccion | ( '{' ( instruccion )* '}' ) )
            {
            dbg.location(36,10);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:10: ( instruccion | ( '{' ( instruccion )* '}' ) )
            int alt16=2;
            try { dbg.enterSubRule(16);
            try { dbg.enterDecision(16);

            int LA16_0 = input.LA(1);

            if ( (LA16_0==IDENTIFICADOR||LA16_0==16||(LA16_0>=18 && LA16_0<=19)||LA16_0==37||(LA16_0>=42 && LA16_0<=45)||(LA16_0>=47 && LA16_0<=49)) ) {
                alt16=1;
            }
            else if ( (LA16_0==40) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(16);}

            switch (alt16) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:11: instruccion
                    {
                    dbg.location(36,11);
                    pushFollow(FOLLOW_instruccion_in_bloque478);
                    instruccion();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:24: ( '{' ( instruccion )* '}' )
                    {
                    dbg.location(36,24);
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:24: ( '{' ( instruccion )* '}' )
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:25: '{' ( instruccion )* '}'
                    {
                    dbg.location(36,25);
                    match(input,40,FOLLOW_40_in_bloque482); 
                    dbg.location(36,29);
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:29: ( instruccion )*
                    try { dbg.enterSubRule(15);

                    loop15:
                    do {
                        int alt15=2;
                        try { dbg.enterDecision(15);

                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==IDENTIFICADOR||LA15_0==16||(LA15_0>=18 && LA15_0<=19)||LA15_0==37||(LA15_0>=42 && LA15_0<=45)||(LA15_0>=47 && LA15_0<=49)) ) {
                            alt15=1;
                        }


                        } finally {dbg.exitDecision(15);}

                        switch (alt15) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:30: instruccion
                    	    {
                    	    dbg.location(36,30);
                    	    pushFollow(FOLLOW_instruccion_in_bloque485);
                    	    instruccion();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(15);}

                    dbg.location(36,44);
                    match(input,41,FOLLOW_41_in_bloque489); 

                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(16);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(36, 49);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "bloque");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "bloque"


    // $ANTLR start "iMientras"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:37:1: iMientras : 'mientras' '(' expresion ')' bloque ;
    public final void iMientras() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iMientras");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(37, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:38:2: ( 'mientras' '(' expresion ')' bloque )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:38:4: 'mientras' '(' expresion ')' bloque
            {
            dbg.location(38,4);
            match(input,42,FOLLOW_42_in_iMientras499); 
            dbg.location(38,15);
            match(input,12,FOLLOW_12_in_iMientras501); 
            dbg.location(38,19);
            pushFollow(FOLLOW_expresion_in_iMientras503);
            expresion();

            state._fsp--;

            dbg.location(38,29);
            match(input,13,FOLLOW_13_in_iMientras505); 
            dbg.location(38,35);
            pushFollow(FOLLOW_bloque_in_iMientras509);
            bloque();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(38, 41);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iMientras");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iMientras"


    // $ANTLR start "iHaga"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:39:1: iHaga : 'haga' bloque 'mientras' '(' expresion ')' ';' ;
    public final void iHaga() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iHaga");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(39, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:39:7: ( 'haga' bloque 'mientras' '(' expresion ')' ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:39:9: 'haga' bloque 'mientras' '(' expresion ')' ';'
            {
            dbg.location(39,9);
            match(input,43,FOLLOW_43_in_iHaga516); 
            dbg.location(39,18);
            pushFollow(FOLLOW_bloque_in_iHaga520);
            bloque();

            state._fsp--;

            dbg.location(39,27);
            match(input,42,FOLLOW_42_in_iHaga524); 
            dbg.location(39,38);
            match(input,12,FOLLOW_12_in_iHaga526); 
            dbg.location(39,42);
            pushFollow(FOLLOW_expresion_in_iHaga528);
            expresion();

            state._fsp--;

            dbg.location(39,52);
            match(input,13,FOLLOW_13_in_iHaga530); 
            dbg.location(39,56);
            match(input,17,FOLLOW_17_in_iHaga532); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(39, 59);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iHaga");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iHaga"


    // $ANTLR start "iPara"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:1: iPara : 'para' '(' ( iCrearVariable | iAsignar ) expresion ';' ( IDENTIFICADOR '=' expresion ) ')' bloque ;
    public final void iPara() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iPara");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(40, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:7: ( 'para' '(' ( iCrearVariable | iAsignar ) expresion ';' ( IDENTIFICADOR '=' expresion ) ')' bloque )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:9: 'para' '(' ( iCrearVariable | iAsignar ) expresion ';' ( IDENTIFICADOR '=' expresion ) ')' bloque
            {
            dbg.location(40,9);
            match(input,44,FOLLOW_44_in_iPara539); 
            dbg.location(40,16);
            match(input,12,FOLLOW_12_in_iPara541); 
            dbg.location(40,20);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:20: ( iCrearVariable | iAsignar )
            int alt17=2;
            try { dbg.enterSubRule(17);
            try { dbg.enterDecision(17);

            int LA17_0 = input.LA(1);

            if ( (LA17_0==19) ) {
                alt17=1;
            }
            else if ( (LA17_0==IDENTIFICADOR) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(17);}

            switch (alt17) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:21: iCrearVariable
                    {
                    dbg.location(40,21);
                    pushFollow(FOLLOW_iCrearVariable_in_iPara544);
                    iCrearVariable();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:36: iAsignar
                    {
                    dbg.location(40,36);
                    pushFollow(FOLLOW_iAsignar_in_iPara546);
                    iAsignar();

                    state._fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(17);}

            dbg.location(40,46);
            pushFollow(FOLLOW_expresion_in_iPara549);
            expresion();

            state._fsp--;

            dbg.location(40,56);
            match(input,17,FOLLOW_17_in_iPara551); 
            dbg.location(40,60);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:60: ( IDENTIFICADOR '=' expresion )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:61: IDENTIFICADOR '=' expresion
            {
            dbg.location(40,61);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_iPara554); 
            dbg.location(40,75);
            match(input,15,FOLLOW_15_in_iPara556); 
            dbg.location(40,79);
            pushFollow(FOLLOW_expresion_in_iPara558);
            expresion();

            state._fsp--;


            }

            dbg.location(40,90);
            match(input,13,FOLLOW_13_in_iPara561); 
            dbg.location(40,96);
            pushFollow(FOLLOW_bloque_in_iPara565);
            bloque();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(40, 102);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iPara");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iPara"


    // $ANTLR start "iSeleccion"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:41:1: iSeleccion : 'seleccion' '(' IDENTIFICADOR ')' bloqueCasos ;
    public final void iSeleccion() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iSeleccion");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(41, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:42:2: ( 'seleccion' '(' IDENTIFICADOR ')' bloqueCasos )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:42:4: 'seleccion' '(' IDENTIFICADOR ')' bloqueCasos
            {
            dbg.location(42,4);
            match(input,45,FOLLOW_45_in_iSeleccion573); 
            dbg.location(42,16);
            match(input,12,FOLLOW_12_in_iSeleccion575); 
            dbg.location(42,20);
            match(input,IDENTIFICADOR,FOLLOW_IDENTIFICADOR_in_iSeleccion577); 
            dbg.location(42,34);
            match(input,13,FOLLOW_13_in_iSeleccion579); 
            dbg.location(42,40);
            pushFollow(FOLLOW_bloqueCasos_in_iSeleccion583);
            bloqueCasos();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(42, 52);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iSeleccion");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iSeleccion"


    // $ANTLR start "bloqueCasos"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:43:1: bloqueCasos : '{' ( caso )* '}' ;
    public final void bloqueCasos() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "bloqueCasos");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(43, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:2: ( '{' ( caso )* '}' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:4: '{' ( caso )* '}'
            {
            dbg.location(44,4);
            match(input,40,FOLLOW_40_in_bloqueCasos592); 
            dbg.location(44,8);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:8: ( caso )*
            try { dbg.enterSubRule(18);

            loop18:
            do {
                int alt18=2;
                try { dbg.enterDecision(18);

                int LA18_0 = input.LA(1);

                if ( (LA18_0==39||LA18_0==46) ) {
                    alt18=1;
                }


                } finally {dbg.exitDecision(18);}

                switch (alt18) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:9: caso
            	    {
            	    dbg.location(44,9);
            	    pushFollow(FOLLOW_caso_in_bloqueCasos595);
            	    caso();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);
            } finally {dbg.exitSubRule(18);}

            dbg.location(44,16);
            match(input,41,FOLLOW_41_in_bloqueCasos599); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(44, 19);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "bloqueCasos");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "bloqueCasos"


    // $ANTLR start "caso"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:1: caso : ( ( 'caso' ( NUMERO | CARACTER ) ) | 'sino' ) bloque ;
    public final void caso() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "caso");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(45, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:6: ( ( ( 'caso' ( NUMERO | CARACTER ) ) | 'sino' ) bloque )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:8: ( ( 'caso' ( NUMERO | CARACTER ) ) | 'sino' ) bloque
            {
            dbg.location(45,8);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:8: ( ( 'caso' ( NUMERO | CARACTER ) ) | 'sino' )
            int alt19=2;
            try { dbg.enterSubRule(19);
            try { dbg.enterDecision(19);

            int LA19_0 = input.LA(1);

            if ( (LA19_0==46) ) {
                alt19=1;
            }
            else if ( (LA19_0==39) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(19);}

            switch (alt19) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:9: ( 'caso' ( NUMERO | CARACTER ) )
                    {
                    dbg.location(45,9);
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:9: ( 'caso' ( NUMERO | CARACTER ) )
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:10: 'caso' ( NUMERO | CARACTER )
                    {
                    dbg.location(45,10);
                    match(input,46,FOLLOW_46_in_caso608); 
                    dbg.location(45,17);
                    if ( input.LA(1)==NUMERO||input.LA(1)==CARACTER ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:38: 'sino'
                    {
                    dbg.location(45,38);
                    match(input,39,FOLLOW_39_in_caso619); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(19);}

            dbg.location(45,48);
            pushFollow(FOLLOW_bloque_in_caso624);
            bloque();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(45, 54);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "caso");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "caso"


    // $ANTLR start "iSalir"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:46:1: iSalir : 'salir' ';' ;
    public final void iSalir() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iSalir");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(46, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:46:8: ( 'salir' ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:46:10: 'salir' ';'
            {
            dbg.location(46,10);
            match(input,47,FOLLOW_47_in_iSalir631); 
            dbg.location(46,18);
            match(input,17,FOLLOW_17_in_iSalir633); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(46, 21);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iSalir");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iSalir"


    // $ANTLR start "iContinuar"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:47:1: iContinuar : 'continuar' ';' ;
    public final void iContinuar() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iContinuar");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(47, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:48:2: ( 'continuar' ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:48:4: 'continuar' ';'
            {
            dbg.location(48,4);
            match(input,48,FOLLOW_48_in_iContinuar641); 
            dbg.location(48,16);
            match(input,17,FOLLOW_17_in_iContinuar643); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(48, 19);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iContinuar");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iContinuar"


    // $ANTLR start "iRetornar"
    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:49:1: iRetornar : 'retornar' ( expresion )? ';' ;
    public final void iRetornar() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "iRetornar");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(49, 1);

        try {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:50:2: ( 'retornar' ( expresion )? ';' )
            dbg.enterAlt(1);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:50:4: 'retornar' ( expresion )? ';'
            {
            dbg.location(50,4);
            match(input,49,FOLLOW_49_in_iRetornar651); 
            dbg.location(50,15);
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:50:15: ( expresion )?
            int alt20=2;
            try { dbg.enterSubRule(20);
            try { dbg.enterDecision(20);

            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=NUMERO && LA20_0<=IDENTIFICADOR)||LA20_0==CARACTER||LA20_0==30) ) {
                alt20=1;
            }
            } finally {dbg.exitDecision(20);}

            switch (alt20) {
                case 1 :
                    dbg.enterAlt(1);

                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:50:15: expresion
                    {
                    dbg.location(50,15);
                    pushFollow(FOLLOW_expresion_in_iRetornar653);
                    expresion();

                    state._fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(20);}

            dbg.location(50,26);
            match(input,17,FOLLOW_17_in_iRetornar656); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(50, 30);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "iRetornar");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "iRetornar"

    // Delegated rules


 

    public static final BitSet FOLLOW_funcion_in_programa148 = new BitSet(new long[]{0x0000000000F00A00L});
    public static final BitSet FOLLOW_9_in_programa151 = new BitSet(new long[]{0x0003BC20000D0420L});
    public static final BitSet FOLLOW_instruccion_in_programa155 = new BitSet(new long[]{0x0003BC20000D0420L});
    public static final BitSet FOLLOW_10_in_programa158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tipo_in_funcion167 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11_in_funcion169 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_funcion172 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_funcion174 = new BitSet(new long[]{0x0000000000F02000L});
    public static final BitSet FOLLOW_listaparametros_in_funcion176 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_funcion179 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_funcion183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parametro_in_listaparametros189 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_listaparametros192 = new BitSet(new long[]{0x0000000000F00000L});
    public static final BitSet FOLLOW_parametro_in_listaparametros194 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_tipo_in_parametro204 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_parametro206 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_parametro209 = new BitSet(new long[]{0x00000000000000D0L});
    public static final BitSet FOLLOW_set_in_parametro211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iImprimir_in_instruccion227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iPedir_in_instruccion229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iAsignar_in_instruccion231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iCrearVariable_in_instruccion233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iSi_in_instruccion235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iMientras_in_instruccion237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iPara_in_instruccion239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iHaga_in_instruccion241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iSeleccion_in_instruccion243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iSalir_in_instruccion245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iContinuar_in_instruccion247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iRetornar_in_instruccion249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_iImprimir257 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_set_in_iImprimir259 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iImprimir269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_iPedir277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_iPedir279 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iPedir281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_iCrearVariable289 = new BitSet(new long[]{0x0000000000F00000L});
    public static final BitSet FOLLOW_tipo_in_iCrearVariable291 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_iCrearVariable293 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_15_in_iCrearVariable296 = new BitSet(new long[]{0x00000000000000D0L});
    public static final BitSet FOLLOW_set_in_iCrearVariable298 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iCrearVariable308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tipo315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_iAsignar329 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_iAsignar331 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iAsignar333 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iAsignar335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suma_in_expresion343 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_set_in_expresion346 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_suma_in_expresion352 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_producto_in_suma361 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_set_in_suma364 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_producto_in_suma370 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_proposicion_in_producto378 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_set_in_producto381 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_proposicion_in_producto387 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_30_in_proposicion397 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_comparacion_in_proposicion400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_comparacion406 = new BitSet(new long[]{0x0000001F80000002L});
    public static final BitSet FOLLOW_set_in_comparacion409 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_factor_in_comparacion423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_factor433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_iSi446 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_iSi448 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iSi450 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_iSi452 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_iSi454 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_iSi456 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_sino_in_iSi458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_sino466 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_sino468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_instruccion_in_bloque478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_bloque482 = new BitSet(new long[]{0x0003BE20000D0020L});
    public static final BitSet FOLLOW_instruccion_in_bloque485 = new BitSet(new long[]{0x0003BE20000D0020L});
    public static final BitSet FOLLOW_41_in_bloque489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_iMientras499 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_iMientras501 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iMientras503 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_iMientras505 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_iMientras509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_iHaga516 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_iHaga520 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_iHaga524 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_iHaga526 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iHaga528 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_iHaga530 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iHaga532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_iPara539 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_iPara541 = new BitSet(new long[]{0x0000000000080020L});
    public static final BitSet FOLLOW_iCrearVariable_in_iPara544 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_iAsignar_in_iPara546 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iPara549 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iPara551 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_iPara554 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_iPara556 = new BitSet(new long[]{0x00000000400000B0L});
    public static final BitSet FOLLOW_expresion_in_iPara558 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_iPara561 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_iPara565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_iSeleccion573 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_iSeleccion575 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IDENTIFICADOR_in_iSeleccion577 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_iSeleccion579 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_bloqueCasos_in_iSeleccion583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_bloqueCasos592 = new BitSet(new long[]{0x0000428000000000L});
    public static final BitSet FOLLOW_caso_in_bloqueCasos595 = new BitSet(new long[]{0x0000428000000000L});
    public static final BitSet FOLLOW_41_in_bloqueCasos599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_caso608 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_set_in_caso610 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_39_in_caso619 = new BitSet(new long[]{0x0003BD20000D0020L});
    public static final BitSet FOLLOW_bloque_in_caso624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_iSalir631 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iSalir633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_iContinuar641 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iContinuar643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_iRetornar651 = new BitSet(new long[]{0x00000000400200B0L});
    public static final BitSet FOLLOW_expresion_in_iRetornar653 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_iRetornar656 = new BitSet(new long[]{0x0000000000000002L});

}