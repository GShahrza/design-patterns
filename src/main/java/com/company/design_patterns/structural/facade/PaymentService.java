package com.company.design_patterns.structural.facade;

import java.math.BigDecimal;

public class PaymentService {
    public boolean charge(String cardNumber, BigDecimal amount) {
        boolean ok = cardNumber.length() == 16;
        System.out.println("Payment: charging " + amount + " AZN -> " + (ok ? "OK" : "DECLINED"));
        return ok;
    }
}
