package main.ast.expressions.literals;

import main.ast.expressions.ConstantExpression;
import main.visitor.IVisitor;

public class CharLiteral extends ConstantExpression {
    private char value;

    public CharLiteral(char value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}