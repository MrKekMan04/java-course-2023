package edu.hw9.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseFilesFinderTest {
    protected static final Path TEST_DIRECTORY = Path.of("src/test/java/edu/hw9/task2/testData");

    protected void createFiles(int count) {
        for (int i = 0; i < count; i++) {
            try {
                Files.createFile(TEST_DIRECTORY.resolve(i + ".txt"));
            } catch (IOException ignored) {
            }
        }
    }

    @BeforeEach
    public void createTestFolder() {
        if (!TEST_DIRECTORY.toFile().exists()) {
            try {
                Files.createDirectory(TEST_DIRECTORY);
            } catch (IOException ignored) {
            }
        }
    }

    @AfterEach
    public void removeFiles() {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(TEST_DIRECTORY)) {
            paths.forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException ignored) {
                }
            });
        } catch (IOException ignored) {
        }
    }
}
