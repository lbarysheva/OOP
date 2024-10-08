package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class AddTest {

    @Test
    public void testPrint() {
        Expression left = new Number(3);
        Expression right = new Number(5);
        Add add = new Add(left, right);

        assertEquals("(3+5)", add.print());
    }

    @Test
    public void testDerivative() {
        Expression left = new Variable("x");
        Expression right = new Variable("y");
        Add add = new Add(left, right);
        Expression derivative = add.derivative("x");

        assertEquals("(1+0)", derivative.print());
    }

    @Test
    public void testDerivativeWithConstant() {
        Expression left = new Variable("x");
        Expression right = new Number(5);
        Add add = new Add(left, right);
        Expression derivative = add.derivative("x");

        assertEquals("(1+0)", derivative.print());
    }
}