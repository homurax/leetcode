/**
 * 576. Out of Boundary Paths
 *
 *
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent four cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
 *
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 *
 * Example 2:
 *
 *
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 *
 *
 * Constraints:
 *
 * 1. 1 <= m, n <= 50
 * 2. 0 <= maxMove <= 50
 * 3. 0 <= startRow <= m
 * 4. 0 <= startColumn <= n
 */
public class OutOfBoundaryPaths {

    /**
     * (x,y) -> x * n + y
     * 从 (x,y) 出发 step 步可以走出矩阵的路径数
     * dp[(x, y)][step] = dp[(x − 1, y)][step − 1] + dp[(x + 1, y)][step − 1] + dp[(x, y − 1)][step − 1] + dp[(x, y + 1)][step − 1]
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] dp = new int[m * n][maxMove + 1];
        for (int j = 0; j < n; j++) {
            init(dp, n, maxMove, 0, j);
            init(dp, n, maxMove, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            init(dp, n, maxMove, i, 0);
            init(dp, n, maxMove, i, n - 1);
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int step = 1; step <= maxMove; step++) {
            for (int index = 0; index < m * n; index++) {
                int x = index / n, y = index % n;
                for (int[] d : directions) {
                    int x1 = x + d[0], y1 = y + d[1];
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
                        dp[index][step] += dp[x1 * n + y1][step - 1];
                        dp[index][step] %= 1000000007;
                    }
                }
            }
        }
        return dp[startRow * n + startColumn][maxMove];
    }

    private void init(int[][] dp, int n, int maxMove, int x, int y) {
        int index = x * n + y;
        for (int step = 1; step <= maxMove; step++) {
            dp[index][step]++;
        }
    }

}
