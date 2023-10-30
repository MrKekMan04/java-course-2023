package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Task17 {
    private Task17() {
    }

    public static Boolean areSpidersBitesMoreThenDogs(List<Animal> animals) {
        Map<Animal.Type, Long> bitesSpidersAndDogs = animals.stream()
            .filter(animal -> (animal.type() == Animal.Type.SPIDER || animal.type() == Animal.Type.DOG)
                && animal.bites())
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));

        return bitesSpidersAndDogs.size() == 2
            && bitesSpidersAndDogs.get(Animal.Type.SPIDER) > bitesSpidersAndDogs.get(Animal.Type.DOG);
    }
}
