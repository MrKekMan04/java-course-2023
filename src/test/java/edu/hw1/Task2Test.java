package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    @Test
    public void taskDataTest() {
        assertEquals(Task2.countDigits(4666), 4);
        assertEquals(Task2.countDigits(544), 3);
        assertEquals(Task2.countDigits(0), 1);
    }

    @Test
    public void negativeNumbersTest() {
        assertEquals(Task2.countDigits(-100), 3);
        assertEquals(Task2.countDigits(-0), 1);
        assertEquals(Task2.countDigits(-1), 1);
    }

    @Test
    public void negativeNumbersLengthEqualsPositiveNumbersLengthTest() {
        assertEquals(Task2.countDigits(-0), Task2.countDigits(0));
        assertEquals(Task2.countDigits(-1), Task2.countDigits(1));
        assertEquals(Task2.countDigits(-1983674824), Task2.countDigits(1983674824));
    }
}
