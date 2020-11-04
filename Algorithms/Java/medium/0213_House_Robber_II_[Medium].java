/**
 * 213. House Robber II
 *
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {

    // 首位都不取时 最大值一定小于等于只去掉首或者只去掉尾的队列
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int rst1 = test(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int rst2 = test(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(rst1, rst2);
    }

    private int test(int[] nums) {
        int max = 0, prev = 0, temp;
        for (int num : nums) {
            temp = max;
            max = Math.max(prev + num, max);
            prev = temp;
        }
        return max;
    }

}
