package edu.project3.entity.chain;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileFinder extends AbstractFinder {
    public FileFinder(AbstractFinder next) {
        super(next);
    }

    @Override
    public List<Stream<String>> getLogsLines(String path) {
        try {
            Path filePath = Path.of(path);

            if (filePath.toFile().exists()) {
                return List.of(Files.lines(filePath));
            }
        } catch (Exception ignored) {
        }

        return super.getLogsLines(path);
    }
}
