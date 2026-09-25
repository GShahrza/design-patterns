package com.company.design_patterns.creational.factory_method;

public class Main {
    public static void main(String[] args) {
        NotificationService service = configure("sms");
        service.notifyUser("+994501234567", "ORD-42");

        service = configure("email");
        service.notifyUser("user@example.com", "ORD-43");
    }

    // Konfiqurasiyaya görə creator seçilir; client kodu konkret Notifier siniflərini tanımır.
    private static NotificationService configure(String channel) {
        return switch (channel) {
            case "sms" -> new SmsNotificationService();
            case "email" -> new EmailNotificationService();
            default -> throw new IllegalArgumentException("Unknown channel: " + channel);
        };
    }
}
