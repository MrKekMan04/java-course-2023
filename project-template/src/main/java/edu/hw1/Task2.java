package edu.hw1;

public final class Task2 {
    private Task2() {
    }

    public static int countDigits(int number) {
        var unSignedNumber = Math.abs(number);
        var digits = 1;

        if (unSignedNumber > 9) {
            while (unSignedNumber / 10 != 0) {
                unSignedNumber /= 10;
                digits++;
            }
        }

        return digits;
    }
}
