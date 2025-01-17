package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task7Test {
    @Test
    public void taskDataTest() {
        assertEquals(Task7.rotateRight(8, 1), 4);
        assertEquals(Task7.rotateLeft(16, 1), 1);
        assertEquals(Task7.rotateLeft(17, 2), 6);
    }

    @Test
    public void cycleShiftTest() {
        assertEquals(Task7.rotateRight(8, 1 + 4), 4);
        assertEquals(Task7.rotateLeft(16, 1 + 5), 1);
        assertEquals(Task7.rotateLeft(17, 2 + 5), 6);
    }
}
