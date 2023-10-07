package edu.hw1;

public final class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (time != null && time.contains(":")) {
            var splitTime = time.split(":");

            if (splitTime.length == 2) {
                try {
                    var minutes = Integer.parseInt(splitTime[0]);
                    var seconds = Integer.parseInt(splitTime[1]);

                    if (seconds >= 0 && minutes >= 0 && seconds < SECONDS_IN_MINUTE) {
                        return minutes * SECONDS_IN_MINUTE + seconds;
                    }

                } catch (NumberFormatException ignored) {
                }
            }
        }

        return -1;
    }
}
