package com.company.design_patterns.structural.bridge;

/** Implementation tərəfi: mesajın HARADAN/NECƏ göndərildiyi. */
public interface MessageSender {
    void sendMessage(String subject, String body);
}
