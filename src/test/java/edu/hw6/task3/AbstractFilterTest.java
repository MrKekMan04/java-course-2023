package edu.hw6.task3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFilterTest {
    @Test
    public void assertThatFilterChainWorksRight() {
        AbstractFilter isReadable = Files::isReadable;

        DirectoryStream.Filter<Path> filter = isReadable
            .and(Files::exists)
            .and(Files::isWritable)
            .and(path -> Predicates.magicNumber(path, 0x89, 'P', 'N', 'G'))
            .and(path -> Predicates.globMatches(path, "*.png"))
            .and(path -> Predicates.largerThan(path, 100))
            .and(path -> Predicates.regexContains(path, "mg"));

        List<String> expectedFileNames = List.of("img.png");
        List<String> actualFileNames = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(
            Path.of("src", "test", "java", "edu", "hw6", "task3", "data"), filter)) {
            entries.forEach(e -> actualFileNames.add(e.getFileName().toString()));
        } catch (IOException ignored) {
        }

        assertEquals(expectedFileNames, actualFileNames);
    }
}
