package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiskMap extends HashMap<String, String> {
    private static final Pattern FORMAT_PATTERN = Pattern.compile("(?<key>.*):(?<value>.*)");
    private static final Logger LOGGER = LogManager.getLogger();
    private final File file;

    public DiskMap(Path path) {
        this(path, false);
    }

    public DiskMap(Path path, boolean preLoadData) {
        this.file = path.toFile();

        if (preLoadData) {
            loadData();
        }
    }

    public boolean saveData() {
        if (!(file.exists() && file.isFile())) {
            try {
                if (file.createNewFile()) {
                    return saveData();
                }
            } catch (IOException e) {
                LOGGER.error(e);
                return false;
            }
        }

        if (file.canWrite()) {
            try (var out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                forEach((key, value) -> {
                    try {
                        out.write(key + ":" + value + "\n");
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                });

                return true;
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        return false;
    }

    public boolean loadData() {
        if (!(file.exists() && file.isFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                LOGGER.error(e);
                return false;
            }
        }

        if (file.canRead()) {
            try (var in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                in.lines()
                    .forEach(line -> {
                        Matcher matcher = FORMAT_PATTERN.matcher(line);

                        if (matcher.matches()) {
                            put(matcher.group("key"), matcher.group("value"));
                        }
                    });

                return true;
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        return false;
    }
}
