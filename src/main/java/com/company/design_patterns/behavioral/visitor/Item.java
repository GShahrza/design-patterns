package com.company.design_patterns.behavioral.visitor;

/** Element: accept() vasitəsilə visitor-u "qəbul edir" (double dispatch). */
public interface Item {
    <R> R accept(ItemVisitor<R> visitor);
}
