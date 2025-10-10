/**
 * 3347. Maximum Frequency of an Element After Performing Operations II
 *
 *
 * You are given an integer array nums and two integers k and numOperations.
 *
 * You must perform an operation numOperations times on nums, where in each operation you:
 *
 * Select an index i that was not selected in any previous operations.
 * Add an integer in the range [-k, k] to nums[i].
 * Return the maximum possible frequency of any element in nums after performing the operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,5], k = 1, numOperations = 2
 *
 * Output: 2
 *
 * Explanation:
 *
 * We can achieve a maximum frequency of two by:
 *
 * Adding 0 to nums[1], after which nums becomes [1, 4, 5].
 * Adding -1 to nums[2], after which nums becomes [1, 4, 4].
 *
 *
 * Example 2:
 *
 * Input: nums = [5,11,20,20], k = 5, numOperations = 1
 *
 * Output: 2
 *
 * Explanation:
 *
 * We can achieve a maximum frequency of two by:
 *
 * Adding 0 to nums[1].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * 0 <= numOperations <= nums.length
 */
public class MaximumFrequencyOfAnElementAfterPerformingOperationsII {

    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> diff = new TreeMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
            diff.putIfAbsent(x, 0); // 确保能遍历到 x
            diff.merge(x - k, 1, Integer::sum); // diff[x - k]++
            diff.merge(x + k + 1, -1, Integer::sum); // diff[x + k + 1]--
        }
        int ans = 0;
        int total = 0;
        for (Map.Entry<Integer, Integer> e : diff.entrySet()) {
            total += e.getValue();
            ans = Math.max(ans, Math.min(total, cnt.getOrDefault(e.getKey(), 0) + numOperations));
        }
        return ans;
    }

}
