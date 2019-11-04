/**
 * 812. Largest Triangle Area
 *
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 *
 * Example:
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2
 * Explanation:
 * The five points are show in the figure below. The red triangle is the largest.
 *
 *
 * Notes:
 *
 * 3 <= points.length <= 50.
 * No points will be duplicated.
 *  -50 <= points[i][j] <= 50.
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class LargestTriangleArea {

    /**
     * Shoelace formula [https://en.wikipedia.org/wiki/Shoelace_formula]
     *
     * S = |x1y2 + x2y3 + x3y1 - x2y1 - x3y2 - x1y3|/2
     *
     * 遍历 每三个点计算一次
     */
    public double largestTriangleArea(int[][] points) {
        int length = points.length;
        double ans = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                for (int k = j+1; k < length; k++) {
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
                }
            }
        }
        return ans;
    }

    private double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }
}
