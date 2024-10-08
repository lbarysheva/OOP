package nsu.barysheva;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * example of main
 */

public class Main {
    public static void main(String[] args) {
        // example of expression: 3 + (2 * x)
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println("Expression: " + e.print()); // expected result: (3+(2*x))

        // derivative of expression
        Expression derivative = e.derivative("x");
        System.out.println("Derivative: " + derivative.print()); // expected result: (0+((0*x)+(2*1)))

        Map<String, Integer> variables = new HashMap<>();
        variables.put("x", 10);
        int result = e.eval(variables);
        System.out.println("Result with x = 10: " + result); // expected result: 23
    }
}