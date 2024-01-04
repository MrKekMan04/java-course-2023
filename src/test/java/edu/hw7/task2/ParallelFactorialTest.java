package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import static edu.hw7.task2.ParallelFactorial.calculate;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelFactorialTest {
    @Test
    public void assertThatParallelFactorialCalculationWorksRight() {
        assertEquals(1, calculate(0));
        assertEquals(1, calculate(1));
        assertEquals(2, calculate(2));
        assertEquals(6, calculate(3));
        assertEquals(24, calculate(4));
        assertEquals(120, calculate(5));
    }

    @Test
    public void assertThatIncorrectInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculate(-1));
    }
}
