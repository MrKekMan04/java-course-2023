package edu.hw3.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {
    @Test
    public void assertThatAllOperationWorksRight() {
        StockMarketImpl market = new StockMarketImpl();
        Stock stock = new Stock("Stock name", 10);

        market.add(stock);
        market.add(new Stock("Second", 423));

        assertEquals(market.size(), 2);

        market.remove(stock);

        assertEquals(market.size(), 1);
        assertEquals(market.mostValuableStock().getCost(), 423);
        assertEquals(market.size(), 1);
    }

    @Test
    public void assertThatNullStockThrowException() {
        StockMarketImpl market = new StockMarketImpl();

        assertThrows(NullPointerException.class, () -> market.add(null));
        assertThrows(NullPointerException.class, () -> market.remove(null));
    }
}
