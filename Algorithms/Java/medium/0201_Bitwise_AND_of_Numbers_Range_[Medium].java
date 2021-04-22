/**
 * 201. Bitwise AND of Numbers Range
 *
 *
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 *
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 *
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 0 <= left <= right <= 2^31 - 1
 */
public class BitwiseANDOfNumbersRange {

    /**
     * 显然真的遍历一遍按位与肯定超时
     *
     * 00100(2)
     * 00101(2)
     * 00110(2)
     * 00111(2)
     * --------
     * 00100
     *
     * 二进制字符串的公共前缀再用零补上后面的剩余位
     * 1. 右移到相等即找到公共前缀
     * 2. 左移补零
     */
    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count++;
        }
        return left << count;
    }

    /**
     * Brian Kernighan
     * n & (n - 1)
     * 抹去 n 最右侧的 1
     */
    public int rangeBitwiseAnd1(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return right;
    }
}
