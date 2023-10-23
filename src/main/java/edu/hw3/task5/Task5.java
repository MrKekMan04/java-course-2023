package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Task5 {
    private Task5() {
    }

    public static Collection<Person> parseContacts(Collection<String> contacts, String sortBy) {
        boolean sortByAscending = switch (sortBy) {
            case "ASC" -> true;
            case "DESC" -> false;
            default -> throw new IllegalArgumentException("Parameter `sortBy` must be only `ASC` or `DESC`!");
        };

        if (contacts == null || contacts.isEmpty()) {
            return List.of();
        }

        ArrayList<Person> result = contacts.stream().map(contact -> {
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

                return new Person(null, contact);
            })
            .collect(Collectors.toCollection(ArrayList::new));

        return result.stream()
            .sorted(sortByAscending ? Comparator.naturalOrder() : Comparator.reverseOrder())
            .collect(Collectors.toList());
    }
}
