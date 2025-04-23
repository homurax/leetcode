/**
 * 3397. Maximum Number of Distinct Elements After Operations
 *
 *
 * You are given an integer array nums and an integer k.
 *
 * You are allowed to perform the following operation on each element of the array at most once:
 *
 * Add an integer in the range [-k, k] to the element.
 * Return the maximum possible number of distinct elements in nums after performing the operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,3,4], k = 2
 *
 * Output: 6
 *
 * Explanation:
 *
 * nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.
 *
 *
 *
 * Example 2:
 *
 * Input: nums = [4,4,4,4], k = 1
 *
 * Output: 3
 *
 * Explanation:
 *
 * By adding -1 to nums[0] and 1 to nums[1], nums changes to [3, 5, 4, 4].
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
public class MaximumNumberOfDistinctElementsAfterOperations {

    // 贪心
    // 排序后, 考虑最左边的最小值 nums[0], 将其向左移动可以空出更多位置, 空出最多的即为 nums[0] = nums[0] - k
    // 考虑 nums[1], 同样左移但是不能变为修改后的 nums[0], 即 nums[1] = max(nums[1] - k, nums[0] + 1)
    // 但是修改后的 nums[0] + 1 也不能超过 nums[1] + k 的范围
    // 即 nums[i] = min(max(nums[i] - k, nums[i - 1] + 1), nums[i] + k)
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        // 最多可以变化 x + k - (x - k) + 1 = 2k + 1
        // 如果可变化数大于总数则代表均可变为不同
        if (k * 2 + 1 >= n) {
            return n;
        }
        Arrays.sort(nums);
        int ans = 0;
        int pre = Integer.MIN_VALUE; // 记录前一个
        for (int num : nums) {
            num = Math.min(Math.max(num - k, pre + 1), num + k);
            if (num > pre) {
                ans++;
                pre = num;
            }
        }
        return ans;
    }

}
