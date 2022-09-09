/**
 * 1937. Maximum Number of Points with Cost
 * <p>
 * <p>
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 * <p>
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 * <p>
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 * <p>
 * Return the maximum number of points you can achieve.
 * <p>
 * abs(x) is defined as:
 * <p>
 * x for x >= 0.
 * -x for x < 0.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1. m == points.length
 * 2. n == points[r].length
 * 3. 1 <= m, n <= 10^5
 * 4. 1 <= m * n <= 10^5
 * 5. 0 <= points[r][c] <= 10^5
 */
public class MaximumNumberOfPointsWithCost {


    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] prev = new long[n];
        long[] curr = new long[n];
        for (int j = 0; j < n; j++) {
            prev[j] = points[0][j];
            curr[j] = points[0][j];
        }
        for (int i = 1; i < m; i++) {
            long max = 0;
            for (int j = 0; j < n; j++) {
                max = Math.max(max - 1, prev[j]);
                curr[j] = max;
            }
            for (int j = n - 1; j >= 0; j--) {
                max = Math.max(max - 1, prev[j]);
                curr[j] = Math.max(max, curr[j]) + points[i][j];
            }
            prev = curr;
        }
        long ans = curr[0];
        for (long v : curr) {
            ans = Math.max(ans, v);
        }
        return ans;
    }

    // dp[i][j] =  points[i][j] + max(dp[i - 1][k] - abs(k - j))    k in [0, n - 1]
    //      points[i][j] - j + dp[i - 1][k] + k     (k <= j)
    //      points[i][j] + j + dp[i - 1][k] - k     (k > j)
    public long maxPoints1(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[][] dp = new long[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = points[0][j];
        }
        for (int i = 1; i < m; i++) {
            long v = dp[i - 1][0];
            for (int j = 0; j < n; j++) {
                v = Math.max(v, dp[i - 1][j] + j);
                dp[i][j] = points[i][j] - j + v;
            }
            v = dp[i - 1][n - 1] - (n - 1);
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i][j], points[i][j] + j + v);
                v = Math.max(v, dp[i - 1][j] - j);
            }
        }
        long ans = 0;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[m - 1][j]);
        }
        return ans;
    }

}
