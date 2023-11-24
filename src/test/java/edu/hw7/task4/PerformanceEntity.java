package edu.hw7.task4;

public record PerformanceEntity<T>(T returnedValue, Long executionTimeNanos) {
}
