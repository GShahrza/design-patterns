package com.company.design_patterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/** Colleague: yalnız mediator-u tanıyır, digər istifadəçiləri yox. */
public class User {

    private final String name;
    private final ChatMediator chat;
    private final List<String> inbox = new ArrayList<>();

    public User(String name, ChatMediator chat) {
        this.name = name;
        this.chat = chat;
        chat.join(this);
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        chat.send(message, this);
    }

    public void sendPrivate(String toName, String message) {
        chat.sendPrivate(message, this, toName);
    }

    void receive(String formatted) {
        inbox.add(formatted);
        System.out.println("  [" + name + "'s screen] " + formatted);
    }

    public List<String> getInbox() {
        return List.copyOf(inbox);
    }
}
