package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscount implements DiscountStrategy {

    private final int percent;

    public PercentageDiscount(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be 0..100");
        }
        this.percent = percent;
    }

    @Override
    public BigDecimal apply(BigDecimal total) {
        BigDecimal factor = BigDecimal.valueOf(100 - percent).divide(BigDecimal.valueOf(100));
        return total.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
