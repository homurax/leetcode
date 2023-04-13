/**
 * 2615. Sum of Distances
 *
 *
 * You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
 *
 * Return the array arr.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1,1,2]
 * Output: [5,0,3,4,0]
 * Explanation:
 * When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
 * When i = 1, arr[1] = 0 because there is no other index with value 3.
 * When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
 * When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
 * When i = 4, arr[4] = 0 because there is no other index with value 2.
 *
 *
 * Example 2:
 *
 * Input: nums = [0,5,3]
 * Output: [0,0,0]
 * Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 */
public class SumOfDistances {

    // 增量
    // sum + (idx[i] - idx[i - 1]) - (n - 1) * (idx[i] - idx[i - 1])
    // sum + (2 * i - n) * (idx[i] - idx[i - 1])
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        long[] ans = new long[n];
        for (List<Integer> idxList : map.values()) {
            long sum = 0;
            for (int idx : idxList) {
                sum += idx - idxList.get(0);
            }
            ans[idxList.get(0)] = sum;
            int m = idxList.size();
            for (int i = 1; i < m; i++) {
                ans[idxList.get(i)] = sum += (2L * i - m) * (idxList.get(i) - idxList.get(i - 1));
            }
        }
        return ans;
    }

}
