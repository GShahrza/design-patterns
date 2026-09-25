package com.company.design_patterns.behavioral.observer;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();

        StockObserver logger = (symbol, oldP, newP) ->
                System.out.println("  [LOG] " + symbol + ": " + oldP + " -> " + newP);
        exchange.subscribe(logger);
        exchange.subscribe(new PriceAlert("AAPL", new BigDecimal("200")));
        exchange.subscribe(new Portfolio(Map.of("AAPL", 10, "TSLA", 5)));

        System.out.println("AAPL 190");
        exchange.updatePrice("AAPL", new BigDecimal("190"));
        System.out.println("AAPL 205");
        exchange.updatePrice("AAPL", new BigDecimal("205"));

        exchange.unsubscribe(logger);
        System.out.println("TSLA 250 (logger unsubscribed)");
        exchange.updatePrice("TSLA", new BigDecimal("250"));
        exchange.updatePrice("TSLA", new BigDecimal("240"));
    }
}
