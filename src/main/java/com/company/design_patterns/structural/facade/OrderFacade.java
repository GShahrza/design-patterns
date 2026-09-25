package com.company.design_patterns.structural.facade;

import java.math.BigDecimal;

/**
 * Facade: bir neçə alt sistemin (anbar, ödəniş, çatdırılma, email) mürəkkəb iş axınını
 * tək sadə metodun arxasında gizlədir. Client yalnız {@link #placeOrder} çağırır.
 */
public class OrderFacade {

    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();
    private final EmailService email = new EmailService();

    public boolean placeOrder(String product, BigDecimal price, String card, String address, String customerEmail) {
        if (!inventory.isAvailable(product)) {
            email.send(customerEmail, "Sorry, " + product + " is out of stock");
            return false;
        }
        if (!payment.charge(card, price)) {
            email.send(customerEmail, "Payment failed for " + product);
            return false;
        }
        inventory.reserve(product);
        String tracking = shipping.ship(product, address);
        email.send(customerEmail, "Order confirmed! Tracking number: " + tracking);
        return true;
    }
}
