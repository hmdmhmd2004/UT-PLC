package main.ast.statements;

import main.ast.expressions.Expression;
import main.visitor.IVisitor;

public class ForStmt extends Statement {
    private Statement initialize;
    private Expression condition;
    private Statement update;
    private Statement body;

    public ForStmt(Statement initialize, Expression condition, Statement update, Statement body) {
        this.initialize = initialize;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Statement getInitialize() {
        return initialize;
    }

    public void setInitialize(Statement initialize) {
        this.initialize = initialize;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Statement getUpdate() {
        return update;
    }

    public void setUpdate(Statement update) {
        this.update = update;
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = body;
    }
}