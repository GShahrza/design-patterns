package com.company.design_patterns.behavioral.command;

/** Command: sorğunu (əməliyyatı) obyekt kimi inkapsulyasiya edir — saxlamaq, növbəyə qoymaq, geri almaq olur. */
public interface Command {
    void execute();

    void undo();
}
