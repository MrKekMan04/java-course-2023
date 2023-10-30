package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task12Test extends TestBase {
    @Test
    public void assertThatCountAnimalsWhoseWeightMoreThenHeightRightAnswerTest() {
        final Integer animalsWhoseWeightMoreThenHeight = 0;

        assertEquals(animalsWhoseWeightMoreThenHeight, Task12.countAnimalsWhoseWeightMoreThenHeight(ANIMALS));
    }

    @Test
    public void assertThatCountAnimalsWhoseWeightMoreThenHeightRightAnswerAlternativeTest() {
        final List<Animal> alternativeAnimals = List.of(
            new Animal("Fatty", Animal.Type.CAT, Animal.Sex.M, 5, 20, 25, false),
            new Animal("Sally", Animal.Type.DOG, Animal.Sex.F, 3, 15, 10, false)
        );
        final Integer animalsWhoseWeightMoreThenHeight = 1;

        assertEquals(
            animalsWhoseWeightMoreThenHeight,
            Task12.countAnimalsWhoseWeightMoreThenHeight(alternativeAnimals)
        );
    }
}
