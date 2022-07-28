/**
 * 2233. Maximum Product After K Increments
 *
 *
 * You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.
 *
 * Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7. Note that you should maximize the product before taking the modulo.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,4], k = 5
 * Output: 20
 * Explanation: Increment the first number 5 times.
 * Now nums = [5, 4], with a product of 5 * 4 = 20.
 * It can be shown that 20 is maximum product possible, so we return 20.
 * Note that there may be other ways to increment nums to have the maximum product.
 *
 *
 * Example 2:
 *
 * Input: nums = [6,3,3,2], k = 2
 * Output: 216
 * Explanation: Increment the second number 1 time and increment the fourth number 1 time.
 * Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
 * It can be shown that 216 is maximum product possible, so we return 216.
 * Note that there may be other ways to increment nums to have the maximum product.
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length, k <= 10^5
 * 2. 0 <= nums[i] <= 10^6
 */
public class MaximumProductAfterKIncrements {

    // 优先增加较小的数
    // 有可能出现所有元素都会被更新的情况 排序后添加一个足够大的元素
    // 可以用二分来找临界值
    public int maximumProduct(int[] nums, int k) {
        int n = nums.length;
        int[] nums2 = Arrays.copyOf(nums, n + 1);
        nums2[n] = Integer.MAX_VALUE;
        Arrays.sort(nums2);
        for (int i = 0; i < n; i++) {
            long temp = (i + 1L) * (nums2[i + 1] - nums2[i]);
            if (k >= temp) {
                k -= temp;
                continue;
            }
            int add = k / (i + 1);
            int limit = k % (i + 1);
            for (int j = 0; j <= i; j++) {
                nums2[j] = nums2[i] + add;
                if (j < limit) {
                    nums2[j]++;
                }
            }
            break;
        }
        int mod = 1000000007;
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * nums2[i] % mod;
        }
        return (int) ans;
    }

}
