package edu.hw5.task4;

import org.junit.jupiter.api.Test;
import static edu.hw5.task4.Task4.isPasswordValid;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    @Test
    public void assertThatIsPasswordValidReturnedRightAnswer() {
        final String containsOnlyOneSpecialSymbol = "some@password";
        final String containsManySpecialSymbols = "AnY~O!he^&";
        final String notContainsSpecialSymbols = "password";

        assertTrue(isPasswordValid(containsOnlyOneSpecialSymbol));
        assertTrue(isPasswordValid(containsManySpecialSymbols));
        assertFalse(isPasswordValid(notContainsSpecialSymbols));
    }

    @Test
    public void assertThatNullInputReturnedFalse() {
        assertFalse(isPasswordValid(null));
    }
}
