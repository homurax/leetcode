/**
 * 459. Repeated Substring Pattern
 *
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 *
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 *
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {


    public boolean repeatedSubstringPattern(String s) {

        String twice = s + s;
        return twice.substring(1, twice.length() - 1).contains(s);
    }


    /**
     * 假设为满足条件 可以获得 pattern 的最后一位字符
     * 再从中间往前查找位置 得出假定长度
     * 循环验证
     */
    public boolean repeatedSubstringPattern2(String s) {

        if (s.length() <= 1)
            return false;

        char lastChar = s.charAt(s.length() - 1);
        int len = s.lastIndexOf(lastChar, s.length() / 2 - 1) + 1;

        while (len > 0) {
            if (s.length() % len == 0) {
                String pattern = s.substring(0, len);
                boolean equals = true;
                for (int i = len; i < s.length(); i += len) {
                    if (!s.substring(i, i + len).equals(pattern)) {
                        equals = false;
                        break;
                    }
                }
                if (equals)
                    return true;
            }
            len = s.lastIndexOf(lastChar, len - 2) + 1;
        }
        return false;
    }
}
