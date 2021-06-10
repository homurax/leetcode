/**
 * 1234. Replace the Substring for Balanced String
 *
 *
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 *
 * Return 0 if the string is already balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 *
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 *
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 *
 * Example 4:
 *
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s.length is a multiple of 4
 * 3. s contains only 'Q', 'W', 'E' and 'R'.
 */
public class ReplaceTheSubstringForBalancedString {

    // right 扩大 left 缩小
    // 只要滑窗外都不超 n/4 即可
    public int balancedString(String s) {
        int n = s.length();
        int[] arr = new int[n];
        int[] count = new int[26];
        char[] letters = s.toCharArray();
        for (int i = 0; i < n; i++) {
            arr[i] = letters[i] - 'A';
            count[arr[i]]++;
        }
        int Q = 'Q' - 'A', W = 'W' - 'A', E = 'E' - 'A', R = 'R' - 'A';
        if (count[Q] == count[W] && count[W] == count[E] && count[E] == count[R]) {
            return 0;
        }
        int ans = n;
        int limit = n / 4;
        int left = 0, right = 0;
        while (right < n) {
            // 窗口外情况
            count[arr[right]]--;
            while (left < n && left <= right && count[Q] <= limit && count[W] <= limit && count[E] <= limit && count[R] <= limit) {
                ans = Math.min(ans, right - left + 1);
                count[arr[left]]++;
                left++;
            }
            right++;
        }
        return ans;
    }

}
