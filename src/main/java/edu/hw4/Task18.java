package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public final class Task18 {
    private Task18() {
    }

    @SafeVarargs
    public static Animal getHardestFish(List<Animal>... listsAnimals) {
        return Arrays.stream(listsAnimals)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}
