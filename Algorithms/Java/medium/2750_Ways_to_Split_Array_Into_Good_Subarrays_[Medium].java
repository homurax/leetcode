/**
 * 2750. Ways to Split Array Into Good Subarrays
 *
 *
 * You are given a binary array nums.
 *
 * A subarray of an array is good if it contains exactly one element with the value 1.
 *
 * Return an integer denoting the number of ways to split the array nums into good subarrays. As the number may be too large, return it modulo 109 + 7.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,0,1]
 * Output: 3
 * Explanation: There are 3 ways to split nums into good subarrays:
 * - [0,1] [0,0,1]
 * - [0,1,0] [0,1]
 * - [0,1,0,0] [1]
 *
 *
 * Example 2:
 *
 * Input: nums = [0,1,0]
 * Output: 1
 * Explanation: There is 1 way to split nums into good subarrays:
 * - [0,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 1
 */
public class WaysToSplitArrayIntoGoodSubarrays {

    // 在两个 1 之前划线, 之间有 n 个 0 就可以划 n + 1 次, 划线数相乘
    // 没有 1 -> 0
    // 只有一个 1 -> 1
    public int numberOfGoodSubarraySplits(int[] nums) {
        long MOD = 1_000_000_007;
        long ans = 1;
        int pre = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (pre >= 0) {
                ans = ans * (i - pre) % MOD;
            }
            pre = i;
        }
        return pre < 0 ? 0 : (int) ans;
    }

}
