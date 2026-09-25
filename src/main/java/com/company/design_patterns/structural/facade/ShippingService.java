package com.company.design_patterns.structural.facade;

import java.util.UUID;

public class ShippingService {
    public String ship(String product, String address) {
        String trackingNumber = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Shipping: " + product + " -> " + address + " [tracking " + trackingNumber + "]");
        return trackingNumber;
    }
}
