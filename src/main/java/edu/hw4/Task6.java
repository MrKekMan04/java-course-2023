package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Task6 {
    private Task6() {
    }

    public static Map<Animal.Type, Animal> getHardestAnimalByType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type))
            .values().stream()
            .map(animalList -> animalList.stream()
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Animal::type, animal -> animal));
    }
}
