package edu.hw7.task3;

public record Person(int id, String name, String address, String phoneNumber) {
    public boolean isNullableAttributes() {
        return name == null || address == null || phoneNumber == null;
    }
}
