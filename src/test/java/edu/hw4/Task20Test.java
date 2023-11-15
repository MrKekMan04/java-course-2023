package edu.hw4;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task20Test extends TestBase {
    @Test
    public void assertThatGetNonValidAnimalsReturnedRightAnswerTest() {
        final Map<String, String> invalidAnimals = Map.of();

        assertEquals(invalidAnimals, Task20.getNonValidAnimalsStringy(ANIMALS));
    }

    @Test
    public void assertThatGetNonValidAnimalsReturnedRightAnswerAlternativeTest() {
        final List<Animal> listWithInvalidAnimals = List.of(
            ANIMALS.get(2), ANIMALS.get(3), ANIMALS.get(1), ANIMALS.get(0),
            new Animal("Invalid 1", Animal.Type.DOG, Animal.Sex.M, -1, 20, 10, false),
            new Animal("Invalid 2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 10, false),
            new Animal("Invalid 3", Animal.Type.DOG, Animal.Sex.M, 0, 10, -20, false),
            new Animal("Invalid 4", Animal.Type.DOG, Animal.Sex.M, 100, 10, 20, false),
            new Animal(null, Animal.Type.DOG, Animal.Sex.M, 0, -10, 20, false)
        );

        final Map<String, String> invalidAnimals = Map.of(
            listWithInvalidAnimals.get(4).name(), "age",
            listWithInvalidAnimals.get(5).name(), "height",
            listWithInvalidAnimals.get(6).name(), "weight",
            listWithInvalidAnimals.get(7).name(), "age",
            "null", "name, height"
        );

        assertEquals(invalidAnimals, Task20.getNonValidAnimalsStringy(listWithInvalidAnimals));
    }
}
