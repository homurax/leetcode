/**
 * 3201. Find the Maximum Length of Valid Subsequence I
 *
 *
 * You are given an integer array nums.
 * A subsequence sub of nums with length x is called valid if it satisfies:
 *
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
 * Return the length of the longest valid subsequence of nums.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 *
 * Output: 4
 *
 * Explanation:
 *
 * The longest valid subsequence is [1, 2, 3, 4].
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,1,1,2,1,2]
 *
 * Output: 6
 *
 * Explanation:
 *
 * The longest valid subsequence is [1, 2, 1, 2, 1, 2].
 *
 *
 * Example 3:
 *
 * Input: nums = [1,3]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The longest valid subsequence is [1, 3].
 *
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 2 * 10^5
 * 1 <= nums[i] <= 10^7
 */
public class FindTheMaximumLengthOfValidSubsequenceI {

    // (a + b) % k = (b + c) % k
    // -> (a + b - (b + c)) % k  = 0
    // -> (a - c) % k = 0
    // 子序列的奇数项都关于模 k 同余，偶数项都关于模 k 同余, k = 2
    // 枚举子序列相邻两项模 k 的结果为 0, 1
    public int maximumLength(int[] nums) {
        int ans = 0;
        for (int m = 0; m < 2; m++) {
            int[] f = new int[2];
            for (int num : nums) {
                num %= 2;
                f[num] = f[(m - num + 2) % 2] + 1;
                ans = Math.max(ans, f[num]);
            }
        }
        return ans;
    }


}
