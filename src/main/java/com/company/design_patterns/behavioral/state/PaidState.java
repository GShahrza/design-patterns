package com.company.design_patterns.behavioral.state;

public class PaidState implements OrderState {
    @Override
    public String name() {
        return "PAID";
    }

    @Override
    public void ship(Order order) {
        order.setState(new ShippedState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("  refunding payment...");
        order.setState(new CancelledState());
    }
}
