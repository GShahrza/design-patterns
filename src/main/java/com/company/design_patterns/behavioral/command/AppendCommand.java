package com.company.design_patterns.behavioral.command;

public class AppendCommand implements Command {

    private final TextEditor editor;
    private final String value;
    private int position;

    public AppendCommand(TextEditor editor, String value) {
        this.editor = editor;
        this.value = value;
    }

    @Override
    public void execute() {
        position = editor.length();
        editor.insert(position, value);
    }

    @Override
    public void undo() {
        editor.delete(position, position + value.length());
    }
}
