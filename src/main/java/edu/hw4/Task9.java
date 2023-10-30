package edu.hw4;

import java.util.List;

public final class Task9 {
    private Task9() {
    }

    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .reduce(0, (counter, animal) -> counter + animal.paws(), Integer::sum);
    }
}
