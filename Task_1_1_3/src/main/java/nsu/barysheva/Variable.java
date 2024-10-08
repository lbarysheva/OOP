package nsu.barysheva;

import java.util.Map;

public class Variable extends Expression {
    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return name;
    }

    @Override
    public Expression derivative(String variable) {
        return name.equals(variable) ? new Number(1) : new Number(0);
    }

    @Override
    public int eval(Map<String, Integer> values) {
        return values.getOrDefault(name, 0);
    }

}