package edu.project3.entity;

import edu.project3.repository.LogRepository;
import edu.project3.ui.TextFormatter;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class LogReporter {
    private final LogAnalyzer analyzer;
    private final TextFormatter formatter;

    public LogReporter(LogAnalyzer analyzer, TextFormatter formatter) {
        this.analyzer = analyzer;
        this.formatter = formatter;
    }

    public void report(Path outputDirectoryPath) {
        try (var osw = new OutputStreamWriter(new BufferedOutputStream(Files.newOutputStream(
            outputDirectoryPath.resolve("log_report." + formatter.getExtension()))))) {
            osw.write(buildInformation());
        } catch (IOException ignored) {
        }
    }

    private String buildInformation() {
        return formatter.title("Общая информация") + '\n' + getGeneralInformation() + '\n'
            + formatter.title("Запрашиваемые ресурсы") + '\n' + getRequestedResources() + '\n'
            + formatter.title("Коды ответа") + '\n' + getCodesInformation() + '\n'
            + formatter.title("Методы запросов") + '\n' + getRequestMethodsInformation() + '\n'
            + formatter.title("Http User Agents") + '\n' + getUserAgentInformation() + '\n';
    }

    private String getGeneralInformation() {
        LogRepository repository = analyzer.getRepository();

        return formatter.table(6, 2, new String[][] {
            new String[] {"Метрика", "Значение"},
            new String[] {"Файл(-ы)", formatter.monospace(String.valueOf(analyzer.getParsedLogs()))},
            new String[] {"Начальная дата",
                repository.getNewestRecord() != null ? repository.getNewestRecord().getDateTime().toString() : "null"},
            new String[] {"Конечная дата",
                repository.getOldestRecord() != null ? repository.getOldestRecord().getDateTime().toString() : "null"},
            new String[] {"Количество запросов", String.valueOf(repository.size())},
            new String[] {"Средний размер ответа", repository.size() != 0
                ? String.valueOf(repository.getResponsesSizeInBytes() / repository.size()) : "None"}
        });
    }

    private String getRequestedResources() {
        Map<String, Long> requestedResources = analyzer.getRepository()
            .getAll().stream()
            .collect(Collectors.groupingBy(LogRecord::getRequestPath, Collectors.counting()));

        return getTableFromMap(requestedResources, new String[] {"Ресурс", "Кол-во обращений"});
    }

    private String getCodesInformation() {
        Map<Integer, Long> responseStatuses = analyzer.getRepository()
            .getAll().stream()
            .collect(Collectors.groupingBy(LogRecord::getResponseStatus, Collectors.counting()));

        String[][] tableData = new String[responseStatuses.size() + 1][3];
        tableData[0] = new String[] {"Код", "Имя", "Количество"};
        int index = 0;

        for (Integer code : responseStatuses.keySet()) {
            tableData[++index][0] = String.valueOf(code);
            tableData[index][1] = ResponseStatuses.getStatusFromCode(code);
            tableData[index][2] = String.valueOf(responseStatuses.get(code));
        }

        return formatter.table(responseStatuses.size() + 1, 3, tableData);
    }

    private String getRequestMethodsInformation() {
        Map<RequestType, Long> requestMethods = analyzer.getRepository()
            .getAll().stream()
            .collect(Collectors.groupingBy(LogRecord::getRequestMethod, Collectors.counting()));

        return getTableFromMap(requestMethods, new String[] {"Метод", "Количество"});
    }

    private String getUserAgentInformation() {
        Map<String, Long> userAgents = analyzer.getRepository()
            .getAll().stream()
            .collect(Collectors.groupingBy(LogRecord::getHttpUserAgent, Collectors.counting()));

        return getTableFromMap(userAgents, new String[] {"Агент", "Количество"});
    }

    private String getTableFromMap(Map<?, Long> requestMethods, String[] header) {
        String[][] tableData = new String[requestMethods.size() + 1][2];
        tableData[0] = header;
        int index = 0;

        for (Object method : requestMethods.keySet()) {
            tableData[++index][0] = String.valueOf(method);
            tableData[index][1] = String.valueOf(requestMethods.get(method));
        }

        return formatter.table(requestMethods.size() + 1, 2, tableData);
    }

}
