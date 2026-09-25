package com.company.design_patterns.behavioral.memento;

public class Main {
    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter();
        SaveManager saves = new SaveManager();

        hero.moveTo("Forest");
        hero.fight(30);
        hero.levelUp();
        System.out.println("Before boss: " + hero);
        saves.save("before-boss", hero);

        hero.moveTo("Dragon Cave");
        hero.fight(70);
        hero.fight(50);
        System.out.println("After boss:  " + hero + (hero.isDead() ? "  -> GAME OVER" : ""));

        saves.load("before-boss", hero);
        System.out.println("Restored:    " + hero);
    }
}
