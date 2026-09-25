package com.company.design_patterns.behavioral.interpreter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // (x + 2) * (y - 1)
        Expression expression = RpnParser.parse("x 2 + y 1 - *");

        System.out.println("Tree: " + expression);
        System.out.println("x=3, y=5  => " + expression.interpret(Map.of("x", 3, "y", 5)));
        System.out.println("x=10, y=2 => " + expression.interpret(Map.of("x", 10, "y", 2)));
    }
}
