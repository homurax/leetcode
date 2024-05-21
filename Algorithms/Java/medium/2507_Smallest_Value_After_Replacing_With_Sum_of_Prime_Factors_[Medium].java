/**
 * 2507. Smallest Value After Replacing With Sum of Prime Factors
 *
 *
 * You are given a positive integer n.
 *
 * Continuously replace n with the sum of its prime factors.
 *
 * Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it divides n.
 * Return the smallest value n will take on.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 15
 * Output: 5
 * Explanation: Initially, n = 15.
 * 15 = 3 * 5, so replace n with 3 + 5 = 8.
 * 8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
 * 6 = 2 * 3, so replace n with 2 + 3 = 5.
 * 5 is the smallest value n will take on.
 *
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, n = 3.
 * 3 is the smallest value n will take on.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 */
public class SmallestValueAfterReplacingWithSumOfPrimeFactors {

    public int smallestValue(int n) {
        while (true) {
            int x = n, sum = 0;
            for (int i = 2; i * i <= x; i++) {
                for (; x % i == 0; x /= i) {
                    sum += i;
                }
            }
            if (x > 1) {
                sum += x;
            }
            if (sum == n) {
                return n;
            }
            n = sum;
        }
    }

}
