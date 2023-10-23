package edu.hw3.task6;

import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> market = new PriorityQueue<>();

    @Override
    public void add(@NotNull Stock stock) {
        market.add(stock);
    }

    @Override
    public void remove(@NotNull Stock stock) {
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
