package com.company.design_patterns.behavioral.observer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Subject (Publisher): abunəçilərin siyahısını saxlayır və vəziyyət dəyişəndə hamısına bildirir.
 * Subject observer-lərin konkret sinfini bilmir — yalnız interfeysi (loose coupling).
 */
public class StockExchange {

    private final Map<String, BigDecimal> prices = new HashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public void subscribe(StockObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(StockObserver observer) {
        observers.remove(observer);
    }

    public void updatePrice(String symbol, BigDecimal newPrice) {
        BigDecimal oldPrice = prices.put(symbol, newPrice);
        if (oldPrice != null && oldPrice.compareTo(newPrice) == 0) {
            return;
        }
        // Kopya üzərində dövr: observer bildiriş zamanı abunəlikdən çıxsa belə, xəta olmasın.
        for (StockObserver observer : List.copyOf(observers)) {
            observer.onPriceChanged(symbol, oldPrice, newPrice);
        }
    }
}
