/**
 * 1186. Maximum Subarray Sum with One Deletion
 *
 *
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 *
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 *
 * Example 2:
 *
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 *
 * Example 3:
 *
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^5
 * 2. -10^4 <= arr[i] <= 10^4
 */
public class MaximumSubarraySumWithOneDeletion {

    /**
     * f[i] -> sum(arr[:i]), 不删除元素
     * g[i] -> sum(arr[:i]), 删除元素
     *
     * 当前元素累加 or 重选选择
     * f[i] = max(f[i - 1] + arr[i], arr[i])
     * 加上当前元素（之前发生过删除） or 删除当前元素（就是 f[i - 1]）
     * g[i] = max(g[i - 1] + arr[i], f[i - 1])
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = arr[0];
        g[0] = -200001;
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1] + arr[i], arr[i]);
            g[i] = Math.max(g[i - 1] + arr[i], f[i - 1]);
            ans = Math.max(ans, Math.max(f[i], g[i]));
        }
        return ans;
    }


    public int maximumSum1(int[] arr) {
        int ans = Integer.MIN_VALUE;
        for (int num : arr) {
            ans = Math.max(ans, num);
        }
        if (ans <= 0) {
            return ans;
        }

        int sum = 0, removeSum = 0;
        for (int num : arr) {
            int temp = sum;
            sum += num;
            removeSum = Math.max(removeSum + num, temp);
            if (ans < sum) {
                ans = sum;
            }
            if (ans < removeSum) {
                ans = removeSum;
            }
            if (sum < 0) {
                sum = 0;
            }
            if (removeSum < 0) {
                removeSum = 0;
            }
        }
        return ans;
    }
}
