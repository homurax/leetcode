/**
 * 866. Prime Palindrome
 *
 *
 * Given an integer n, return the smallest prime palindrome greater than or equal to n.
 *
 * An integer is prime if it has exactly two divisors: 1 and itself. Note that 1 is not a prime number.
 *
 * For example, 2, 3, 5, 7, 11, and 13 are all primes.
 * An integer is a palindrome if it reads the same from left to right as it does from right to left.
 *
 * For example, 101 and 12321 are palindromes.
 * The test cases are generated so that the answer always exists and is in the range [2, 2 * 108].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: 7
 *
 *
 * Example 2:
 *
 * Input: n = 8
 * Output: 11
 *
 *
 * Example 3:
 *
 * Input: n = 13
 * Output: 101
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^8
 */
public class PrimePalindrome {

    // 不存在长度为 8 的质数
    // 能被 11 整除的数的特征：奇数位数字之和与偶数位数字之和的差是 11 的倍数，这个数就能被 11 整除
    // 对于 8 位回文数，abcddcba，其奇数位数字之和与偶数位数字之和相等，差为 0，能被 11 整除
    public int primePalindrome(int n) {
        while (true) {
            if (reverse(n) == n && isPrime(n)) {
                return n;
            }
            n++;
            if (10_000_000 < n && n < 100_000_000) {
                n = 100_000_000;
            }
        }
    }

    private boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int reverse(int n) {
        int rst = 0;
        while (n > 0) {
            rst = rst * 10 + (n % 10);
            n /= 10;
        }
        return rst;
    }

}
