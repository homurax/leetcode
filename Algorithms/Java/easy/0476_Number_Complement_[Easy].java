/**
 * 476. Number Complement
 *
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 *
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 */
public class NumberComplement {

    /**
     * 用同位数最大值与num做异或
     */
    public int findComplement(int num) {

        int temp = num;
        int i = 0;
        while (temp > 0) {
            temp >>= 1;
            i =  (i << 1) + 1;
        }
        return num ^ i;
    }
}
