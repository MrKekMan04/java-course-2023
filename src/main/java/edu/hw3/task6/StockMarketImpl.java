package edu.hw3.task6;

import java.util.Objects;
import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> market = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        Objects.requireNonNull(stock);

        market.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        Objects.requireNonNull(stock);

        market.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return market.peek();
    }

    public int size() {
        return market.size();
    }
}
