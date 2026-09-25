package com.company.design_patterns.behavioral.state;

public class NewState implements OrderState {
    @Override
    public String name() {
        return "NEW";
    }

    @Override
    public void pay(Order order) {
        order.setState(new PaidState());
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CancelledState());
    }
}
