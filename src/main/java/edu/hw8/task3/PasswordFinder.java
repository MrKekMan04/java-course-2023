package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class PasswordFinder {
    private PasswordFinder() {
    }

    private static final Map<String, String> DATABASE = Map.of(
        "c4ca4238a0b923820dcc509a6f75849b", "a.v.petrov",
        "1132c8bf0d0e2c72e42489f33857d51c", "v.v.belov",
        "e0c75c190ccb11909edbf9741d1ac9f0", "a.s.ivanov",
        "28f70f89a0694c09e5284d55b4b018d4", "k.p.maslov"
    );

    public static Map<String, String> bruteForceSingleThread() {
        HashMap<String, String> solvedPasswords = new HashMap<>();
        HashMap<String, String> copyDatabase = new HashMap<>(DATABASE);
        BruteForce force = new BruteForce();
        MD5Encoder encoder = new MD5Encoder();

        String password;

        while ((password = force.nextPassword()) != null && !copyDatabase.isEmpty()) {
            String encoded = encoder.encode(password);

            if (copyDatabase.containsKey(encoded)) {
                solvedPasswords.put(copyDatabase.get(encoded), password);
                copyDatabase.remove(encoded);
            }
        }

        return solvedPasswords;
    }

    public static Map<String, String> bruteForceMultiThread(int threadsCount) {
        if (threadsCount < 1) {
            throw new IllegalArgumentException("Threads count must be more then 0");
        }

        ConcurrentHashMap<String, String> solvedPasswords = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> copyDatabase = new ConcurrentHashMap<>(DATABASE);

        try (ExecutorService pool = Executors.newFixedThreadPool(threadsCount)) {
            CompletableFuture.allOf(IntStream.range(0, threadsCount)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        BruteForce force = new BruteForce(i, threadsCount);
                        MD5Encoder encoder = new MD5Encoder();
                        String password;

                        while ((password = force.nextPassword()) != null && !copyDatabase.isEmpty()) {
                            String encoded = encoder.encode(password);

                            if (copyDatabase.containsKey(encoded)) {
                                solvedPasswords.put(copyDatabase.get(encoded), password);
                                copyDatabase.remove(encoded);
                            }
                        }
                    }, pool))
                    .toArray(CompletableFuture[]::new))
                .join();
        }

        return solvedPasswords;
    }
}
