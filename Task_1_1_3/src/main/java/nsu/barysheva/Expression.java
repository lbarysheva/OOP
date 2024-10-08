package nsu.barysheva;

import java.util.Map;

/**
 * absract class needed to declare methods.
 */

public abstract class Expression {
    public abstract String print();

    public abstract Expression derivative(String variable);

    public abstract int eval(Map<String, Integer> values);
}