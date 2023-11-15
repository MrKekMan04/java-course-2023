package edu.hw4;

import java.util.List;

public class TestBase {
    protected static final List<Animal> ANIMALS = List.of(
        new Animal("Harry", Animal.Type.SPIDER, Animal.Sex.M, 3, 10, 3, true),
        new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 7, 38, 10, false),
        new Animal("Garald", Animal.Type.CAT, Animal.Sex.M, 5, 32, 7, false),
        new Animal("Milka", Animal.Type.CAT, Animal.Sex.F, 4, 33, 6, false)
    );
}
