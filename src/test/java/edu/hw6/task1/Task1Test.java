package edu.hw6.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {
    private File existsFile;
    private File notExistsFile;
    private static DiskMap existsFileDiskMap;
    private static DiskMap existsFileDiskMapWithAutoLoad;
    private static DiskMap notExistsFileDiskMap;

    @BeforeEach
    public void setUp() {
        existsFile = Path.of("src", "main", "java", "edu", "hw6", "task1", "data.txt").toFile();
        notExistsFile = Path.of("src", "main", "java", "edu", "hw6", "task1", "abc.txt").toFile();
        notExistsFileDiskMap = new DiskMap(notExistsFile.toPath());
        existsFileDiskMap = new DiskMap(existsFile.toPath());
        existsFileDiskMapWithAutoLoad = new DiskMap(existsFile.toPath(), true);
    }

    @AfterEach
    public void afterEach() {
        if (notExistsFile.exists()) {
            notExistsFile.delete();
        }
    }

    @Test
    public void assertThatDiskMapDataLoadingWorksRight() {
        assertEquals(-1, getFileLinesCount(notExistsFile));
        assertEquals(3, getFileLinesCount(existsFile));
        assertTrue(existsFileDiskMap.isEmpty());
        assertTrue(notExistsFileDiskMap.isEmpty());
        assertFalse(existsFileDiskMapWithAutoLoad.isEmpty());
        assertEquals(-1, getFileLinesCount(notExistsFile));
        assertTrue(notExistsFileDiskMap.loadData());
        assertTrue(notExistsFileDiskMap.isEmpty());
        assertEquals(0, getFileLinesCount(notExistsFile));
        assertEquals(3, getFileLinesCount(existsFile));
    }

    @Test
    public void assertThatInvalidLinesIgnored() {
        final File invalidLinesFile = Path.of("src", "main", "java", "edu", "hw6", "task1", "invalidData.txt").toFile();
        final DiskMap map = new DiskMap(invalidLinesFile.toPath());

        assertEquals(3, getFileLinesCount(invalidLinesFile));
        assertEquals(0, map.size());
        assertTrue(map.loadData());
        assertEquals(1, map.size());
    }

    @Test
    public void assertThatSavingDataWorksRight() {
        assertEquals(-1, getFileLinesCount(notExistsFile));
        assertTrue(notExistsFileDiskMap.saveData());
        assertEquals(0, getFileLinesCount(notExistsFile));
        assertEquals(3, getFileLinesCount(existsFile));
        assertTrue(existsFileDiskMap.saveData());
        assertEquals(0, getFileLinesCount(existsFile));
        assertTrue(existsFileDiskMapWithAutoLoad.saveData());
        assertEquals(3, getFileLinesCount(existsFile));
    }

    private long getFileLinesCount(File file) {
        try (var in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            return in.lines().count();
        } catch (IOException ignored) {
        }

        return -1;
    }
}
