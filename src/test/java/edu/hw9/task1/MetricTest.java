package edu.hw9.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetricTest {
    @Test
    public void assertThatGettersWorksRight() {
        String metricName = "test";
        double metricMin = 1;
        double metricMax = 2;
        double metricSum = 3;
        double metricAverage = 1.5;
        long metricCount = 2;

        Metric test = new Metric(metricName, metricMin, metricMax, metricSum, metricAverage, metricCount);

        assertEquals(metricName, test.name());
        assertEquals(metricMin, test.min());
        assertEquals(metricMax, test.max());
        assertEquals(metricSum, test.sum());
        assertEquals(metricAverage, test.average());
        assertEquals(metricCount, test.count());
    }

    @Test
    public void asserThatAddMetricWorksRight() {
        Metric test = new Metric("test", 1, 1, 1, 1, 1);

        String name = test.name();
        double min = test.min();
        double max = test.max();
        double sum = test.sum();
        double average = test.average();
        long count = test.count();

        test = test.add(test);

        assertEquals(name, test.name());
        assertEquals(min, test.min());
        assertEquals(max, test.max());
        assertEquals(2 * sum, test.sum());
        assertEquals(average, test.average());
        assertEquals(2 * count, test.count());
    }

    @Test
    public void assertThatAddAnotherMetricNameThrowsException() {
        Metric test = new Metric("test", 1, 1, 1, 1, 1);
        Metric notTest = new Metric("notTest", 1, 1, 1, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> test.add(notTest));
    }
}
