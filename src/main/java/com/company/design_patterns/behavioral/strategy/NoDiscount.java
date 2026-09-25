package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;

public class NoDiscount implements DiscountStrategy {
    @Override
    public BigDecimal apply(BigDecimal total) {
        return total;
    }
}
