package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test extends TestBase {
    @Test
    public void assertThatSortingByWeightDescReturnedRightAnswerTest() {
        final List<Animal> sortedByWeightDesc = List.of(ANIMALS.get(1), ANIMALS.get(2), ANIMALS.get(3), ANIMALS.get(0));

        assertEquals(sortedByWeightDesc, Task2.getKSortedAnimalsByWeightDesc(ANIMALS, 4));
        assertEquals(sortedByWeightDesc.subList(0, 2), Task2.getKSortedAnimalsByWeightDesc(ANIMALS, 2));
    }
}
