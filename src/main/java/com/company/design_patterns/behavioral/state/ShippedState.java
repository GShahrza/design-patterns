package com.company.design_patterns.behavioral.state;

public class ShippedState implements OrderState {
    @Override
    public String name() {
        return "SHIPPED";
    }

    @Override
    public void deliver(Order order) {
        order.setState(new DeliveredState());
    }
}
