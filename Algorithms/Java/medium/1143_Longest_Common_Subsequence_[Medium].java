/**
 * 1143. Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * If there is no common subsequence, return 0.
 *
 *
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 */
public class LongestCommonSubsequence {


    public int longestCommonSubsequence(String text1, String text2) {

        int len1 = text1.length(), len2 = text2.length();
        if (len1 < len2) {
            return longestCommonSubsequence(text2, text1);
        }

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }


    /**
     * 只需要两行 最后判断下取第几行的结果
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        if (len1 < len2) {
            return longestCommonSubsequence2(text2, text1);
        }
        int[][] dp = new int[2][len2 + 1];
        for (int i = 0, k = 1; i < len1; i++, k ^= 1) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[k][j + 1] = 1 + dp[k ^ 1][j];
                } else {
                    dp[k][j + 1] = Math.max(dp[k ^ 1][j + 1], dp[k][j]);
                }
            }
        }
        return dp[len1 % 2][len2];
    }

    public static int longestCommonSubsequence3(String text1, String text2) {

        char[] charArr = text1.toCharArray();
        int[] dp = new int[charArr.length + 1];

        for (int i = text2.length() - 1; i >= 0; i--) {
            char c = text2.charAt(i);
            int dp0 = 0, dp1 = 0;
            for (int j = charArr.length - 1; j >= 0; j--) {
                int dpj = dp[j];
                if (charArr[j] == c) {
                    dp1 = Math.max(dp0 + 1, dp1);
                } else {
                    dp1 = Math.max(dpj, dp1);
                }
                if (dpj != dp1) {
                    dp[j] = dp1;
                }
                dp0 = dpj;
            }
        }
        return dp[0];
    }
}
