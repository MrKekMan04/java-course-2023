package edu.hw7.task4;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SuppressWarnings("MagicNumber")
public class PiCalculator {
    private static final double SIDE_LENGTH = 1;
    private static final double MIDDLE = SIDE_LENGTH / 2;
    private static final String INVALID_ITERATIONS_COUNT_INPUT_EXCEPTION_MESSAGE =
        "Parameter `iterationsCount` must be more then 0";
    private static final String INVALID_THREADS_COUNT_INPUT_EXCEPTION_MESSAGE =
        "Parameter `threadCount` must be more then 0";

    private PiCalculator() {
    }

    public static double calculateSingleThread(long iterationsCount) {
        if (iterationsCount < 1) {
            throw new IllegalArgumentException(INVALID_ITERATIONS_COUNT_INPUT_EXCEPTION_MESSAGE);
        }

        long circleCount = 0L;

        for (int i = 0; i < iterationsCount; i++) {
            double dx = (MIDDLE - ThreadLocalRandom.current().nextDouble() * SIDE_LENGTH);
            double dy = (MIDDLE - ThreadLocalRandom.current().nextDouble() * SIDE_LENGTH);
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (MIDDLE >= distance) {
                circleCount++;
            }
        }

        return 4 * (circleCount / (double) iterationsCount);
    }

    public static double calculateMultiThread(int threadsCount, long iterationsCount) {
        if (iterationsCount < 1) {
            throw new IllegalArgumentException(INVALID_ITERATIONS_COUNT_INPUT_EXCEPTION_MESSAGE);
        }

        if (threadsCount < 1) {
            throw new IllegalArgumentException(INVALID_THREADS_COUNT_INPUT_EXCEPTION_MESSAGE);
        }

        AtomicLong circleCount = new AtomicLong(0);

        List<Thread> threads = IntStream.range(0, threadsCount)
            .mapToObj(i -> new Thread(() -> circleCount.addAndGet(LongStream.range(
                    i * iterationsCount / threadsCount,
                    (i + 1) * iterationsCount / threadsCount
                )
                .filter(l -> {
                    double dx = (MIDDLE - ThreadLocalRandom.current().nextDouble() * SIDE_LENGTH);
                    double dy = (MIDDLE - ThreadLocalRandom.current().nextDouble() * SIDE_LENGTH);
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    return MIDDLE >= distance;
                }).count())))
            .toList();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        });

        return 4 * (circleCount.get() / (double) iterationsCount);
    }
}
