package com.company.design_patterns.behavioral.state;

/** Context: işi cari state obyektinə ötürür. State dəyişdikcə davranış da dəyişir. */
public class Order {

    private final String id;
    private OrderState state = new NewState();

    public Order(String id) {
        this.id = id;
    }

    void setState(OrderState state) {
        System.out.println("  " + id + ": " + this.state.name() + " -> " + state.name());
        this.state = state;
    }

    public String getStateName() {
        return state.name();
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void cancel() {
        state.cancel(this);
    }
}
