package edu.hw9.task1;

public record Metric(String name, double min, double max, double sum, double average, long count) {
    public Metric add(Metric metric) {
        if (!name.equals(metric.name)) {
            throw new IllegalArgumentException("Parameter name does not match");
        }

        double totalSum = sum + metric.sum;
        long totalCount = count + metric.count;

        return new Metric(
            name,
            Math.min(min, metric.min),
            Math.max(max, metric.max),
            totalSum,
            totalSum / totalCount,
            totalCount
        );
    }
}
