package edu.hw10.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {
    private static final Path PERSIST_DIRECTORY = Path.of("src/test/java/edu/hw10/task2/testData");
    private static FibCalculator calculator;

    @BeforeAll
    public static void setUp() throws IOException {
        if (!Files.exists(PERSIST_DIRECTORY)) {
            Files.createDirectories(PERSIST_DIRECTORY);
        }

        var c = new FibCalculatorImpl();
        calculator = CacheProxy.create(c, c.getClass(), PERSIST_DIRECTORY);
    }

    @AfterEach
    public void afterEach() throws IOException {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(PERSIST_DIRECTORY)) {
            for (Path path : paths) {
                Files.deleteIfExists(path);
            }
        }
    }

    @Test
    public void assertThatCachingWorksRight() {
        final int expected = 165580141;

        long firstStartTime = System.nanoTime();
        assertEquals(expected, calculator.fib(40));
        long firstWorkingTime = System.nanoTime() - firstStartTime;

        long secondStartTime = System.nanoTime();
        assertEquals(expected, calculator.fib(40));
        long secondWorkingTime = System.nanoTime() - secondStartTime;

        assertTrue(secondWorkingTime < firstWorkingTime);
    }

    @Test
    public void assertThatPersistWorksRight() throws IOException {
        calculator.fib(0);
        calculator.fib(1);
        calculator.fib(2);

        int filesCount = 0;

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(PERSIST_DIRECTORY)) {
            for (Path ignored : paths) {
                filesCount++;
            }
        }

        assertEquals(3, filesCount);
    }
}
