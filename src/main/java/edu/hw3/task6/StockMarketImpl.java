package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> market = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        if (stock == null)  {
            throw new NullPointerException("Stock can't be null!");
        }

        market.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null)  {
            throw new NullPointerException("Stock can't be null!");
        }

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
