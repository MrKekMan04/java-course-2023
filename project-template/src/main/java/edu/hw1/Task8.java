package edu.hw1;

public final class Task8 {
    private static final int BOARD_LENGTH = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int y = 0; y < BOARD_LENGTH; y++) {
            for (int x = 0; x < BOARD_LENGTH; x++) {
                if (board[y][x] == 1 && isAttackAnotherKnight(board, x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("MagicNumber")
    private static boolean isAttackAnotherKnight(int[][] board, int knightX, int knightY) {
        var delta1 = new int[] {-1, 1};
        var delta2 = new int[] {-2, 2};

        for (int i : delta1) {
            for (int j : delta2) {
                if (isValueValid(knightX + i) && isValueValid(knightY + j)) {
                    if (board[knightY + j][knightX + i] == 1) {
                        return true;
                    }
                }

                if (isValueValid(knightX + j) && isValueValid(knightY + i)) {
                    if (board[knightY + i][knightX + j] == 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isValueValid(int value) {
        return value >= 0 && value < BOARD_LENGTH;
    }
}
