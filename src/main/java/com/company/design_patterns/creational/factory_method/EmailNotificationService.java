package com.company.design_patterns.creational.factory_method;

public class EmailNotificationService extends NotificationService {
    @Override
    protected Notifier createNotifier() {
        return new EmailNotifier();
    }
}
