package com.company.design_patterns.structural.flyweight;

/** Context: hər ağaca məxsus (extrinsic) vəziyyət — koordinatlar — və paylaşılan tipə istinad. */
public record Tree(int x, int y, TreeType type) {

    public void draw() {
        type.draw(x, y);
    }
}
