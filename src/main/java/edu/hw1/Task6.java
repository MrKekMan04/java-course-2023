package edu.hw1;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class Task6 {
    private static final int KAPREKAR_CONSTANT = 6174;

    private Task6() {
    }

    public static int countK(int number) {
        return countK(number, 0);
    }

    private static int countK(int number, int step) {
        if (number == KAPREKAR_CONSTANT) {
            return step;
        }

        var minNumberBuilder = new StringBuilder(Arrays.stream(getDigits(String.valueOf(number)))
            .sorted()
            .mapToObj(String::valueOf)
            .collect(Collectors.joining("")));

        var minNumber = Integer.parseInt(minNumberBuilder.toString());

        return countK(
            Integer.parseInt(minNumberBuilder.reverse().toString()) - minNumber,
            step + 1
        );
    }

    private static int[] getDigits(String stringyNumber) {
        var digits = new int[stringyNumber.length()];

        for (int i = 0; i < stringyNumber.length(); i++) {
            digits[i] = Integer.parseInt(String.valueOf(stringyNumber.charAt(i)));
        }

        return digits;
    }
}
