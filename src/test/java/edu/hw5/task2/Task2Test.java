package edu.hw5.task2;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.task2.Task2.getAllFridaysThirteenForYear;
import static edu.hw5.task2.Task2.getClosestFridayThirteen;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    @Test
    public void assertThatGetAllFridaysThirteenForYearReturnedRightResult() {
        final List<LocalDate> expected1925 = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        final List<LocalDate> expected2024 = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );

        assertEquals(expected1925, getAllFridaysThirteenForYear(1925));
        assertEquals(expected2024, getAllFridaysThirteenForYear(2024));
    }

    @Test
    public void assertThatGetClosestFridayThirteenReturnedRightResult() {
        final LocalDate currentDate = LocalDate.of(1925, 3, 7);
        final LocalDate closestFriday13 = LocalDate.of(1925, 3, 13);

        assertEquals(closestFriday13, getClosestFridayThirteen(currentDate));
    }

    @Test
    public void assertThatCurrentFridayThirteenLookingForNext() {
        final LocalDate currentFriday13 = LocalDate.of(2024, 12, 13);
        final LocalDate nextFriday13 = LocalDate.of(2025, 6, 13);

        assertEquals(nextFriday13, getClosestFridayThirteen(currentFriday13));
    }
}
