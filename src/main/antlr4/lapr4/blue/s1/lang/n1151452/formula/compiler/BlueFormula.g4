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

atom
	:	function_call
	|	reference
	|	literal
	|	LPAR comparison RPAR
	|	block
	|	assignment
	;

for_loop
    : FOR L_CURLY_BRACKET assignment SEMI  comparison ( SEMI comparison )+ R_CURLY_BRACKET
    ;

block
	: L_CURLY_BRACKET comparison ( SEMI comparison )* R_CURLY_BRACKET
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
	:	CELL_REF ( ( COLON ) CELL_REF )?
	;

literal
	:	NUMBER
	|	STRING
	;
	

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

/* String literals, i.e. anything inside the delimiters */

STRING  : QUOT ('\\"' | ~'"')* QUOT
        ;

QUOT: '"' 
	;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

fragment 
DIGIT : '0'..'9' ;

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

/* White-space (ignored) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;
	
	
 