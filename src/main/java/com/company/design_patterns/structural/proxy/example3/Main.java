package com.company.design_patterns.structural.proxy.example3;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ManagerProxy managerProxy = new ManagerProxy("yusuf", "12345");

        BigDecimal ciro = managerProxy.getCiro();
        System.out.println("Şirket Cirosu: " + ciro);
    }
}
