/**
 * 2429. Minimize XOR
 *
 *
 * Given two positive integers num1 and num2, find the positive integer x such that:
 *
 * x has the same number of set bits as num2, and
 * The value x XOR num1 is minimal.
 * Note that XOR is the bitwise XOR operation.
 *
 * Return the integer x. The test cases are generated such that x is uniquely determined.
 *
 * The number of set bits of an integer is the number of 1's in its binary representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = 3, num2 = 5
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0011 and 0101, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
 *
 *
 * Example 2:
 *
 * Input: num1 = 1, num2 = 12
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0001 and 1100, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
 *
 *
 * Constraints:
 *
 * 1 <= num1, num2 <= 10^9
 */
public class MinimizeXOR {

    // 贪心
    // num2 中 1 的个数应当优先匹配 num1 中的 1
    // 如果 num2 有多余的 1, 将 num1 低位的 0 变为 1
    // 如果 num2 的 1 更少, 将 num1 低位的 1 变为 0
    public int minimizeXor(int num1, int num2) {
        int cnt1 = Integer.bitCount(num1);
        int cnt2 = Integer.bitCount(num2);
        for (; cnt2 < cnt1; cnt2++) {
            num1 &= num1 - 1; // 最低的 1 变成 0
        }
        for (; cnt2 > cnt1; cnt2--) {
            num1 |= num1 + 1; // 最低的 0 变成 1
        }
        return num1;
    }

}
