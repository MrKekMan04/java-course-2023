package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> collection;
    private int pointer;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        pointer = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return pointer > 0;
    }

    @Override
    public T next() {
        return collection.get(pointer--);
    }
}
