package edu.hw5.task3;

import edu.hw5.task3.parser.DateParser;
import edu.hw5.task3.parser.ExpressionDateParser;
import edu.hw5.task3.parser.SimpleDateParser;
import edu.hw5.task3.parser.WordDateParser;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public final class Task3 {
    private static final DateParser CHAIN;

    static {
        try {
            CHAIN = buildChain();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        return string != null ? CHAIN.tryParse(string) : Optional.empty();
    }

    private static DateParser buildChain() throws ReflectiveOperationException {
        DateParser finalParser = null;

        List<Class<? extends DateParser>> parsers = List.of(
            WordDateParser.class, SimpleDateParser.class, ExpressionDateParser.class
        );

        for (Class<? extends DateParser> parser : parsers) {
            finalParser = parser.getConstructor(DateParser.class).newInstance(finalParser);
        }

        return finalParser;
    }
}
