package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task13Test extends TestBase {
    @Test
    public void assertThatGetAnimalsWhoseNamesConsistTwoOrMoreWordsRightAnswerTest() {
        final List<Animal> animalsWhoseNamesConsistTwoOrMoreWords = List.of();

        assertEquals(animalsWhoseNamesConsistTwoOrMoreWords, Task13.getAnimalsWhoseNamesConsistTwoOrMoreWords(ANIMALS));
    }

    @Test
    public void assertThatGetAnimalsWhoseNamesConsistTwoOrMoreWordsRightAnswerAlternativeTest() {
        final List<Animal> alternativeAnimals = List.of(
            new Animal("Emperor Kitty", Animal.Type.CAT, Animal.Sex.M, 5, 20, 25, false),
            new Animal("Sally", Animal.Type.DOG, Animal.Sex.F, 3, 15, 10, false)
        );
        final List<Animal> animalsWhoseNamesConsistTwoOrMoreWords = List.of(alternativeAnimals.get(0));

        assertEquals(
            animalsWhoseNamesConsistTwoOrMoreWords,
            Task13.getAnimalsWhoseNamesConsistTwoOrMoreWords(alternativeAnimals)
        );
    }

    @Test
    public void assertThatAnimalsWithManyWhiteSpacesProcessRightTest() {
        assertEquals(List.of(), Task13.getAnimalsWhoseNamesConsistTwoOrMoreWords(List.of(
            new Animal("   word", Animal.Type.SPIDER, Animal.Sex.F, 6, 5, 4, true)
        )));
    }
}
