package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test extends TestBase {
    @Test
    public void assertThatSortingByHeightReturnedRightAnswerTest() {
        final List<Animal> sortedByHeight = List.of(ANIMALS.get(0), ANIMALS.get(2), ANIMALS.get(3), ANIMALS.get(1));

        assertEquals(sortedByHeight, Task1.sortAnimalsByHeight(ANIMALS));
    }
}
