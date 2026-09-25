package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;

public record Electronics(String name, BigDecimal price, double weightKg) implements Item {
    @Override
    public <R> R accept(ItemVisitor<R> visitor) {
        return visitor.visitElectronics(this);
    }
}
