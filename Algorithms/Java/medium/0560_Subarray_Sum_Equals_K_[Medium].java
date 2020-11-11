/**
 * 560. Subarray Sum Equals K
 *
 *
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 2 * 10^4
 * 2. -1000 <= nums[i] <= 1000
 * 3. -107 <= k <= 10^7
 */
public class SubarraySumEqualsK {

    /**
     * 枚举
     * 0 ≤ j ≤ i 且 [j, i] 这个子数组的和为 k
     * i 从 0 向后走，j 从 i 向前走，兼顾全部情况
     *
     *
     * 前缀和
     * pre[i] = pre[i−1] + nums[i]
     *     sum([j, i]) == k
     * ->  pre[i] − pre[j−1] == k
     * ->  pre[j−1] == pre[i] − k
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, prev = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            prev += num;
            if (map.containsKey(prev - k)) {
                count += map.get(prev - k);
            }
            map.merge(prev, 1, Integer::sum);
        }
        return count;
    }

}
