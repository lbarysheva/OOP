package nsu.barysheva;

import java.util.Map;

/**
 * class with implementation of multiplying
 */

public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        Expression ld = left.derivative(variable); // left part derivative
        Expression rd = right.derivative(variable); // right part derivative

        return new Add(new Mul(ld, right), new Mul(left, rd));
    }

    @Override
    public int eval(Map<String, Integer> values) {
        return left.eval(values) * right.eval(values);
    }
}