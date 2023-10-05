package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        var stringyNumber = String.valueOf(number);
        return isPalindrome(stringyNumber) || isPalindromeDescendant(getDescendant(stringyNumber));
    }

    private static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - (i + 1))) {
                return false;
            }
        }

        return true;
    }

    private static int getDescendant(String stringyNumber) {
        var builder = new StringBuilder();

        for (int i = 0; i < stringyNumber.length() - 1; i += 2) {
            builder.append(Integer.parseInt(String.valueOf(stringyNumber.charAt(i)))
                + Integer.parseInt(String.valueOf(stringyNumber.charAt(i + 1))));
        }

        if (stringyNumber.length() % 2 == 1) {
            builder.append(stringyNumber.length() - 1);
        }

        return Integer.parseInt(builder.toString());
    }
}
