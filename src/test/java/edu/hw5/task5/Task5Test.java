package edu.hw5.task5;

import org.junit.jupiter.api.Test;
import static edu.hw5.task5.Task5.isNumberPlateValid;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
    @Test
    public void assertThatIsNumberPlateValidReturnedRightAnswer() {
        final String correct1 = "А123ВЕ777";
        final String correct2 = "О777ОО177";
        final String incorrect1 = "123АВЕ777";
        final String incorrect2 = "А123ВГ77";
        final String incorrect3 = "А123ВЕ7777";

        assertTrue(isNumberPlateValid(correct1));
        assertTrue(isNumberPlateValid(correct2));
        assertFalse(isNumberPlateValid(incorrect1));
        assertFalse(isNumberPlateValid(incorrect2));
        assertFalse(isNumberPlateValid(incorrect3));
    }

    @Test
    public void assertThatNullInputReturnedFalse() {
        assertFalse(isNumberPlateValid(null));
    }
}
