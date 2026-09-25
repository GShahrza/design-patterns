package com.company.design_patterns.creational.factory_method;

public class SmsNotificationService extends NotificationService {
    @Override
    protected Notifier createNotifier() {
        return new SmsNotifier();
    }
}
