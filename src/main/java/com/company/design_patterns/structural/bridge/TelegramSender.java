package com.company.design_patterns.structural.bridge;

public class TelegramSender implements MessageSender {
    @Override
    public void sendMessage(String subject, String body) {
        System.out.println("Telegram -> *" + subject + "*\n" + body);
    }
}
