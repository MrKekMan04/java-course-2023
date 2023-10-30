package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task17Test extends TestBase {
    @Test
    public void assertThatAreSpidersBitesMoreThenDogsRightAnswerTest() {
        final List<Animal> spidersMoreThenDogs = List.of(
            new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.M, 2, 10, 2, true),
            new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 2, 10, 2, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 20, 18, true)
        );

        assertTrue(Task17.areSpidersBitesMoreThenDogs(spidersMoreThenDogs));
    }

    @Test
    public void assertThatAreSpidersBitesMoreThenDogsRightAnswerAlternativeTest() {
        final List<Animal> alternativeAnimals = List.of(
            new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 2, 10, 2, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 3, 20, 18, true)
        );

        assertFalse(Task17.areSpidersBitesMoreThenDogs(alternativeAnimals));
    }

    @Test
    public void assertThatAreSpidersBitesMoreThenDogsWithNotEnoughDataReturnedFalseTest() {
        assertFalse(Task17.areSpidersBitesMoreThenDogs(ANIMALS));
    }
}
