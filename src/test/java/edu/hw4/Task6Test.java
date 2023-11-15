package edu.hw4;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test extends TestBase {
    @Test
    public void assertThatGetHardestAnimalByTypeReturnedRightAnswerTest() {
        final Map<Animal.Type, Animal> hardestAnimalByType = Map.of(
            Animal.Type.SPIDER, ANIMALS.get(0),
            Animal.Type.DOG, ANIMALS.get(1),
            Animal.Type.CAT, ANIMALS.get(2)
        );

        assertEquals(hardestAnimalByType, Task6.getHardestAnimalByType(ANIMALS));
    }
}
