/**
 * 643. Maximum Average Subarray I
 *
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.
 *
 * Example 1:
 *
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 * Note:
 *
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 *
 */
public class MaximumAverageSubarrayI {

    /**
     * 记录累积和 依次比较取最大值
     */
    public double findMaxAverage(int[] nums, int k) {

        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        double ans = sum[k - 1] * 1.0 / k;
        for (int i = k; i < sum.length; i++) {
            ans = Math.max(ans, (sum[i] - sum[i - k]) * 1.0 / k);
        }

        return ans;
    }


    /**
     * 滑窗法 每次加上新的元素 舍弃尾部
     */
    public double findMaxAverage2(int[] nums, int k) {

        double sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];

        double ans = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            ans = Math.max(ans, sum);
        }
        return ans / k;
    }


}
