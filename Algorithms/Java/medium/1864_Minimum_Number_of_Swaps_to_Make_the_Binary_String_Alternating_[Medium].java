/**
 * 1864. Minimum Number of Swaps to Make the Binary String Alternating
 *
 *
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 *
 *
 * Example 2:
 *
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 *
 *
 * Example 3:
 *
 * Input: s = "1110"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s[i] is either '0' or '1'.
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {

    // 只有两种合法序列
    // 01010...
    // 10101...
    // 当前序列与之相比位置不同的数量分别为 diff1 diff2
    // ans = min(diff1 / 2, diff2 / 2)
    public int minSwaps(String s) {
        int count0 = 0, count1 = 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '0') {
                count0++;
            } else {
                count1++;
            }
        }
        int ans = Integer.MAX_VALUE;
        // 10101... 1 可以多一位
        if (count1 == (n + 1) / 2 && count0 == n / 2) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                if (i % 2 == chars[i] - '0') {
                    diff++;
                }
            }
            ans = diff / 2;
        }
        // 01010... 0 可以多一位
        if (count0 == (n + 1) / 2 && count1 == n / 2) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                if (i % 2 != chars[i] - '0') {
                    diff++;
                }
            }
            ans = Math.min(ans, diff / 2);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
