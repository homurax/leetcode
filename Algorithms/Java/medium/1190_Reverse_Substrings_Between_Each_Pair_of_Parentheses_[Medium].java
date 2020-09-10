/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 *
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 *
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 *
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 *
 * Example 4:
 *
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

    public static String reverseParentheses(String s) {
        char[] letters = new char[s.length()];
        int cur = -1;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                int pre = cur;
                while (letters[pre] != '(') {
                    pre--;
                }
                letters[pre] = '\u0000';
                reverse(letters, pre + 1, cur);
            } else {
                letters[++cur] = c;
            }
        }
        int write = 0;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != '\u0000') {
                letters[write++] = letters[i];
            }
        }
        return new String(letters, 0, write);
    }

    private static void reverse(char[] letters, int left, int right) {
        while (left < right) {
            char temp = letters[left];
            letters[left] = letters[right];
            letters[right] = temp;
            left++;
            right--;
        }
    }

}
