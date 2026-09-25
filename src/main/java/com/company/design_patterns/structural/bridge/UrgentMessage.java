package com.company.design_patterns.structural.bridge;

public class UrgentMessage extends Message {

    public UrgentMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String text) {
        sender.sendMessage("URGENT", text.toUpperCase() + " (reply ASAP)");
    }
}
