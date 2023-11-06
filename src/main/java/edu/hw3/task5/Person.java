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
        return this.surname.isEmpty()
            ? o.surname.isEmpty() ? this.name.compareTo(o.name) : this.name.compareTo(o.surname)
            : o.surname.isEmpty() ? this.surname.compareTo(o.name) : this.surname.compareTo(o.surname);
    }

    @Override
    public String toString() {
        return name + (!surname.isEmpty() ? " " + surname : "");
    }
}
