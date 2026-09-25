package com.company.design_patterns.behavioral.memento;

/** Originator: öz vəziyyətini memento-ya yazır və memento-dan bərpa edir. */
public class GameCharacter {

    private int level = 1;
    private int health = 100;
    private String location = "Village";

    public void fight(int damage) {
        health = Math.max(0, health - damage);
    }

    public void levelUp() {
        level++;
        health = 100;
    }

    public void moveTo(String location) {
        this.location = location;
    }

    public Memento save() {
        return new Memento(level, health, location);
    }

    public void restore(Memento memento) {
        this.level = memento.level;
        this.health = memento.health;
        this.location = memento.location;
    }

    public boolean isDead() {
        return health == 0;
    }

    @Override
    public String toString() {
        return "level=" + level + ", health=" + health + ", location=" + location;
    }

    /**
     * Memento: vəziyyətin dəyişməz snapshot-u. Sahələr private-dır — yalnız GameCharacter
     * (outer class) onları oxuya bilir; caretaker üçün bu "qara qutu"dur (inkapsulyasiya pozulmur).
     */
    public static final class Memento {
        private final int level;
        private final int health;
        private final String location;

        private Memento(int level, int health, String location) {
            this.level = level;
            this.health = health;
            this.location = location;
        }
    }
}
