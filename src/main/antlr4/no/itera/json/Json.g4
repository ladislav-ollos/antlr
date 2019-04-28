grammar Json;
/*
 * Parser Rules
 */
object : '{'( STRING ':' value (',' STRING ':' value )* )? '}';


// {    "glossary": {        "title": "example glossary",		"GlossDiv": {            "title": "S",			"GlossList": {                "GlossEntry": {                    "ID": "SGML",					"SortAs": "SGML",					"GlossTerm": "Standard Generalized Markup Language",
//					"Acronym": "SGML",					"Abbrev": "ISO 8879:1986",					"GlossDef": {                        "para": "A meta-markup language, used to create markup languages such as DocBook.",						"GlossSeeAlso": ["GML", "XML"]                    },					"GlossSee": "markup"                }            }        }    }}
value:   object
       | array
       | STRING
       | NUMBER
       | 'true'
       | 'false'
       | NULL
       ;

array: '['(  value (',' value )* )? ']';

/*
 * Lexer Rules
 */
NULL: 'null';
STRING: '"'('\\'["\\/bfnrt] | UNICODE | SAFECODEPOINT)*'"';
fragment SAFECODEPOINT: ~["\\\u0000-\u001F];
fragment UNICODE: '\\u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

NUMBER: '0' | '-'? INT ('.'[0-9]+)? | '-'? '0.'[0-9]+; // for simplicity not dealing with exponents

fragment INT : [1-9][0-9]*;
WHITESPACE : [ \n\t\r]+ -> skip;