package com.company.design_patterns.creational.builder;

public class Main {
    public static void main(String[] args) {

        Product product = Product.builder()
                .id(2L)
                .name("Example")
                .description("Worked!")
                .build();

        System.out.println(product);
    }
}
