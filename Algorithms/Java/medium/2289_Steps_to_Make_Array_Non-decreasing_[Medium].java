/**
 * 2289. Steps to Make Array Non-decreasing
 *
 *
 * You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 *
 * Return the number of steps performed until nums becomes a non-decreasing array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
 * Output: 3
 * Explanation: The following are the steps performed:
 * - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
 * - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
 * - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
 * [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
 *
 *
 * Example 2:
 *
 * Input: nums = [4,5,7,7,13]
 * Output: 0
 * Explanation: nums is already a non-decreasing array. Therefore, we return 0.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 */
public class StepsToMakeArrayNonDecreasing {

    // 一个数字是被它左边离它最近且比它大的那个数删除的
    public int totalSteps(int[] nums) {
        int ans = 0;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int num : nums) {
            int time = 0;
            while (!stack.isEmpty() && num >= stack.peek()[0]) {
                time = Math.max(time, stack.pop()[1]);
            }
            time = stack.isEmpty() ? 0 : time + 1;
            ans = Math.max(ans, time);
            stack.push(new int[]{num, time});
        }
        return ans;
    }


}
