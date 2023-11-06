package edu.hw3.task3;

import java.util.HashMap;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static <K> Map<K, Integer> freqDict(Iterable<K> collection) {
        if (collection == null) {
            return null;
        }

        Map<K, Integer> frequency = new HashMap<>();

        collection.forEach(item -> frequency.put(item, frequency.getOrDefault(item, 0) + 1));

        return frequency;
    }
}
