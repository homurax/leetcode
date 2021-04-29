/**
 * 845. Longest Mountain in Array
 *
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 *
 * Example 2:
 *
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 *
 *
 * Follow up:
 *
 * 1. Can you solve it using only one pass?
 * 2. Can you solve it in O(1) space?
 */
public class LongestMountainInArray {

    public int longestMountain(int[] arr) {
        int ans = 0, left = 0, n = arr.length;
        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[left + 1]) {
                // 找山顶
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    right++;
                }
                // 存在右侧山脚 确保严格递减
                if (right + 1 < n && arr[right] > arr[right + 1]) {
                    // 右侧山脚
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        right++;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    right++;
                }
            }
            left = right;
        }
        return ans;
    }

    public int longestMountain1(int[] arr) {
        int ans = 0, index = 1, n = arr.length;
        while (index < n) {
            while (index < n && arr[index - 1] == arr[index]) {
                index++;
            }
            int leftLen = 0;
            while (index < n && arr[index - 1] < arr[index]) {
                leftLen++;
                index++;
            }
            int rightLen = 0;
            while (index < n && arr[index - 1] > arr[index]) {
                rightLen++;
                index++;
            }
            if (leftLen > 0 && rightLen > 0) {
                ans = Math.max(ans, rightLen + leftLen + 1);
            }
        }
        return ans;
    }

}
