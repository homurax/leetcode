/**
 * 1039. Minimum Score Triangulation of Polygon
 *
 *
 * You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values where values[i] is the value of the ith vertex (i.e., clockwise order).
 *
 * You will triangulate the polygon into n - 2 triangles. For each triangle, the value of that triangle is the product of the values of its vertices, and the total score of the triangulation is the sum of these values over all n - 2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: values = [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 *
 *
 * Example 2:
 *
 *
 * Input: values = [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
 * The minimum score is 144.
 *
 *
 * Example 3:
 *
 *
 * Input: values = [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 *
 *
 * Constraints:
 *
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
public class MinimumScoreTriangulationOfPolygon {

    // 对于点 i、j 以 ij 为边枚举另一顶点 k
    // dp(i, j) 表示 ij边 这个多边形的最低得分
    // dp(i, j) = min{dp(i, k) + dp(k, j) + v[i] * v[j] * v[k]}, k in [i + 1, j - 1]
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] f = new int[n][n];
        // i < k < j
        // f[i] 由 f[k] 而来，i 倒序
        // f[i][j] 由 f[]i[k] 而来，j 正序
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }
        return f[0][n - 1];
    }

}
