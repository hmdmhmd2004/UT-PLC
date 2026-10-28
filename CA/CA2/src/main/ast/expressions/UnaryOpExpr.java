package main.ast.expressions;

import main.visitor.IVisitor;

public class UnaryOpExpr extends Expression {
    public enum Operator {
        MINUS, NOT, PRE_INC, PRE_DEC, POST_INC, POST_DEC
    }

    private Expression operand;
    private Operator operator;

    public UnaryOpExpr(Expression operand, Operator operator) {
        this.operand = operand;
        this.operator = operator;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}