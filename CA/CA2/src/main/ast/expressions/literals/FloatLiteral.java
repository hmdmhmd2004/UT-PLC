package main.ast.expressions.literals;

import main.ast.expressions.ConstantExpression;
import main.visitor.IVisitor;

public class FloatLiteral extends ConstantExpression {
    private float value;

    public FloatLiteral(float value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}