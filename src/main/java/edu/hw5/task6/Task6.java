package edu.hw5.task6;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public final class Task6 {
    private Task6() {
    }

    public static boolean isSubstring(String substring, String string) {
        return substring != null && string != null
            && Pattern.compile(buildRegularFromString(substring))
            .matcher(string)
            .find();
    }

    private static String buildRegularFromString(String input) {
        StringBuilder builder = new StringBuilder();

        IntStream.range(0, input.length())
            .mapToObj(input::charAt)
            .forEach(c -> builder.append("[").append(c == '\\' ? "\\\\" : c).append("]"));

        return builder.toString();
    }
}
