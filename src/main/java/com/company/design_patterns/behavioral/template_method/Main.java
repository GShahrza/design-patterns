package com.company.design_patterns.behavioral.template_method;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Sale> sales = List.of(
                new Sale("Baku", "Laptop", new BigDecimal("1800")),
                new Sale("Ganja", "Phone", new BigDecimal("900")),
                new Sale("Baku", "Mouse", new BigDecimal("25")));

        System.out.println(new CsvReport().generate(sales));
        System.out.println(new HtmlReport("Baku").generate(sales));
    }
}
