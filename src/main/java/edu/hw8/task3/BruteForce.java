package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;

public class BruteForce {
    private static final int MAX_KEY_SIZE = 4;
    private final List<Character> symbols;
    private final Link link;
    private long iterationsCount;
    private long currentIteration;

    public BruteForce() {
        symbols = generateSymbols();

        for (int i = 0; i < MAX_KEY_SIZE; i++) {
            iterationsCount += (long) Math.pow(symbols.size(), i + 1);
        }

        link = initLinks(symbols, MAX_KEY_SIZE);
    }

    public BruteForce(int currentThreadIndex, int threadsCount) {
        symbols = generateSymbols();

        for (int i = 0; i < MAX_KEY_SIZE; i++) {
            iterationsCount += (long) Math.pow(symbols.size(), i + 1);
        }

        link = initLinks(
            symbols,
            MAX_KEY_SIZE,
            (long) Math.ceil((double) iterationsCount / threadsCount) * currentThreadIndex
        );
    }

    public String nextPassword() {
        if (currentIteration++ < iterationsCount) {
            link.next();

            return link.getKey();
        }

        return null;
    }

    private Link initLinks(List<Character> symbols, int level) {
        return level > 0 ? new Link(symbols, initLinks(symbols, level - 1)) : null;
    }

    private Link initLinks(List<Character> symbols, int level, long startPosition) {
        return level > 0
            ? new Link(
            symbols,
            initLinks(symbols, level - 1, Math.max(startPosition / symbols.size() - 1, -1)),
            (int) (startPosition % symbols.size())
        )
            : null;
    }

    private List<Character> generateSymbols() {
        List<Character> result = new ArrayList<>();

        for (char c = '0'; c <= '9'; c++) {
            result.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            result.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            result.add(c);
        }

        return result;
    }
}
