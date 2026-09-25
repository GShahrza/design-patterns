package com.company.design_patterns.structural.composite;

/** Leaf: övladı olmayan element. */
public class File implements FileSystemNode {

    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    }
}
