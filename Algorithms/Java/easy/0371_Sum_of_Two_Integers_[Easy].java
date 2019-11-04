/**
 * 371. Sum of Two Integers
 *
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = -2, b = 3
 * Output: 1
 */
public class SumOfTwoIntegers {

    /**
     * 两数异或 => 无进位的相加
     * 两数相与后左移 => 进位位置
     * 递归至进位为0
     *  3 ^ 2   3 & 2
     *  0 1 1   0 1 1   ^ 0 0 1
     *  0 1 0   0 1 0     1 0 0
     * ------- -------   -------
     *  0 0 1   0 1 0     1 0 1
     */
    public int getSum(int a, int b) {

        int sum = a ^ b;
        int carry = (a & b) << 1;
        while (carry != 0) {
            return getSum(sum, carry);
        }
        return sum;
    }
}
