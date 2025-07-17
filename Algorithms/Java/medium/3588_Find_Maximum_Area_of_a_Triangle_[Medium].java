/**
 * 3588. Find Maximum Area of a Triangle
 *
 *
 * You are given a 2D array coords of size n x 2, representing the coordinates of n points in an infinite Cartesian plane.
 *
 * Find twice the maximum area of a triangle with its corners at any three elements from coords, such that at least one side of this triangle is parallel to the x-axis or y-axis. Formally, if the maximum area of such a triangle is A, return 2 * A.
 *
 * If no such triangle exists, return -1.
 *
 * Note that a triangle cannot have zero area.
 *
 *
 *
 * Example 1:
 *
 * Input: coords = [[1,1],[1,2],[3,2],[3,3]]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * The triangle shown in the image has a base 1 and height 2. Hence its area is 1/2 * base * height = 1.
 *
 *
 * Example 2:
 *
 * Input: coords = [[1,1],[2,2],[3,3]]
 *
 * Output: -1
 *
 * Explanation:
 *
 * The only possible triangle has corners (1, 1), (2, 2), and (3, 3). None of its sides are parallel to the x-axis or the y-axis.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n == coords.length <= 10^5
 * 1 <= coords[i][0], coords[i][1] <= 10^6
 * All coords[i] are unique.
 */
public class FindMaximumAreaOfATriangle {

    // 对于确定的 x，找最长的边（x 的上下） = maxY[x] - minY[x]
    // 最大的高（x 边的左右两侧找顶点） = max(maxX - x, x - minX)
    public long maxArea(int[][] coords) {
        calc(coords);
        for (int[] p : coords) {
            int tmp = p[0]; // 交换横纵坐标
            p[0] = p[1];
            p[1] = tmp;
        }
        calc(coords);
        return ans > 0 ? ans : -1;
    }

    private long ans = 0;

    private void calc(int[][] coords) {
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        Map<Integer, Integer> minY = new HashMap<>();
        Map<Integer, Integer> maxY = new HashMap<>();
        for (int[] p : coords) {
            int x = p[0];
            int y = p[1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            maxY.put(x, Math.max(maxY.getOrDefault(x, 0), y));
            minY.put(x, Math.min(minY.getOrDefault(x, y), y));
        }
        for (Map.Entry<Integer, Integer> e : minY.entrySet()) {
            int x = e.getKey();
            int y = e.getValue();
            ans = Math.max(ans, (long) (maxY.get(x) - y) * Math.max(maxX - x, x - minX));
        }
    }

}
