package edu.hw7.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseImpl implements PersonDatabase {
    private final Map<Integer, Person> database = new HashMap<>();
    private final ReadWriteLock locker = new ReentrantReadWriteLock();
    private int size = 0;

    @Override
    public void add(Person person) {
        locker.writeLock().lock();

        try {
            if (!database.containsKey(person.id())) {
                size++;
            }

            database.put(person.id(), person);
        } finally {
            locker.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        locker.writeLock().lock();

        try {
            if (database.containsKey(id)) {
                size--;
                database.remove(id);
            }
        } finally {
            locker.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        locker.readLock().lock();

        try {
            return database.values()
                .stream()
                .filter(person -> !person.isNullableAttributes() && person.name().equals(name))
                .toList();
        } finally {
            locker.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        locker.readLock().lock();

        try {
            return database.values()
                .stream()
                .filter(person -> !person.isNullableAttributes() && person.address().equals(address))
                .toList();
        } finally {
            locker.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        locker.readLock().lock();

        try {
            return database.values()
                .stream()
                .filter(person -> !person.isNullableAttributes() && person.phoneNumber().equals(phone))
                .toList();
        } finally {
            locker.readLock().unlock();
        }
    }

    @Override
    public int size() {
        locker.writeLock().lock();
        try {
            return size;
        } finally {
            locker.writeLock().unlock();
        }
    }
}
