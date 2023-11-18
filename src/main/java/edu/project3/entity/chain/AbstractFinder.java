package edu.project3.entity.chain;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractFinder {
    private final AbstractFinder next;

    public AbstractFinder(AbstractFinder next) {
        this.next = next;
    }

    public List<Stream<String>> getLogsLines(String path) {
        if (next != null) {
            return next.getLogsLines(path);
        }

        return List.of();
    }
}
