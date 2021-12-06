/**
 * 939. Minimum Area Rectangle
 *
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 *
 * Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is not any such rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 *
 * Example 2:
 *
 *
 * Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1. 1 <= points.length <= 500
 * 2. points[i].length == 2
 * 3. 0 <= xi, yi <= 4 * 10^4
 * 4. All the given points are unique.
 */
public class MinimumAreaRectangle {

    // 存在重复计算 一个可行的矩形四个点都会计算一次
    public int minAreaRect1(int[][] points) {
        if (points.length < 4) {
            return 0;
        }
        Set<Integer> memo = new HashSet<>();
        int area = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x1 = point[0], y1 = point[1];
            for (Integer hash : memo) {
                int x2 = hash / 40001, y2 = hash % 40001;
                int possible = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                // (x1, y2) (x2, y1)
                if (possible < area && memo.contains(x1 * 40001 + y2) && memo.contains(x2 * 40001 + y1)) {
                    area = possible;
                }
            }
            memo.add(x1 * 40001 + y1);
        }
        return area == Integer.MAX_VALUE ? 0 : area;
    }

}
