package edu.project3.ui;

public interface TextFormatter {
    String getExtension();

    String title(String text);

    String monospace(String text);

    String table(int rows, int columns, String[][] data);
}
