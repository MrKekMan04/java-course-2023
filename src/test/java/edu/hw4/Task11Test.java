package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task11Test extends TestBase {
    @Test
    public void assertThatGetAnimalsWhoseCanBitesAndHeightLessOrEquals100RightAnswerTest() {
        final List<Animal> animalsWhoseCanBitesAndHeightLessOrEquals100 = List.of(ANIMALS.get(0));

        assertEquals(
            animalsWhoseCanBitesAndHeightLessOrEquals100,
            Task11.getAnimalsWhoseCanBitesAndHeightLessOrEquals100(ANIMALS)
        );
    }
}
