package edu.hw3.task1;

import org.junit.jupiter.api.Test;
import static edu.hw3.task1.Task1.getAtbashString;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    @Test
    public void assertThatLatinSymbolsReturnedRightAnswerTest() {
        final String taskTestData1 = "Hello world!";
        final String taskExpected1 = "Svool dliow!";
        final String taskTestData2 = "Any fool can write code that a computer can understand."
            + " Good programmers write code that humans can understand. ― Martin Fowler";
        final String taskExpected2 = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. "
            + "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";

        assertEquals(getAtbashString(taskTestData1), taskExpected1);
        assertEquals(getAtbashString(taskTestData2), taskExpected2);
    }

    @Test
    public void assertThatCaseHasValueTest() {
        final String smallRegisterData = "hello";
        final String capitalRegisterData = "HELLO";

        assertNotEquals(getAtbashString(smallRegisterData), getAtbashString(capitalRegisterData));
    }

    @Test
    public void assertThatNotLatinSymbolsDoesNotChangeTest() {
        final String testData = "Проверка на то, что не латинские символы не изменяется!";

        assertEquals(getAtbashString(testData), testData);
    }

    @Test
    public void assertThatNullableInputReturnedNullTest() {
        assertNull(getAtbashString(null));
    }
}
