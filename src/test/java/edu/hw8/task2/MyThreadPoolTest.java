package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyThreadPoolTest {
    @Test
    public void assertThatFibonacciCalculatingRight() {
        try (MyThreadPool pool = MyThreadPool.create(4)) {
            pool.execute(() -> assertEquals(1, calculateFibonacci(0)));
            pool.execute(() -> assertEquals(1, calculateFibonacci(1)));
            pool.execute(() -> assertEquals(2, calculateFibonacci(2)));
            pool.execute(() -> assertEquals(3, calculateFibonacci(3)));
            pool.execute(() -> assertEquals(5, calculateFibonacci(4)));

            pool.start();
        }
    }

    @Test
    public void assertThatThreadPoolWorksFasterThanSingleThread() {
        final int maxN = 40;

        long singleThreadStartTime = System.nanoTime();
        for (int i = 0; i < maxN; i++) {
            calculateFibonacci(i);
        }
        long singleThreadEndTime = System.nanoTime();

        long threadPoolStartTime = System.nanoTime();
        try (MyThreadPool pool = MyThreadPool.create(4)) {
            for (int i = 0; i < maxN; i++) {
                final int finalI = i;

                pool.execute(() -> calculateFibonacci(finalI));
            }

            pool.start();
        }
        long threadPoolEndTime = System.nanoTime();

        assertTrue(threadPoolEndTime - threadPoolStartTime < singleThreadEndTime - singleThreadStartTime);

        System.out.printf(
            "%d первых чисел Фибоначчи вычисляются быстрее в 4 потока в %f раз%n",
            maxN, (float) (singleThreadEndTime - singleThreadStartTime) / (threadPoolEndTime - threadPoolStartTime)
        );
    }

    @Test
    public void assertThatIncorrectThreadsCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            try (MyThreadPool pool = MyThreadPool.create(-1)) {
                pool.execute(() -> System.out.println("Unreachable"));
            }
        });
    }

    private long calculateFibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
