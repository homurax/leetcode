/**
 * 11. Container With Most Water
 *
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class ContainerWithMostWater {

    /**
     * 容量 = 两个指针指向的数字中较小值 ∗ 指针之间的距离
     * 移动数字较小的那个指针
     *
     * 对应的数字较小的那个指针不可能再作为容器的边界:
     *  假设 x <= y, min(x, y) * t -> x * t
     *  如果保持左指针的位置不变，那么无论右指针在哪里，这个容器的容量都不会超过 x * t
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

}
