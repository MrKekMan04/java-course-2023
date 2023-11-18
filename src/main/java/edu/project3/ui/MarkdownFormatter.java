package edu.project3.ui;

public class MarkdownFormatter implements TextFormatter {
    @Override
    public String getExtension() {
        return "md";
    }

    @Override
    public String title(String text) {
        return "# " + text;
    }

    @Override
    public String monospace(String text) {
        return '`' + text + '`';
    }

    @Override
    public String table(int rows, int columns, String[][] data) {
        StringBuilder table = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            table.append('|');

            for (int column = 0; column < columns; column++) {
                table.append(data[row][column]).append('|');
            }

            table.append('\n');

            if (row == 0) {
                table.append('|').append(":-:|".repeat(columns)).append('\n');
            }
        }

        return table.toString();
    }
}
