/**
 * 678. Valid Parenthesis String
 *
 *
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 *
 *
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 *
 *
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 100
 * 2. s[i] is '(', ')' or '*'.
 */
public class ValidParenthesisString {

    // * 可能出现在 ( 左边
    public boolean checkValidString(String s) {
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
                r++;
            } else if (c == ')') {
                l--;
                r--;
            } else {
                l--;
                r++;
            }
            l = Math.max(l, 0);
            if (l > r) {
                return false;
            }
        }
        return l == 0;
    }

}
