/**
 * 1513. Number of Substrings With Only 1s
 *
 *
 * Given a binary string s (a string consisting only of '0' and '1's).
 *
 * Return the number of substrings with all characters 1's.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0110111"
 * Output: 9
 * Explanation: There are 9 substring in total with only 1's characters.
 * "1" -> 5 times.
 * "11" -> 3 times.
 * "111" -> 1 time.
 *
 * Example 2:
 *
 * Input: s = "101"
 * Output: 2
 * Explanation: Substring "1" is shown 2 times in s.
 *
 * Example 3:
 *
 * Input: s = "111111"
 * Output: 21
 * Explanation: Each substring contains only 1's characters.
 *
 * Example 4:
 *
 * Input: s = "000"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. s[i] == '0' or s[i] == '1'
 * 2. 1 <= s.length <= 10^5
 */
public class NumberOfSubstringsWithOnly1s {

    public static int numSub1(String s) {
        char[] chars = s.toCharArray();
        int ans = 0, l = 0, r = 0, len = s.length();
        while (r < len) {
            if (chars[l] != chars[r]) {
                l = r;
            }
            if (chars[l] == '1') {
                ans += r - l + 1;
                if (ans > 1000000007) {
                    ans -= 1000000007;
                }
            }
            r++;
        }
        return ans;
    }

    public static int numSub2(String s) {
        char[] chars = s.toCharArray();
        long ans = 0;
        int one = 0;
        for (char aChar : chars) {
            one = (aChar == '1') ? one + 1 : 0;
            ans += one;
        }
        return (int) (ans % 1000000007);
    }

}
