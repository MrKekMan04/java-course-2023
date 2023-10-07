package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task3Test {
    @Test
    public void taskDataTest() {
        assertTrue(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
        assertTrue(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0}));
        assertFalse(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
        assertFalse(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
    }

    @Test
    public void emptyArrayTest() {
        assertFalse(Task3.isNestable(new int[0], new int[] {0, 6}));
        assertFalse(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[0]));
    }
}
