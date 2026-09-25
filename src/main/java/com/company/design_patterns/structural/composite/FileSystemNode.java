package com.company.design_patterns.structural.composite;

/**
 * Component: həm tək elementin (File), həm də konteynerin (Directory) ümumi interfeysi.
 * Client ağacın yarpağı ilə budağını eyni cür istifadə edir.
 */
public interface FileSystemNode {

    String getName();

    long getSize();

    void print(String indent);
}
