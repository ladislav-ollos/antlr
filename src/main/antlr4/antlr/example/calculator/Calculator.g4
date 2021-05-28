grammar Calculator;
// this is how you define headers of generated files (you can add comments or imports here)
@header {
// generated source, do not touch!
}

/*
 * Parser Rules
 */
expression :
            left=sum op=(CARET|AMP|VERTI_BAR|SHIFT_L|SHIFT_R) right=expression #bitwise
            | sum  #toSum
           ;
sum:
        expr+=product (op+=(PLUS|MINUS) expr+=product)*
;
product:
         left=function op=(MUL|DIV|MOD) right=product #prod
         | function     #functionCall
;

function:  f=(EXP | GCD) '(' first=expression ',' second=expression ')' #binaryFunction
           | '(' exp=expression ')' #parenthesis
           | exp=function '!'       #factorial
           | value                  #number
;

value:
        HEXADECIMAL            #hexadecimal
        | OCTAL                #octal
        | DECIMAL              #decimal
        | ANY                  #error
;

/*
 * Lexer Rules
 */
fragment HEX : [0-9a-fA-F];
fragment OCT : [0-7] ;
fragment DIGIT : [0-9] ;

DECIMAL: '0' | [1-9]DIGIT*;
HEXADECIMAL: '0x0' | '0x'[1-9a-fA-F]HEX*;
OCTAL: '00' | '0'[1-7]OCT*;

EXP : 'exp' | 'EXP';
GCD : 'gcd' | 'GCD';

MUL : '*';
DIV : '/';
MOD : '%';

PLUS: '+';
MINUS: '-';

SHIFT_L: '<<';
SHIFT_R: '>>';
AMP: '&';
CARET: '^';
VERTI_BAR: '|';

WHITESPACE : [ \n\t\r]+ -> skip; // channel

ANY : . ; //catch all rule