package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private final double cost;

    public Stock(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return -Double.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return "Stock{"
            + "name='" + name + '\''
            + ", cost=" + cost
            + '}';
    }
}
