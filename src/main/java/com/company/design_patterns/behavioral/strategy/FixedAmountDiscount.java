package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;

public class FixedAmountDiscount implements DiscountStrategy {

    private final BigDecimal amount;
    private final BigDecimal minimumTotal;

    public FixedAmountDiscount(BigDecimal amount, BigDecimal minimumTotal) {
        this.amount = amount;
        this.minimumTotal = minimumTotal;
    }

    @Override
    public BigDecimal apply(BigDecimal total) {
        if (total.compareTo(minimumTotal) < 0) {
            return total;
        }
        return total.subtract(amount).max(BigDecimal.ZERO);
    }
}
