package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;

public record Food(String name, BigDecimal price, boolean perishable) implements Item {
    @Override
    public <R> R accept(ItemVisitor<R> visitor) {
        return visitor.visitFood(this);
    }
}
