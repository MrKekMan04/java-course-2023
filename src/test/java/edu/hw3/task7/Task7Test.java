package edu.hw3.task7;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

public class Task7Test {
    @Test
    public void assertThatComparatorProcessesNullTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7());

        assertDoesNotThrow(() -> tree.put(null, "test"));
        assertTrue(tree.containsKey(null));
    }
}
