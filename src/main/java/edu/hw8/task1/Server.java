package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Server {
    private static final int SERVER_PORT = 1704;
    private static final int THREAD_COUNT = 4;
    private final ConcurrentHashMap<String, String> answers = new ConcurrentHashMap<>();

    public Server() {
        answers.put("личности", "Не переходи на личности там, где их нет");
        answers.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        answers.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        answers.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public void run() throws IOException {
        try (ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
             ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            CompletableFuture.allOf(Stream.generate(() -> CompletableFuture.runAsync(handle(serverSocket), threadPool))
                    .limit(THREAD_COUNT)
                    .toArray(CompletableFuture[]::new))
                .join();
        }
    }

    private Runnable handle(ServerSocket serverSocket) {
        return () -> {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

                    writer.println(answers.getOrDefault(reader.readLine(), "Unknown answer"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
