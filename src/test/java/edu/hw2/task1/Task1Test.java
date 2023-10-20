package edu.hw2.task1;

import org.junit.jupiter.api.Test;

import static edu.hw2.task1.Expr.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    @Test
    public void assertThatConstantEvaluateReturnedRightAnswerTest() {
        assertEquals(new Constant(1).evaluate(), 1);
    }

    @Test
    public void assertThatNegateEvaluateReturnedRightAnswerTest() {
        assertEquals(new Negate(new Constant(1)).evaluate(), -1);
        assertEquals(new Negate(new Constant(1)).evaluate(), new Constant(-1).evaluate());
    }

    @Test
    public void assertThatExponentEvaluateReturnedRightAnswerTest() {
        assertEquals(new Exponent(2, 10).evaluate(), 1024);
        assertEquals(new Exponent(new Constant(-1), new Constant(2)).evaluate(), new Constant(1).evaluate());
    }

    @Test
    public void assertThatAdditionEvaluateReturnedRightAnswerTest() {
        assertEquals(new Addition(1, 2).evaluate(), 1 + 2);
        assertEquals(new Addition(1, 2), new Addition(new Constant(1), new Constant(2)));
        assertEquals(new Addition(1, 2).evaluate(), new Addition(new Constant(1), new Constant(2)).evaluate());
    }

    @Test
    public void assertThatMultiplicationEvaluateReturnedRightAnswerTest() {
        assertEquals(new Multiplication(1, 2).evaluate(), 2);
        assertEquals(new Multiplication(-2, -2).evaluate(), new Constant(4).evaluate());
        assertEquals(new Multiplication(new Constant(-2), new Constant(-1)).evaluate(), 2);
    }
}
