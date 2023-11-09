package edu.hw5.task3;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.task3.Task3.parseDate;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    @Test
    public void assertThatParseDateReturnedRightAnswer() {
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), parseDate("2020-10-10"));
        assertEquals(Optional.of(LocalDate.of(1976, 3, 1)), parseDate("1/3/1976"));
        assertEquals(Optional.of(LocalDate.now()), parseDate("today"));
        assertEquals(Optional.of(LocalDate.now().minusDays(1)), parseDate("1 day ago"));
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), parseDate("1 days after"));
        assertEquals(Optional.empty(), parseDate("some unsupported format"));
        assertEquals(Optional.empty(), parseDate(null));
    }
}
