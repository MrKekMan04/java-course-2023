package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task16Test extends TestBase {
    @Test
    public void assertThatSortByTypeThenBySexThenByNameReturnedRightAnswerTest() {
        final List<Animal> sorted = List.of(ANIMALS.get(2), ANIMALS.get(3), ANIMALS.get(1), ANIMALS.get(0));

        assertEquals(sorted, Task16.sortByTypeThenBySexThenByName(ANIMALS));
    }
}
