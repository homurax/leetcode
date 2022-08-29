/**
 * 1658. Minimum Operations to Reduce X to Zero
 *
 *
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 *
 *
 * Example 2:
 *
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 *
 * Example 3:
 *
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^4
 * 3. 1 <= x <= 10^9
 */
public class MinimumOperationsToReduceXToZero {

    // 找最长连续子数组
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        int ans = -1;
        int n = nums.length;
        int l = 0, r = 0;
        int subSum = 0;
        while (r < n) {
            subSum += nums[r];
            while (subSum > target) {
                subSum -= nums[l++];
            }
            if (subSum == target) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans == -1 ? -1 : n - ans;
    }

}
