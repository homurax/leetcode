/**
 * 456. 132 Pattern
 *
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 *
 *
 * Example 2:
 *
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 *
 * Example 3:
 *
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 *
 * Constraints:
 *
 * 1. n == nums.length
 * 2. 1 <= n <= 2 * 10^5
 * 3. -10^9 <= nums[i] <= 10^9
 */
public class Pattern132 {

    // 对于 i j k 结构
    // 如果枚举 i, 找到 j > i && k > i && j > k, 即在 [i, k] 之间找到 j
    // 如果枚举 j, 在 j 右边找小于 j 的最大的数, 在 j 左边找小于 j 的最小的数, 单调栈不满足最大最小逻辑
    // 如果枚举 k, 在 k 左边确定 i, 在 [i, k] 之间存在比 i 和 k 大的数字, 与枚举 i 逻辑相同
    //
    // 从后向前维护单调递减的栈, k 记录最大的出栈元素
    // 当遍历到 t 时, 如果 t < k, 则代表找到了 ijk 结构
    // 因为 k 是出栈元素, t 和 k 之间存在大于 k 的 j
    public boolean find132pattern(int[] nums) {
        int k = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                k = deque.pollLast();
            }
            deque.addLast(nums[i]);
        }
        return false;
    }

}
