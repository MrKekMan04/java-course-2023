package edu.hw4;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Task19 {
    private Task19() {
    }

    public static Map<String, Set<ValidationError>> getNonValidAnimals(List<Animal> animals) {
        return animals.stream()
            .map(animal -> new AbstractMap.SimpleEntry<>(animal, AnimalValidator.validateAnimal(animal)))
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(
                entry -> entry.getKey().name() != null ? entry.getKey().name() : "null",
                entry -> new HashSet<>(entry.getValue())
            ));
    }
}
