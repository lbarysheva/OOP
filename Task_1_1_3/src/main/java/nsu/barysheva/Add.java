package nsu.barysheva;

import java.util.Map;

public class Add extends Expression {
    private final Expression left, right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> variables) {
        return left.eval(variables) + right.eval(variables);
    }

    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }
}