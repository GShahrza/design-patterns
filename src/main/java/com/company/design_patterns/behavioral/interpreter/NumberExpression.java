package com.company.design_patterns.behavioral.interpreter;

import java.util.Map;

/** Terminal expression: sabit ədəd. */
public record NumberExpression(int value) implements Expression {
    @Override
    public int interpret(Map<String, Integer> context) {
        return value;
    }
}
