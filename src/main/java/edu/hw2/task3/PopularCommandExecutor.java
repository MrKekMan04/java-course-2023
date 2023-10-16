package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

     private void tryExecute(String command) {
        var attempts = 0;
        var cause = (Throwable) null;

        while (attempts != maxAttempts) {
            try {
                manager.getConnection().execute(command);
                break;
            } catch (ConnectionException e) {
                attempts++;
                cause = e.getCause();
            }
        }

        if (cause != null) {
            LOGGER.error(cause);
        }
    }
}
