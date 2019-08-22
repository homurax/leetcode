/**
 * 22. Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {

    private List<String> ans;

    /**
     * 可以优化为不传递 string 传递 char[]
     */
    public List<String> generateParenthesis(int n) {
        this.ans = new ArrayList<>();
        build("(", n - 1, n);
        return ans;
    }

    private void build(String curr, int left, int right) {
        if (left == 0 && right == 0) {
            this.ans.add(curr);
        }
        if (left > 0) {
            build(curr + "(", left - 1, right);
        }
        if (right > 0 && right > left) {
            build(curr + ")", left, right - 1);
        }
    }


}
