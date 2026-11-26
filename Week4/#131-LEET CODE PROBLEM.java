import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        // Precompute palindrome substrings: length from 1 to n
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = (len <= 2) || isPalindrome[i + 1][j - 1];
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        backtrack(0, s, isPalindrome, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(
            int start,
            String s,
            boolean[][] isPalindrome,
            List<String> current,
            List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome[start][end]) {
                current.add(s.substring(start, end + 1));
                backtrack(end + 1, s, isPalindrome, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
