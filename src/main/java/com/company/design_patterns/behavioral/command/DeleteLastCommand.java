package com.company.design_patterns.behavioral.command;

public class DeleteLastCommand implements Command {

    private final TextEditor editor;
    private final int count;
    private String deleted = "";
    private int from;

    public DeleteLastCommand(TextEditor editor, int count) {
        this.editor = editor;
        this.count = count;
    }

    @Override
    public void execute() {
        from = Math.max(0, editor.length() - count);
        deleted = editor.delete(from, editor.length());
    }

    @Override
    public void undo() {
        editor.insert(from, deleted);
    }
}
