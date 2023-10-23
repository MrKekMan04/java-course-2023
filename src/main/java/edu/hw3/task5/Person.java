package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        int compareResult = this.surname.compareTo(o.surname);

        return compareResult != 0 ? compareResult : this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return (name != null ? name + " " : "") + surname;
    }
}
