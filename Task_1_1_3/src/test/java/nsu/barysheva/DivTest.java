package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class DivTest {

    @Test
    public void testPrint() {
        Expression left = new Number(10);
        Expression right = new Number(5);
        Div div = new Div(left, right);

        assertEquals("(10/5)", div.print());
    }

    @Test
    void testDerivative() {
        Expression left = new Add(new Number(10), new Variable("x"));
        Expression right = new Mul(new Variable("x"), new Number(5));
        Expression ld = left.derivative("x");
        Expression rd = right.derivative("x");

        Sub sub = new Sub(new Mul(ld, right), new Mul(left, rd));
        Div div = new Div(sub, new Mul(right, right));
        assertEquals("((((0+1)*(x*5))-((10+x)*((1*5)+(x*0))))/((x*5)*(x*5)))", div.print());

    }

    @Test
    void testDerivative2() {
        Expression left = new Variable("x");
        Expression right = new Number(4);
        Div div = new Div(left, right);
        Expression derivative = div.derivative("x");
        assertEquals("(((1*4)-(x*0))/(4*4))", derivative.print());
    }
}