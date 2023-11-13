package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task7Test extends TestBase {
    @Test
    public void assertThatGetOldestAnimalReturnedRightAnswerTest() {
        final Animal firstOldestAnimal = ANIMALS.get(1);
        final Animal thirdOldestAnimal = ANIMALS.get(3);

        assertEquals(firstOldestAnimal, Task7.getKOldestAnimal(ANIMALS, 1));
        assertEquals(thirdOldestAnimal, Task7.getKOldestAnimal(ANIMALS, 3));
        assertNull(Task7.getKOldestAnimal(ANIMALS, 6));
    }

    @Test
    public void assertThatEmptyListGetOldestAnimalReturnedNullTest() {
        final List<Animal> emptyList = List.of();

        assertNull(Task7.getKOldestAnimal(emptyList, 1));
    }
}
