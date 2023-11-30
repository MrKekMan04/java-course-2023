package edu.project2.entity;

public record Cell(int row, int col, Type type) {
    public enum Type {
        WALL("◇"),
        PASSAGE("◆");
        private final String symbol;

        Type(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return this.symbol;
        }
    }
}
