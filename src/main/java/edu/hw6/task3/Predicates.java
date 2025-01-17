package edu.hw6.task3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Predicates {
    private static final Logger LOGGER = LogManager.getLogger();

    private Predicates() {
    }

    public static boolean largerThan(Path entry, int bytes) {
        try {
            return Files.size(entry) > bytes;
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        }
    }

    public static boolean magicNumber(Path entry, int... startIdentifiers) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(entry.toFile()))) {
            byte[] bytes = in.readNBytes(startIdentifiers.length);

            for (int i = 0; i < bytes.length; i++) {
                if ((byte) startIdentifiers[i] != bytes[i]) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        }
    }

    public static boolean globMatches(Path entry, String glob) {
        return entry.getFileSystem()
            .getPathMatcher("glob:" + glob)
            .matches(entry.getFileName());
    }

    public static boolean regexContains(Path entry, String regex) {
        return Pattern.compile("^.*" + regex + ".*$")
            .matcher(entry.getFileName().toString())
            .matches();
    }
}
