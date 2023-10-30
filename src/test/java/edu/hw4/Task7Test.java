package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task7Test extends TestBase {
    @Test
    public void assertThatGetOldestAnimalReturnedRightAnswerTest() {
        final Animal oldestAnimal = ANIMALS.get(1);

        assertEquals(oldestAnimal, Task7.getOldestAnimal(ANIMALS));
    }

    @Test
    public void assertThatEmptyListGetOldestAnimalReturnedNullTest() {
        final List<Animal> emptyList = List.of();

        assertNull(Task7.getOldestAnimal(emptyList));
    }
}
