package edu.project3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {
    private static final Path directoryPath = Path.of("./");
    private static final File mdPath = directoryPath.resolve("log_report.md").toFile();
    private static final File adocPath = directoryPath.resolve("log_report.adoc").toFile();

    @AfterAll
    public static void afterAll() {
        mdPath.delete();
        adocPath.delete();
    }

    @Test
    public void assertThatProgramWorksRight() {
        Program.main(new String[] {"--path", "src/main/java/edu/project3/logs/nginx_logs.log"});
        assertTrue(mdPath.exists());
        assertFalse(adocPath.exists());

        Program.main(new String[] {"--path", "src/main/java/edu/project3/logs/nginx_logs.log", "--format", "adoc"});
        assertTrue(adocPath.exists());
    }
}
