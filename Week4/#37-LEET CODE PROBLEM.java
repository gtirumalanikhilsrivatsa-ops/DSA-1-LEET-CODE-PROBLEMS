class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];

        // Record initially filled digits
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch != '.') {
                    int d = ch - '0';
                    int box = (r / 3) * 3 + (c / 3);
                    rows[r][d] = true;
                    cols[c][d] = true;
                    boxes[box][d] = true;
                }
            }
        }

        backtrack(board, rows, cols, boxes);
    }

    private boolean backtrack(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    int box = (r / 3) * 3 + (c / 3);

                    for (int d = 1; d <= 9; d++) {
                        if (!rows[r][d] && !cols[c][d] && !boxes[box][d]) {
                            board[r][c] = (char) ('0' + d);
                            rows[r][d] = true;
                            cols[c][d] = true;
                            boxes[box][d] = true;

                            if (backtrack(board, rows, cols, boxes)) {
                                return true;
                            }

                            // Backtrack
                            board[r][c] = '.';
                            rows[r][d] = false;
                            cols[c][d] = false;
                            boxes[box][d] = false;
                        }
                    }

                    // No valid digit 1-9 fits here; trigger backtracking in caller
                    return false;
                }
            }
        }

        // All cells filled validly
        return true;
    }
}
