/**
 * 1208. Get Equal Substrings Within Budget
 *
 *
 * You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 *
 * You are also given an integer maxCost.
 *
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.
 *
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 *
 * Example 2:
 *
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
 *
 * Example 3:
 *
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length, t.length <= 10^5
 * 2. 0 <= maxCost <= 10^6
 * 3. s and t only contain lower case English letters.
 */
public class GetEqualSubstringsWithinBudget {


    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int ans = 0, start = 0, end = 0, sum = 0;
        while (end < n) {
            sum += cost[end];
            while (sum > maxCost) {
                sum -= cost[start++];
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }

}
