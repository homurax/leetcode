/**
 * 1856. Maximum Subarray Min-Product
 * <p>
 * <p>
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 * <p>
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.
 * <p>
 * Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^7
 */
public class MaximumSubarrayMinProduct {


    // 将每一位元素视作最小值 然后确定子数组的边界
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                right[stack.pop()] = i - 1;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = n - 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left[stack.pop()] = i + 1;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = 0;
        }
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, nums[i] * (preSum[right[i]] - preSum[left[i]] + nums[left[i]]));
        }
        return (int) (ans % 1000000007);
    }


    public int maxSumMinProduct2(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        int[] stack = new int[n + 1];
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            preSum[i] = arr[i] + (i == 0 ? 0 : preSum[i - 1]);
        }
        n++;
        int index = -1;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (index > -1 && arr[stack[index]] >= arr[i]) {
                int min = arr[stack[index]];
                long sum = preSum[i - 1] - (index == 0 ? 0 : preSum[stack[index - 1]]);
                ans = Math.max(ans, min * sum);
                index--;
            }
            stack[++index] = i;
        }
        return (int) (ans % 1000000007);
    }

}
