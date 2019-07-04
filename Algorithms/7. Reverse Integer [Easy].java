/**
 * 7. Reverse Integer
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 *
 * Example 2:
 *
 * Input: -123
 * Output: -321
 *
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    /**
     * 注意检查溢出
     */
    public int reverse(int x) {

        int ans = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 2147483647
            if (ans > max || (ans == max && pop > 7))
                return 0;
            // -2147483648
            if (ans < min || (ans == min && pop < -8))
                return 0;
            ans = ans * 10 + pop;
        }
        return ans;
    }
}
