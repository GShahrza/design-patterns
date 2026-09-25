package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Context: strategiyanı saxlayır və işi ona həvalə edir. Strategiya runtime-da dəyişdirilə bilər. */
public class ShoppingCart {

    private final List<BigDecimal> items = new ArrayList<>();
    private DiscountStrategy discount = new NoDiscount();

    public void add(BigDecimal price) {
        items.add(price);
    }

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount;
    }

    public BigDecimal subtotal() {
        return items.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal total() {
        return discount.apply(subtotal());
    }
}
