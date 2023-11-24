package edu.hw7.task4;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static edu.hw7.task4.PiCalculator.calculateMultiThread;
import static edu.hw7.task4.PiCalculator.calculateSingleThread;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PiCalculatorTest {
    @Test
    public void assertThatMultiThreadingWorksFasterOn10MilsIterations()
        throws ExecutionException, InterruptedException {
        final int iterationsCount = 10_000_000;

        try (ExecutorService pool = Executors.newCachedThreadPool()) {
            PerformanceEntity<Double> multiThread =
                calculatePerformance(pool.submit(() -> calculateMultiThread(4, iterationsCount)));
            PerformanceEntity<Double> singleThread =
                calculatePerformance(pool.submit(() -> calculateSingleThread(iterationsCount)));

            assertTrue(multiThread.executionTimeNanos() < singleThread.executionTimeNanos());
            System.out.println(getPerformanceMessage(singleThread, multiThread));
        }
    }

    @Test
    public void assertThatMultiThreadingWorksFasterOn100MilsIterations()
        throws ExecutionException, InterruptedException {
        final int iterationsCount = 100_000_000;

        try (ExecutorService pool = Executors.newCachedThreadPool()) {
            PerformanceEntity<Double> multiThread =
                calculatePerformance(pool.submit(() -> calculateMultiThread(4, iterationsCount)));
            PerformanceEntity<Double> singleThread =
                calculatePerformance(pool.submit(() -> calculateSingleThread(iterationsCount)));

            assertTrue(multiThread.executionTimeNanos() < singleThread.executionTimeNanos());
            System.out.println(getPerformanceMessage(singleThread, multiThread));
        }
    }

    @Test
    public void assertThatMultiThreadingWorksFasterOn1BilIterations()
        throws ExecutionException, InterruptedException {
        final int iterationsCount = 1_000_000_000;

        try (ExecutorService pool = Executors.newCachedThreadPool()) {
            PerformanceEntity<Double> multiThread =
                calculatePerformance(pool.submit(() -> calculateMultiThread(4, iterationsCount)));
            PerformanceEntity<Double> singleThread =
                calculatePerformance(pool.submit(() -> calculateSingleThread(iterationsCount)));

            assertTrue(multiThread.executionTimeNanos() < singleThread.executionTimeNanos());
            System.out.println(getPerformanceMessage(singleThread, multiThread));
        }
    }

    @Test
    public void assertThatIncorrectInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculateSingleThread(-1));
        assertThrows(IllegalArgumentException.class, () -> calculateSingleThread(0));

        assertThrows(IllegalArgumentException.class, () -> calculateMultiThread(1, -1));
        assertThrows(IllegalArgumentException.class, () -> calculateMultiThread(1, 0));
        assertThrows(IllegalArgumentException.class, () -> calculateMultiThread(0, 1));
        assertThrows(IllegalArgumentException.class, () -> calculateMultiThread(-1, 1));
    }

    private PerformanceEntity<Double> calculatePerformance(Future<Double> task)
        throws ExecutionException, InterruptedException {
        long start = System.nanoTime();

        return new PerformanceEntity<>(task.get(), System.nanoTime() - start);
    }

    private String getPerformanceMessage(
        PerformanceEntity<Double> singleThread,
        PerformanceEntity<Double> multiThread
    ) {
        return """
            Число pi, вычисленное в однопотоке: %f
            Дельта pi в однопотоке: %f
            Число pi, вычисленное в многопотоке: %f
            Дельта pi в мнопотоке: %f
            Время выполнения в однопотоке: %d nanos
            Время выполнения в многопотоке: %d nanos
            Коэффициент ускорения в многопотоке: %f
            """.formatted(singleThread.returnedValue(), multiThread.returnedValue(),
            Math.abs(Math.PI - singleThread.returnedValue()), Math.abs(Math.PI - multiThread.returnedValue()),
            singleThread.executionTimeNanos(), multiThread.executionTimeNanos(),
            (double) singleThread.executionTimeNanos() / multiThread.executionTimeNanos()
        );
    }
}
