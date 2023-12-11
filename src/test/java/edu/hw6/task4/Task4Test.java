package edu.hw6.task4;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task4.Task4.write;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {
    private static Path outputFilePath;

    @BeforeAll
    public static void setUp() {
        outputFilePath = Path.of("src", "test", "java", "edu", "hw6", "task4", "output.txt");
    }

    @AfterAll
    public static void afterAll() {
        outputFilePath.toFile().delete();
    }

    @Test
    public void assertThatOutputFileContainsRightText() {
        assertTrue(write(outputFilePath));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(outputFilePath)))) {
            assertEquals("Programming is learned by writing programs. ― Brian Kernighan", reader.readLine());
        } catch (IOException ignored) {
        }
    }
}
