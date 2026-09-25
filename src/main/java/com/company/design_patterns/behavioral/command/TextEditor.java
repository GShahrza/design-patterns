package com.company.design_patterns.behavioral.command;

/** Receiver: real işi görən obyekt. Command-lar onu idarə edir. */
public class TextEditor {

    private final StringBuilder text = new StringBuilder();

    public void insert(int position, String value) {
        text.insert(position, value);
    }

    public String delete(int from, int to) {
        String removed = text.substring(from, to);
        text.delete(from, to);
        return removed;
    }

    public int length() {
        return text.length();
    }

    public String getText() {
        return text.toString();
    }
}
