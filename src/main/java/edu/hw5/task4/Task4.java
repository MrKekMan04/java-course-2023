package edu.hw5.task4;

import java.util.regex.Pattern;

public final class Task4 {
    private static final Pattern CONTAINS_AT_LEAST_ONE_SPECIAL_SYMBOL_PATTERN =
        Pattern.compile("[~!@#$%^&*|]");

    private Task4() {
    }

    public static boolean isPasswordValid(String password) {
        return password != null && CONTAINS_AT_LEAST_ONE_SPECIAL_SYMBOL_PATTERN.matcher(password).find();
    }
}
