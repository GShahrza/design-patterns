package com.company.design_patterns.creational.prototype;

/**
 * Prototype: obyekt özünü klonlamağı bacarır, beləliklə yeni obyekt "sıfırdan" yaradılmır,
 * mövcud nümunədən (prototype) kopyalanır.
 */
public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    public abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @Override
    public Shape clone() {
        try {
            // Shallow copy: burada yalnız String (immutable) sahələr var, ona görə kifayətdir.
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Shape implements Cloneable", e);
        }
    }
}
