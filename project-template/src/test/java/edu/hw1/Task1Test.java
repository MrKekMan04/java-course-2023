package edu.hw1;

import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    private final Random random = new Random();
    private final int randomTestIterations = 10;

    @Test
    public void taskDataTest() {
        assertEquals(Task1.minutesToSeconds("01:00"), 60);
        assertEquals(Task1.minutesToSeconds("13:56"), 836);
        assertEquals(Task1.minutesToSeconds("10:60"), -1);
    }

    @Test
    public void randomDataTest() {
        for (var i = 0; i < randomTestIterations; i++) {
            var minutes = random.nextInt(200);
            var seconds = random.nextInt(60);

            test(minutes, seconds, minutes * 60 + seconds);
        }
    }

    @Test
    public void invalidMinutesTest() {
        var minutes = -1;

        for (var i = 0; i < randomTestIterations; i++) {
            var seconds = random.nextInt(60);

            test(minutes, seconds, -1);
        }
    }

    @Test
    public void invalidSecondsTest() {
        var seconds = -1;

        for (var i = 0; i < randomTestIterations; i++) {
            var minutes = random.nextInt(60);

            test(minutes, seconds, -1);
        }

        seconds = 60;

        for (var i = 0; i < randomTestIterations; i++) {
            var minutes = random.nextInt(60);

            test(minutes, seconds, -1);
        }
    }

    private void test(int minutes, int seconds, int actual) {
        assertEquals(Task1.minutesToSeconds("%d:%d".formatted(minutes, seconds)), actual);
    }
}
