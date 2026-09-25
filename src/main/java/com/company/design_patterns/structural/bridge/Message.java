package com.company.design_patterns.structural.bridge;

/**
 * Abstraction tərəfi: mesajın NƏ olduğu. {@link MessageSender}-ə istinad (körpü/bridge) saxlayır.
 * Bridge olmasaydı, hər növ × hər kanal üçün ayrıca sinif lazım olardı
 * (TextEmailMessage, UrgentSmsMessage, ... = N × M sinif). İndi isə N + M sinif kifayətdir.
 */
public abstract class Message {

    protected final MessageSender sender;

    protected Message(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send(String text);
}
