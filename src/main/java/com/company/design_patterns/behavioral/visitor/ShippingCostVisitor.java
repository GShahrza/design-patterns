package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Əməliyyat #2: çatdırılma qiyməti. Item siniflərinə heç bir dəyişiklik lazım olmadı. */
public class ShippingCostVisitor implements ItemVisitor<BigDecimal> {

    @Override
    public BigDecimal visitBook(Book book) {
        return new BigDecimal("2.00");
    }

    @Override
    public BigDecimal visitElectronics(Electronics electronics) {
        return BigDecimal.valueOf(5 + Math.ceil(electronics.weightKg()) * 1.5).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal visitFood(Food food) {
        return food.perishable() ? new BigDecimal("8.00") : new BigDecimal("3.00");
    }
}
