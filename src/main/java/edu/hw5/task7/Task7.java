package edu.hw5.task7;

import java.util.regex.Pattern;

public final class Task7 {
    private static final Pattern AT_LEAST_3_SYMBOLS_AND_THIRD_IS_0 = Pattern.compile("^[01]{2}0[01]*$");
    private static final Pattern START_AND_END_WITH_SAME_CHARACTER = Pattern.compile("^0[01]*0$|^1[01]*1$|^0$|^1$");
    private static final Pattern LENGTH_MORE_OR_EQUALS_1_AND_LESS_OR_EQUALS_3 = Pattern.compile("^[01]{1,3}$");

    private Task7() {
    }

    public static boolean checkRegularExpression(int index, String input) {
        Pattern pattern = switch (index) {
            case 0 -> AT_LEAST_3_SYMBOLS_AND_THIRD_IS_0;
            case 1 -> START_AND_END_WITH_SAME_CHARACTER;
            case 2 -> LENGTH_MORE_OR_EQUALS_1_AND_LESS_OR_EQUALS_3;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };

        return pattern.matcher(input).find();
    }
}
