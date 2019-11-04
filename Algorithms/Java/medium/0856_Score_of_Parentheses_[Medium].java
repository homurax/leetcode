/**
 * 856. Score of Parentheses
 *
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 * Input: "()"
 * Output: 1
 *
 * Example 2:
 * Input: "(())"
 * Output: 2
 *
 * Example 3:
 * Input: "()()"
 * Output: 2
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {


    public int scoreOfParentheses(String S) {
        return score(S, 0, S.length());
    }

    private int score(String S, int low, int high) {

        int ans = 0, balance = 0;
        for (int i = low; i < high; i++) {
            balance += S.charAt(i) == '(' ? 1 : -1;
            if (balance == 0) {
                if (i - low == 1) {
                    ans++;
                } else {
                    ans += 2 * score(S, low + 1, i);
                }
                low = i + 1;
            }
        }
        return ans;
    }


    public int scoreOfParentheses2(String S) {

        LinkedList<Integer> deque = new LinkedList<>();
        deque.push(0);

        for (char c : S.toCharArray()) {
            if (c == '(') {
                deque.push(0);
            } else {
                int v = deque.pop();
                int w = deque.pop();
                deque.push(w + Math.max(2 * v, 1));
            }
        }
        return deque.pop();
    }

    public int scoreOfParentheses3(String S) {
        int ans = 0, balance = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
                if (S.charAt(i - 1) == '(') {
                    ans += 1 << balance;
                }
            }
        }
        return ans;
    }
}
