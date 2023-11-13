package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task11Test extends TestBase {
    @Test
    public void assertThatGetAnimalsWhoseCanBitesAndHeightMoreThen100RightAnswerTest() {
        assertEquals(List.of(), Task11.getAnimalsWhoseCanBitesAndHeightLessOrEquals100(ANIMALS));
    }

    @Test
    public void assertThatGetAnimalsWhoseCanBitesAndHeightMoreThen100RightAnswerAlternativeTest() {
        final List<Animal> alternativeAnimals = List.of(
            new Animal("Titan", Animal.Type.DOG, Animal.Sex.M, 15, 110, 60, true),
            new Animal("Mike", Animal.Type.FISH, Animal.Sex.M, 2, 5, 3, false)
        );

        assertEquals(
            alternativeAnimals.subList(0, 1),
            Task11.getAnimalsWhoseCanBitesAndHeightLessOrEquals100(alternativeAnimals)
        );
    }
}
