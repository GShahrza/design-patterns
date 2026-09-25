package com.company.design_patterns.behavioral.mediator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** Concrete mediator: kimin kimə nə göndərə biləcəyi qaydaları bir yerdə cəmlənib. */
public class ChatRoom implements ChatMediator {

    private final Map<String, User> users = new LinkedHashMap<>();
    private final Set<String> bannedWords = Set.of("spam");

    @Override
    public void join(User user) {
        users.values().forEach(u -> u.receive("*** " + user.getName() + " joined ***"));
        users.put(user.getName(), user);
    }

    @Override
    public void send(String message, User from) {
        if (bannedWords.stream().anyMatch(message.toLowerCase()::contains)) {
            from.receive("*** message blocked by moderator ***");
            return;
        }
        users.values().stream()
                .filter(u -> u != from)
                .forEach(u -> u.receive(from.getName() + ": " + message));
    }

    @Override
    public void sendPrivate(String message, User from, String toName) {
        User to = users.get(toName);
        if (to == null) {
            from.receive("*** user " + toName + " not found ***");
            return;
        }
        to.receive("(private) " + from.getName() + ": " + message);
    }
}
