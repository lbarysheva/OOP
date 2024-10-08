package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MulTest {

    @Test
    public void testPrint() {
        Expression left = new Number(2);
        Expression right = new Number(3);
        Mul mul = new Mul(left, right);

        assertEquals("(2*3)", mul.print());
    }

    @Test
    public void testDerivative() {
        Expression left = new Variable("x");
        Expression right = new Variable("y");
        Mul mul = new Mul(left, right);
        Expression derivative = mul.derivative("x");

        assertEquals("((1*y)+(x*0))", derivative.print());  // Производная по x: u'v + uv'
    }

    @Test
    public void testDerivativeWithConstant() {
        Expression left = new Variable("x");
        Expression right = new Number(5);
        Mul mul = new Mul(left, right);
        Expression derivative = mul.derivative("x");

        assertEquals("((1*5)+(x*0))", derivative.print());
    }
}