package edu.hw2.task3;

import java.util.concurrent.ThreadLocalRandom;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return ThreadLocalRandom.current().nextInt(0, 5) == 3 ? new FaultyConnection() : new StableConnection();
    }
}
