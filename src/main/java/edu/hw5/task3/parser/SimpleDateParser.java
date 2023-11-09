package edu.hw5.task3.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDateParser extends DateParser {
    private static final List<Pattern> SUPPORTED_PATTERNS = List.of(
        Pattern.compile("^(?<negativeYear>-)?(?<year>\\d{1,9})-(?<month>\\d{1,2})-(?<day>\\d{1,2})$"),
        Pattern.compile("^(?<day>\\d{1,2})/(?<month>\\d{1,2})/(?<negativeYear>-)?(?<year>\\d{1,9})$")
    );

    public SimpleDateParser() {
    }

    public SimpleDateParser(DateParser nextParser) {
        super(nextParser);
    }

    @Override
    public Optional<LocalDate> tryParse(String date) {
        for (Pattern pattern : SUPPORTED_PATTERNS) {
            try {
                Matcher matcher = pattern.matcher(date);

                if (matcher.find()) {
                    int year = Integer.parseInt(matcher.group("year"))
                        * (matcher.group("negativeYear") != null ? -1 : 1);
                    int month = Integer.parseInt(matcher.group("month"));
                    int day = Integer.parseInt(matcher.group("day"));

                    return Optional.of(LocalDate.of(year, month, day));
                }
            } catch (DateTimeException ignored) {
            }
        }

        return super.tryParse(date);
    }
}
