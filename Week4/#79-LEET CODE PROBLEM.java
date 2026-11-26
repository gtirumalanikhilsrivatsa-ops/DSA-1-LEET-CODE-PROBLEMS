class Solution {
    private static final int[][] DIRS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Pruning 1: verify character counts suffice
        int[] boardCount = new int[128];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                boardCount[board[r][c]]++;
            }
        }

        int[] wordCount = new int[128];
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            wordCount[ch]++;
            if (wordCount[ch] > boardCount[ch]) {
                return false;
            }
        }

        // Pruning 2: start from whichever end character is rarer on the board
        if (boardCount[word.charAt(word.length() - 1)] < boardCount[word.charAt(0)]) {
            word = new StringBuilder(word).reverse().toString();
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == word.charAt(0) && dfs(r, c, 0, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int r, int c, int index, char[][] board, String word) {
        if (board[r][c] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }

        char saved = board[r][c];
        board[r][c] = '#'; // mark visited on current path

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] != '#') {
                if (dfs(nr, nc, index + 1, board, word)) {
                    board[r][c] = saved;
                    return true;
                }
            }
        }

        board[r][c] = saved; // backtrack
        return false;
    }
}
