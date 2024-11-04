/**
 * 497. Random Point in Non-overlapping Rectangles
 *
 *
 * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.
 *
 * Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
 *
 * Note that an integer point is a point that has integer coordinates.
 *
 * Implement the Solution class:
 *
 * Solution(int[][] rects) Initializes the object with the given rectangles rects.
 * int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * Output
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 *
 * Explanation
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // return [1, -2]
 * solution.pick(); // return [1, -1]
 * solution.pick(); // return [-1, -2]
 * solution.pick(); // return [-2, -2]
 * solution.pick(); // return [0, 0]
 *
 *
 * Constraints:
 *
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -10^9 <= ai < xi <= 10^9
 * -10^9 <= bi < yi <= 10^9
 * xi - ai <= 2000
 * yi - bi <= 2000
 * All the rectangles do not overlap.
 * At most 10^4 calls will be made to pick.
 */
public class RandomPointInNonOverlappingRectangles {

    class Solution {

        int[][] rs;
        int[] sum;
        int n;
        Random random = new Random();

        public Solution(int[][] rects) {
            this.rs = rects;
            this.n = rs.length;
            this.sum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + (rs[i - 1][2] - rs[i - 1][0] + 1) * (rs[i - 1][3] - rs[i - 1][1] + 1);
            }
        }

        // 二分面积保证在选取矩形上随机
        public int[] pick() {
            int val = random.nextInt(sum[n]) + 1;
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= val) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            int[] cur = rs[r - 1];
            return new int[]{
                    random.nextInt(cur[2] - cur[0] + 1) + cur[0],
                    random.nextInt(cur[3] - cur[1] + 1) + cur[1]
            };
        }
    }

}
