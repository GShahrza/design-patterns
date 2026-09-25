package com.company.design_patterns.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Mətni (Reverse Polish Notation, məs. "x 2 + 3 *") Expression ağacına çevirir.
 * Interpreter pattern-in özü parser deyil, ağacdır — parser sadəcə ağacı qurmağa kömək edir.
 */
public final class RpnParser {

    private RpnParser() {
    }

    public static Expression parse(String source) {
        Deque<Expression> stack = new ArrayDeque<>();
        for (String token : source.trim().split("\\s+")) {
            switch (token) {
                case "+" -> { Expression r = stack.pop(); stack.push(new AddExpression(stack.pop(), r)); }
                case "-" -> { Expression r = stack.pop(); stack.push(new SubtractExpression(stack.pop(), r)); }
                case "*" -> { Expression r = stack.pop(); stack.push(new MultiplyExpression(stack.pop(), r)); }
                default -> stack.push(token.matches("-?\\d+")
                        ? new NumberExpression(Integer.parseInt(token))
                        : new VariableExpression(token));
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: " + source);
        }
        return stack.pop();
    }
}
