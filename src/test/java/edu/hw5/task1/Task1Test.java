package edu.hw5.task1;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import static edu.hw5.task1.Task1.getSessionDuration;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    @Test
    public void assertThatGetSessionDurationReturnedRightAnswer() {
        final Duration expected = Duration.ofHours(3).plus(Duration.ofMinutes(40));

        final Duration actual = getSessionDuration(new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        });

        assertEquals(expected, actual);
    }

    @Test
    public void assertThatEmptySessionsReturnedZeroDuration() {
        final Duration expected = Duration.ZERO;

        assertEquals(expected, getSessionDuration(new String[] {}));
    }

    @Test
    public void assertThatIncorrectInputReturnedNull() {
        assertThrows(RuntimeException.class, () -> getSessionDuration(new String[] {
            "2022-03-12, 20:20-2022-03-12, 23:50"
        }));
    }
}
