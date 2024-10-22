package nsu.barysheva;

import java.util.Map;

/**
 * class with implementation of number
 */

public class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int eval(Map<String, Integer> variables) {
        return value;
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }
}