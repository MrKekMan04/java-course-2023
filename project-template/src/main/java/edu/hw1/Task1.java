package edu.hw1;

public final class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        var splitTime = time.split(":");

        var minutes = Integer.parseInt(splitTime[0]);
        var seconds = Integer.parseInt(splitTime[1]);

        if (seconds < 0 || minutes < 0 || seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }

        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
