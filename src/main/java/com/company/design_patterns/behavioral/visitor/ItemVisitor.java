package com.company.design_patterns.behavioral.visitor;

/**
 * Visitor: element siniflərinə toxunmadan onlara YENİ ƏMƏLİYYAT əlavə etməyə imkan verir.
 * Yeni əməliyyat = yeni visitor sinfi. (Əksinə, yeni element növü əlavə etmək bütün visitor-ları dəyişdirir.)
 */
public interface ItemVisitor<R> {
    R visitBook(Book book);

    R visitElectronics(Electronics electronics);

    R visitFood(Food food);
}
