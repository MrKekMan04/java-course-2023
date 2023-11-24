package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class CounterTest {
    private static final int ITERATIONS_COUNT = 10_000;
    private static final int THREADS_COUNT = 4;

    @Test
    public void assertThatSingleThreadCounterWorksRight() {
        Counter counter = new Counter();

        for (int i = 0; i < ITERATIONS_COUNT; i++) {
            counter.increment();
        }

        assertEquals(ITERATIONS_COUNT, counter.getValue());
    }

    @Test
    public void assertThatParallelCounterWorksRight() {
        Counter counter = new Counter();

        List<Thread> threads = IntStream.range(0, THREADS_COUNT)
            .mapToObj(i -> new Thread(() -> {
                for (int j = 0; j < ITERATIONS_COUNT; j++) {
                    counter.increment();
                }
            })).toList();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        });

        assertEquals(THREADS_COUNT * ITERATIONS_COUNT, counter.getValue());
    }
}
