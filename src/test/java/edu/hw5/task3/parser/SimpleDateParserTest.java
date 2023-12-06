package edu.hw5.task3.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleDateParserTest {
    private static SimpleDateParser parser;

    @BeforeAll
    public static void setUp() {
        parser = new SimpleDateParser();
    }

    @Test
    public void assertThatParserWorksRight() {
        assertEquals(Optional.of(LocalDate.of(2020, 2, 12)), parser.tryParse("2020-02-12"));
        assertEquals(Optional.of(LocalDate.of(-20, 2, 1)), parser.tryParse("-20-2-1"));
        assertEquals(Optional.of(LocalDate.of(2020, 10, 1)), parser.tryParse("1/10/2020"));
        assertEquals(Optional.of(LocalDate.of(-20, 1, 12)), parser.tryParse("12/1/-20"));

        assertEquals(Optional.empty(), parser.tryParse("1/10/2020202020"));
        assertEquals(Optional.empty(), parser.tryParse("1/13/2020"));
        assertEquals(Optional.empty(), parser.tryParse("33/1/2020"));
    }
}
