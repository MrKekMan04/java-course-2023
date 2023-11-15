package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task4Test extends TestBase {
    @Test
    public void assertThatGetAnimalWithLongestNameReturnedRightAnswerTest() {
        final Animal animalWithLongestName = ANIMALS.get(2);

        assertEquals(animalWithLongestName, Task4.getAnimalWithLongestName(ANIMALS));
    }

    @Test
    public void assertThatEmptyListGetAnimalWithLongestNameReturnedNullTest() {
        final List<Animal> emptyList = List.of();

        assertNull(Task4.getAnimalWithLongestName(emptyList));
    }
}
