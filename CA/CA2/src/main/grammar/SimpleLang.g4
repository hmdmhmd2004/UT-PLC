grammar SimpleLang;

@header {
import main.ast.core.*;
import main.ast.declarations.Module;
import main.ast.declarations.*;
import main.ast.types.*;
import main.ast.statements.*;
import main.ast.expressions.*;
import main.ast.expressions.literals.*;
import main.ast.expressions.operators.*;
import java.util.ArrayList;
import java.util.List;
}

program returns [Program programRet]
    :
        { $programRet = new Program(); }
        (t=topLevelDecl { $programRet.addTopLevelDeclaration($t.topLevelDeclRet); })*
        EOF
    ;
topLevelDecl returns [TopLevelDecl topLevelDeclRet]
    :
        m=module { $topLevelDeclRet = new ModuleDecl($m.moduleRet); }
    |
        s=structDef { $topLevelDeclRet = new StructDecl($s.structRet); }
    ;

module returns [Module moduleRet]
    :
        k=KW_MODULE i=ID
        { $moduleRet = new Module(new Identifier($i.text)); $moduleRet.setLine($i.line); }
        (KW_INCLUDES i1=ID { $moduleRet.addInclude(new Identifier($i1.text)); } (COMMA i2=ID { $moduleRet.addInclude(new Identifier($i2.text)); } )*)?
        KW_BEGIN
        (mem=member { $moduleRet.addMember($mem.memberRet); } )*
        KW_END
    ;

structDef returns [Struct structRet]
    :
        k=KW_STRUCT i=ID 
        { $structRet = new Struct(new Identifier($i.text)); $structRet.setLine($i.line); }
        KW_BEGIN 
        (mem=member { $structRet.addMember($mem.memberRet); } )* KW_END
    ;

member returns [Member memberRet]
    :
        (am=accessModifier)? m=method_decl
        { 
            MethodDecl md = new MethodDecl();
            md.setMethod($m.methodRet);
            
            if ($am.ctx != null) {
                md.setAccessModifier($am.accessModifierRet);
            }
            $memberRet = md;
        }
    |
        (am=accessModifier)? v=vardecl SEMI
        { 
            VarDecl vd = new VarDecl();
            vd.setVar($v.varRet);
            
            if ($am.ctx != null) {
                vd.setAccessModifier($am.accessModifierRet);
            }
            $memberRet = vd;
        }
    ;

accessModifier returns [AccessModifier accessModifierRet]
    :
        pr=KW_PRIVATE { $accessModifierRet = AccessModifier.PRIVATE; }
    |
        pu=KW_PUBLIC { $accessModifierRet = AccessModifier.PUBLIC; }
    ;

method_decl returns [Method methodRet]
    :
        t=type i=ID LPAREN a=arguments RPAREN b=block
        { 
            $methodRet = new Method($t.typeRet, new Identifier($i.text), $a.parametersRet, $b.blockRet); 
            $methodRet.setLine($i.line); 
        }
    ;

arguments returns [List<Parameter> parametersRet]
    :
        { $parametersRet = new ArrayList<>(); }
        (p1=parameter { $parametersRet.add($p1.parameterRet); } (COMMA p2=parameter { $parametersRet.add($p2.parameterRet); } )*)?
    ;

parameter returns [Parameter parameterRet]
    :
        { boolean isMut = false; }
        (KW_MUT { isMut = true; } )?
        t=type i=ID
        { 
            $parameterRet = new Parameter(isMut, $t.typeRet, new Identifier($i.text)); 
            $parameterRet.setLine($i.line); 
        }
    ;
type returns [Type typeRet]
    :
        i=ID { $typeRet = new UserDefinedType(new Identifier($i.text)); }
    |   
        KW_INT { $typeRet = new PrimitiveType(PrimitiveType.Primitive.INT); }
    |
        KW_FLOAT { $typeRet = new PrimitiveType(PrimitiveType.Primitive.FLOAT); }
    |   
        KW_DOUBLE { $typeRet = new PrimitiveType(PrimitiveType.Primitive.DOUBLE); }
    |   
        KW_CHAR { $typeRet = new PrimitiveType(PrimitiveType.Primitive.CHAR); }
    |
        KW_VOID { $typeRet = new PrimitiveType(PrimitiveType.Primitive.VOID); }
    |   
        KW_BOOL { $typeRet = new PrimitiveType(PrimitiveType.Primitive.BOOL); }
    ;
vardecl returns [Var varRet]
    :
        { boolean isMut = false; }
        (KW_MUT { isMut = true; } )?
        (t=type { $varRet = new Var(isMut, $t.typeRet); } | c=cons { $varRet = new Var(isMut, $c.constructorCallRet); } )
        i=ID
        { 
            $varRet.setName(new Identifier($i.text)); 
            $varRet.setLine($i.line); 
        }
    ;

cons returns [ConstructorCall constructorCallRet]
    :
        i=ID
        { $constructorCallRet = new ConstructorCall(new Identifier($i.text)); }
        LPAREN
        (e1=expr { $constructorCallRet.addArgument($e1.expressionRet); } (COMMA e2=expr { $constructorCallRet.addArgument($e2.expressionRet); } )*)?
        RPAREN
        { $constructorCallRet.setLine($i.line); }
    ;

block returns [Block blockRet]
    :
        { $blockRet = new Block(); }
        k=KW_BEGIN
        (s=st { $blockRet.addStatement($s.statementRet); } )*
        KW_END
        { $blockRet.setLine($k.line); }
    ;

st returns [Statement statementRet]
    :
        b=block { $statementRet = $b.blockRet; $statementRet.setLine($b.blockRet.getLine()); }
    |
        vd=vardecl SEMI { $statementRet = new VarDeclStmt($vd.varRet); $statementRet.setLine($vd.varRet.getLine()); }
    |
        v=vardecl ASSIGN e=expr SEMI { 
            $statementRet = new VarDeclStmt($v.varRet); 
            ((VarDeclStmt)$statementRet).setInitial($e.expressionRet); 
            $statementRet.setLine($e.expressionRet.getLine()); 
        }
    |
        mc=methodcall SEMI { $statementRet = new MethodCallStmt($mc.methodCallRet); $statementRet.setLine($mc.methodCallRet.getLine()); }
    |
        ifs=ifStmt { $statementRet = $ifs.ifStmtRet; $statementRet.setLine($ifs.ifStmtRet.getLine()); }
    |
        fs=forStmt { $statementRet = $fs.forStmtRet; $statementRet.setLine($fs.forStmtRet.getLine()); }
    |
        ws=whileStmt { $statementRet = $ws.whileStmtRet; $statementRet.setLine($ws.whileStmtRet.getLine()); }
    |
        as=assignStmt { $statementRet = $as.assignStmtRet; $statementRet.setLine($as.assignStmtRet.getLine()); }
    |
        rs=returnStmt { $statementRet = $rs.returnStmtRet; $statementRet.setLine($rs.returnStmtRet.getLine()); }
    |
        is=inputStmt { $statementRet = $is.inputStmtRet; $statementRet.setLine($is.inputStmtRet.getLine()); }
    |
        os=outputStmt { $statementRet = $os.outputStmtRet; $statementRet.setLine($os.outputStmtRet.getLine()); }
    |
        js=jumpStmt { $statementRet = $js.jumpStmtRet; $statementRet.setLine($js.jumpStmtRet.getLine()); }
    ;

jumpStmt returns [JumpStmt jumpStmtRet]
    :
        kb=KW_BREAK { $jumpStmtRet = new BreakJump(); $jumpStmtRet.setLine($kb.line); }
    |
        kc=KW_CONTINUE { $jumpStmtRet = new ContinueJump(); $jumpStmtRet.setLine($kc.line); }
    ;

ifStmt returns [IfStmt ifStmtRet]
    :
        k=KW_IF LPAREN e=expr RPAREN s1=st
        { $ifStmtRet = new IfStmt($e.expressionRet, $s1.statementRet); }
        (KW_ELSE s2=st { $ifStmtRet.setElseBranch($s2.statementRet); } )?
        { $ifStmtRet.setLine($k.line); }
    ;

forStmt returns [ForStmt forStmtRet]
    @init {
        Block initBlock = new Block();
        Block updateBlock = new Block();
    }
    :
        k=KW_FOR LPAREN
        (ie1=initexpr { initBlock.addStatement($ie1.initExprRet); }
            (COMMA ie2=initexpr { initBlock.addStatement($ie2.initExprRet); })* )?
        SEMI
        (e=expr)?
        SEMI
        (l1=loc ASSIGN e1=expr {
            AssignStmt as1 = new AssignStmt($l1.locationRet, $e1.expressionRet);
            updateBlock.addStatement(as1);
        }
        (COMMA l2=loc ASSIGN e2=expr {
            AssignStmt as2 = new AssignStmt($l2.locationRet, $e2.expressionRet);
            updateBlock.addStatement(as2);
        })* )?
        RPAREN
        s=st
        {
            $forStmtRet = new ForStmt(
                initBlock.getStatements().isEmpty() ? null : initBlock,
                _localctx.e != null ? $e.expressionRet : null,   // <--- FIXED THIS LINE
                updateBlock.getStatements().isEmpty() ? null : updateBlock,
                $s.statementRet
            );
            $forStmtRet.setLine($k.line);
        }
    ;

whileStmt returns [WhileStmt whileStmtRet]
    :
        k=KW_WHILE LPAREN e=expr RPAREN s=st
        {
            $whileStmtRet = new WhileStmt($e.expressionRet, $s.statementRet);
            $whileStmtRet.setLine($k.line);
        }
    ;

assignStmt returns [AssignStmt assignStmtRet]
    :
        l=loc a=ASSIGN e=expr SEMI
        { 
            $assignStmtRet = new AssignStmt($l.locationRet, $e.expressionRet); 
            $assignStmtRet.setLine($a.line); 
        }
    ;

returnStmt returns [ReturnStmt returnStmtRet]
    :
        { $returnStmtRet = new ReturnStmt(); }
        k=KW_RETURN (e=expr { $returnStmtRet.setValue($e.expressionRet); } )? SEMI
        { $returnStmtRet.setLine($k.line); }
    ;

inputStmt returns [InputStmt inputStmtRet]
    :
        k=KW_INPUT l=loc SEMI
        { 
            $inputStmtRet = new InputStmt($l.locationRet); 
            $inputStmtRet.setLine($k.line); 
        }
    ;

outputStmt returns [OutputStmt outputStmtRet]
    :
        k=KW_OUTPUT e=expr SEMI
        { 
            $outputStmtRet = new OutputStmt($e.expressionRet); 
            $outputStmtRet.setLine($k.line); 
        }
    ;

loc returns [Location locationRet]
    :
        s=simpleLoc { $locationRet = $s.simpleLocRet; }
    |
        m=memberLoc { $locationRet = $m.memberLocRet; }
    |
        t=thisLoc { $locationRet = $t.thisLocRet; $locationRet.setLine($t.thisLocRet.getLine()); }
    ;

simpleLoc returns [SimpleLoc simpleLocRet]
    :
        i=ID
        { $simpleLocRet = new SimpleLoc(new Identifier($i.text)); $simpleLocRet.setLine($i.line); }
    ;

memberLoc returns [MemberLoc memberLocRet]
    :
        i=ID DOT l=loc
        { $memberLocRet = new MemberLoc(new Identifier($i.text), $l.locationRet); $memberLocRet.setLine($i.line); }
    ;

thisLoc returns [ThisLoc thisLocRet]
    :
        { $thisLocRet = new ThisLoc(); }
        k=KW_THIS (DOT l=loc { $thisLocRet.setLoc($l.locationRet); } )?
        { $thisLocRet.setLine($k.line); }
    ;

methodcall returns [MethodCall methodCallRet]
    :
        { $methodCallRet = new MethodCall(); }
        (l=loc DOT {$methodCallRet.setInstance($l.locationRet); })?
        i=ID
        { $methodCallRet.setCallee(new Identifier($i.text)); }
        LPAREN
        (e1=expr { $methodCallRet.addArgument($e1.expressionRet);}
         (COMMA e2=expr { $methodCallRet.addArgument($e2.expressionRet); } )*)?
        RPAREN
        { $methodCallRet.setLine($i.line); }
    ;

expr returns[Expression expressionRet]
    :
        l=loc { $expressionRet = $l.locationRet; }
    |   k=KW_THIS { $expressionRet = new ThisLoc(); $expressionRet.setLine($k.line); }
    |   mc=methodcall { $expressionRet = $mc.methodCallRet; }
    |   c=cons { $expressionRet = $c.constructorCallRet; }
    |   LPAREN e=expr RPAREN { $expressionRet = new ParanthesisExpr($e.expressionRet); }
    |   ci=CONSTINT { $expressionRet = new IntLiteral(Integer.parseInt($ci.text)); $expressionRet.setLine($ci.line); }
    |   cf=CONSTFLOAT { $expressionRet = new FloatLiteral(Float.parseFloat($cf.text)); $expressionRet.setLine($cf.line); }
    |   cd=CONSTDOUBLE { $expressionRet = new DoubleLiteral(Double.parseDouble($cd.text)); $expressionRet.setLine($cd.line); }
    |   cc=CONSTCHAR { $expressionRet = new CharLiteral($cc.text.charAt(1)); $expressionRet.setLine($cc.line); }
    |   cb=CONSTBOOL { $expressionRet = new BoolLiteral(Boolean.parseBoolean($cb.text)); $expressionRet.setLine($cb.line); }
    |   (m=MINUS | n=KW_NOT) e1=expr {
            UnaryOpExpr.Operator op = ($m != null) ? UnaryOpExpr.Operator.MINUS : UnaryOpExpr.Operator.NOT;
            $expressionRet = new UnaryOpExpr($e1.expressionRet, op);
        }
    |   e1=expr op=(STAR | SLASH) e2=expr {
            BinaryOperator bop = ($op.type == STAR) ? BinaryOperator.MULT : BinaryOperator.DIV;
            $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, bop);
        }
    |   e1=expr op=(PLUS | MINUS) e2=expr {
            BinaryOperator bop = ($op.type == PLUS) ? BinaryOperator.PLUS : BinaryOperator.MINUS;
            $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, bop);
        }
    |   e1=expr op=(LESS | GREATER | LESS_EQ | GREATER_EQ) e2=expr {
            BinaryOperator bop = BinaryOperator.LT;
            if ($op.type == GREATER) bop = BinaryOperator.GT;
            else if ($op.type == LESS_EQ) bop = BinaryOperator.LTE;
            else if ($op.type == GREATER_EQ) bop = BinaryOperator.GTE;
            $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, bop);
        }
    |   e1=expr op=(EQUAL | NOT_EQUAL) e2=expr {
            BinaryOperator bop = ($op.type == EQUAL) ? BinaryOperator.EQ : BinaryOperator.NEQ;
            $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, bop);
        }
    |   e1=expr KW_AND e2=expr { $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, BinaryOperator.AND); }
    |   e1=expr KW_OR e2=expr { $expressionRet = new BinaryExpression($e1.expressionRet, $e2.expressionRet, BinaryOperator.OR); }
    ;

initexpr returns [Statement initExprRet]
    :
        v1=vardecl
        { $initExprRet = new VarDeclStmt($v1.varRet); $initExprRet.setLine($v1.varRet.getLine()); }
    |
        l1=loc ASSIGN e1=expr
        { $initExprRet = new AssignStmt($l1.locationRet, $e1.expressionRet); $initExprRet.setLine($l1.locationRet.getLine()); }
    |
        v2=vardecl ASSIGN e2=expr
        { 
            $initExprRet = new VarDeclStmt($v2.varRet); 
            ((VarDeclStmt)$initExprRet).setInitial($e2.expressionRet); 
            $initExprRet.setLine($v2.varRet.getLine()); 
        }
    ;

// Tokens
KW_MODULE   : 'module' ;
KW_STRUCT   : 'struct' ;
KW_INCLUDES : 'includes' ;
KW_BEGIN    : 'begin' ;
KW_END      : 'end' ;
KW_PUBLIC   : 'public' ;
KW_PRIVATE  : 'private' ;
KW_INT      : 'int' ;
KW_FLOAT    : 'float' ;
KW_DOUBLE   : 'double' ;
KW_CHAR     : 'char' ;
KW_VOID     : 'void' ;
KW_IF       : 'if' ;
KW_ELSE     : 'else' ;
KW_FOR      : 'for' ;
KW_WHILE    : 'while' ;
KW_DO       : 'do' ;
KW_RETURN   : 'return' ;
KW_INPUT    : 'input' ;
KW_OUTPUT   : 'output' ;
KW_THIS     : 'this' ;
KW_NOT      : 'not' ;
KW_AND      : 'and' ;
KW_OR       : 'or' ;
KW_MUT      : 'mut' ;
KW_BREAK    : 'break' ;
KW_CONTINUE : 'continue' ;
KW_BOOL     : 'bool' ;
CONSTBOOL   : 'true' | 'false' ;

SEMI        : ';' ;
COMMA       : ',' ;
LPAREN      : '(' ;
RPAREN      : ')' ;
LBRACK      : '[' ;
RBRACK      : ']' ;
ASSIGN      : '=' ;
DOT         : '.' ;
ARROW       : '->' ;
MINUS       : '-' ;
PLUS        : '+' ;
STAR        : '*' ;
SLASH       : '/' ;
AMPERSAND   : '&' ;
LESS        : '<' ;
GREATER     : '>' ;
LESS_EQ     : '<=' ;
GREATER_EQ  : '>=' ;
EQUAL       : '==' ;
NOT_EQUAL   : '!=' ;

CONSTINT    : DIGIT+ ;
CONSTFLOAT  : DIGIT+ DOT DIGIT+ ;
CONSTDOUBLE : DIGIT+ DOT DIGIT+ EXPONENT SIGN? DIGIT+ ;
CONSTCHAR   : '\'' . '\'' ;

ID          : LETTER (LETTER | DIGIT | '_')* ;

fragment LETTER   : [a-zA-Z] ;
fragment DIGIT    : [0-9] ;
fragment EXPONENT : [eE] ;
fragment SIGN     : [+\-] ;

WS          : [ \t\r\n]+ -> skip ;
COMMENT     : '%%' ~[\r\n]* -> skip ;
MULTICOMMENT: '%%%' .*? '%%%' -> skip ;