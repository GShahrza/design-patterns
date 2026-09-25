package com.company.design_patterns.structural.bridge;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String subject, String body) {
        System.out.println("Email -> subject: '" + subject + "', body: '" + body + "'");
    }
}
