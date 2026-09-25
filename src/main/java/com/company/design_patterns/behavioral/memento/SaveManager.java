package com.company.design_patterns.behavioral.memento;

import java.util.LinkedHashMap;
import java.util.Map;

/** Caretaker: memento-ları saxlayır, amma içinə baxmır və dəyişmir. */
public class SaveManager {

    private final Map<String, GameCharacter.Memento> slots = new LinkedHashMap<>();

    public void save(String slot, GameCharacter character) {
        slots.put(slot, character.save());
        System.out.println("  saved to slot '" + slot + "'");
    }

    public void load(String slot, GameCharacter character) {
        GameCharacter.Memento memento = slots.get(slot);
        if (memento == null) {
            throw new IllegalArgumentException("Empty slot: " + slot);
        }
        character.restore(memento);
        System.out.println("  loaded slot '" + slot + "'");
    }
}
