package edu.hw5.task8;

import edu.hw5.task7.Task7;
import org.junit.jupiter.api.Test;
import static edu.hw5.task8.Task8.checkRegularExpression;
import static org.junit.jupiter.api.Assertions.*;

public class Task8Test {
    @Test
    public void assertThatFirstRegularWorksRight() {
        assertTrue(checkRegularExpression(0, "010"));
        assertTrue(checkRegularExpression(0, "0101111"));
        assertFalse(checkRegularExpression(0, ""));
        assertFalse(checkRegularExpression(0, "11"));
        assertFalse(checkRegularExpression(0, "110000"));

        assertFalse(checkRegularExpression(0, "non alphabetic"));
    }

    @Test
    public void assertThatSecondRegularWorksRight() {
        assertTrue(checkRegularExpression(1, "010"));
        assertFalse(checkRegularExpression(1, "0110"));
        assertTrue(checkRegularExpression(1, "1110"));
        assertFalse(checkRegularExpression(1, "11101"));
        assertFalse(checkRegularExpression(1, ""));

        assertFalse(checkRegularExpression(1, "non alphabetic"));
    }

    @Test
    public void assertThatThirdRegularWorksRight() {
        assertTrue(checkRegularExpression(2, "101011"));
        assertTrue(checkRegularExpression(2, "111"));
        assertTrue(checkRegularExpression(2, "101110"));
        assertFalse(checkRegularExpression(2, "100"));
        assertFalse(checkRegularExpression(2, "110"));

        assertFalse(checkRegularExpression(2, "non alphabetic"));
    }

    @Test
    public void assertThatFourthRegularWorksRight() {
        assertTrue(checkRegularExpression(3, "010"));
        assertTrue(checkRegularExpression(3, "0101010100"));
        assertFalse(checkRegularExpression(3, "0110"));
        assertFalse(checkRegularExpression(3, "110"));
        assertFalse(checkRegularExpression(3, "011"));
        assertFalse(checkRegularExpression(3, "011f"));

        assertFalse(checkRegularExpression(3, "non alphabetic"));
    }

    @Test
    public void assertThatIncorrectRegularIndexThrowException() {
        assertThrows(IllegalStateException.class, () -> Task7.checkRegularExpression(4, ""));
    }
}
