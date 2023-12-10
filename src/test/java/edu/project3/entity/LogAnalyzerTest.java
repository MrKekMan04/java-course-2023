package edu.project3.entity;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalyzerTest {
    private final int expectedLogRecords = 51462;
    private final Map<String, String> filePathParameter =
        Map.of("--path", "src/main/java/edu/project3/logs/nginx_logs.log");
    private final Map<String, String> filesInDirectoryPathParameter =
        Map.of("--path", "src/main/java/edu/project3/logs/*");
    private final Map<String, String> fileInIntermediateDirectoriesParameter =
        Map.of("--path", "src/main/**/nginx_logs.log");
    private final Map<String, String> urlPathParameter = Map.of(
        "--path",
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
    );

    @Test
    public void assertThatAllLinesParsedRight() {
        final int expectedRecordsWithTimeConstraint = 2864;
        final Map<String, String> parametersWithTimeConstraint = Map.of(
            "--path", "src/main/java/edu/project3/logs/nginx_logs.log",
            "--from", "2015-06-01",
            "--to", "2015-06-03"
        );

        LogAnalyzer analyzer = new LogAnalyzer(filePathParameter);
        analyzer.analyze();
        LogAnalyzer analyzerWithTimeConstraint = new LogAnalyzer(parametersWithTimeConstraint);
        analyzerWithTimeConstraint.analyze();

        assertEquals(expectedLogRecords, analyzer.getRepository().size());
        assertEquals(List.of(filePathParameter.get("--path")), analyzer.getParsedLogs());

        assertEquals(expectedRecordsWithTimeConstraint, analyzerWithTimeConstraint.getRepository().size());
        assertEquals(List.of(parametersWithTimeConstraint.get("--path")), analyzerWithTimeConstraint.getParsedLogs());
    }

    @Test
    public void assertThatAnalyzerWorksWithAnyFormats() {
        LogAnalyzer urlAnalyzer = new LogAnalyzer(urlPathParameter);
        urlAnalyzer.analyze();

        LogAnalyzer fileAnalyzer = new LogAnalyzer(filePathParameter);
        fileAnalyzer.analyze();

        LogAnalyzer filesInDirectoryPathAnalyzer = new LogAnalyzer(filesInDirectoryPathParameter);
        filesInDirectoryPathAnalyzer.analyze();

        LogAnalyzer fileInIntermediateDirectoriesAnalyzer = new LogAnalyzer(fileInIntermediateDirectoriesParameter);
        fileInIntermediateDirectoriesAnalyzer.analyze();

        assertEquals(expectedLogRecords, urlAnalyzer.getRepository().size());
        assertEquals(expectedLogRecords, fileAnalyzer.getRepository().size());
        assertEquals(expectedLogRecords, filesInDirectoryPathAnalyzer.getRepository().size());
        assertEquals(expectedLogRecords, fileInIntermediateDirectoriesAnalyzer.getRepository().size());
    }
}
