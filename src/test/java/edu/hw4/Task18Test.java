package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task18Test extends TestBase {
    private static List<Animal> secondAnimalsList;
    private static List<Animal> thirdAnimalsList;

    @BeforeAll
    public static void setUp() {
        secondAnimalsList = List.of(
            new Animal("Sponge", Animal.Type.FISH, Animal.Sex.F, 1, 20, 4, false)
        );
        thirdAnimalsList = List.of(
            new Animal("Garry", Animal.Type.FISH, Animal.Sex.M, 2, 15, 3, false)
        );
    }

    @Test
    public void assertThatGetHardestFishReturnedRightAnswerTest() {
        final Animal hardest = thirdAnimalsList.get(0);

        assertEquals(hardest, Task18.getHardestFish(ANIMALS, thirdAnimalsList));
    }

    @Test
    public void assertThatGetHardestFishReturnedRightAnswerAlternativeTest() {
        final Animal hardest = secondAnimalsList.get(0);

        assertEquals(hardest, Task18.getHardestFish(ANIMALS, secondAnimalsList, thirdAnimalsList));
    }

    @Test
    public void assertThatNotEnoughDataGetHardestFishReturnedNullTest() {
        assertNull(Task18.getHardestFish(ANIMALS, List.of()));
    }
}
