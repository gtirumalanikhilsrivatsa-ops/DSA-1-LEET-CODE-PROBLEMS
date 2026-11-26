import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(int n, int openCount, int closeCount, StringBuilder current, List<String> result) {
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        if (openCount < n) {
            current.append('(');
            backtrack(n, openCount + 1, closeCount, current, result);
            current.deleteCharAt(current.length() - 1);
        }

        if (closeCount < openCount) {
            current.append(')');
            backtrack(n, openCount, closeCount + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
