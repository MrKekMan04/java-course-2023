package edu.hw8.task1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    @Test
    public void assertThatServerReturnsRightAnswer() {
        Server server = new Server();
        Client client = new Client();

        Thread serverThread = new Thread(() -> {
            try {
                server.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        serverThread.start();

        assertEquals("Не переходи на личности там, где их нет", client.sendMessage("личности"));
        assertEquals("Unknown answer", client.sendMessage("unknown"));

        serverThread.interrupt();
    }
}
