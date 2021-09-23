/**
 * 1362. Closest Divisors
 *
 *
 * Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.
 *
 * Return the two integers in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 8
 * Output: [3,3]
 * Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 = 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
 *
 * Example 2:
 *
 * Input: num = 123
 * Output: [5,25]
 *
 * Example 3:
 *
 * Input: num = 999
 * Output: [40,25]
 *
 *
 * Constraints:
 *
 * 1. 1 <= num <= 10^9
 */
public class ClosestDivisors {

    /**
     * (num + 2) % i == 1 -> (num + 1) % i == 0
     * 即 (num + 2) % i in {0, 1} 均符合条件
     *
     * num > 1 -> divisor >= 2 + 2
     * divisor 为质数 i 才会为 1, 除 2 以外质数都是奇数
     * divisor - 1 (num + 1) 为偶数，至少有 [2, (num + 1) / 2] 成立, i 不会递减到 1
     */
    public int[] closestDivisors(int num) {
        int divisor = num == 1 ? num + 1 : num + 2;
        int i = (int) Math.sqrt(divisor);
        while (divisor % i > 1) {
            i--;
        }
        return new int[]{i, divisor / i};
    }

}
