grammar simpleLang;

@header{
    package main.grammar;
    import main.ast.*;
}


program returns [Program programRet]
: mol EOF
;

mol
: (module | struct)+
;

module
: m_dec BEGIN (field | method)* END
;

struct
: s_dec BEGIN field* END
;

m_dec
: MODULE id include?
;

s_dec
: STRUCT id include?
;

include
: INCLUDES id
;


method
: access_lvl? return_type? id '(' args? ')' BEGIN stmt* END
;

field
: access_lvl? type id (assign expr)? ';'
;

stmt
: output_stmt ';'
| return_stmt ';'
| assign_stmt ';'
| if_stmt
| for_stmt
| while_stmt
| module_sample_stmt ';'
| access ';'
| dec_stmt ';'
;

module_sample_stmt
: MUT? id ('(' passing_args ')')? id
;

dec_stmt
: MUT? type id
;

return_stmt
: RETURN expr
;


output_stmt
: OUTPUT expr
;

assign_stmt
: type? (id|access) assign expr ('(' passing_args ')')?
;

access
: id '.' access
| id '.' id '(' passing_args? ')'
| id '.' id
;

if_stmt
: IF '(' condition ')' BEGIN stmt* END elif_stmt* else_stmt?
;

elif_stmt
: ELIF '(' condition ')' BEGIN stmt END
;

else_stmt
: ELSE BEGIN stmt END
;

while_stmt
: WHILE '(' condition ')' BEGIN stmt* END
;

for_stmt
: FOR '(' passing_args? ';' condition? ';' for_changes? ')' BEGIN stmt* END
;

for_changes
: assign_stmt (',' assign_stmt)* 
;

passing_args
: passing_arg (',' passing_arg)*
;

passing_arg
: expr | assign_stmt
;

args
: arg (',' arg)*
;

arg
: MUT? simple_type id (assign primitive)?
;

expr
: expr_rest (math expr_rest)*
;

expr_rest
: '(' expr ')'
| id
| primitive
| access
;


return_type
: simple_type
| VOID
;

type
: MUT? simple_type
;

condition
: expr comparison expr | expr
;

simple_type
: INT_TYPE
| BOOL_TYPE
| FLOAT_TYPE
| CHAR_TYPE
| DOUBLE_TYPE
| id
;

primitive
: true
| false
| int
| float
;

true
: TRUE_VAL
;

false
: FALSE_VAL
;

uint
: INT_VAL
;

int
: num_sign? INT_VAL
;

float
: int '.' uint?
| '.' uint?
;

math
: '+'
| '-'
| '*'
| '/'
| '%'
;

assign
: '='
;

num_sign
: '+'
| '-'
;

comparison
: '>'
| '<'
| '=='
| '>='
| '<='
| '!='
;

id
: ID
;

access_lvl
: PRIVATE
| PUBLIC
;

////

MODULE : 'module';
STRUCT : 'struct';
INCLUDES : 'includes';
BEGIN : 'begin';
END : 'end';

MUT : 'mut';

// Types
INT_TYPE : 'int';
BOOL_TYPE : 'bool';
FLOAT_TYPE : 'float';
DOUBLE_TYPE : 'double';
CHAR_TYPE : 'char';

VOID : 'void';
RETURN : 'return';
OUTPUT: 'output';

WHILE : 'while';
FOR : 'for';

// Values
TRUE_VAL : 'true';
FALSE_VAL : 'false';
INT_VAL : [1-9][0-9]* | '0';

IF : 'if';
ELIF : 'elif';
ELSE : 'else';

PUBLIC : 'public';
PRIVATE : 'private';

ID : [a-zA-Z_][a-zA-Z0-9_]*;
DIGITS : [0-9]+;

WHITE_SPACE : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;
