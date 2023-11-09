package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateParser extends DateParser {
    public WordDateParser() {
    }

    public WordDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> tryParse(String date) {
        LocalDate parsedDate = switch (date) {
            case "tomorrow" -> LocalDate.now().plusDays(1);
            case "today" -> LocalDate.now();
            case "yesterday" -> LocalDate.now().minusDays(1);
            default -> null;
        };

        return parsedDate != null ? Optional.of(parsedDate) : super.tryParse(date);
    }
}
