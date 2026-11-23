import java.util.*;

class Solution {
    public boolean parseBoolExpr(String expression) {

        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {

            if (ch == ',' || ch == '(') {
                continue;
            }

            if (ch != ')') {
                stack.push(ch);
            } else {

                int trueCount = 0;
                int falseCount = 0;

                while (stack.peek() == 't' || stack.peek() == 'f') {
                    char curr = stack.pop();

                    if (curr == 't') {
                        trueCount++;
                    } else {
                        falseCount++;
                    }
                }

                char operator = stack.pop();

                if (operator == '!') {
                    stack.push(falseCount == 1 ? 't' : 'f');
                }
                else if (operator == '&') {
                    stack.push(falseCount == 0 ? 't' : 'f');
                }
                else { // '|'
                    stack.push(trueCount > 0 ? 't' : 'f');
                }
            }
        }

        return stack.peek() == 't';
    }
}
