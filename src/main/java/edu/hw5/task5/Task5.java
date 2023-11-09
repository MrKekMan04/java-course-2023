package edu.hw5.task5;

import java.util.regex.Pattern;

public final class Task5 {
    private static final Pattern PRIVATE_CAR_PATTERN =
        Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    private Task5() {
    }

    public static boolean isNumberPlateValid(String number) {
        return number != null && PRIVATE_CAR_PATTERN.matcher(number).find();
    }
}
