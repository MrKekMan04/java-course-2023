package edu.hw4;

import java.util.List;

public final class Task12 {
    private Task12() {
    }

    public static Integer countAnimalsWhoseWeightMoreThenHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }
}
