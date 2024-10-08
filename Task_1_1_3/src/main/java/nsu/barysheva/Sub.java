package nsu.barysheva;

import java.util.Map;
/**
 * class with implementation of subtraction
 */

public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "-" + right.print() + ")";
    }

    @Override
    public int eval(Map<String, Integer> values) {
        return left.eval(values) - right.eval(values);
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }
}
