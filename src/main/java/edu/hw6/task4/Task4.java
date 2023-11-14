package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class Task4 {
    private Task4() {
    }

    public static boolean write(Path path) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
            new BufferedOutputStream(new CheckedOutputStream(Files.newOutputStream(path), new CRC32())),
            StandardCharsets.UTF_8
        ))) {
            writer.write("Programming is learned by writing programs. ― Brian Kernighan");

            return true;
        } catch (IOException ignored) {
        }

        return false;
    }
}
