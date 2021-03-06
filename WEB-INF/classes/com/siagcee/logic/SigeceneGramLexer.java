// $ANTLR 3.2 Sep 23, 2009 12:02:23 Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g 2011-06-26 19:31:24

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

public class SigeceneGramLexer extends Lexer {
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

    public SigeceneGramLexer() {;} 
    public SigeceneGramLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SigeceneGramLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g"; }

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:19:7: ( '=' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:19:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:20:7: ( 'obtener' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:20:9: 'obtener'
            {
            match("obtener"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:21:7: ( 'pre' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:21:9: 'pre'
            {
            match("pre"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:22:7: ( '{' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:22:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:23:7: ( '}' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:23:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:24:7: ( 'post' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:24:9: 'post'
            {
            match("post"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:25:7: ( 'si' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:25:9: 'si'
            {
            match("si"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:26:7: ( '(' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:26:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:27:7: ( ';' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:27:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:28:7: ( ')' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:28:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:29:7: ( 'Y' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:29:9: 'Y'
            {
            match('Y'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:30:7: ( 'O' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:30:9: 'O'
            {
            match('O'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:31:7: ( 'NO' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:31:9: 'NO'
            {
            match("NO"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:32:7: ( '>' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:32:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:33:7: ( '>=' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:33:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:34:7: ( '<' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:34:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:35:7: ( '<=' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:35:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:36:7: ( '==' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:36:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:37:7: ( '<>' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:37:9: '<>'
            {
            match("<>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:38:7: ( '+' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:38:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:39:7: ( '-' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:39:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:40:7: ( '*' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:40:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:41:7: ( '/' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:41:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:42:7: ( 'promedio' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:42:9: 'promedio'
            {
            match("promedio"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:43:7: ( 'sumatoria' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:43:9: 'sumatoria'
            {
            match("sumatoria"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:44:7: ( 'contar' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:44:9: 'contar'
            {
            match("contar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:45:7: ( 'max' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:45:9: 'max'
            {
            match("max"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:46:7: ( 'min' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:46:9: 'min'
            {
            match("min"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:47:7: ( 'redondea' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:47:9: 'redondea'
            {
            match("redondea"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:48:7: ( 'diff_fechas' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:48:9: 'diff_fechas'
            {
            match("diff_fechas"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:49:7: ( ',' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:49:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:50:7: ( 'concatenar' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:50:9: 'concatenar'
            {
            match("concatenar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:143:8: ( '\"' (~ ( '\"' | '\\n' | '\\r' | '\\t' ) )+ '\"' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:143:10: '\"' (~ ( '\"' | '\\n' | '\\r' | '\\t' ) )+ '\"'
            {
            match('\"'); 
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:143:14: (~ ( '\"' | '\\n' | '\\r' | '\\t' ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\b')||(LA1_0>='\u000B' && LA1_0<='\f')||(LA1_0>='\u000E' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:143:15: ~ ( '\"' | '\\n' | '\\r' | '\\t' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:144:9: ( 'verdad' | 'falso' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='v') ) {
                alt2=1;
            }
            else if ( (LA2_0=='f') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:144:11: 'verdad'
                    {
                    match("verdad"); 


                    }
                    break;
                case 2 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:144:22: 'falso'
                    {
                    match("falso"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:145:4: ( '[' (~ ( '[' | ']' | '\\n' | '\\r' | '\\t' ) )+ ']' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:145:6: '[' (~ ( '[' | ']' | '\\n' | '\\r' | '\\t' ) )+ ']'
            {
            match('['); 
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:145:10: (~ ( '[' | ']' | '\\n' | '\\r' | '\\t' ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\u0000' && LA3_0<='\b')||(LA3_0>='\u000B' && LA3_0<='\f')||(LA3_0>='\u000E' && LA3_0<='Z')||LA3_0=='\\'||(LA3_0>='^' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:145:11: ~ ( '[' | ']' | '\\n' | '\\r' | '\\t' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='Z')||input.LA(1)=='\\'||(input.LA(1)>='^' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "DOUBLE"
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:8: ( ( '0' .. '9' )+ ( ( ',' | '.' ) ( '0' .. '9' )+ )? )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:10: ( '0' .. '9' )+ ( ( ',' | '.' ) ( '0' .. '9' )+ )?
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:10: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:20: ( ( ',' | '.' ) ( '0' .. '9' )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==','||LA6_0=='.') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:21: ( ',' | '.' ) ( '0' .. '9' )+
                    {
                    if ( input.LA(1)==','||input.LA(1)=='.' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:31: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:146:31: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:147:9: ( ( '\\r' )? '\\n' )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:147:11: ( '\\r' )? '\\n'
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:147:11: ( '\\r' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\r') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:147:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:148:4: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:148:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:148:6: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | STRING | BOOLEAN | ID | DOUBLE | NEWLINE | WS )
        int alt9=38;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:10: T__10
                {
                mT__10(); 

                }
                break;
            case 2 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:16: T__11
                {
                mT__11(); 

                }
                break;
            case 3 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:22: T__12
                {
                mT__12(); 

                }
                break;
            case 4 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:28: T__13
                {
                mT__13(); 

                }
                break;
            case 5 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:34: T__14
                {
                mT__14(); 

                }
                break;
            case 6 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:40: T__15
                {
                mT__15(); 

                }
                break;
            case 7 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:46: T__16
                {
                mT__16(); 

                }
                break;
            case 8 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:52: T__17
                {
                mT__17(); 

                }
                break;
            case 9 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:58: T__18
                {
                mT__18(); 

                }
                break;
            case 10 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:64: T__19
                {
                mT__19(); 

                }
                break;
            case 11 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:70: T__20
                {
                mT__20(); 

                }
                break;
            case 12 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:76: T__21
                {
                mT__21(); 

                }
                break;
            case 13 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:82: T__22
                {
                mT__22(); 

                }
                break;
            case 14 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:88: T__23
                {
                mT__23(); 

                }
                break;
            case 15 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:94: T__24
                {
                mT__24(); 

                }
                break;
            case 16 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:100: T__25
                {
                mT__25(); 

                }
                break;
            case 17 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:106: T__26
                {
                mT__26(); 

                }
                break;
            case 18 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:112: T__27
                {
                mT__27(); 

                }
                break;
            case 19 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:118: T__28
                {
                mT__28(); 

                }
                break;
            case 20 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:124: T__29
                {
                mT__29(); 

                }
                break;
            case 21 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:130: T__30
                {
                mT__30(); 

                }
                break;
            case 22 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:136: T__31
                {
                mT__31(); 

                }
                break;
            case 23 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:142: T__32
                {
                mT__32(); 

                }
                break;
            case 24 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:148: T__33
                {
                mT__33(); 

                }
                break;
            case 25 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:154: T__34
                {
                mT__34(); 

                }
                break;
            case 26 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:160: T__35
                {
                mT__35(); 

                }
                break;
            case 27 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:166: T__36
                {
                mT__36(); 

                }
                break;
            case 28 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:172: T__37
                {
                mT__37(); 

                }
                break;
            case 29 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:178: T__38
                {
                mT__38(); 

                }
                break;
            case 30 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:184: T__39
                {
                mT__39(); 

                }
                break;
            case 31 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:190: T__40
                {
                mT__40(); 

                }
                break;
            case 32 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:196: T__41
                {
                mT__41(); 

                }
                break;
            case 33 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:202: STRING
                {
                mSTRING(); 

                }
                break;
            case 34 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:209: BOOLEAN
                {
                mBOOLEAN(); 

                }
                break;
            case 35 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:217: ID
                {
                mID(); 

                }
                break;
            case 36 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:220: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 37 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:227: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 38 :
                // Y:\\webapps\\siagcee\\WEB-INF\\classes\\com\\siagcee\\logic\\SigeceneGram.g:1:235: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\1\40\13\uffff\1\46\1\51\15\uffff\1\36\1\55\25\uffff";
    static final String DFA9_eofS =
        "\63\uffff";
    static final String DFA9_minS =
        "\1\11\1\75\1\uffff\1\157\2\uffff\1\151\6\uffff\2\75\4\uffff\1\157"+
        "\1\141\7\uffff\1\12\1\11\3\uffff\1\145\10\uffff\1\156\5\uffff\1"+
        "\143\2\uffff";
    static final String DFA9_maxS =
        "\1\175\1\75\1\uffff\1\162\2\uffff\1\165\6\uffff\1\75\1\76\4\uffff"+
        "\1\157\1\151\7\uffff\1\12\1\40\3\uffff\1\157\10\uffff\1\156\5\uffff"+
        "\1\164\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\uffff\1\4\1\5\1\uffff\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\2\uffff\1\24\1\25\1\26\1\27\2\uffff\1\35\1\36\1\37\1\41\1\42"+
        "\1\43\1\44\2\uffff\1\46\1\22\1\1\1\uffff\1\6\1\7\1\31\1\17\1\16"+
        "\1\21\1\23\1\20\1\uffff\1\33\1\34\1\45\1\3\1\30\1\uffff\1\32\1\40";
    static final String DFA9_specialS =
        "\63\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\36\1\35\2\uffff\1\34\22\uffff\1\36\1\uffff\1\30\5\uffff"+
            "\1\7\1\11\1\21\1\17\1\27\1\20\1\uffff\1\22\12\33\1\uffff\1\10"+
            "\1\16\1\1\1\15\17\uffff\1\14\1\13\11\uffff\1\12\1\uffff\1\32"+
            "\7\uffff\1\23\1\26\1\uffff\1\31\6\uffff\1\24\1\uffff\1\2\1\3"+
            "\1\uffff\1\25\1\6\2\uffff\1\31\4\uffff\1\4\1\uffff\1\5",
            "\1\37",
            "",
            "\1\42\2\uffff\1\41",
            "",
            "",
            "\1\43\13\uffff\1\44",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\45",
            "\1\47\1\50",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53\7\uffff\1\54",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\35",
            "\2\36\2\uffff\1\36\22\uffff\1\36",
            "",
            "",
            "",
            "\1\56\11\uffff\1\57",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\60",
            "",
            "",
            "",
            "",
            "",
            "\1\62\20\uffff\1\61",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | STRING | BOOLEAN | ID | DOUBLE | NEWLINE | WS );";
        }
    }
 

}