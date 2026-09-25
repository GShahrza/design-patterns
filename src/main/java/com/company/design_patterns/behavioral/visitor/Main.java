package com.company.design_patterns.behavioral.visitor;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Item> cart = List.of(
                new Book("Clean Code", new BigDecimal("45.00")),
                new Electronics("Headphones", new BigDecimal("120.00"), 0.4),
                new Electronics("Monitor", new BigDecimal("400.00"), 5.2),
                new Food("Ice cream", new BigDecimal("6.00"), true));

        ItemVisitor<BigDecimal> tax = new TaxVisitor();
        ItemVisitor<BigDecimal> shipping = new ShippingCostVisitor();

        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalShipping = BigDecimal.ZERO;
        for (Item item : cart) {
            BigDecimal t = item.accept(tax);
            BigDecimal s = item.accept(shipping);
            System.out.printf("%-60s tax=%6s shipping=%6s%n", item, t, s);
            totalTax = totalTax.add(t);
            totalShipping = totalShipping.add(s);
        }
        System.out.println("Total tax: " + totalTax + ", total shipping: " + totalShipping);
    }
}
