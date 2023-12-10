package edu.project3.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    private final List<T> repository;

    public Repository() {
        this.repository = new ArrayList<>();
    }

    public void add(T item) {
        repository.add(item);
    }

    public List<T> getAll() {
        return repository;
    }

    public int size() {
        return repository.size();
    }
}
