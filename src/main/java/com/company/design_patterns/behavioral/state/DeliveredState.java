package com.company.design_patterns.behavioral.state;

public class DeliveredState implements OrderState {
    @Override
    public String name() {
        return "DELIVERED";
    }
}
