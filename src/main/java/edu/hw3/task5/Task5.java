package edu.hw3.task5;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Task5 {
    private Task5() {
    }

    public static Collection<Person> parseContacts(Collection<String> contacts, String sortBy) {
        Comparator<Person> sortOrder = getSortOrder(sortBy);

        return contacts == null || contacts.isEmpty() ? List.of() : contacts.stream()
            .map(Task5::getPersonFromContact)
            .sorted(sortOrder)
            .toList();
    }

    @NotNull
    private static Person getPersonFromContact(String contact) {
        if (contact == null) {
            throw new NullPointerException("Person name can't be null!");
        }

        if (contact.contains(" ")) {
            String[] personData = contact.split(" ");

            if (personData.length != 2) {
                throw new IllegalArgumentException("Person data length must be 2!");
            }

            return new Person(personData[0], personData[1]);
        }

        return new Person(contact, "");
    }

    private static Comparator<Person> getSortOrder(String sortBy) {
        return switch (sortBy) {
            case "ASC" -> Comparator.naturalOrder();
            case "DESC" -> Comparator.reverseOrder();
            default -> throw new IllegalArgumentException("Parameter `sortBy` must be only `ASC` or `DESC`!");
        };
    }
}
