package edu.hw4;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public final class AnimalValidator {
    private AnimalValidator() {
    }

    public static List<ValidationError> validateAnimal(Animal animal) {
        final List<BadField> badFields = new ArrayList<>();

        if (animal.name() == null) {
            badFields.add(BadField.NAME);
        }

        if (animal.age() < 0 || animal.age() > getMaxAge(animal)) {
            badFields.add(BadField.AGE);
        }

        if (animal.height() <= 0) {
            badFields.add(BadField.HEIGHT);
        }

        if (animal.weight() <= 0) {
            badFields.add(BadField.WEIGHT);
        }

        return badFields.stream()
            .map(badField -> new ValidationError(badField.getName()))
            .toList();
    }

    private static int getMaxAge(Animal animal) {
        return switch (animal.type()) {
            case CAT, DOG -> 18;
            case BIRD -> 20;
            case FISH -> 12;
            case SPIDER -> 5;
        };
    }

    private enum BadField {
        NAME("name"),
        AGE("age"),
        HEIGHT("height"),
        WEIGHT("weight");

        private final String parameterName;

        BadField(String parameterName) {
            this.parameterName = parameterName;
        }

        public String getName() {
            return this.parameterName;
        }
    }
}
