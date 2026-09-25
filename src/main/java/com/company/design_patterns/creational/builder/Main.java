package com.company.design_patterns.creational.builder;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product product = Product.builder()
                .id(2L)
                .name("Example")
                .description("Worked!")
                .price(new BigDecimal("19.90"))
                .build();
        System.out.println(product);

        try {
            Product.builder().id(3L).build();
        } catch (NullPointerException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
