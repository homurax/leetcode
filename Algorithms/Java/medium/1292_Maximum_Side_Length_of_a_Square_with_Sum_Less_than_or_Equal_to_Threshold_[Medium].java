/**
 * 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 *
 *
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 *
 * Example 2:
 *
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 *
 * Example 3:
 *
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 *
 * Example 4:
 *
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1. 1 <= m, n <= 300
 * 2. m == mat.length
 * 3. n == mat[i].length
 * 4. 0 <= mat[i][j] <= 10000
 * 5. 0 <= threshold <= 10^5
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {

    /**
     * 二位前缀和 Prefix Sum
     * P[i][j] -> (1, 1) (i, j) 为对角线的正方形元素和
     * P[i][j] = P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1] + mat[i][j]
     *
     * sum((x1, y1), (x2, y2)) = P[x2][y2] - P[x1 - 1][y2] - P[x2][y1 - 1] + P[x1 - 1][y1 - 1]
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int max = 0, limit = Math.min(m, n);
        // i, j 为 左上角; 通过边长枚举右下角
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = max + 1; k <= limit; k++) {
                    if (i + k - 1 <= m && j + k - 1 <= n
                            && (preSum[i + k - 1][j + k - 1] - preSum[i - 1][j + k - 1] - preSum[i + k - 1][j - 1] + preSum[i - 1][j - 1] <= threshold)) {
                        max = k;
                    } else {
                        break;
                    }
                }
            }
        }
        return max;
    }

}
