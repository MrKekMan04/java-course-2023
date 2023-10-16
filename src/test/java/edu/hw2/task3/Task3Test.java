package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    @Test
    public void connectionManagersTest() {
        assertTrue(new FaultyConnectionManager().getConnection() instanceof FaultyConnection);
        assertTrue(IntStream.range(0, 10_000)
            .mapToObj(i -> new DefaultConnectionManager())
            .anyMatch(manager -> manager.getConnection() instanceof FaultyConnection));
    }

    @Test
    public void connectionsTest() {
        IntStream.range(0, 10_000)
            .mapToObj(i -> new StableConnection())
            .forEach(connection -> assertDoesNotThrow(() -> connection.execute("")));

        var someFaultyConnectionThrown = false;

        for (int i = 0; i < 10_000; i++) {
            try (var connection = new FaultyConnection()){
                connection.execute("");
            } catch (Exception ignored) {
                someFaultyConnectionThrown = true;
                break;
            }
        }

        assertTrue(someFaultyConnectionThrown);
    }
}
