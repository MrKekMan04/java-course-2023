package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class Task18 {
    private Task18() {
    }

    @SafeVarargs
    public static Animal getHardestFish(List<Animal>... listsAnimals) {
        return Arrays.stream(listsAnimals)
            .map(animals -> animals.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight)))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}
