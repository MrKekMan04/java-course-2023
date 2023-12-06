package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Task1 {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private Task1() {
    }

    public static Duration getSessionDuration(String[] sessions) {
        try {
            Duration duration = Duration.ZERO;

            for (String session : sessions) {
                String[] sessionTime = session.split(" - ");
                LocalDateTime start = DATE_TIME_FORMATTER.parse(sessionTime[0], LocalDateTime::from);
                LocalDateTime end = DATE_TIME_FORMATTER.parse(sessionTime[1], LocalDateTime::from);
                duration = duration.plus(Duration.between(start, end));
            }

            return sessions.length > 0 ? duration.dividedBy(sessions.length) : duration;
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }
}
