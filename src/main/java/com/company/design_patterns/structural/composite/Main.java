package com.company.design_patterns.structural.composite;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("project")
                .add(new File("README.md", 4))
                .add(new Directory("src")
                        .add(new File("Main.java", 12))
                        .add(new File("Util.java", 8)))
                .add(new Directory("assets")
                        .add(new File("logo.png", 250))
                        .add(new Directory("icons")
                                .add(new File("home.svg", 3))));

        root.print("");
        System.out.println("Total size: " + root.getSize() + " KB");
    }
}
