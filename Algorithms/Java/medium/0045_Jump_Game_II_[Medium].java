/**
 * 45. Jump Game II
 *
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII {

    // 贪心
    // 到达前一个最远位置时更新最远位置 同时跳跃数增加
    // 由于一定可达 最远位置一定大于等于最后一个元素的位置
    // 当最远位置与最后一个元素的位置相同时会增加跳跃数
    // 所以不需要访问最后一个元素
    public int jump(int[] nums) {
        int preMaxIdx = 0;
        int maxIdx = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxIdx = Math.max(maxIdx, i + nums[i]);
            if (i == preMaxIdx) {
                preMaxIdx = maxIdx;
                step++;
            }
        }
        return step;
    }


}
