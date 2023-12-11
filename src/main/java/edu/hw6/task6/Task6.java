package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MultipleStringLiterals")
public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Map<Integer, List<String>> POPULAR_PORTS = Map.of(
        21, List.of("SSH (Secure Shell)", "TCP"),
        80, List.of("HTTP (HyperText Transfer Protocol)", "TCP/UDP"),
        135, List.of("EPMAP", "TCP/UDP"),
        443, List.of("HTTPS (HyperText Transfer Protocol Secure)", "TCP/UDP"),
        1521, List.of("Oracle Database", "TCP"),
        1900, List.of("Simple Service Discovery Protocol (SSDP)", "UDP"),
        3306, List.of("MySQL database server", "TCP/UDP"),
        27017, List.of("Mongo DB Database", "TCP")
    );

    private Task6() {
    }

    public static boolean checkPort(int id) {
        final int maxPort = 49151;

        if (id < 0 || id > maxPort) {
            throw new IllegalArgumentException("Parameter `id` must be from 0 to 49151");
        }

        try (ServerSocket ignored = new ServerSocket(id);
             DatagramSocket ignored1 = new DatagramSocket(id)) {
            return true;
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        }
    }

    public static String getPopularPortsInfo() {
        StringBuilder builder = new StringBuilder();

        builder.append("Protocol\tPort\tService\n");

        for (Integer port : POPULAR_PORTS.keySet()) {
            builder.append("%-9s\t%-5d\t%s\n".formatted(POPULAR_PORTS.get(port).get(1), port, checkPort(port)
                ? ""
                : POPULAR_PORTS.get(port).get(0)));
        }

        return builder.toString();
    }
}
