package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        if (firstArray.length > 0 && secondArray.length > 0) {
            return Arrays.stream(firstArray).min().getAsInt() > Arrays.stream(secondArray).min().getAsInt()
                || Arrays.stream(firstArray).max().getAsInt() < Arrays.stream(secondArray).max().getAsInt();
        }

        return false;
    }
}
