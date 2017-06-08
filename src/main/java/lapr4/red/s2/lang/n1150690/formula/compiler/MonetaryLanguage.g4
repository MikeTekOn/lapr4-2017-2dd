grammar MonetaryLanguage;
@header {
    package lapr4.red.s2.lang.n1150690.formula.compiler;
}

formula: START_CHAR currency L_CURLY_BRACKET expression R_CURLY_BRACKET EOF;

currency: DOLLAR | EURO | POUND;

expression:
    LPAR expression RPAR
    | left=expression op=MULTI (NUMBER | NUMBER_FOR_COIN)
    | left=expression op=DIV NUMBER
    | left=expression op=(PLUS|MINUS) right=expression
    | value=NUMBER
    | value=NUMBER_FOR_COIN;

START_CHAR: '#' ;
DOLLAR  : 'dollar' | 'Dollar' | 'DOLLAR';
EURO    : 'euro' | 'Euro' | 'EURO';
POUND   : 'pound' | 'Pound' | 'POUND';
NUMBER_FOR_COIN  : [0-9]+'.'[0-9][0-9]('€' | '$' | '£' );
NUMBER  : '-'?[0-9]+('.'[0-9][0-9])? ;


/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV	: '/' ;

/* Miscellaneous operators */
COMMA	: ',' ;
LPAR	: '(' ;
RPAR	: ')' ;
L_CURLY_BRACKET	: '{' ;
R_CURLY_BRACKET	: '}' ;

/* White-space (ignored) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;