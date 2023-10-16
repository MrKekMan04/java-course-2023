package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return this.value;
        }
    }

    record Negate(double value) implements Expr {
        public Negate(Constant value) {
            this(value.evaluate());
        }

        @Override
        public double evaluate() {
            return -value;
        }
    }

    record Exponent(double value, double pow) implements Expr {
        public Exponent(Constant value, Constant pow) {
            this(value.evaluate(), pow.evaluate());
        }

        @Override
        public double evaluate() {
            return Math.pow(value, pow);
        }
    }

    record Addition(double value1, double value2) implements Expr {
        public Addition(Constant value1, Constant value2) {
            this(value1.evaluate(), value2.evaluate());
        }

        @Override
        public double evaluate() {
            return value1 + value2;
        }
    }

    record Multiplication(double value1, double value2) implements Expr {
        public Multiplication(Constant value1, Constant value2) {
            this(value1.evaluate(), value2.evaluate());
        }

        @Override
        public double evaluate() {
            return value1 * value2;
        }
    }
}
