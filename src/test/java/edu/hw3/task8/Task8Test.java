package edu.hw3.task8;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    @Test
    public void assertThatBackwardIteratorReturnedReversedCollectionTest() {
        Iterator<Integer> myIterator = new BackwardIterator<>(List.of(1, 2, 3));
        Iterator<Integer> reversedIterator = List.of(3, 2, 1).iterator();

        while (myIterator.hasNext()) {
            assertEquals(myIterator.next(), reversedIterator.next());
        }
    }
}
