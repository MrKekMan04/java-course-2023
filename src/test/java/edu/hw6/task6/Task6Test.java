package edu.hw6.task6;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Arrays;
import static edu.hw6.task6.Task6.checkPort;
import static edu.hw6.task6.Task6.getPopularPortsInfo;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    public void assertThatCheckPortWorksRight() {
        int port = 49123;

        assertTrue(checkPort(port));

        try (ServerSocket ignored = new ServerSocket(port)) {
            assertFalse(checkPort(port));
        } catch (IOException ignored) {
        }

        try (DatagramSocket ignored = new DatagramSocket(port)) {
            assertFalse(checkPort(port));
        } catch (IOException ignored) {
        }
    }

    @Test
    public void assertThatIncorrectInputThrowException() {
        assertThrows(IllegalArgumentException.class, () -> checkPort(Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> checkPort(-1));
    }

    @Test
    public void assertThatGetPopularPortsInfoWorksRight() {
        String portsInfo = getPopularPortsInfo();

        assertEquals(9, Arrays.stream(portsInfo.split("\n")).toArray().length);
        assertTrue(portsInfo.contains("Protocol") && portsInfo.contains("Port") && portsInfo.contains("Service"));
        assertTrue(portsInfo.contains("UDP") && portsInfo.contains("1900"));
    }
}
