package edu.hw5.task8;

import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public final class Task8 {
    private static final Pattern ODD_LENGTH_PATTERN = Pattern.compile("^[01]([01][01])*$");
    private static final Pattern START_WITH_0_AND_ODD_LENGTH_OR_START_WITH_1_AND_EVEN_LENGTH_PATTERN =
        Pattern.compile("^(0|1[01])([01][01])*$");
    private static final Pattern EVERY_ODD_SYMBOL_IS_1_PATTERN = Pattern.compile("^1([01]1)*[01]?$");
    private static final Pattern DOES_NOT_CONTAINS_CONSECUTIVE_1_PATTERN = Pattern.compile("^(?![01]*11)[01]*$");

    private Task8() {
    }

    public static boolean checkRegularExpression(int index, String input) {
        Pattern pattern = switch (index) {
            case 0 -> ODD_LENGTH_PATTERN;
            case 1 -> START_WITH_0_AND_ODD_LENGTH_OR_START_WITH_1_AND_EVEN_LENGTH_PATTERN;
            case 2 -> EVERY_ODD_SYMBOL_IS_1_PATTERN;
            case 3 -> DOES_NOT_CONTAINS_CONSECUTIVE_1_PATTERN;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };

        return pattern.matcher(input).find();
    }
}
