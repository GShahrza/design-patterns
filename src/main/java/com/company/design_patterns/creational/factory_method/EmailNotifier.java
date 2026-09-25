package com.company.design_patterns.creational.factory_method;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[EMAIL] to " + recipient + ": " + message);
    }
}
