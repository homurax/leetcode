/**
 * 1015. Smallest Integer Divisible by K
 *
 *
 * Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.
 *
 * Return the length of n. If there is no such n, return -1.
 *
 * Note: n may not fit in a 64-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: k = 1
 * Output: 1
 * Explanation: The smallest answer is n = 1, which has length 1.
 *
 *
 * Example 2:
 *
 * Input: k = 2
 * Output: -1
 * Explanation: There is no such positive integer n divisible by 2.
 *
 *
 * Example 3:
 *
 * Input: k = 3
 * Output: 3
 * Explanation: The smallest answer is n = 111, which has length 3.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 10^5
 */
public class SmallestIntegerDivisibleByK {

    // n 为 1, 11, 111 ...
    // x = n % k
    // 下一个 n % k = (10x + 1) % k, x 初始值为 1, 如果 x 出现重复则不满足
    // 与使用 set 判断重复相比, 可以计算 k 次，没有 0 则不满足
    // n % k in [0, k - 1]，如果循环 k 次没有 0 则根据抽屉原理必然存在重复
    // n 为 1 结尾, 不会是 2 或者 5 的倍数, 如果 k 是 2 或者 5 的倍数则不满足
    public int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0) {
            return -1;
        }
        int x = 1 % K;
        for (int i = 1; ; i++) {
            if (x == 0) {
                return i;
            }
            x = (x * 10 + 1) % K;
        }
    }

}
