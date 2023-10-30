package edu.hw4;

import java.util.List;

public final class Task11 {
    private static final int MAX_HEIGHT = 100;

    private Task11() {
    }

    public static List<Animal> getAnimalsWhoseCanBitesAndHeightLessOrEquals100(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() <= MAX_HEIGHT)
            .toList();
    }
}
