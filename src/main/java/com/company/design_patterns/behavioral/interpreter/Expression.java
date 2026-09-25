package com.company.design_patterns.behavioral.interpreter;

import java.util.Map;

/** Abstract expression: qrammatikanın hər qaydası bir sinifdir və interpret() ilə hesablanır. */
public interface Expression {
    int interpret(Map<String, Integer> context);
}
