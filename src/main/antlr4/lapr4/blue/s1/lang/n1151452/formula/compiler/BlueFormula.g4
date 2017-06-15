grammar BlueFormula;
@header {
    package lapr4.blue.s1.lang.n1151452.formula.compiler;
}

expression
	: EQ comparison EOF
	;

comparison
	: concatenation
		(  ( EQ | NEQ | LTEQ | GTEQ | GT | LT ) concatenation )?
	| for_loop
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
    : ( SPECIAL_CHAR | (( '<![SHELL[' | '<[SHELL[' ) script ']]>') | ~('<[SHELL[' | '<![SHELL[' | ']]>'))*
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
    |   VARIABLE_NAME
    |   G_VARIABLE_NAME
	;

for_loop
    : FOR L_CURLY_BRACKET assignment SEMI  comparison ( SEMI comparison )+ R_CURLY_BRACKET
    ;

block
	: L_CURLY_BRACKET comparison ( SEMI comparison )* R_CURLY_BRACKET
	;

assignment
	:  LPAR (reference | (VARIABLE_NAME) | (G_VARIABLE_NAME) ) ASSIGN comparison RPAR
	;

function_call
	:	FUNCTION LPAR
		( comparison ( SEMI comparison )* )?
		RPAR
	;

reference
	:	CELL_REF ( ( COLON ) CELL_REF )? | CELL | array
	;

literal
	:	NUMBER
	|	STRING
	;

array:  
    (ARRAY_NAME)(index|string_index) ;

index: INDEX;

string_index: '[' STRING ']';

fragment LETTER: ('a'..'z'|'A'..'Z') ;

FOR : 'FOR' | 'for' | 'For';

FUNCTION :
	  ( LETTER )+
	;

CELL_REF
	:
		( ABS )? LETTER ( LETTER )?
		( ABS )? ( DIGIT )+
	;

CELL : '!' 'CELL';


ARRAY_NAME: '&''COL';
               
VARIABLE_NAME 
        : UNDERSCORE LETTER (DIGIT|LETTER)* (INDEX)?
        ;

G_VARIABLE_NAME
        : AT LETTER (DIGIT|LETTER)* (INDEX)?
        ;

INDEX
        :  L_RIGHT_PAR POSITIVE_DIGIT (DIGIT)* R_RIGHT_PAR
        ;

/* String literals, i.e. anything inside the delimiters */

STRING  : QUOT ('\\"' | ~'"')* QUOT
        ;

QUOT: '"'
	;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

fragment
DIGIT : '0'..'9' ;
POSITIVE_DIGIT : '0'..'9' ;

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
COMMA	    : ',' ;
SEMI	    : ';' ;
LPAR	    : '(' ;
RPAR	    : ')' ;
L_RIGHT_PAR	: '[' ;
R_RIGHT_PAR	: ']' ;
L_CURLY_BRACKET	: '{' ;
R_CURLY_BRACKET	: '}' ;
fragment UNDERSCORE : '_';
fragment AT : '@';

/* assignment operator */
ASSIGN  : ':=' ;

/* White-space (ignored) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;

SPECIAL_CHAR: [.!?_*] ;
