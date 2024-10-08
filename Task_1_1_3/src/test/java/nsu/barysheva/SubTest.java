package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class SubTest {
    @Test
    public void testPrint() {
        Expression left = new Number(8);
        Expression right = new Number(3);
        Sub sub = new Sub(left, right);

        assertEquals("(8-3)", sub.print());
    }

    @Test
    public void testDerivative() {
        Expression left = new Variable("x");
        Expression right = new Variable("y");
        Sub sub = new Sub(left, right);
        Expression derivative = sub.derivative("x");

        assertEquals("(1-0)", derivative.print());  // Производная разности: u' - v'
    }

    @Test
    public void testDerivativeWithConstant() {
        Expression left = new Variable("x");
        Expression right = new Number(7);
        Sub sub = new Sub(left, right);
        Expression derivative = sub.derivative("x");

        assertEquals("(1-0)", derivative.print());
    }
}