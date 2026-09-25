package com.company.design_patterns.behavioral.observer;

import java.math.BigDecimal;

/** Concrete observer: qiymət müəyyən həddi keçəndə xəbərdarlıq edir. */
public class PriceAlert implements StockObserver {

    private final String symbol;
    private final BigDecimal threshold;

    public PriceAlert(String symbol, BigDecimal threshold) {
        this.symbol = symbol;
        this.threshold = threshold;
    }

    @Override
    public void onPriceChanged(String symbol, BigDecimal oldPrice, BigDecimal newPrice) {
        if (this.symbol.equals(symbol) && newPrice.compareTo(threshold) >= 0) {
            System.out.println("  [ALERT] " + symbol + " reached " + newPrice + " (threshold " + threshold + ")");
        }
    }
}
