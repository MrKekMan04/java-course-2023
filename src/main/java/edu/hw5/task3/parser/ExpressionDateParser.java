package edu.hw5.task3.parser;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionDateParser extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("^(?<daysCount>\\d+) day(s)? (?<when>ago|after)$");

    public ExpressionDateParser() {
    }

    public ExpressionDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> tryParse(String date) {
        Matcher matcher = PATTERN.matcher(date);

        if (matcher.find()) {
            int days = Integer.parseInt(matcher.group("daysCount"));
            String when = matcher.group("when");

            LocalDate parsedDate = switch (when) {
                case "ago" -> LocalDate.now().minusDays(days);
                case "after" -> LocalDate.now().plusDays(days);
                default -> throw new IllegalStateException("Unexpected value: " + when);
            };

            return Optional.of(parsedDate);
        }

        return super.tryParse(date);
    }
}
