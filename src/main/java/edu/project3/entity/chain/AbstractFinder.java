package edu.project3.entity.chain;

import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractFinder {
    protected static final Logger LOGGER = LogManager.getLogger();
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
