package edu.hw3.task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static edu.hw3.task5.Task5.parseContacts;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
    public static Arguments[] getData() {
        return new Arguments[] {
            Arguments.of(List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"), "ASC",
                List.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke")
            ),
            Arguments.of(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"), "DESC",
                List.of("Carl Gauss", "Leonhard Euler", "Paul Erdos")
            ),
            Arguments.of(List.of(), "DESC", List.of()),
            Arguments.of(null, "DESC", List.of()),
        };
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void assertThatMethodReturnedRightAnswerTest(List<String> input, String sortBy, List<String> answer) {
        assertEquals(parseContacts(input, sortBy).stream().map(String::valueOf).collect(Collectors.toList()), answer);
    }

    @Test
    public void assertThatPersonWithoutSurnameReturnedRightAnswer() {
        assertEquals(parseContacts(List.of("Andrew", "Thomas Morgan"), "ASC")
            .stream()
            .map(String::valueOf)
            .collect(Collectors.toList()), List.of("Andrew", "Thomas Morgan"));
    }

    @Test
    public void assertThatIncorrectSortTypeThrowExceptionTest() {
        assertThrowsExactly(IllegalArgumentException.class, () -> parseContacts(List.of(), "SOME"));
    }

    @Test
    public void assertThatNullPersonThrowExceptionTest() {
        List<String> testData = new ArrayList<>(List.of("Eugene"));
        testData.add(null);

        assertThrowsExactly(NullPointerException.class, () -> parseContacts(testData, "ASC"));
    }

    @Test
    public void assertThatIncorrectPersonDataThrowExceptionTest() {
        assertThrowsExactly(
            IllegalArgumentException.class,
            () -> parseContacts(List.of("Name Surname Something More"), "ASC")
        );
    }
}
