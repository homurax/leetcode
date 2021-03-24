/**
 * 1401. Circle and Rectangle Overlapping
 *
 *
 * Given a circle represented as (radius, x_center, y_center) and an axis-aligned rectangle represented as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the rectangle.
 *
 * Return True if the circle and rectangle are overlapped otherwise return False.
 *
 * In other words, check if there are any point (xi, yi) such that belongs to the circle and the rectangle at the same time.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * Output: true
 * Explanation: Circle and rectangle share the point (1,0)
 *
 * Example 2:
 *
 *
 *
 * Input: radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * Output: true
 *
 * Example 3:
 *
 *
 *
 * Input: radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
 * Output: true
 *
 * Example 4:
 *
 * Input: radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= radius <= 2000
 * 2. -10^4 <= x_center, y_center, x1, y1, x2, y2 <= 10^4
 * 3. x1 < x2
 * 4. y1 < y2
 */
public class CircleAndRectangleOverlapping {

    // 找距离圆心的最近且在矩形内的点
    // 左右和上下只需要考虑水平和垂直距离
    // 四个斜角区域就好似勾股定理
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int dist = 0;
        // 左右
        if (x_center < x1 || x_center > x2) {
            dist += Math.min((x1 - x_center) * (x1 - x_center), (x2 - x_center) * (x2 - x_center));
        }
        // 下上
        if (y_center < y1 || y_center > y2) {
            dist += Math.min((y1 - y_center) * (y1 - y_center), (y2 - y_center) * (y2 - y_center));
        }
        return dist <= radius * radius;

    }

}
