package com.company.design_patterns.behavioral.interpreter;

import java.util.Map;

/** Terminal expression: dəyişən — dəyəri context-dən götürülür. */
public record VariableExpression(String name) implements Expression {
    @Override
    public int interpret(Map<String, Integer> context) {
        Integer value = context.get(name);
        if (value == null) {
            throw new IllegalArgumentException("Undefined variable: " + name);
        }
        return value;
    }
}
