/**
 * 1003. Check If Word Is Valid After Substitutions
 *
 *
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
 *
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 *
 *
 * Example 2:
 *
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 *
 *
 * Example 3:
 *
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 10^4
 * s consists of letters 'a', 'b', and 'c'
 */
public class CheckIfWordIsValidAfterSubstitutions {

    // 类似合法括号
    // a 直接入栈
    // b 要求栈顶为 a，然后 a 出栈 b 入栈，即栈顶修改为 b
    // c 要求栈顶为 b，然后 b 出栈
    // a、b 入栈可以合并，b、c 出栈逻辑可以合并
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        for (char letter : chars) {
            if (letter > 'a' && (i == 0 || letter - chars[--i] != 1)) {
                return false;
            }
            if (letter < 'c') {
                chars[i++] = letter;
            }
        }
        return i == 0;
    }

}
