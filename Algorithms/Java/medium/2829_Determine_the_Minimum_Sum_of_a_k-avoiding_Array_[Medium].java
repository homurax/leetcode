/**
 * 2829. Determine the Minimum Sum of a k-avoiding Array
 *
 *
 * You are given two integers, n and k.
 *
 * An array of distinct positive integers is called a k-avoiding array if there does not exist any pair of distinct elements that sum to k.
 *
 * Return the minimum possible sum of a k-avoiding array of length n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, k = 4
 * Output: 18
 * Explanation: Consider the k-avoiding array [1,2,4,5,6], which has a sum of 18.
 * It can be proven that there is no k-avoiding array with a sum less than 18.
 *
 *
 * Example 2:
 *
 * Input: n = 2, k = 6
 * Output: 3
 * Explanation: We can construct the array [1,2], which has a sum of 3.
 * It can be proven that there is no k-avoiding array with a sum less than 3.
 *
 *
 * Constraints:
 *
 * 1 <= n, k <= 50
 */
public class DetermineTheMinimumSumOfAKAvoidingArray {

    // [1, k - 1] 只能选择 k / 2 个
    // 第一段, m = min(k / 2, n), (1 + m) * m / 2
    // 第二段选择 n - m 个数, [k, k + n - m - 1], (2 * k + n - m - 1) * (n - m) / 2
    // 当 k / 2 > n 时, m = n, 的第二段自然为 0
    public int minimumSum(int n, int k) {
        int m = Math.min(k / 2, n);
        return ((1 + m) * m + (k * 2 + n - m - 1) * (n - m)) / 2;
    }

}
