package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalValidatorTest {
    @Test
    public void assertThatValidAnimalReturnedRightAnswerTest() {
        List<Animal> invalidAnimals = List.of(
            new Animal("1", Animal.Type.FISH, Animal.Sex.F, -1, 0, 20, false),
            new Animal(null, Animal.Type.DOG, Animal.Sex.M, 2, 20, 10, true),
            new Animal("2", Animal.Type.SPIDER, Animal.Sex.F, 2, 10, -30, false),
            new Animal("3", Animal.Type.SPIDER, Animal.Sex.M, 20, -20, 10, true)
        );

        List<List<ValidationError>> validationErrors = List.of(
            List.of(new ValidationError("age"), new ValidationError("height")),
            List.of(new ValidationError("name")),
            List.of(new ValidationError("weight")),
            List.of(new ValidationError("age"), new ValidationError("height"))
        );

        for (int i = 0; i < invalidAnimals.size(); i++) {
            assertEquals(validationErrors.get(i), AnimalValidator.validAnimal(invalidAnimals.get(i)));
        }
    }
}
