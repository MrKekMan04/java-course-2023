package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class Task8 {
    private Task8() {
    }

    public static Optional<Animal> getHardestAnimalWhoseHeightLessThen(List<Animal> animals, int sm) {
        return animals.stream()
            .filter(animal -> animal.height() < sm)
            .max(Comparator.comparingInt(Animal::weight));
    }
}
