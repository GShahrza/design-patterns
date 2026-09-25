package com.company.design_patterns.structural.facade;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>(Map.of("laptop", 3, "mouse", 0));

    public boolean isAvailable(String product) {
        return stock.getOrDefault(product, 0) > 0;
    }

    public void reserve(String product) {
        stock.merge(product, -1, Integer::sum);
        System.out.println("Inventory: reserved 1 x " + product);
    }
}
