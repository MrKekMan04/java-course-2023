package edu.hw4;

import java.util.Comparator;
import java.util.List;

public final class Task2 {
    private Task2() {
    }

    public static List<Animal> sortAnimalsByWeightDesc(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .toList();
    }
}
