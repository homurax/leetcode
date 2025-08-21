/**
 * 1191. K-Concatenation Maximum Sum
 *
 *
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2], k = 3
 * Output: 9
 *
 *
 * Example 2:
 *
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 *
 *
 * Example 3:
 *
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class KConcatenationMaximumSum {

    public int kConcatenationMaxSum(int[] arr, int k) {
        if (k == 1) {
            return maxSubArray(arr, 1);
        }
        long ans = maxSubArray(arr, 2);
        int s = 0;
        for (int x : arr) {
            s += x;
        }
        ans += (long) Math.max(s, 0) * (k - 2);
        return (int) (ans % 1_000_000_007);
    }

    private int maxSubArray(int[] nums, int repeat) {
        int n = nums.length;
        int ans = 0;
        int f = 0;
        for (int i = 0; i < n * repeat; i++) {
            f = Math.max(f, 0) + nums[i % n];
            ans = Math.max(ans, f);
        }
        return ans;
    }

}
