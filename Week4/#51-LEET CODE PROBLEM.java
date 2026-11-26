import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col + n - 1
        boolean[] diag2 = new boolean[2 * n]; // row + col

        backtrack(0, n, board, cols, diag1, diag2, result);
        return result;
    }

    private void backtrack(
            int row,
            int n,
            char[][] board,
            boolean[] cols,
            boolean[] diag1,
            boolean[] diag2,
            List<List<String>> result) {
        if (row == n) {
            result.add(buildBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;

            backtrack(row + 1, n, board, cols, diag1, diag2, result);

            // Backtrack (remove queen)
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    private List<String> buildBoard(char[][] board) {
        List<String> formatted = new ArrayList<>(board.length);
        for (char[] row : board) {
            formatted.add(new String(row));
        }
        return formatted;
    }
}
