package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Əməliyyat #1: vergi hesablanması (hər məhsul növü üçün fərqli dərəcə). */
public class TaxVisitor implements ItemVisitor<BigDecimal> {

    @Override
    public BigDecimal visitBook(Book book) {
        return new BigDecimal("0.00"); // kitablar vergidən azaddır
    }

    @Override
    public BigDecimal visitElectronics(Electronics electronics) {
        return percent(electronics.price(), 18);
    }

    @Override
    public BigDecimal visitFood(Food food) {
        return percent(food.price(), 5);
    }

    private static BigDecimal percent(BigDecimal price, int percent) {
        return price.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
