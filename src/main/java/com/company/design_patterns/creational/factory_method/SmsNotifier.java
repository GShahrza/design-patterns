package com.company.design_patterns.creational.factory_method;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        String text = message.length() > 20 ? message.substring(0, 20) + "..." : message;
        System.out.println("[SMS] to " + recipient + ": " + text);
    }
}
