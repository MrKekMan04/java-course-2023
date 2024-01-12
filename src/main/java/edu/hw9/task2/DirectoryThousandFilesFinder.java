package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class DirectoryThousandFilesFinder extends RecursiveTask<List<Path>> {
    private static final int MIN_FILES = 1_000;
    private final Path directory;

    public DirectoryThousandFilesFinder(Path directory) {
        if (directory == null) {
            throw new NullPointerException();
        }

        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Parameter directory must be directory");
        }

        this.directory = directory;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        ConcurrentLinkedQueue<ForkJoinTask<List<Path>>> tasks = new ConcurrentLinkedQueue<>();

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            AtomicInteger filesCount = new AtomicInteger();

            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    tasks.add(new DirectoryThousandFilesFinder(path).fork());
                } else {
                    filesCount.getAndIncrement();
                }
            });

            if (filesCount.get() > MIN_FILES) {
                result.add(directory);
            }
        } catch (IOException ignored) {
        }

        tasks.forEach(task -> result.addAll(task.join()));

        return result;
    }
}
