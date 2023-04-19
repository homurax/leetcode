/**
 * 2195. Append K Integers With Minimal Sum
 *
 *
 * You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
 *
 * Return the sum of the k integers appended to nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,25,10,25], k = 2
 * Output: 5
 * Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
 * The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
 * The sum of the two integers appended is 2 + 3 = 5, so we return 5.
 *
 *
 * Example 2:
 *
 * Input: nums = [5,6], k = 6
 * Output: 25
 * Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
 * The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
 * The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^8
 */
public class AppendKIntegersWithMinimalSum {

    // 可以考虑数组中加入 0 和 Integer.MIN_VALUE 当作哨兵
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0;
        int prev = 0;
        for (int i = 0; i < nums.length && k > 0; i++) {
            /*for (int j = prev + 1; j < nums[i] && k > 0; j++, k--) {
                ans += j;
            }*/
            // [prev + 1, nums[i] - 1]
            if (nums[i] - 1 >= prev + 1) {
                int len = Integer.min(nums[i] - prev - 1, k);
                // ((prev + 1) + (prev + 1 + len - 1)) * len / 2
                ans += (2L * prev + len + 1) * len / 2;
                k -= len;
            }
            prev = nums[i];
        }
        if (k > 0) {
            // ((prev + 1) + (prev + 1 + k - 1)) * k / 2
            ans += (2L * prev + k + 1) * k / 2;
        }
        return ans;
    }

}
