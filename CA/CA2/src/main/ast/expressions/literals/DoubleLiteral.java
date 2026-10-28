package main.ast.expressions.literals;

import main.ast.expressions.ConstantExpression;
import main.visitor.IVisitor;

public class DoubleLiteral extends ConstantExpression {
    private double value;

    public DoubleLiteral(double value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}