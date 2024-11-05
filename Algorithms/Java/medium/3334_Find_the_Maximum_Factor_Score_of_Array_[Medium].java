/**
 * 3334. Find the Maximum Factor Score of Array
 *
 *
 * You are given an integer array nums.
 *
 * The factor score of an array is defined as the product of the LCM and GCD of all elements of that array.
 *
 * Return the maximum factor score of nums after removing at most one element from it.
 *
 * Note that both the LCM and GCD of a single number are the number itself, and the factor score of an empty array is 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,8,16]
 *
 * Output: 64
 *
 * Explanation:
 *
 * On removing 2, the GCD of the rest of the elements is 4 while the LCM is 16, which gives a maximum factor score of 4 * 16 = 64.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 *
 * Output: 60
 *
 * Explanation:
 *
 * The maximum factor score of 60 can be obtained without removing any elements.
 *
 *
 * Example 3:
 *
 * Input: nums = [3]
 *
 * Output: 9
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 30
 */
public class FindTheMaximumFactorScoreOfArray {

    // gcd(a, b, c) = gcd(gcd(a, b), c)
    // lcm(a, b, c) = lcm(lcm(a, b), c)
    // gcd(0, x) = x
    // lcd(0, x) = 0
    public long maxScore(int[] nums) {
        int n = nums.length;
        int[] sufGcd = new int[n + 1];
        long[] sufLcm = new long[n + 1];
        sufLcm[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            sufGcd[i] = (int) gcd(sufGcd[i + 1], nums[i]);
            sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
        }

        long ans = sufGcd[0] * sufLcm[0];
        int preGcd = 0;
        long preLcm = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
            preGcd = (int) gcd(preGcd, nums[i]);
            preLcm = lcm(preLcm, nums[i]);
        }
        return ans;
    }

    private long gcd(long a, long b) {
        while (a != 0) {
            long tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}
