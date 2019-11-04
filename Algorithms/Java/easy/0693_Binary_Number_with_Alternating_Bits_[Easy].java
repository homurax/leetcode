/**
 * 693. Binary Number with Alternating Bits
 *
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 *
 * Example 2:
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 *
 * Example 3:
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 *
 * Example 4:
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.
 */
public class BinaryNumberWithAlternatingBits {

    /**
     * 遍历比较
     */
    public boolean hasAlternatingBits(int n) {

        String bits = Integer.toBinaryString(n);
        for (int i = 0; i < bits.length() - 1; i++) {
            if (bits.charAt(i) == bits.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * n & 1   => 用来判断奇偶 即获取最后一位
     * n >>= 1 => 右移比较
     */
    public boolean hasAlternatingBits2(int n) {

        int curr = n & 1;
        n >>= 1;
        while (n > 0) {
            if (curr == (n & 1)) {
                return false;
            }
            curr = n & 1;
            n >>= 1;
        }
        return true;
    }

}
