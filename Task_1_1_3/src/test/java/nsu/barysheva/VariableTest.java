package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class VariableTest {

    @Test
    public void testPrint() {
        Variable variable = new Variable("x");
        assertEquals("x", variable.print());
    }

    @Test
    public void testDerivativeOfSameVariable() {
        Variable variable = new Variable("x");
        Expression derivative = variable.derivative("x");

        assertEquals("1", derivative.print());
    }

    @Test
    public void testDerivativeOfDifferentVariable() {
        Variable variable = new Variable("x");
        Expression derivative = variable.derivative("y");

        assertEquals("0", derivative.print());
    }
}