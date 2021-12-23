/**
 * 688. Knight Probability in Chessboard
 *
 *
 * On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 *
 * A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 *
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 *
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 2, row = 0, column = 0
 * Output: 0.06250
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 * Example 2:
 *
 * Input: n = 1, k = 0, row = 0, column = 0
 * Output: 1.00000
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 25
 * 2. 0 <= k <= 100
 * 3. 0 <= row, column <= n
 */
public class KnightProbabilityInChessboard {

    double[][] memo;
    int[] dx = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
    int[] dy = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

    public double knightProbability(int N, int k, int r, int c) {
        memo = new double[k][N * N];
        return dfs(N, k, r, c);
    }

    private double dfs(int n, int k, int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (memo[k - 1][r * n + c] != 0) {
            return memo[k - 1][r * n + c];
        }
        double ans = 0;
        for (int i = 0; i < 8; i++) {
            ans += dfs(n, k - 1, r + dx[i], c + dy[i]) / 8;
        }
        memo[k - 1][r * n + c] = ans;
        return ans;
    }


    public double knightProbability2(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        dp[r][c] = 1;
        for (; K > 0; K--) {
            double[][] temp = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int idx = 0; idx < 8; idx++) {
                        int x = i + dx[idx];
                        int y = j + dy[idx];
                        if (0 <= x && x < N && 0 <= y && y < N) {
                            temp[x][y] += dp[i][j] / 8.0;
                        }
                    }
                }
            }
            dp = temp;
        }
        double ans = 0;
        for (double[] row: dp) {
            for (double x: row) {
                ans += x;
            }
        }
        return ans;
    }



    // Time Limit Exceeded
    // 使用上面两种方式降维
    private Double[][][] memo1;

    public double knightProbability1(int N, int K, int r, int c) {
        memo1 = new Double[K + 1][N][N];
        return dfs1(N, K, r, c) / Math.pow(8, K);
    }

    private double dfs1(int n, int k, int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (memo1[k][r][c] != null) {
            return memo1[k][r][c];
        }
        double count = 0;
        for (int i = 0; i < 8; i++) {
            count += dfs1(n, k - 1, r + dx[i], c + dy[i]);
        }
        return count;
    }

}
