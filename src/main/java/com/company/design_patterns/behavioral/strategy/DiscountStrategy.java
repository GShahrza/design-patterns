package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;

/** Strategy: bir-birini əvəz edə bilən alqoritmlər ailəsinin ümumi interfeysi. */
@FunctionalInterface
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal total);
}
