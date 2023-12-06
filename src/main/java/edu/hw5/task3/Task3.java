package edu.hw5.task3;

import edu.hw5.task3.parser.DateParser;
import edu.hw5.task3.parser.ExpressionDateParser;
import edu.hw5.task3.parser.SimpleDateParser;
import edu.hw5.task3.parser.WordDateParser;
import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    private static final DateParser CHAIN = buildChain();

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        return string != null ? CHAIN.tryParse(string) : Optional.empty();
    }

    private static DateParser buildChain() {
        return new ExpressionDateParser(new SimpleDateParser(new WordDateParser()));
    }
}
