package com.company.design_patterns.behavioral.observer;

import java.math.BigDecimal;

/** Observer: subject-də dəyişiklik olanda xəbərdar edilir. */
@FunctionalInterface
public interface StockObserver {
    void onPriceChanged(String symbol, BigDecimal oldPrice, BigDecimal newPrice);
}
