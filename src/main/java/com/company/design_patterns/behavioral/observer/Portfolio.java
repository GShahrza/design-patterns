package com.company.design_patterns.behavioral.observer;

import java.math.BigDecimal;
import java.util.Map;

/** Concrete observer: səhmlərin ümumi dəyərini yeniləyir. */
public class Portfolio implements StockObserver {

    private final Map<String, Integer> shares;

    public Portfolio(Map<String, Integer> shares) {
        this.shares = shares;
    }

    @Override
    public void onPriceChanged(String symbol, BigDecimal oldPrice, BigDecimal newPrice) {
        Integer count = shares.get(symbol);
        if (count != null && oldPrice != null) {
            BigDecimal diff = newPrice.subtract(oldPrice).multiply(BigDecimal.valueOf(count));
            System.out.println("  [PORTFOLIO] " + symbol + " position changed by " + diff);
        }
    }
}
