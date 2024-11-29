/**
 * 2420. Find All Good Indices
 *
 *
 * You are given a 0-indexed integer array nums of size n and a positive integer k.
 *
 * We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
 *
 * The k elements that are just before the index i are in non-increasing order.
 * The k elements that are just after the index i are in non-decreasing order.
 * Return an array of all good indices sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,1,1,3,4,1], k = 2
 * Output: [2,3]
 * Explanation: There are two good indices in the array:
 * - Index 2. The subarray [2,1] is in non-increasing order, and the subarray [1,3] is in non-decreasing order.
 * - Index 3. The subarray [1,1] is in non-increasing order, and the subarray [3,4] is in non-decreasing order.
 * Note that the index 4 is not good because [4,1] is not non-decreasing.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,1,1,2], k = 2
 * Output: []
 * Explanation: There are no good indices in this array.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= n / 2
 */
public class FindAllGoodIndices {

    // 非递增 k <= i < n - k 非递减
    // 从后向前维护非递减数量
    // 从前向后维护非递增数量并记录 ans
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] desc = new int[n];
        desc[n - 1] = 1;
        for (int i = n - 2; i > k; i--) {
            if (nums[i] <= nums[i + 1]) {
                desc[i] = desc[i + 1] + 1;
            } else {
                desc[i] = 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1, inc = 1; i < n - k; i++) {
            if (inc >= k && desc[i + 1] >= k) {
                ans.add(i);
            }
            if (nums[i - 1] >= nums[i]) {
                inc++;
            } else {
                inc = 1;
            }
        }
        return ans;
    }

}
