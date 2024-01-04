package edu.hw8.task3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Link {
    private final AtomicInteger currentIndex = new AtomicInteger(-1);
    private final List<Character> symbols;
    private final Link next;

    public Link(List<Character> symbols, Link link) {
        this.symbols = symbols;
        this.next = link;
    }

    public Link(List<Character> symbols, Link link, int startIndex) {
        this(symbols, link);
        this.currentIndex.set(startIndex);
    }

    public void next() {
        currentIndex.incrementAndGet();

        if (currentIndex.get() == symbols.size()) {
            currentIndex.set(0);

            if (next != null) {
                next.next();
            }
        }
    }

    public String getKey() {
        StringBuilder key = new StringBuilder();

        if (next != null) {
            key.append(next.getKey());
        }

        if (currentIndex.get() != -1) {
            key.append(symbols.get(currentIndex.get()));
        }

        return key.toString();
    }
}
