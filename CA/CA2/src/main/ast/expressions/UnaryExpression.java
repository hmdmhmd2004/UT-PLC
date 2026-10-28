package main.ast.expressions;

import main.ast.expressions.operators.UnaryOperator;
import main.visitor.IVisitor;

public class UnaryExpression extends Expression {
    private Expression operand;
    private UnaryOperator unaryOperator;

    public UnaryExpression(Expression operand, UnaryOperator unaryOperator) {
        this.operand = operand;
        this.unaryOperator = unaryOperator;
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

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public void setUnaryOperator(UnaryOperator unaryOperator) {
        this.unaryOperator = unaryOperator;
    }
}