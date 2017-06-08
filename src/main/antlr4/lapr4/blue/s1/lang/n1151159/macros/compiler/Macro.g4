grammar Macro;
@header {
    package lapr4.blue.s1.lang.n1151159.macros.compiler;
}

macro
    : NEWLINE* (SEMI (~NEWLINE)* NEWLINE+| expression)* (SEMI (~NEWLINE)* NEWLINE+ | EQ? comparison) NEWLINE* EOF
    ;

expression
	: EQ? comparison NEWLINE+
	;

block
	: L_CURLY_BRACKET comparison ( SEMI comparison )* R_CURLY_BRACKET
	;

comparison
	: concatenation
		( ( EQ | NEQ | GT | LT | LTEQ | GTEQ ) concatenation )?
	;

concatenation
        : ( MINUS )? atom
        | concatenation PERCENT
        | <assoc=right> concatenation POWER concatenation
        | concatenation ( MULTI | DIV ) concatenation
        | concatenation ( PLUS | MINUS ) concatenation
        | concatenation AMP concatenation
        ;

script
    : ( SPECIAL_CHAR | 'macro_start' | 'macro_end' | (( '<![SHELL[' | '<[SHELL[' ) script ']]>') | ~(']]>'))*
    ;

shellscript
    :   ( '<![SHELL[' | '<[SHELL[' ) script ']]>'
    ;

atom
	:	function_call
	|	reference
	|	literal
	|	LPAR comparison RPAR
	|	block
	|	assignment
	|   shellscript
	;

assignment
	:  LPAR reference ASSIGN comparison RPAR
	;

function_call
	:	FUNCTION LPAR
		( comparison ( SEMI comparison )* )?
		RPAR
	;

reference
	:	CELL_REF
		( ( COLON ) CELL_REF )?
	;

literal
	:	NUMBER
	|	STRING
	;


fragment LETTER: ('a'..'z'|'A'..'Z') ;

FUNCTION :
	  ( LETTER )+
	;


CELL_REF
	:
		( ABS )? LETTER ( LETTER )?
		( ABS )? ( DIGIT )+
	;

/* String literals, i.e. anything inside the delimiters */

STRING  : QUOT ('\\"' | ~'"')* QUOT
        ;

QUOT: '"'
	;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

fragment DIGIT : '0'..'9' ;

/* Comparison operators */
EQ		: '=' ;
NEQ		: '<>' ;
LTEQ	: '<=' ;
GTEQ	: '>=' ;
GT		: '>' ;
LT		: '<' ;

/* Text operators */
AMP		: '&' ;

/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV		: '/' ;
POWER	: '^' ;
PERCENT : '%' ;

/* Reference operators */
fragment ABS : '$' ;
fragment EXCL:  '!'  ;
COLON	: ':' ;

/* Miscellaneous operators */
COMMA	: ',' ;
SEMI	: ';' ;
LPAR	: '(' ;
RPAR	: ')' ;
L_CURLY_BRACKET	: '{' ;
R_CURLY_BRACKET	: '}' ;

/* assignment operator */
ASSIGN  : ':=' ;

fragment PARAGRAPH : ('\r'? '\n' | '\r');

NEWLINE : PARAGRAPH;

/* Items to be ignored */
WHITESPACE   : (' ' | '\t')         -> skip;

SPECIAL_CHAR : [.!?_] ;
