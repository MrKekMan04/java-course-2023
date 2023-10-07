package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    public void taskDataTest() {
        assertEquals(Task6.countK(3524), 3);
        assertEquals(Task6.countK(6621), 5);
        assertEquals(Task6.countK(6554), 4);
        assertEquals(Task6.countK(1234), 3);
    }
}
