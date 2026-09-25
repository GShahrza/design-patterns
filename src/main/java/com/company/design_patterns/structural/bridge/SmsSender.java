package com.company.design_patterns.structural.bridge;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String subject, String body) {
        System.out.println("SMS -> " + subject + ": " + body);
    }
}
