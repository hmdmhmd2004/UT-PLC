package main.ast.expressions;

import main.ast.types.Identifier;
import main.visitor.IVisitor;

public class MemberLoc extends Location {
    private Expression target;
    private Identifier member;

    public MemberLoc(Identifier member, Expression target) {
        this.member = member;
        this.target = target;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    public Identifier getMember() {
        return member;
    }

    public void setMember(Identifier member) {
        this.member = member;
    }
}