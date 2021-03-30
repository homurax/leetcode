/**
 * 1759. Count Number of Homogenous Substrings
 *
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.
 *
 * A string is homogenous if all the characters of the string are the same.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbcccaa"
 * Output: 13
 * Explanation: The homogenous substrings are listed as below:
 * "a"   appears 3 times.
 * "aa"  appears 1 time.
 * "b"   appears 2 times.
 * "bb"  appears 1 time.
 * "c"   appears 3 times.
 * "cc"  appears 2 times.
 * "ccc" appears 1 time.
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
 *
 * Example 2:
 *
 * Input: s = "xy"
 * Output: 2
 * Explanation: The homogenous substrings are "x" and "y".
 *
 * Example 3:
 *
 * Input: s = "zzzzz"
 * Output: 15
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of lowercase letters.
 */
public class CountNumberOfHomogenousSubstrings {

    /**
     * aa -> aaa => a、aa、aaa 的数量都加一 增加三 增加窗口的宽度
     */
    public int countHomogenous(String s) {
        int ans = 0, l = 0, r = 0;
        while (r < s.length()) {
            if (s.charAt(l) != s.charAt(r)) {
                l = r;
            }
            ans = (ans + r - l + 1) % 1000000007;
            r++;
        }
        return ans;
    }

    public int countHomogenous2(String s) {
        char[] chars = s.toCharArray();
        int ans = 0, l = 0, r = 0, len = chars.length;
        while (r < len) {
            if (chars[l] != chars[r]) {
                l = r;
            }
            ans += r - l + 1;
            if (ans > 1000000007) {
                ans -= 1000000007;
            }
            r++;
        }
        return ans;
    }

}
