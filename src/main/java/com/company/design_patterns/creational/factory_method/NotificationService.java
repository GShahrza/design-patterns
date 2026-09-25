package com.company.design_patterns.creational.factory_method;

/**
 * Creator. {@link #createNotifier()} — factory method-dur: hansı konkret {@link Notifier}
 * yaradılacağına alt siniflər qərar verir. Əsas biznes məntiqi ({@link #notifyUser})
 * isə burada, dəyişmədən qalır.
 */
public abstract class NotificationService {

    protected abstract Notifier createNotifier();

    public void notifyUser(String recipient, String orderId) {
        Notifier notifier = createNotifier();
        notifier.send(recipient, "Your order " + orderId + " has been shipped and will arrive soon");
    }
}
