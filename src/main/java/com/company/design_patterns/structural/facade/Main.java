package com.company.design_patterns.structural.facade;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        OrderFacade shop = new OrderFacade();

        shop.placeOrder("laptop", new BigDecimal("1899.00"), "4169123412341234", "Baku, Nizami 10", "ali@example.com");
        System.out.println("---");
        shop.placeOrder("mouse", new BigDecimal("25.00"), "4169123412341234", "Baku, Nizami 10", "ali@example.com");
    }
}
