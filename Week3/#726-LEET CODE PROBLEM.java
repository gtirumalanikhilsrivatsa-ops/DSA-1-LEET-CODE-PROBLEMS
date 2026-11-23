import java.util.*;

class Solution {

    public String countOfAtoms(String formula) {

        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        int i = 0;
        int n = formula.length();

        while (i < n) {

            char ch = formula.charAt(i);

            if (ch == '(') {
                stack.push(new HashMap<>());
                i++;
            }

            else if (ch == ')') {

                i++;

                int start = i;

                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int multiplier =
                        start < i
                        ? Integer.parseInt(formula.substring(start, i))
                        : 1;

                Map<String, Integer> top = stack.pop();
                Map<String, Integer> current = stack.peek();

                for (String atom : top.keySet()) {
                    current.put(
                        atom,
                        current.getOrDefault(atom, 0)
                            + top.get(atom) * multiplier
                    );
                }
            }

            else {

                int start = i++;

                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }

                String atom = formula.substring(start, i);

                start = i;

                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }

                int count =
                        start < i
                        ? Integer.parseInt(formula.substring(start, i))
                        : 1;

                Map<String, Integer> current = stack.peek();

                current.put(
                    atom,
                    current.getOrDefault(atom, 0) + count
                );
            }
        }

        TreeMap<String, Integer> sorted =
                new TreeMap<>(stack.pop());

        StringBuilder ans = new StringBuilder();

        for (String atom : sorted.keySet()) {
            ans.append(atom);

            int count = sorted.get(atom);

            if (count > 1) {
                ans.append(count);
            }
        }

        return ans.toString();
    }
}
