package nsu.barysheva;

import java.util.Map;

/**
 * class with implementation of division
 */

public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "/" + right.print() + ")";
    }

    @Override
    public int eval(Map<String, Integer> values) {
        int rightValue = right.eval(values);
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.eval(values) / rightValue;
    }

    @Override
    public Expression derivative(String variable) {
        Expression ld = left.derivative(variable);
        Expression rd = right.derivative(variable);
        Sub numerator = new Sub(new Mul(ld, right), new Mul(left, rd));
        Expression denominator = new Mul(right, right);

        if (right instanceof Number && ((Number) right).eval(Map.of()) == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        return new Div(numerator, denominator);
    }
}