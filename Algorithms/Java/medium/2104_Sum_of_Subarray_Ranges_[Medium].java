/**
 * 2104. Sum of Subarray Ranges
 *
 *
 * You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
 *
 * Return the sum of all subarray ranges of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [2], range = 2 - 2 = 0
 * [3], range = 3 - 3 = 0
 * [1,2], range = 2 - 1 = 1
 * [2,3], range = 3 - 2 = 1
 * [1,2,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,3,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [3], range = 3 - 3 = 0
 * [3], range = 3 - 3 = 0
 * [1,3], range = 3 - 1 = 2
 * [3,3], range = 3 - 3 = 0
 * [1,3,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
 *
 *
 * Example 3:
 *
 * Input: nums = [4,-2,-3,4,1]
 * Output: 59
 * Explanation: The sum of all subarray ranges of nums is 59.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Follow-up: Could you find a solution with O(n) time complexity?
 */
public class SumOfSubarrayRanges {

    // ans 为若干个区间的 max - min
    // 假设 nums[i] 在 k1 个区间中充当 max，在 k2 个区间中充当 min，则对 ans 的贡献为 (k1 - k2) * nums[i]
    // nums[i] 作为最大值次数：左右找到最近的一个不满足的位置（nums[l] > nums[i]、nums[r] > nums[i]）,(i - l) * (r - i)
    // nums[i] 作为最小值次数：同理（nums[l] < nums[i]、nums[r] < nums[i]）,(i - l) * (r - i)
    // 通过单调栈找 nums[i] 左右最近的位置
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long[] min = getCnt(nums, true);
        long[] max = getCnt(nums, false);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (max[i] - min[i]) * nums[i];
        }
        return ans;
    }

    private long[] getCnt(int[] nums, boolean isMin) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i])) {
                d.pollLast();
            }
            left[i] = d.isEmpty() ? -1 : d.peekLast();
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] > nums[i] : nums[d.peekLast()] < nums[i])) {
                d.pollLast();
            }
            right[i] = d.isEmpty() ? n : d.peekLast();
            d.addLast(i);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (long) (i - left[i]) * (right[i] - i);
        }
        return ans;
    }



    public long subArrayRanges1(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

}
