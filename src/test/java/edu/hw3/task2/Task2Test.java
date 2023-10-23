package edu.hw3.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Collection;
import java.util.List;
import static edu.hw3.task2.Task2.clusterize;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    private static Arguments[] taskTestsData() {
        return new Arguments[] {
            Arguments.of("()()()", 3, List.of("()", "()", "()")),
            Arguments.of("((()))", 1, List.of("((()))")),
            Arguments.of("((()))(())()()(()())", 5, List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", 2, List.of("((())())", "(()(()()))"))
        };
    }

    @ParameterizedTest
    @MethodSource("taskTestsData")
    public void assertThatCorrectInputReturnedCorrectResultTest(String input, int answerLen, List<String> answer) {
        Collection<String> actualAnswer = clusterize(input);

        assertEquals(actualAnswer.size(), answerLen);
        assertEquals(actualAnswer, answer);
    }

    @Test
    public void assertThatIncorrectInputReturnedNullTest() {
        for (String input : new String[] {")", "()anotherSymbol()", "(()", null}) {
            assertNull(clusterize(input));
        }
    }
}
