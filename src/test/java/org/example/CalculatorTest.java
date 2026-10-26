package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void add() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(2, -3));
    }

    @Test
    void subtract() {
        assertEquals(-1, calculator.subtract(2, 3));
        assertEquals(5, calculator.subtract(2, -3));
    }

    @Test
    void multiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(2, 0));
        assertEquals(2, calculator.multiply(2, 1));
    }

    @Test
    void sin() {
        assertEquals(0.0, calculator.sin(0), 1e-9);
        assertEquals(1.0, calculator.sin(Math.PI / 2), 1e-9);
        assertEquals(0.0, calculator.sin(Math.PI), 1e-9);
    }

    @Test
    void cos() {
        assertEquals(1.0, calculator.cos(0), 1e-9);
        assertEquals(0.0, calculator.cos(Math.PI / 2), 1e-9);
        assertEquals(-1.0, calculator.cos(Math.PI), 1e-9);
    }

    @Test
    void acos() {
        // Test boundary values
        assertEquals(0.0, calculator.acos(1), 1e-9);
        assertEquals(Math.PI / 2, calculator.acos(0), 1e-9);
        assertEquals(Math.PI, calculator.acos(-1), 1e-9);

        // Test intermediate value
        assertEquals(Math.PI / 3, calculator.acos(0.5), 1e-9);

        // Test out-of-domain
        assertTrue(Double.isNaN(calculator.acos(2)));
        assertTrue(Double.isNaN(calculator.acos(-2)));
    }
}
