package edu.hw4;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test extends TestBase {
    @Test
    public void assertThatGetHardestAnimalWhoseHeightLessThenReturnedRightAnswerTest() {
        final int maxHeight = 35;
        final Optional<Animal> animalsWhoseHeightLessThenMaxHeight = Optional.of(ANIMALS.get(2));

        assertEquals(
            animalsWhoseHeightLessThenMaxHeight,
            Task8.getHardestAnimalWhoseHeightLessThen(ANIMALS, maxHeight)
        );
    }

    @Test
    public void assertThatNoValidMembersGetOldestAnimalReturnedEmptyOptionalTest() {
        final int maxHeight = 10;

        assertTrue(Task8.getHardestAnimalWhoseHeightLessThen(ANIMALS, maxHeight).isEmpty());
    }

    @Test
    public void assertThatEmptyListGetOldestAnimalReturnedEmptyOptionalTest() {
        final int maxHeight = 35;
        final List<Animal> emptyList = List.of();

        assertTrue(Task8.getHardestAnimalWhoseHeightLessThen(emptyList, maxHeight).isEmpty());
    }
}
