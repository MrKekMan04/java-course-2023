package edu.hw9.task2;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryPredicateFinderTest extends BaseFilesFinderTest {
    @Test
    public void assertThatDirectoriesFindRight() {
        DirectoryPredicateFinder finder =
            new DirectoryPredicateFinder(
                TEST_DIRECTORY,
                List.of(path -> path.getFileName().toString().startsWith("1"))
            );

        createFiles(11);

        List<Path> result = finder.compute();

        assertEquals(2, result.size());
        assertEquals(List.of(TEST_DIRECTORY.resolve("1.txt"), TEST_DIRECTORY.resolve("10.txt")), result);
    }

    @Test
    public void assertThatIncorrectInputThrowsException() {
        assertThrows(NullPointerException.class, () -> new DirectoryPredicateFinder(null, List.of()));
        assertThrows(NullPointerException.class, () -> new DirectoryPredicateFinder(TEST_DIRECTORY, null));
        assertThrows(
            IllegalArgumentException.class,
            () -> new DirectoryPredicateFinder(TEST_DIRECTORY.resolve("test"), List.of())
        );
    }
}
