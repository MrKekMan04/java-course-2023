package edu.hw8.task3;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordFinderTest {
    @Test
    public void assertThatMultiThreadWorksFasterThanSingleThread() {
        final Map<String, String> expectedDatabase = Map.of(
            "a.v.petrov", "1",
            "v.v.belov", "fE23",
            "a.s.ivanov", "feht",
            "k.p.maslov", "tg4F"
        );

        final int threads = 4;

        long singleStart = System.nanoTime();
        assertEquals(expectedDatabase, PasswordFinder.bruteForceSingleThread());
        long singleWorkTime = System.nanoTime() - singleStart;

        long multiStart = System.nanoTime();
        assertEquals(expectedDatabase, PasswordFinder.bruteForceMultiThread(threads));
        long multiWorkTime = System.nanoTime() - multiStart;

        assertTrue(multiWorkTime < singleWorkTime);

        System.out.printf(
            "Подбор пароля в %d потоков быстрее в %f раз%n",
            threads, (double) singleWorkTime / multiWorkTime
        );
    }
}
