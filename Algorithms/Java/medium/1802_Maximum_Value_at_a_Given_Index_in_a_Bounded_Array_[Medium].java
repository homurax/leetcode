/**
 * 1802. Maximum Value at a Given Index in a Bounded Array
 *
 *
 * You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 *
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 *
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, index = 2,  maxSum = 6
 * Output: 2
 * Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
 * There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
 *
 *
 * Example 2:
 *
 * Input: n = 6, index = 1,  maxSum = 10
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= maxSum <= 10^9
 * 2. 0 <= index < n
 */
public class MaximumValueAtAGivenIndexInABoundedArray {

    // 二分查找
    // 对于 nums[index] = x 的符合条件的数组，最小的数组和构建方式一定是 index 两侧的值从 x - 1 降至 1
    // 通过二分确定 x
    // sum(max, count) = 最大值为 max，元素个数为 count 的数组和
    // 最小的数组和 = 前半部分（不含 index） + 后半部分（含 index） = sum(x - 1, index) + sum(x, n - index)
    // max < n 时，如 [1, 1, 2, 3]:
    //      sum(3, 4) = ((1 + max) * max) / 2 + count - max
    // max >= n 时，如 [2, 3]:
    //      sum(3, 2) = (1 + max) * max / 2 - (1 + max - count) * (max - count) / 2
    //                = (1 + max + max - count) * count / 2
    public int maxValue(int n, int index, int maxSum) {
        int l = 1, r = maxSum;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private long sum(long max, int cnt) {
        return max >= cnt ? (1 + max + max - cnt) * cnt / 2 : (1 + max) * max / 2 + cnt - max;
    }


    public int maxValue1(int n, int index, int maxSum) {
        int ans = 0;
        int extend = 1;
        int sum = n;
        while (sum <= maxSum) {
            ans++;
            if (extend == n) { // 左右都铺满了
                return ans + (maxSum - sum) / n;
            }
            sum += extend;
            if (index - ans >= 0) { // 左侧可以扩展
                extend++;
            }
            if (index + ans < n) { // 右侧可以扩展
                extend++;
            }
        }
        return ans;
    }

}
