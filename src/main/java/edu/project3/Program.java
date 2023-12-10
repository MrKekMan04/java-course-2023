package edu.project3;

import edu.project3.entity.LogAnalyzer;
import edu.project3.entity.LogReporter;
import edu.project3.ui.AsciiDocFormatter;
import edu.project3.ui.MarkdownFormatter;
import edu.project3.ui.TextFormatter;
import java.nio.file.Path;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UncommentedMain", "ModifiedControlVariable", "MultipleStringLiterals"})
public final class Program {
    private Program() {
    }

    public static void main(String[] args) {
        HashMap<String, String> startParameters = getStartParameters(args);

        LogAnalyzer analyzer = new LogAnalyzer(startParameters);
        LogReporter reporter = new LogReporter(analyzer, determineFormatter(startParameters.get("--format")));

        analyzer.analyze();

        reporter.report(Path.of("./"));
    }

    @NotNull
    private static HashMap<String, String> getStartParameters(String[] args) {
        HashMap<String, String> startParameters = new HashMap<>();
        startParameters.put("--path", null);
        startParameters.put("--format", null);
        startParameters.put("--from", null);
        startParameters.put("--to", null);

        for (int i = 0; i < args.length; i++) {
            if (startParameters.containsKey(args[i])) {
                if (i + 1 < args.length) {
                    startParameters.put(args[i], args[i + 1]);
                    i++;
                }
            }
        }

        return startParameters;
    }

    @NotNull
    private static TextFormatter determineFormatter(String format) {
        return format != null && format.equals("adoc") ? new AsciiDocFormatter() : new MarkdownFormatter();
    }
}
