package edu.hw5.task3.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionDateParserTest {
    private static ExpressionDateParser parser;

    @BeforeAll
    public static void setUp() {
        parser = new ExpressionDateParser();
    }

    @Test
    public void assertThatParserWorksRight() {
        final LocalDate oneDayAgo = LocalDate.now().minusDays(1);
        final LocalDate oneWeekAgo = LocalDate.now().minusDays(7);
        final LocalDate oneDayAfter = LocalDate.now().plusDays(1);
        final LocalDate oneWeekAfter = LocalDate.now().plusDays(7);

        assertEquals(Optional.of(oneWeekAgo), parser.tryParse("7 days ago"));
        assertEquals(Optional.of(oneWeekAfter), parser.tryParse("7 days after"));
        assertEquals(Optional.of(oneDayAgo), parser.tryParse("1 day ago"));
        assertEquals(Optional.of(oneDayAgo), parser.tryParse("1 days ago"));
        assertEquals(Optional.of(oneDayAfter), parser.tryParse("1 day after"));
        assertEquals(Optional.of(oneDayAfter), parser.tryParse("1 days after"));

        assertEquals(Optional.empty(), parser.tryParse("unsupported date format"));
    }
}
