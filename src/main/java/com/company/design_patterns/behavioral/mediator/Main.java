package com.company.design_patterns.behavioral.mediator;

public class Main {
    public static void main(String[] args) {
        ChatMediator room = new ChatRoom();

        User aysel = new User("Aysel", room);
        User murad = new User("Murad", room);
        User leyla = new User("Leyla", room);

        System.out.println("Aysel sends:");
        aysel.send("Salam hamıya!");
        System.out.println("Murad sends private to Leyla:");
        murad.sendPrivate("Leyla", "Sabah görüşək?");
        System.out.println("Leyla sends spam:");
        leyla.send("Buy cheap SPAM now");
    }
}
