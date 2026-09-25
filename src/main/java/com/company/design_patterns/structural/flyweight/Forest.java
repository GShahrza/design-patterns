package com.company.design_patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Forest {

    private final List<Tree> trees = new ArrayList<>();

    public void plant(int x, int y, String name, String color, String texture) {
        trees.add(new Tree(x, y, TreeTypeFactory.get(name, color, texture)));
    }

    public int size() {
        return trees.size();
    }

    public void draw(int limit) {
        trees.stream().limit(limit).forEach(Tree::draw);
    }
}
