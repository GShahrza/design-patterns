package com.company.design_patterns.behavioral.interpreter;

import java.util.Map;

public record MultiplyExpression(Expression left, Expression right) implements Expression {
    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) * right.interpret(context);
    }
}
