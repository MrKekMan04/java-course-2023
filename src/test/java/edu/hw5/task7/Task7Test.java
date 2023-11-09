package edu.hw5.task7;

import org.junit.jupiter.api.Test;
import static edu.hw5.task7.Task7.checkRegularExpression;
import static org.junit.jupiter.api.Assertions.*;

public class Task7Test {
    @Test
    public void assertThatFirstRegularWorksRight() {
        assertTrue(checkRegularExpression(0, "100"));
        assertTrue(checkRegularExpression(0, "10010"));
        assertFalse(checkRegularExpression(0, "100F0"));
        assertFalse(checkRegularExpression(0, "F100"));
        assertFalse(checkRegularExpression(0, "1010"));
        assertFalse(checkRegularExpression(0, "10"));
    }

    @Test
    public void assertThatSecondRegularWorksRight() {
        assertTrue(checkRegularExpression(1, "101"));
        assertTrue(checkRegularExpression(1, "11"));
        assertTrue(checkRegularExpression(1, "1"));
        assertTrue(checkRegularExpression(1, "0"));
        assertTrue(checkRegularExpression(1, "00"));
        assertTrue(checkRegularExpression(1, "0101001010"));
        assertFalse(checkRegularExpression(1, "1101001010"));
        assertFalse(checkRegularExpression(1, ""));
    }

    @Test
    public void assertThatThirdRegularWorksRight() {
        assertTrue(checkRegularExpression(2, "100"));
        assertTrue(checkRegularExpression(2, "00"));
        assertTrue(checkRegularExpression(2, "0"));
        assertFalse(checkRegularExpression(2, ""));
        assertFalse(checkRegularExpression(2, "1000"));
    }

    @Test
    public void assertThatIncorrectRegularIndexThrowException() {
        assertThrows(IllegalStateException.class, () -> checkRegularExpression(3, ""));
    }
}
