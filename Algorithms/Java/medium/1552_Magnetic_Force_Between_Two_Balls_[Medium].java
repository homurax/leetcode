/**
 * 1552. Magnetic Force Between Two Balls
 *
 *
 * In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
 *
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 *
 * Given the integer array position and the integer m. Return the required force.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 *
 * Example 2:
 *
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 *
 *
 * Constraints:
 *
 * 1. n == position.length
 * 2. 2 <= n <= 10^5
 * 3. 1 <= position[i] <= 10^9
 * 4. All integers in position are distinct.
 * 5. 2 <= m <= position.length
 */
public class MagneticForceBetweenTwoBalls {


    // 最小距离（两个相近的球之间的距离）最大 -> 两个球之间尽量远 && 整体尽量均匀
    // 二分最小和最大距离后测试是否为可行解
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = (position[position.length - 1] - position[0])/(m - 1), ans = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (check(position, mid, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int[] position, int force, int m) {
        int prev = position[0], count = 1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - prev >= force) {
                prev = position[i];
                count += 1;
            }
        }
        return count >= m;
    }

}
