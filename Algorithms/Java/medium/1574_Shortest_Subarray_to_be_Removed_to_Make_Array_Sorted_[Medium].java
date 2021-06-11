/**
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 *
 *
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the length of the shortest subarray to remove.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 *
 * Example 2:
 *
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 *
 * Example 3:
 *
 * Input: arr = [1,2,3]
 * Output: 0
 * Explanation: The array is already non-decreasing. We do not need to remove any elements.
 *
 * Example 4:
 *
 * Input: arr = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^5
 * 2. 0 <= arr[i] <= 10^9
 */
public class ShortestSubarrayToBeRemovedToMakeArraySorted {

    // 删 [0, left], 保证 [left + 1, n - 1] 非递减
    // 删 [right, n - 1], 保证 [0, right, - 1] 非递减
    // 删 [left, right], 保证 [0, left - 1] 拼接 [right + 1, n - 1] 非递减
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }
        if (left == n - 1) {
            return 0;
        }
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }
        // 删右边 [left + 1, n - 1], 删左边 [0, right - 1]
        int min = Math.min(n - left - 1, right);
        // 删中间
        int i = 0, j = right;
        while (i <= left && j <= n - 1) {
            if (arr[i] <= arr[j]) {
                // [0, i] 拼接 [j, n - 1] 非递减, 删除 [i + 1, j - 1]
                min = Math.min(min, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return min;
    }

}
