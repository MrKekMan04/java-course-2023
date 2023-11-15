package edu.hw4;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task15Test extends TestBase {
    @Test
    public void assertThatGetTotalWeightEachTypeWhoseAgeFromKToLReturnedRightAnswerTest() {
        final int ageFrom = 5;
        final int ageTo = 8;
        final Map<Animal.Type, Integer> totalWeight = Map.of(
            Animal.Type.DOG, 10,
            Animal.Type.CAT, 7
        );

        assertEquals(totalWeight, Task15.getTotalWeightEachTypeWhoseAgeFromKToL(ANIMALS, ageFrom, ageTo));
    }
}
