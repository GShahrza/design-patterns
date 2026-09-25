package com.company.design_patterns.behavioral.state;

/**
 * State: hər vəziyyət ayrıca sinifdir və öz davranışını müəyyən edir.
 * Order sinfində böyük "if (status == ...)" / switch blokları olmur.
 * Default olaraq əməliyyat icazəsizdir; hər state yalnız ona aid olanı override edir.
 */
public interface OrderState {

    String name();

    default void pay(Order order) {
        reject("pay");
    }

    default void ship(Order order) {
        reject("ship");
    }

    default void deliver(Order order) {
        reject("deliver");
    }

    default void cancel(Order order) {
        reject("cancel");
    }

    private void reject(String action) {
        throw new IllegalStateException("Cannot " + action + " order in state " + name());
    }
}
