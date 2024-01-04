package edu.hw7.task2;

import java.util.stream.LongStream;

public final class ParallelFactorial {
    private ParallelFactorial() {
    }

    public static long calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Parameter `n` must be more or equals 0");
        }

        return LongStream.range(1, n + 1)
            .parallel()
            .reduce(1L, (acc, number) -> acc * number);
    }
}
