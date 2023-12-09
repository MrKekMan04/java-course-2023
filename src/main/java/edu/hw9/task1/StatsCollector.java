package edu.hw9.task1;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final ConcurrentHashMap<String, Metric> statistics = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public StatsCollector(int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException("Parameter threads must be more then 0");
        }
    }

    public void push(String name, double[] values) {
        CompletableFuture.runAsync(() -> {
                Metric metric = calculateMetric(name, values);

                lock.writeLock().lock();

                try {
                    if (statistics.containsKey(metric.name())) {
                        statistics.put(metric.name(), statistics.get(metric.name()).add(metric));
                    } else {
                        statistics.put(metric.name(), metric);
                    }
                } finally {
                    lock.writeLock().unlock();
                }
            }, pool)
            .join();
    }

    public Collection<Metric> stats() {
        lock.readLock().lock();

        try {
            return statistics.values();
        } finally {
            lock.readLock().unlock();
        }
    }

    private Metric calculateMetric(String name, double[] values) {
        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (double value : values) {
            sum += value;
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        return new Metric(name, min, max, sum, sum / values.length, values.length);
    }
}
