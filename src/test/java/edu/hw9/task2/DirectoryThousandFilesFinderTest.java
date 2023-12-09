package edu.hw9.task2;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DirectoryThousandFilesFinderTest extends BaseFilesFinderTest {
    @Test
    public void assertThatDirectoriesFindRight() {
        DirectoryThousandFilesFinder finder = new DirectoryThousandFilesFinder(TEST_DIRECTORY);

        List<Path> result = finder.compute();
        assertEquals(0, result.size());
        assertEquals(List.of(), result);

        createFiles(1_001);

        result = finder.compute();

        assertEquals(1, result.size());
        assertEquals(List.of(TEST_DIRECTORY), result);
    }

    @Test
    public void assertThatIncorrectInputThrowsException() {
        assertThrows(NullPointerException.class, () -> new DirectoryThousandFilesFinder(null));
        assertThrows(
            IllegalArgumentException.class,
            () -> new DirectoryThousandFilesFinder(TEST_DIRECTORY.resolve("test"))
        );
    }
}
