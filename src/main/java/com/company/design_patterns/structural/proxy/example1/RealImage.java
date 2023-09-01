package com.company.design_patterns.structural.proxy.example1;

public class RealImage implements Image{

    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFrmDisk(fileName);
    }

    private void loadFrmDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying + " + fileName);
    }
}
