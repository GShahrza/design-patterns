package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;

public record Book(String title, BigDecimal price) implements Item {
    @Override
    public <R> R accept(ItemVisitor<R> visitor) {
        return visitor.visitBook(this);
    }
}
