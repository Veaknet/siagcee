// $ANTLR 3.2 Sep 23, 2009 12:02:23 \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g 2011-04-29 21:58:55

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class testLexer extends Lexer {
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int NUMERO=4;
    public static final int T__24=24;
    public static final int TEXTO=6;
    public static final int FINLINEA=8;
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

    public testLexer() {;} 
    public testLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public testLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "\\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:7:6: ( 'inicio' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:7:8: 'inicio'
            {
            match("inicio"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:8:7: ( 'fin' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:8:9: 'fin'
            {
            match("fin"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:7: ( 'proceso' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:9: 'proceso'
            {
            match("proceso"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:10:7: ( '(' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:10:9: '('
            {
            match('('); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:7: ( ')' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:11:9: ')'
            {
            match(')'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:12:7: ( ',' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:12:9: ','
            {
            match(','); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:7: ( '=' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:13:9: '='
            {
            match('='); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:7: ( 'imprimir' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:14:9: 'imprimir'
            {
            match("imprimir"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:15:7: ( ';' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:15:9: ';'
            {
            match(';'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:7: ( 'pedir' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:16:9: 'pedir'
            {
            match("pedir"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:17:7: ( 'variable' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:17:9: 'variable'
            {
            match("variable"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:7: ( 'real' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:18:9: 'real'
            {
            match("real"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:19:7: ( 'caracter' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:19:9: 'caracter'
            {
            match("caracter"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:20:7: ( 'booleana' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:20:9: 'booleana'
            {
            match("booleana"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:21:7: ( 'entera' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:21:9: 'entera'
            {
            match("entera"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:22:7: ( '+' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:22:9: '+'
            {
            match('+'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:7: ( '-' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:23:9: '-'
            {
            match('-'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:24:7: ( '*' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:24:9: '*'
            {
            match('*'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:25:7: ( '/' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:25:9: '/'
            {
            match('/'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:26:7: ( '&&' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:26:9: '&&'
            {
            match("&&"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:7: ( '||' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:27:9: '||'
            {
            match("||"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:7: ( '!' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:28:9: '!'
            {
            match('!'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:7: ( '>' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:29:9: '>'
            {
            match('>'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:30:7: ( '>=' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:30:9: '>='
            {
            match(">="); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:7: ( '<' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:31:9: '<'
            {
            match('<'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:7: ( '<=' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:32:9: '<='
            {
            match("<="); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:33:7: ( '==' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:33:9: '=='
            {
            match("=="); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:34:7: ( '!=' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:34:9: '!='
            {
            match("!="); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:7: ( 'si' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:35:9: 'si'
            {
            match("si"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:7: ( 'entonces' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:36:9: 'entonces'
            {
            match("entonces"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:37:7: ( 'sino' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:37:9: 'sino'
            {
            match("sino"); 


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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:38:7: ( '{' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:38:9: '{'
            {
            match('{'); 

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
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:39:7: ( '}' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:39:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:7: ( 'mientras' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:40:9: 'mientras'
            {
            match("mientras"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:41:7: ( 'haga' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:41:9: 'haga'
            {
            match("haga"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:42:7: ( 'para' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:42:9: 'para'
            {
            match("para"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:43:7: ( 'seleccion' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:43:9: 'seleccion'
            {
            match("seleccion"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:7: ( 'caso' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:44:9: 'caso'
            {
            match("caso"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:7: ( 'salir' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:45:9: 'salir'
            {
            match("salir"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:46:7: ( 'continuar' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:46:9: 'continuar'
            {
            match("continuar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:47:7: ( 'retornar' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:47:9: 'retornar'
            {
            match("retornar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "NUMERO"
    public final void mNUMERO() throws RecognitionException {
        try {
            int _type = NUMERO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:8: ( ( '+' | '-' )? ( '0' .. '9' )+ ( '.' '0' .. '9' )? )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:10: ( '+' | '-' )? ( '0' .. '9' )+ ( '.' '0' .. '9' )?
            {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:10: ( '+' | '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='+'||LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:22: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:23: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:34: ( '.' '0' .. '9' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='.') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:4:35: '.' '0' .. '9'
                    {
                    match('.'); 
                    matchRange('0','9'); 

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
    // $ANTLR end "NUMERO"

    // $ANTLR start "IDENTIFICADOR"
    public final void mIDENTIFICADOR() throws RecognitionException {
        try {
            int _type = IDENTIFICADOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:2: ( ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '0' .. '9' )* )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '0' .. '9' )*
            {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>='a' && LA4_0<='z')) ) {
                alt4=1;
            }
            else if ( ((LA4_0>='A' && LA4_0<='Z')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:5: ( 'a' .. 'z' )
                    {
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:5: ( 'a' .. 'z' )
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:6: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }


                    }
                    break;
                case 2 :
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:16: ( 'A' .. 'Z' )
                    {
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:16: ( 'A' .. 'Z' )
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:17: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }


                    }
                    break;

            }

            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:28: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '0' .. '9' )*
            loop5:
            do {
                int alt5=4;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt5=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt5=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt5=3;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:29: ( 'a' .. 'z' )
            	    {
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:29: ( 'a' .. 'z' )
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:30: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }


            	    }
            	    break;
            	case 2 :
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:40: ( 'A' .. 'Z' )
            	    {
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:40: ( 'A' .. 'Z' )
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:41: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }


            	    }
            	    break;
            	case 3 :
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:6:51: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFICADOR"

    // $ANTLR start "TEXTO"
    public final void mTEXTO() throws RecognitionException {
        try {
            int _type = TEXTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:7:7: ( '\\\"' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | ' ' )* '\\\"' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:7:9: '\\\"' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | ' ' )* '\\\"'
            {
            match('\"'); 
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:7:14: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | ' ' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==' '||(LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TEXTO"

    // $ANTLR start "CARACTER"
    public final void mCARACTER() throws RecognitionException {
        try {
            int _type = CARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:8:9: ( '\\'' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | ' ' ) '\\'' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:8:11: '\\'' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | ' ' ) '\\''
            {
            match('\''); 
            if ( input.LA(1)==' '||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CARACTER"

    // $ANTLR start "FINLINEA"
    public final void mFINLINEA() throws RecognitionException {
        try {
            int _type = FINLINEA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:9: ( ( '\\r' )? '\\n' )
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:11: ( '\\r' )? '\\n'
            {
            // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:11: ( '\\r' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\r') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:9:11: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINLINEA"

    public void mTokens() throws RecognitionException {
        // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | NUMERO | IDENTIFICADOR | TEXTO | CARACTER | FINLINEA )
        int alt8=46;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:10: T__9
                {
                mT__9(); 

                }
                break;
            case 2 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:15: T__10
                {
                mT__10(); 

                }
                break;
            case 3 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:21: T__11
                {
                mT__11(); 

                }
                break;
            case 4 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:27: T__12
                {
                mT__12(); 

                }
                break;
            case 5 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:33: T__13
                {
                mT__13(); 

                }
                break;
            case 6 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:39: T__14
                {
                mT__14(); 

                }
                break;
            case 7 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:45: T__15
                {
                mT__15(); 

                }
                break;
            case 8 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:51: T__16
                {
                mT__16(); 

                }
                break;
            case 9 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:57: T__17
                {
                mT__17(); 

                }
                break;
            case 10 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:63: T__18
                {
                mT__18(); 

                }
                break;
            case 11 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:69: T__19
                {
                mT__19(); 

                }
                break;
            case 12 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:75: T__20
                {
                mT__20(); 

                }
                break;
            case 13 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:81: T__21
                {
                mT__21(); 

                }
                break;
            case 14 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:87: T__22
                {
                mT__22(); 

                }
                break;
            case 15 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:93: T__23
                {
                mT__23(); 

                }
                break;
            case 16 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:99: T__24
                {
                mT__24(); 

                }
                break;
            case 17 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:105: T__25
                {
                mT__25(); 

                }
                break;
            case 18 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:111: T__26
                {
                mT__26(); 

                }
                break;
            case 19 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:117: T__27
                {
                mT__27(); 

                }
                break;
            case 20 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:123: T__28
                {
                mT__28(); 

                }
                break;
            case 21 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:129: T__29
                {
                mT__29(); 

                }
                break;
            case 22 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:135: T__30
                {
                mT__30(); 

                }
                break;
            case 23 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:141: T__31
                {
                mT__31(); 

                }
                break;
            case 24 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:147: T__32
                {
                mT__32(); 

                }
                break;
            case 25 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:153: T__33
                {
                mT__33(); 

                }
                break;
            case 26 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:159: T__34
                {
                mT__34(); 

                }
                break;
            case 27 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:165: T__35
                {
                mT__35(); 

                }
                break;
            case 28 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:171: T__36
                {
                mT__36(); 

                }
                break;
            case 29 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:177: T__37
                {
                mT__37(); 

                }
                break;
            case 30 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:183: T__38
                {
                mT__38(); 

                }
                break;
            case 31 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:189: T__39
                {
                mT__39(); 

                }
                break;
            case 32 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:195: T__40
                {
                mT__40(); 

                }
                break;
            case 33 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:201: T__41
                {
                mT__41(); 

                }
                break;
            case 34 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:207: T__42
                {
                mT__42(); 

                }
                break;
            case 35 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:213: T__43
                {
                mT__43(); 

                }
                break;
            case 36 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:219: T__44
                {
                mT__44(); 

                }
                break;
            case 37 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:225: T__45
                {
                mT__45(); 

                }
                break;
            case 38 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:231: T__46
                {
                mT__46(); 

                }
                break;
            case 39 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:237: T__47
                {
                mT__47(); 

                }
                break;
            case 40 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:243: T__48
                {
                mT__48(); 

                }
                break;
            case 41 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:249: T__49
                {
                mT__49(); 

                }
                break;
            case 42 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:255: NUMERO
                {
                mNUMERO(); 

                }
                break;
            case 43 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:262: IDENTIFICADOR
                {
                mIDENTIFICADOR(); 

                }
                break;
            case 44 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:276: TEXTO
                {
                mTEXTO(); 

                }
                break;
            case 45 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:282: CARACTER
                {
                mCARACTER(); 

                }
                break;
            case 46 :
                // \\\\debian5\\resin\\siagcee\\WEB-INF\\vistas\\test.g:1:291: FINLINEA
                {
                mFINLINEA(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\3\35\3\uffff\1\50\1\uffff\5\35\1\57\1\60\4\uffff\1\62"+
        "\1\64\1\66\1\35\2\uffff\2\35\5\uffff\6\35\2\uffff\6\35\10\uffff"+
        "\1\113\6\35\1\122\14\35\1\uffff\6\35\1\uffff\2\35\1\150\1\35\1\152"+
        "\2\35\1\155\4\35\1\162\3\35\1\166\3\35\1\172\1\uffff\1\35\1\uffff"+
        "\2\35\1\uffff\4\35\1\uffff\1\35\1\u0083\1\35\1\uffff\1\u0085\2\35"+
        "\1\uffff\5\35\1\u008d\2\35\1\uffff\1\35\1\uffff\1\35\1\u0092\5\35"+
        "\1\uffff\3\35\1\u009b\1\uffff\1\u009c\1\u009d\1\u009e\1\35\1\u00a0"+
        "\1\u00a1\1\35\1\u00a3\4\uffff\1\u00a4\2\uffff\1\u00a5\3\uffff";
    static final String DFA8_eofS =
        "\u00a6\uffff";
    static final String DFA8_minS =
        "\1\12\1\155\1\151\1\141\3\uffff\1\75\1\uffff\1\141\1\145\1\141"+
        "\1\157\1\156\2\60\4\uffff\3\75\1\141\2\uffff\1\151\1\141\5\uffff"+
        "\1\151\1\160\1\156\1\157\1\144\1\162\2\uffff\1\162\1\141\1\162\1"+
        "\156\1\157\1\164\10\uffff\1\60\2\154\1\145\1\147\1\143\1\162\1\60"+
        "\1\143\1\151\1\141\1\151\1\154\1\157\1\141\1\157\1\164\1\154\1\145"+
        "\1\157\1\uffff\1\145\1\151\1\156\1\141\2\151\1\uffff\1\145\1\162"+
        "\1\60\1\141\1\60\1\162\1\143\1\60\1\151\1\145\1\162\1\156\1\60\1"+
        "\143\1\162\1\164\1\60\1\157\1\155\1\163\1\60\1\uffff\1\142\1\uffff"+
        "\1\156\1\164\1\uffff\1\156\2\141\1\143\1\uffff\1\143\1\60\1\162"+
        "\1\uffff\1\60\1\151\1\157\1\uffff\1\154\1\141\1\145\1\165\1\156"+
        "\1\60\1\145\1\151\1\uffff\1\141\1\uffff\1\162\1\60\1\145\2\162\2"+
        "\141\1\uffff\1\163\1\157\1\163\1\60\1\uffff\3\60\1\162\2\60\1\156"+
        "\1\60\4\uffff\1\60\2\uffff\1\60\3\uffff";
    static final String DFA8_maxS =
        "\1\175\1\156\1\151\1\162\3\uffff\1\75\1\uffff\1\141\1\145\2\157"+
        "\1\156\2\71\4\uffff\3\75\1\151\2\uffff\1\151\1\141\5\uffff\1\151"+
        "\1\160\1\156\1\157\1\144\1\162\2\uffff\1\162\1\164\1\163\1\156\1"+
        "\157\1\164\10\uffff\1\172\2\154\1\145\1\147\1\143\1\162\1\172\1"+
        "\143\1\151\1\141\1\151\1\154\1\157\1\141\1\157\1\164\1\154\2\157"+
        "\1\uffff\1\145\1\151\1\156\1\141\2\151\1\uffff\1\145\1\162\1\172"+
        "\1\141\1\172\1\162\1\143\1\172\1\151\1\145\1\162\1\156\1\172\1\143"+
        "\1\162\1\164\1\172\1\157\1\155\1\163\1\172\1\uffff\1\142\1\uffff"+
        "\1\156\1\164\1\uffff\1\156\2\141\1\143\1\uffff\1\143\1\172\1\162"+
        "\1\uffff\1\172\1\151\1\157\1\uffff\1\154\1\141\1\145\1\165\1\156"+
        "\1\172\1\145\1\151\1\uffff\1\141\1\uffff\1\162\1\172\1\145\2\162"+
        "\2\141\1\uffff\1\163\1\157\1\163\1\172\1\uffff\3\172\1\162\2\172"+
        "\1\156\1\172\4\uffff\1\172\2\uffff\1\172\3\uffff";
    static final String DFA8_acceptS =
        "\4\uffff\1\4\1\5\1\6\1\uffff\1\11\7\uffff\1\22\1\23\1\24\1\25\4"+
        "\uffff\1\40\1\41\2\uffff\1\52\1\53\1\54\1\55\1\56\6\uffff\1\33\1"+
        "\7\6\uffff\1\20\1\21\1\34\1\26\1\30\1\27\1\32\1\31\24\uffff\1\35"+
        "\6\uffff\1\2\25\uffff\1\44\1\uffff\1\14\2\uffff\1\46\4\uffff\1\37"+
        "\3\uffff\1\43\3\uffff\1\12\10\uffff\1\47\1\uffff\1\1\7\uffff\1\17"+
        "\4\uffff\1\3\10\uffff\1\10\1\13\1\51\1\15\1\uffff\1\16\1\36\1\uffff"+
        "\1\42\1\50\1\45";
    static final String DFA8_specialS =
        "\u00a6\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\40\2\uffff\1\40\23\uffff\1\24\1\36\3\uffff\1\22\1\37\1\4"+
            "\1\5\1\20\1\16\1\6\1\17\1\uffff\1\21\12\34\1\uffff\1\10\1\26"+
            "\1\7\1\25\2\uffff\32\35\6\uffff\1\35\1\14\1\13\1\35\1\15\1\2"+
            "\1\35\1\33\1\1\3\35\1\32\2\35\1\3\1\35\1\12\1\27\2\35\1\11\4"+
            "\35\1\30\1\23\1\31",
            "\1\42\1\41",
            "\1\43",
            "\1\46\3\uffff\1\45\14\uffff\1\44",
            "",
            "",
            "",
            "\1\47",
            "",
            "\1\51",
            "\1\52",
            "\1\53\15\uffff\1\54",
            "\1\55",
            "\1\56",
            "\12\34",
            "\12\34",
            "",
            "",
            "",
            "",
            "\1\61",
            "\1\63",
            "\1\65",
            "\1\71\3\uffff\1\70\3\uffff\1\67",
            "",
            "",
            "\1\72",
            "\1\73",
            "",
            "",
            "",
            "",
            "",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "",
            "",
            "\1\102",
            "\1\103\22\uffff\1\104",
            "\1\105\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\35\7\uffff\32\35\6\uffff\15\35\1\112\14\35",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135\11\uffff\1\136",
            "\1\137",
            "",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "",
            "\1\146",
            "\1\147",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\151",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\153",
            "\1\154",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\163",
            "\1\164",
            "\1\165",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\167",
            "\1\170",
            "\1\171",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "",
            "\1\173",
            "",
            "\1\174",
            "\1\175",
            "",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "",
            "\1\u0082",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u0084",
            "",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u0086",
            "\1\u0087",
            "",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u008e",
            "\1\u008f",
            "",
            "\1\u0090",
            "",
            "\1\u0091",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u009f",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "\1\u00a2",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "",
            "",
            "",
            "",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "",
            "",
            "\12\35\7\uffff\32\35\6\uffff\32\35",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | NUMERO | IDENTIFICADOR | TEXTO | CARACTER | FINLINEA );";
        }
    }
 

}