package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static final int SERVER_PORT = 1122;

    public String sendMessage(String message) {
        try (Socket server = new Socket(InetAddress.getLocalHost(), SERVER_PORT);
             PrintWriter writer = new PrintWriter(server.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()))) {

            writer.println(message);

            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
