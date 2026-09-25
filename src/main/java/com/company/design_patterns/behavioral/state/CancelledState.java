package com.company.design_patterns.behavioral.state;

public class CancelledState implements OrderState {
    @Override
    public String name() {
        return "CANCELLED";
    }
}
