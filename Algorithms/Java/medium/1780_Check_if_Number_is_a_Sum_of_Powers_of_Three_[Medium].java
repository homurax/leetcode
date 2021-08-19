/**
 * 1780. Check if Number is a Sum of Powers of Three
 *
 *
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
 *
 * An integer y is a power of three if there exists an integer x such that y == 3x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: true
 * Explanation: 12 = 31 + 32
 *
 * Example 2:
 *
 * Input: n = 91
 * Output: true
 * Explanation: 91 = 30 + 32 + 34
 *
 * Example 3:
 *
 * Input: n = 21
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^7
 */
public class CheckIfNumberIsASumOfPowersOfThree {

    /**
     * 转换为三进制, 不出现 2, 则一定可以表示成  0 * 3^n 与 1 * 3^n 之和
     */
    public boolean checkPowersOfThree(int n) {
        // return n < 2 || (n % 3 != 2 && checkPowersOfThree(n / 3));
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }

}
