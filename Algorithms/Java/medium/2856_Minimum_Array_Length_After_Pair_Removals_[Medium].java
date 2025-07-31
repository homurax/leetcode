/**
 * 2856. Minimum Array Length After Pair Removals
 *
 *
 * Given an integer array num sorted in non-decreasing order.
 *
 * You can perform the following operation any number of times:
 *
 * Choose two indices, i and j, where nums[i] < nums[j].
 * Then, remove the elements at indices i and j from nums. The remaining elements retain their original order, and the array is re-indexed.
 * Return the minimum length of nums after applying the operation zero or more times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 *
 * Output: 0
 *
 * Explanation:
 *
 *
 *
 * Example 2:
 *
 * Input: nums = [1,1,2,2,3,3]
 *
 * Output: 0
 *
 * Explanation:
 *
 *
 *
 * Example 3:
 *
 * Input: nums = [1000000000,1000000000]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Since both numbers are equal, they cannot be removed.
 *
 * Example 4:
 *
 * Input: nums = [2,3,4,4,4]
 *
 * Output: 1
 *
 * Explanation:
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums is sorted in non-decreasing order.
 */
public class MinimumArrayLengthAfterPairRemovals {

    // 出现次数最多的为 maxCnt
    // maxCnt * 2 > n -> 其余 n - maxCnt 均与 maxCnt 抵消，剩余 maxCnt * 2 - n
    // maxCnt * 2 <= n && n 为偶数 -> 其余数互相抵消至 maxCnt 个，再与 maxCnt 抵消，剩余 0
    // maxCnt * 2 <= n && n 为奇数 -> 剩余 1
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n / 2);
        int maxCnt = lowerBound(nums, x + 1) - lowerBound(nums, x);
        return Math.max(maxCnt * 2 - n, n % 2);
    }

    private int lowerBound(List<Integer> nums, int target) {
        int left = -1, right = nums.size();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

}
