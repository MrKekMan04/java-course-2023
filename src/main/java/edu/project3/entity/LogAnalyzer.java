package edu.project3.entity;

import edu.project3.entity.chain.AbstractFinder;
import edu.project3.entity.chain.FileFinder;
import edu.project3.entity.chain.FileInIntermediateDirectoriesFinder;
import edu.project3.entity.chain.FilesInDirectoryFinder;
import edu.project3.entity.chain.UrlFinder;
import edu.project3.repository.LogRepository;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH_KEY = "--path";
    private final Map<String, String> parameters;
    private final List<String> parsedLogs;
    private final LogRepository repository;
    private final AbstractFinder finder;

    public LogAnalyzer(Map<String, String> parameters) {
        if (parameters.get(PATH_KEY) == null) {
            throw new NullPointerException("Required start parameter `" + PATH_KEY + "` is not defined!");
        }

        this.parameters = parameters;
        this.parsedLogs = new ArrayList<>();
        this.repository = new LogRepository();
        this.finder = buildChain();
    }

    public void analyze() {
        String path = parameters.get(PATH_KEY);

        for (Stream<String> log : finder.getLogsLines(path)) {
            analyzeLog(log, path);
        }
    }

    public LogRepository getRepository() {
        return repository;
    }

    public List<String> getParsedLogs() {
        return parsedLogs;
    }

    private AbstractFinder buildChain() {
        return new UrlFinder(new FilesInDirectoryFinder(new FileInIntermediateDirectoriesFinder(new FileFinder(null))));
    }

    private void analyzeLog(Stream<String> lines, String path) {
        LocalDate parseFrom = parseTime(parameters.get("--from"));
        LocalDate parseTo = parseTime(parameters.get("--to"));

        lines.map(this::tryParseLogLine)
            .filter(Objects::nonNull)
            .filter(logRecord -> isBetween(logRecord, parseFrom, parseTo))
            .forEach(repository::add);

        parsedLogs.add(path);
    }

    private LocalDate parseTime(String time) {
        return time == null ? null : LocalDate.parse(time, DateTimeFormatter.ISO_DATE);
    }

    private LogRecord tryParseLogLine(String line) {
        try {
            return new LogRecord(line);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    private boolean isBetween(LogRecord logRecord, LocalDate from, LocalDate to) {
        LocalDate recordDate = logRecord.getDateTime().toLocalDate();

        return (to == null || Period.between(to, recordDate).isNegative())
            && (from == null || Period.between(recordDate, from).isNegative());
    }
}
