package com.company.design_patterns.behavioral.command;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();

        history.execute(new AppendCommand(editor, "Hello"));
        history.execute(new AppendCommand(editor, ", World"));
        history.execute(new AppendCommand(editor, "!!!"));
        System.out.println("After typing:  " + editor.getText());

        history.execute(new DeleteLastCommand(editor, 2));
        System.out.println("After delete:  " + editor.getText());

        history.undo();
        System.out.println("Undo:          " + editor.getText());
        history.undo();
        System.out.println("Undo:          " + editor.getText());
        history.redo();
        System.out.println("Redo:          " + editor.getText());
    }
}
