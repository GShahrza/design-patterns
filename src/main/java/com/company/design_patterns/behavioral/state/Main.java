package com.company.design_patterns.behavioral.state;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("ORD-1");
        order.pay();
        order.ship();
        order.deliver();

        Order second = new Order("ORD-2");
        second.pay();
        second.cancel();

        Order third = new Order("ORD-3");
        try {
            third.ship();
        } catch (IllegalStateException e) {
            System.out.println("  error: " + e.getMessage());
        }
    }
}
