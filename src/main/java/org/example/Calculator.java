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

    public double atan(double a) {
        return Math.atan(a);
    }

    public int min(int a, int b) {
        return Math.min(a, b);
    }

    public double min(double a, double b) {
        return Math.min(a, b);
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    public double max(double a, double b) {
        return Math.max(a, b);
    }
}
