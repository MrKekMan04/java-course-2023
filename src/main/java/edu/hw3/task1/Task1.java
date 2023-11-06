package edu.hw3.task1;

import java.util.stream.IntStream;

public final class Task1 {
    private static final int LATIN_SMALL_LETTER_A = 97;
    private static final int LATIN_SMALL_LETTER_Z = 122;
    private static final int LATIN_CAPITAL_LETTER_A = 65;
    private static final int LATIN_CAPITAL_LETTER_Z = 90;

    private Task1() {
    }

    public static String getAtbashString(String raw) {
        if (raw == null) {
            return null;
        }

        return IntStream.range(0, raw.length())
            .mapToObj(i -> transformSymbolCode(raw.charAt(i)))
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    private static int transformSymbolCode(int numericChar) {
        if (numericChar >= LATIN_SMALL_LETTER_A && numericChar <= LATIN_SMALL_LETTER_Z) {
            return LATIN_SMALL_LETTER_Z - (numericChar - LATIN_SMALL_LETTER_A);
        } else if (numericChar >= LATIN_CAPITAL_LETTER_A && numericChar <= LATIN_CAPITAL_LETTER_Z) {
            return LATIN_CAPITAL_LETTER_Z - (numericChar - LATIN_CAPITAL_LETTER_A);
        }

        return numericChar;
    }
}
