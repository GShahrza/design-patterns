package com.company.design_patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Aggregate: daxili strukturunu (burada massiv/list) gizlədir və müxtəlif keçid üsulları
 * (iterator-lar) təqdim edir. Iterable olduğu üçün for-each ilə də işləyir.
 */
public class Playlist implements Iterable<Song> {

    private final List<Song> songs = new ArrayList<>();

    public void add(Song song) {
        songs.add(song);
    }

    @Override
    public Iterator<Song> iterator() {
        return new SequentialIterator();
    }

    public Iterator<Song> reverseIterator() {
        return new ReverseIterator();
    }

    /** Hər mahnını bir dəfə, təsadüfi sırayla qaytarır (Fisher–Yates). */
    public Iterator<Song> shuffleIterator(long seed) {
        List<Song> copy = new ArrayList<>(songs);
        Random random = new Random(seed);
        for (int i = copy.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            copy.set(i, copy.set(j, copy.get(i)));
        }
        return copy.iterator();
    }

    private class SequentialIterator implements Iterator<Song> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < songs.size();
        }

        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return songs.get(index++);
        }
    }

    private class ReverseIterator implements Iterator<Song> {
        private int index = songs.size() - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return songs.get(index--);
        }
    }
}
