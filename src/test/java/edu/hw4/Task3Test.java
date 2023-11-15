package edu.hw4;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test extends TestBase {
    @Test
    public void assertThatGroupingByAnimalTypeReturnedRightAnswerTest() {
        final Map<Animal.Type, Integer> groupedByAnimalType = Map.of(
            Animal.Type.SPIDER, 1,
            Animal.Type.DOG, 1,
            Animal.Type.CAT, 2
        );

        assertEquals(groupedByAnimalType, Task3.countAnimals(ANIMALS));
    }
}
