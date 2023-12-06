package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    private final DateParser nextParser;

    public DateParser() {
        this(null);
    }

    public DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public Optional<LocalDate> tryParse(String date) {
        if (nextParser != null) {
            return nextParser.tryParse(date);
        }

        return Optional.empty();
    }
}
