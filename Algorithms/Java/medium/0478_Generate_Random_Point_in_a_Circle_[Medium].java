/**
 * 478. Generate Random Point in a Circle
 *
 *
 * Given the radius and the position of the center of a circle, implement the function randPoint which generates a uniform random point inside the circle.
 *
 * Implement the Solution class:
 *
 * Solution(double radius, double x_center, double y_center) initializes the object with the radius of the circle radius and the position of the center (x_center, y_center).
 * randPoint() returns a random point inside the circle. A point on the circumference of the circle is considered to be in the circle. The answer is returned as an array [x, y].
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "randPoint", "randPoint", "randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * Output
 * [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 *
 * Explanation
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint(); // return [-0.02493, -0.38077]
 * solution.randPoint(); // return [0.82314, 0.38945]
 * solution.randPoint(); // return [0.36572, 0.17248]
 *
 *
 * Constraints:
 *
 * 0 < radius <= 108
 * -10^7 <= x_center, y_center <= 10^7
 * At most 3 * 10^4 calls will be made to randPoint.
 */
public class GenerateRandomPointInACircle {

    class Solution {
        private double r, x, y;
        private Random random = new Random();

        public Solution(double radius, double x_center, double y_center) {
            r = radius;
            x = x_center;
            y = y_center;
        }

        // 长度随机 [0, r^2], 注意圆面积为 π * r^2, 如果长度仅在 r 范围内随机, 半斤 r/2 扫过的面积仅为总面积的 1/4
        // 角度随机 [0, 2π]
        public double[] randPoint() {
            double len = Math.sqrt(random.nextDouble(r * r)), ang = random.nextDouble(2 * Math.PI);
            double nx = x + len * Math.cos(ang), ny = y + len * Math.sin(ang);
            return new double[]{nx, ny};
        }
    }


}
