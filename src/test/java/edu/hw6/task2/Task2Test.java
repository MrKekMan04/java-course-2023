package edu.hw6.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import static edu.hw6.task2.Task2.cloneFile;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {
    private static Path parentPath;

    @BeforeAll
    public static void setUp() {
        parentPath = Path.of("src", "main", "java", "edu", "hw6", "task2");
    }

    @AfterEach
    public void afterEach() {
        List<String> postfixes = List.of("", " (1)", " (2)");

        postfixes.forEach(postfix -> parentPath.resolve("test — копия" + postfix + ".txt")
            .toFile()
            .delete());
    }

    @Test
    public void assertThatCloneFileWorksRight() {
        Path dataFile = parentPath.resolve("test.txt");

        assertTrue(cloneFile(dataFile));
        assertTrue(parentPath.resolve("test — копия.txt").toFile().exists());
        assertTrue(cloneFile(dataFile));
        assertTrue(parentPath.resolve("test — копия (1).txt").toFile().exists());
        assertTrue(cloneFile(dataFile));
        assertTrue(parentPath.resolve("test — копия (2).txt").toFile().exists());
    }

    @Test
    public void assertThatIncorrectOrNotExistingPathReturnedFalse() {
        assertFalse(cloneFile(parentPath.resolve("test")));
        assertFalse(cloneFile(parentPath.resolve("not_exists_file.txt")));
    }
}
