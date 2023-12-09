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
import java.util.function.Predicate;

public class DirectoryPredicateFinder extends RecursiveTask<List<Path>> {
    private final Path directory;
    private final List<Predicate<Path>> predicates;

    public DirectoryPredicateFinder(Path directory, List<Predicate<Path>> predicates) {
        if (directory == null || predicates == null) {
            throw new NullPointerException();
        }

        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Parameter directory must be directory");
        }

        this.directory = directory;
        this.predicates = predicates;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        ConcurrentLinkedQueue<ForkJoinTask<List<Path>>> tasks = new ConcurrentLinkedQueue<>();

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            paths.forEach(path -> {
                if (Files.isDirectory(path)) {
                    tasks.add(new DirectoryPredicateFinder(path, predicates).fork());
                } else {
                    if (isFileSatisfyPredicates(path)) {
                        result.add(path);
                    }
                }
            });
        } catch (IOException ignored) {
        }

        tasks.forEach(task -> result.addAll(task.join()));

        return result;
    }

    private boolean isFileSatisfyPredicates(Path path) {
        for (Predicate<Path> predicate : predicates) {
            if (!predicate.test(path)) {
                return false;
            }
        }

        return true;
    }
}
