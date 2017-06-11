grammar MonetaryLanguage;
@header {
    package lapr4.red.s2.lang.n1150690.formula.compiler;
}

formula: START_CHAR currency L_CURLY_BRACKET expression R_CURLY_BRACKET EOF;

currency: DOLLAR | EURO | POUND;


expression:
    left=expression op=(MULTI|PLUS|MINUS) LPAR expression RPAR
    | left=expression op=MULTI value
    | left=expression op=DIV NUMBER
    | left=expression op=(PLUS|MINUS) value
    | value;

value: NUMBER | NUMBER_FOR_COIN coins;

START_CHAR: '#' ;
DOLLAR  : 'dollar' | 'Dollar' | 'DOLLAR';
EURO    : 'euro' | 'Euro' | 'EURO';
POUND   : 'pound' | 'Pound' | 'POUND';
NUMBER_FOR_COIN  : [0-9]+'.'[0-9][0-9];
NUMBER  : '-'?[0-9]+('.'[0-9][0-9])? ;

coins: (EURO_SYM |DOLLAR_SYM | LIBRA_SYM);

EURO_SYM : '€' | '\u20AC';
DOLLAR_SYM : '$';
LIBRA_SYM : '£' | '\u00A3';

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