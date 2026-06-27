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
    void testAtan() {
        assertEquals(0.0, calculator.atan(0), 1e-9);
        assertEquals(Math.PI / 4, calculator.atan(1), 1e-9);
        assertEquals(-Math.PI / 4, calculator.atan(-1), 1e-9);
        assertEquals(Math.PI / 2, calculator.atan(Double.POSITIVE_INFINITY), 1e-9);
        assertEquals(-Math.PI / 2, calculator.atan(Double.NEGATIVE_INFINITY), 1e-9);
    }

    @Test
    void testMinInt() {
        assertEquals(2, calculator.min(2, 3));
        assertEquals(2, calculator.min(3, 2));
        assertEquals(-5, calculator.min(-1, -5));
        assertEquals(0, calculator.min(0, 1));
    }

    @Test
    void testMinDouble() {
        assertEquals(2.5, calculator.min(2.5, 3.5), 1e-9);
        assertEquals(2.1, calculator.min(3.7, 2.1), 1e-9);
        assertEquals(-5.5, calculator.min(-1.5, -5.5), 1e-9);
        assertTrue(Double.isNaN(calculator.min(Double.NaN, 1.0)));
    }

    @Test
    void testMaxInt() {
        assertEquals(3, calculator.max(2, 3));
        assertEquals(3, calculator.max(3, 2));
        assertEquals(-1, calculator.max(-1, -5));
        assertEquals(0, calculator.max(0, -1));
    }

    @Test
    void testMaxDouble() {
        assertEquals(3.5, calculator.max(2.5, 3.5), 1e-9);
        assertEquals(3.7, calculator.max(3.7, 2.1), 1e-9);
        assertEquals(-1.5, calculator.max(-1.5, -5.5), 1e-9);
        assertTrue(Double.isNaN(calculator.max(Double.NaN, 1.0)));
    }
}
