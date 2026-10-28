package main.ast.expressions.literals;

import main.ast.expressions.ConstantExpression;
import main.visitor.IVisitor;

public class IntLiteral extends ConstantExpression {
    private int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}