grammar SigeceneGram;

options {
	language=Java;
	backtrack=true;
	memoize=true;
	output=AST;
	ASTLabelType=CommonTree;
}

@lexer::header{
package com.siagcee.logic;

/**
 * Creado por Fabio Pereira.
 * Bajo la tutoria del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computacion.
 * Universidad Central de Venezuela.
 */
}

@parser::header{
package com.siagcee.logic;

/**
 * Creado por Fabio Pereira.
 * Bajo la tutoria del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computacion.
 * Universidad Central de Venezuela.
 */
}

@members {
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
}

@rulecatch {
	catch (RecognitionException e) {
		throw e;
	}
}

prog	:	stat+
	;
	
stat	:	ID '=' proposicion	->	^('=' ID proposicion)
	|	condicional
	|	'obtener' proposicion	->	^('obtener' proposicion)
	|	NEWLINE			->
	;

condicional
	:	'si'^ '('! cond=proposicion ';'! (stat+)')'!
	;

proposicion
	:	((NEWLINE)*)! negacion (('Y'^|'O'^) negacion)*
	;

negacion:	'NO'^ comparacion
	|	comparacion
	;

comparacion
	:	BOOLEAN
	|	factorStr (('>'^|'>='^|'<'^|'<='^|'=='^|'<>'^) factorStr)?
	;

factorStr
	:	STRING
	|	expr
	;

expr	:	producto (('+'^|'-'^) producto)*
	;

producto:	factor (('*'^|'/'^) factor)*
	;

factor	:	DOUBLE
	|	ID
	|	funcPredefinidas
	|	'('! proposicion ')'!
	;

//funciones predefinidas
funcPredefinidas
	:	promedio
	|	sumatoria
	|	contar
	|	max
	|	min
	|	redondea
	|	diff_fechas
	;
	
promedio:	'promedio' '(' ID ')'	->	^('promedio' ID)
	;

sumatoria
	:	'sumatoria' '(' ID ')'	->	^('sumatoria' ID)
	;

contar	:	'contar' '(' ID ')'	->	^('contar' ID)
	;

max	:	'max' '(' ID ')'	->	^('max' ID)
	;

min	:	'min' '(' ID ')'	->	^('min' ID)
	;

redondea:	'redondea' '(' expr ')'	->	^('redondea' expr)
	;

diff_fechas
	:	'diff_fechas' '(' factorStr ',' factorStr ')'	->	^('diff_fechas' factorStr factorStr)
	;
//fin funciones predefinidas

// START:tokens
STRING	:	'"' (~('"' | '\n' | '\r' | '\t'))+ '"';
BOOLEAN	:	'verdad' | 'falso';
ID	:	'[' (~('[' | ']' | '\n' | '\r' | '\t'))+ ']';
DOUBLE	:	'0'..'9'+ ((','|'.') '0'..'9'+)?;
NEWLINE	:	'\r'? '\n';
WS	:	(' '|'\t'|'\n'|'\r')+ {skip();} ;
// END:tokens
