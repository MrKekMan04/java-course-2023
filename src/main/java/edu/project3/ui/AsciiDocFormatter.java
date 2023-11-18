package edu.project3.ui;

@SuppressWarnings("MagicNumber")
public class AsciiDocFormatter implements TextFormatter {
    @Override
    public String getExtension() {
        return "adoc";
    }

    @Override
    public String title(String text) {
        return "== " + text;
    }

    @Override
    public String monospace(String text) {
        return "``" + text + "``";
    }

    @Override
    public String table(int rows, int columns, String[][] data) {
        StringBuilder table = new StringBuilder();
        String tableSeparator = '|' + "=".repeat(10) + '\n';

        table.append("[width=\"100%%\", cols=\"%d\", options=\"header\"]\n".formatted(columns))
            .append(tableSeparator);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                table.append('|').append(data[row][column]).append('\n');
            }
        }

        table.append(tableSeparator);

        return table.toString();
    }
}
