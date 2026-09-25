package com.company.design_patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/** Composite: övladları saxlayır və əməliyyatı rekursiv olaraq onlara ötürür. */
public class Directory implements FileSystemNode {

    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory add(FileSystemNode node) {
        children.add(node);
        return this;
    }

    public void remove(FileSystemNode node) {
        children.remove(node);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return children.stream().mapToLong(FileSystemNode::getSize).sum();
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "+ " + name + "/ (" + getSize() + " KB)");
        for (FileSystemNode child : children) {
            child.print(indent + "   ");
        }
    }
}
