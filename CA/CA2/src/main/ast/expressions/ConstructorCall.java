package main.ast.expressions;

import main.ast.types.Identifier;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class ConstructorCall extends Expression {
    private Identifier className;
    private List<Expression> args;

    public ConstructorCall(Identifier className) {
        this.className = className;
        this.args = new ArrayList<>();
    }

    public void addArgument(Expression arg) {
        this.args.add(arg);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Identifier getClassName() {
        return className;
    }

    public void setClassName(Identifier className) {
        this.className = className;
    }

    public List<Expression> getArgs() {
        return args;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
    }
}