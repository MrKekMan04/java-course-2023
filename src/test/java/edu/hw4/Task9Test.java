package edu.hw4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task9Test extends TestBase {
    @Test
    public void assertThatCountPawsReturnedRightAnswerTest() {
        final Integer paws = 20;

        assertEquals(paws, Task9.countPaws(ANIMALS));
    }
}
