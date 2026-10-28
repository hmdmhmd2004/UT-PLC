package main.ast.expressions;

import main.ast.types.Identifier;
import main.visitor.IVisitor;
import java.util.ArrayList;
import java.util.List;

public class MethodCall extends Expression {
    private Expression instance;
    private Identifier callee;
    private List<Expression> args;

    public MethodCall() {
        this.args = new ArrayList<>();
    }

    public void addArgument(Expression arg) {
        this.args.add(arg);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Expression getInstance() {
        return instance;
    }

    public void setInstance(Expression instance) {
        this.instance = instance;
    }

    public Identifier getCallee() {
        return callee;
    }

    public void setCallee(Identifier callee) {
        this.callee = callee;
    }

    public List<Expression> getArgs() {
        return args;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
    }
}