package edu.hw5.task3.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class WordDateParserTest {
    private static WordDateParser parser;

    @BeforeAll
    public static void setUp() {
        parser = new WordDateParser();
    }

    @Test
    public void assertThatParserWorksRight() {
        final Optional<LocalDate> today = Optional.of(LocalDate.now());
        final Optional<LocalDate> tomorrow = Optional.of(today.get().plusDays(1));
        final Optional<LocalDate> yesterday = Optional.of(today.get().minusDays(1));

        assertEquals(today, parser.tryParse("today"));
        assertEquals(tomorrow, parser.tryParse("tomorrow"));
        assertEquals(yesterday, parser.tryParse("yesterday"));

        assertEquals(Optional.empty(), parser.tryParse("some date"));
    }
}
