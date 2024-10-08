package nsu.barysheva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    public void testPrint() {
        Number number = new Number(5);
        assertEquals("5", number.print());
    }

    @Test
    public void testDerivative() {
        Number number = new Number(7);
        Expression derivative = number.derivative("x");

        assertEquals("0", derivative.print());
    }
}