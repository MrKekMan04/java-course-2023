package edu.hw6.task3;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PredicatesTest {
    private static final Path TEST_DATA_PATH = Path.of("src", "test", "java", "edu", "hw6", "task3", "data");

    @Test
    public void assertThatLargerThanWorksRight() {
        final List<String> expectedFileNames = List.of("file_with_size_more_then_100_bytes.txt", "img.png");
        final ArrayList<String> actualFileNames = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DATA_PATH)) {
            for (Path entry : entries) {
                if (Predicates.largerThan(entry, 100)) {
                    actualFileNames.add(entry.getFileName().toString());
                }
            }
        } catch (IOException ignored) {
        }

        assertEquals(expectedFileNames, actualFileNames);
    }

    @Test
    public void assertThatMagicNumberWorksRight() {
        final List<String> expectedFileNames = List.of("img.png");
        final ArrayList<String> actualFileNames = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DATA_PATH)) {
            for (Path entry : entries) {
                if (Predicates.magicNumber(entry, 0x89, 'P', 'N', 'G')) {
                    actualFileNames.add(entry.getFileName().toString());
                }
            }
        } catch (IOException ignored) {
        }

        assertEquals(expectedFileNames, actualFileNames);
    }

    @Test
    public void assertThatGlobMatchesWorksRight() {
        final List<String> expectedFileNames = List.of("file_with_json_format.json");
        final ArrayList<String> actualFileNames = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DATA_PATH)) {
            for (Path entry : entries) {
                if (Predicates.globMatches(entry, "*.json")) {
                    actualFileNames.add(entry.getFileName().toString());
                }
            }
        } catch (IOException ignored) {
        }

        assertEquals(expectedFileNames, actualFileNames);
    }

    @Test
    public void assertThatRegexContainsWorksRight() {
        final List<String> expectedFileNames =
            List.of("file_with_size_less_then_100_bytes.txt", "file_with_size_more_then_100_bytes.txt");
        final ArrayList<String> actualFileNames = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DATA_PATH)) {
            for (Path entry : entries) {
                if (Predicates.regexContains(entry, "with_size")) {
                    actualFileNames.add(entry.getFileName().toString());
                }
            }
        } catch (IOException ignored) {
        }

        assertEquals(expectedFileNames, actualFileNames);
    }
}
