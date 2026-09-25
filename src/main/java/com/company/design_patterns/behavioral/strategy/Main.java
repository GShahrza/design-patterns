package com.company.design_patterns.behavioral.strategy;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new BigDecimal("40.00"));
        cart.add(new BigDecimal("75.50"));
        System.out.println("Subtotal:                 " + cart.subtotal());

        cart.setDiscount(new PercentageDiscount(10));
        System.out.println("10% off:                  " + cart.total());

        cart.setDiscount(new FixedAmountDiscount(new BigDecimal("20"), new BigDecimal("100")));
        System.out.println("20 AZN off (min 100):     " + cart.total());

        // Strategy funksional interfeys olduğu üçün lambda da strategiyadır.
        cart.setDiscount(total -> total.compareTo(new BigDecimal("100")) > 0 ? new BigDecimal("99.99") : total);
        System.out.println("Black Friday (max 99.99): " + cart.total());
    }
}
