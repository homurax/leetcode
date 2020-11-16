/**
 * 583. Delete Operation for Two Strings
 *
 *
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 *
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 *
 * Note:
 * 1. The length of given words won't exceed 500.
 * 2. Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings {

    // 最长公共子序列 TLE
    public int minDistance0(String s1, String s2) {
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
    }
    private int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        }
        else {
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
        }
    }


    // 带记忆优化
    public int minDistance1(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
    }
    private int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (memo[m][n] > 0) {
            return memo[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = lcs(s1, s2, m - 1, n - 1, memo) + 1;
        }
        else {
            memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo));
        }
        return memo[m][n];
    }


    // 基于 lcs 的动态规划 也就是说 dp 求的是 lcs 值
    // dp[i][j] = s1 前 i 个字符和 s2 前 j 个字符中最长公共子序列
    public int minDistance2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }


    // 动态规划求结果
    // dp[i][j]dp[i][j] = s1 串前 i 个字符和 s2 串前 j 个字符匹配的最少删除次数
    public int minDistance3(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) { // 初始化
                    dp[i][j] = i + j;
                }
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) { // 不需要删除操作
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else { // 需要删除 取最小的加一
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }


    // 一维动态规划 为了求出当前 dp 元素的值，只需要前一行 dp 数组的值
    // dp[i] = s1 到第 i 个字符为止， s2 到最近位置为止的最少删除次数
    public int minDistance4(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= m; i++) {
            int[] temp = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    temp[j] = i + j;
                }
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    temp[j] = dp[j - 1];
                }
                else {
                    temp[j] = Math.min(dp[j], temp[j - 1]) + 1;
                }
            }
            dp = temp;
        }
        return dp[n];
    }


    public int minDistance(String s1, String s2) {
        char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray();
        int m = chars1.length, n = chars2.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[j] = prev + 1;
                }
                else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                prev = temp;
            }
        }
        return m + n - 2 * dp[n];
    }

}
