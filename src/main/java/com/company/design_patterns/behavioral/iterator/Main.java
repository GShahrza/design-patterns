package com.company.design_patterns.behavioral.iterator;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.add(new Song("Sarı gəlin", "Folk"));
        playlist.add(new Song("Bohemian Rhapsody", "Queen"));
        playlist.add(new Song("Numb", "Linkin Park"));
        playlist.add(new Song("Yesterday", "The Beatles"));

        System.out.println("Sequential (for-each):");
        for (Song song : playlist) {
            System.out.println("  " + song.title());
        }

        System.out.println("Reverse:");
        print(playlist.reverseIterator());

        System.out.println("Shuffle:");
        print(playlist.shuffleIterator(7));
    }

    private static void print(Iterator<Song> it) {
        while (it.hasNext()) {
            System.out.println("  " + it.next().title());
        }
    }
}
