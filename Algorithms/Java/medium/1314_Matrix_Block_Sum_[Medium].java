/**
 * 1314. Matrix Block Sum
 *
 *
 * Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 *
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * Constraints:
 *
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n, k <= 100
 * 4. 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {

    /**
     * 二维度前缀和
     *
     *  preSum[i][j] = sum(对角线 [(0, 0), (i, j)] 的矩形)
     *
     *  1   2   3   4   5
     *  6   7   8   9   10
     *  11  12  13  14  15
     *  16  17  18  19  20
     *
     *  preSum[2][2] = sum([(0,0), (2,2)]) = [1,  2,  3,
     *                                        6,  7,  8,
     *                                        11, 12, 13]
     *                                     = [1,  2,  3,     +   [1, 2,     -   [1, 2,  +   13
     *                                        6,  7,  8]          6, 7,          6, 7]
     *                                                           11, 12]
     *
     *  preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1]
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            int r1 = Math.max(i - K, 0);
            int r2 = Math.min(i + K, m - 1);
            for (int j = 0; j < n; j++) {
                int c1 = Math.max(j - K, 0);
                int c2 = Math.min(j + K, n - 1);
                ans[i][j] = preSum[r2 + 1][c2 + 1] - preSum[r1][c2 + 1] - preSum[r2 + 1][c1] + preSum[r1][c1];
            }
        }
        return ans;
    }

}
