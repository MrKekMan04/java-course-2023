package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task14Test extends TestBase {
    @Test
    public void assertThatContainsDogWithHeightMoreThenReturnedRightAnswerTest() {
        final int minimalHeight = 35;

        assertTrue(Task14.containsDogWithHeightMoreThen(ANIMALS, minimalHeight));
    }

    @Test
    public void assertThatEmptyListCountPawsReturnedFalseTest() {
        final List<Animal> emptyList = List.of();
        final int minimalHeight = 35;

        assertFalse(Task14.containsDogWithHeightMoreThen(emptyList, minimalHeight));
    }
}
