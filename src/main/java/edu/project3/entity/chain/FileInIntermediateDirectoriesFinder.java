package edu.project3.entity.chain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileInIntermediateDirectoriesFinder extends AbstractFinder {
    private static final Pattern FILE_IN_INTERMEDIATE_DIRECTORIES_PATTERN =
        Pattern.compile("(?<baseDirectory>(\\w+(/)?)+)\\*{2}/(?<fileName>.+)");

    public FileInIntermediateDirectoriesFinder(AbstractFinder next) {
        super(next);
    }

    @Override
    public List<Stream<String>> getLogsLines(String path) {
        try {
            Matcher fileInIntermediateDirectoriesMatcher = FILE_IN_INTERMEDIATE_DIRECTORIES_PATTERN.matcher(path);

            if (fileInIntermediateDirectoriesMatcher.matches()) {
                ArrayList<Stream<String>> matchedFiles = new ArrayList<>();
                String baseDirectory = fileInIntermediateDirectoriesMatcher.group("baseDirectory");
                String fileName = fileInIntermediateDirectoriesMatcher.group("fileName");

                try (Stream<Path> files = Files.walk(Path.of(baseDirectory))) {
                    files.filter(filePath -> filePath.getFileName().toString().equals(fileName))
                        .forEach(filePath -> {
                            try {
                                matchedFiles.add(Files.lines(filePath));
                            } catch (IOException ignored) {
                            }
                        });
                }

                return matchedFiles;
            }
        } catch (Exception ignored) {
        }

        return super.getLogsLines(path);
    }
}
