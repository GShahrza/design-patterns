package com.company.design_patterns.behavioral.mediator;

/**
 * Mediator: obyektlər bir-birinə birbaşa müraciət etmir, bütün əlaqə mediator üzərindən gedir.
 * Beləliklə N obyekt arasında N×N əlaqə əvəzinə N əlaqə olur.
 */
public interface ChatMediator {
    void join(User user);

    void send(String message, User from);

    void sendPrivate(String message, User from, String toName);
}
