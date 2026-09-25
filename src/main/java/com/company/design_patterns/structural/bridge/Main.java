package com.company.design_patterns.structural.bridge;

public class Main {
    public static void main(String[] args) {
        new TextMessage(new EmailSender()).send("Weekly report is ready");
        new UrgentMessage(new SmsSender()).send("Server is down");
        new UrgentMessage(new TelegramSender()).send("Disk usage 95%");
    }
}
