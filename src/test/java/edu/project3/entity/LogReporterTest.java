package edu.project3.entity;

import edu.project3.ui.AsciiDocFormatter;
import edu.project3.ui.MarkdownFormatter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class LogReporterTest {
    private static final Path directoryPath = Path.of("./");
    private static final File mdPath = directoryPath.resolve("log_report.md").toFile();
    private static final File adocPath = directoryPath.resolve("log_report.adoc").toFile();

    @AfterAll
    public static void afterAll() {
        mdPath.delete();
        adocPath.delete();
    }

    @Test
    public void assertThatReportCreatesReport() {
        final Map<String, String> parameters = Map.of(
            "--path", "src/main/java/edu/project3/logs/nginx_logs.log"
        );

        LogAnalyzer analyzer = new LogAnalyzer(parameters);
        LogReporter mdReporter = new LogReporter(analyzer, new MarkdownFormatter());
        LogReporter adocReporter = new LogReporter(analyzer, new AsciiDocFormatter());

        analyzer.analyze();

        assertFalse(mdPath.exists());
        mdReporter.report(directoryPath);
        assertTrue(mdPath.exists());

        assertFalse(adocPath.exists());
        adocReporter.report(directoryPath);
        assertTrue(adocPath.exists());
    }
}
