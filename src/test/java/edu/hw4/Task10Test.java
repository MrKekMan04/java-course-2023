package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task10Test extends TestBase {
    @Test
    public void assertThatGetAnimalsWhoseAgeNotEqualsPawsRightAnswerTest() {
        final List<Animal> animalsWhoseAgeNotEqualsPaws = List.of(
            ANIMALS.get(0), ANIMALS.get(1), ANIMALS.get(2)
        );

        assertEquals(animalsWhoseAgeNotEqualsPaws, Task10.getAnimalsWhoseAgeNotEqualsPaws(ANIMALS));
    }
}
