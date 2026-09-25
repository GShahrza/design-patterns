package com.company.design_patterns.structural.bridge;

public class TextMessage extends Message {

    public TextMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String text) {
        sender.sendMessage("Info", text);
    }
}
