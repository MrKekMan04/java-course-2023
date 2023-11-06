package edu.hw3.task3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import static edu.hw3.task3.Task3.freqDict;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    @Test
    public void assertThatMethodWorksWithAnyIterableCollectionsTest() {
        final List<Integer> testData1 = List.of(1, 2, 2, 3);
        final ArrayList<Integer> testData2 = new ArrayList<>(testData1);
        final Vector<Integer> testData3 = new Vector<>(testData1);
        final Map<Integer, Integer> expected = Map.of(1, 1, 2, 2, 3, 1);

        assertEquals(freqDict(testData1), expected);
        assertEquals(freqDict(testData2), expected);
        assertEquals(freqDict(testData3), expected);
    }

    @Test
    public void assertThatMethodWorksWithAnyTypesTest() {
        final Iterable<String> testData1 = List.of("this", "this", "and", "and", "And");
        final Map<String, Integer> expected1 = Map.of("this", 2, "and", 2, "And", 1);

        final Iterable<Character> testData2 = List.of('a', 'a', 'a', 'b', 'b', 'c');
        final Map<Character, Integer> expected2 = Map.of('a', 3, 'b', 2, 'c', 1);

        assertEquals(freqDict(testData1), expected1);
        assertEquals(freqDict(testData2), expected2);
    }

    @Test
    public void assertThatNullableCollectionReturnedNullTest() {
        assertNull(freqDict(null));
    }
}
