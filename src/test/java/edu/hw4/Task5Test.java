package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task5Test extends TestBase {
    @Test
    public void assertThatWhoMoreReturnedRightAnswerTest() {
        final Animal.Sex more = Animal.Sex.M;

        assertEquals(more, Task5.whoMore(ANIMALS));
    }

    @Test
    public void assertThatEmptyListWhoMoreReturnedNullTest() {
        final List<Animal> emptyList = List.of();

        assertNull(Task5.whoMore(emptyList));
    }
}
