package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        var binaryString = Integer.toBinaryString(n);
        var bitsCount = binaryString.length();
        var steps = shift % bitsCount;

        return Integer.parseInt(binaryString.substring(steps) + binaryString.substring(0, steps), 2);
    }

    public static int rotateRight(int n, int shift) {
        var binaryString = Integer.toBinaryString(n);
        var bitsCount = binaryString.length();
        var steps = shift % bitsCount;

        return Integer.parseInt(
            binaryString.substring(bitsCount - steps) + binaryString.substring(0, bitsCount - steps), 2);
    }
}
