package org.example;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double pow(int a, int b) {
        return Math.pow(a, b);
    }

    public double abs(int a) {
        return Math.abs(a);
    }

    public double tanh(double a) {
        return Math.tanh(a);
    }

    public double sin(double a) {
        return Math.sin(a);
    }

    public double cos(double a) {
        return Math.cos(a);
    }
}
