package edu.hw9.task1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatsCollectorTest {
    @Test
    public void assertThatCollectorWorksRight() {
        StatsCollector collector = new StatsCollector(2);

        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            CompletableFuture.allOf(Stream.generate(() -> CompletableFuture.runAsync(() -> {
                        collector.push("1", new double[] {1, 1});
                        collector.push("2", new double[] {2, -1, -1});
                    }, pool))
                    .limit(100)
                    .toArray(CompletableFuture[]::new))
                .join();
        }

        Metric[] stats = collector.stats().toArray(Metric[]::new);

        assertEquals(new Metric("1", 1, 1, 200, 1, 200), stats[0]);
        assertEquals(new Metric("2", -1, 2, 0, 0, 300), stats[1]);
    }

    @Test
    public void assertThatIncorrectInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new StatsCollector(0));
    }
}
