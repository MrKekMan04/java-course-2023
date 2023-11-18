package edu.project3.entity.chain;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FilesInDirectoryFinder extends AbstractFinder {
    private static final Pattern ALL_FILES_IN_DIRECTORY_PATTERN = Pattern.compile("(?<directory>(\\w+(/)?)+)\\*");

    public FilesInDirectoryFinder(AbstractFinder next) {
        super(next);
    }

    @Override
    public List<Stream<String>> getLogsLines(String path) {
        try {
            Matcher allFilesInDirectoryMatcher = ALL_FILES_IN_DIRECTORY_PATTERN.matcher(path);

            if (allFilesInDirectoryMatcher.matches()) {
                ArrayList<Stream<String>> matchedFiles = new ArrayList<>();

                try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(Paths.get(allFilesInDirectoryMatcher.group("directory")))) {
                    for (Path filePath : stream) {
                        if (!Files.isDirectory(filePath)) {
                            matchedFiles.add(Files.lines(filePath));
                        }
                    }
                }

                return matchedFiles;
            }
        } catch (Exception ignored) {
        }

        return super.getLogsLines(path);
    }
}
