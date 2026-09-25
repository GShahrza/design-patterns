package com.company.design_patterns.structural.flyweight;

/**
 * Flyweight: bir çox obyekt arasında PAYLAŞILAN, dəyişməz (intrinsic) vəziyyət.
 * Real proqramda burada ağır məlumat olardı (məs. tekstura şəkli — meqabaytlarla).
 */
public record TreeType(String name, String color, String texture) {

    public void draw(int x, int y) {
        System.out.println("Drawing " + name + " (" + color + ") at [" + x + "," + y + "]");
    }
}
