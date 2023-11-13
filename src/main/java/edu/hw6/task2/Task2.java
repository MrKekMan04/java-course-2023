package edu.hw6.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task2 {
    private static final Pattern FILE_NAME_AND_EXTENSION_PATTERN =
        Pattern.compile("^(?<name>.+)\\.(?<extension>\\w+)?$");

    private Task2() {
    }

    public static boolean cloneFile(Path path) {
        if (path.toFile().exists()) {
            try {
                Matcher nameAndExtensionMatcher =
                    FILE_NAME_AND_EXTENSION_PATTERN.matcher(path.getFileName().toString());

                if (nameAndExtensionMatcher.matches()) {
                    String filename = nameAndExtensionMatcher.group("name");
                    String extension = nameAndExtensionMatcher.group("extension");

                    if (extension == null) {
                        extension = "";
                    }

                    Pattern cloneFilesPattern = comlilePattern(filename, extension);
                    Path parent = path.getParent();
                    int maxIndex = -1;

                    try (var dirStream = Files.newDirectoryStream(parent)) {
                        for (Path siblingPath : dirStream) {
                            Matcher matcher = cloneFilesPattern.matcher(siblingPath.getFileName().toString());

                            if (matcher.matches()) {
                                maxIndex = Math.max(maxIndex, parseIndex(matcher.group("index")));
                            }
                        }
                    }

                    return copyContent(
                        path.toFile(),
                        parent.resolve(getFileCloneNameByIndex(maxIndex, filename, extension)).toFile()
                    );
                }
            } catch (IOException ignored) {
            }
        }

        return false;
    }

    private static Pattern comlilePattern(String filename, String extension) {
        return Pattern.compile("^%s — копия( \\((?<index>\\d+)\\))?\\.%s$".formatted(filename, extension));
    }

    private static int parseIndex(String index) {
        if (index != null) {
            try {
                return Integer.parseInt(index);
            } catch (Exception ignored) {
                return -1;
            }
        }

        return 0;
    }

    private static String getFileCloneNameByIndex(int index, String filename, String extension) {
        return index == -1
            ? ("%s — копия" + (extension.isEmpty() ? "" : ".") + "%s").formatted(filename, extension)
            : ("%s — копия (%d)" + (extension.isEmpty() ? "" : ".") + "%s").formatted(filename, index + 1, extension);
    }

    private static boolean copyContent(File from, File to) {
        try {
            if (!to.exists()) {
                to.createNewFile();
            }

            try (FileInputStream fileInputStream = new FileInputStream(from);
                 FileOutputStream fileOutputStream = new FileOutputStream(to);
                 FileChannel in = fileInputStream.getChannel();
                 FileChannel out = fileOutputStream.getChannel()) {
                out.transferFrom(in, 0, in.size());
            }

            return true;
        } catch (IOException ignored) {
        }

        return false;
    }
}
