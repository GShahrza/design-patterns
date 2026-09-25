package com.company.design_patterns.structural.proxy.example3;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ManagerProxy managerProxy = new ManagerProxy("yusuf", "12345");

        BigDecimal ciro = managerProxy.getCiro();
        System.out.println("Şirkət dövriyyəsi: " + ciro);

        try {
            new ManagerProxy("ayse", "1234").getCiro();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }
}
