package com.company.design_patterns.structural.facade;

public class EmailService {
    public void send(String to, String text) {
        System.out.println("Email to " + to + ": " + text);
    }
}
