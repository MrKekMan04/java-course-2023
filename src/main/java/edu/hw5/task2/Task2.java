package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("MagicNumber")
public final class Task2 {
    private static final int THIRTEEN_DAY = 13;

    private Task2() {
    }

    public static List<LocalDate> getAllFridaysThirteenForYear(int year) {
        return IntStream.range(1, 13)
            .mapToObj(month -> LocalDate.of(year, month, THIRTEEN_DAY))
            .filter(date -> date.getDayOfWeek() == DayOfWeek.FRIDAY)
            .toList();
    }

    public static LocalDate getClosestFridayThirteen(LocalDate date) {
        LocalDate currentDate = getNextThirteen(date);
        TemporalAdjuster nextMonth = TemporalAdjusters.ofDateAdjuster(d -> d.plusMonths(1));

        while (currentDate.getDayOfWeek() != DayOfWeek.FRIDAY || currentDate == date) {
            currentDate = LocalDate.from(nextMonth.adjustInto(currentDate));
        }

        return currentDate;
    }

    private static LocalDate getNextThirteen(LocalDate date) {
        LocalDate nextYearAndMonth = date.getDayOfMonth() <= 12 ? date : date.plusMonths(1);

        return LocalDate.of(nextYearAndMonth.getYear(), nextYearAndMonth.getMonth(), THIRTEEN_DAY);
    }
}
