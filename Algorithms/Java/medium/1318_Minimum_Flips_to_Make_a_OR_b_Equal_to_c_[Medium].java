/**
 * 1318. Minimum Flips to Make a OR b Equal to c
 *
 *
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 *
 *
 * Example 1:
 *
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 *
 *
 * Example 2:
 *
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 *
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= a <= 10^9
 * 2. 1 <= b <= 10^9
 * 3. 1 <= c <= 10^9
 */
public class MinimumFlipsToMakeAORBEqualToC {

    /**
     *  1. x & 1 -> 二进制表示的最低位
     *
     *  2. x & (1 << k)  ->  x 二进制表示的第 k 位（最低位为第 0 位）是否为 1
     *      > 0, 为 1
     *      = 0, 为 0
     *
     *  3. (x >> k) & 1 -> x 二进制表示的第 k 位（最低位为第 0 位）
     */
    public int minFlips(int a, int b, int c) {
        if ((a | b) == c) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int bit_a = (a >> i) & 1;
            int bit_b = (b >> i) & 1;
            int bit_c = (c >> i) & 1;
            if (bit_c == 0) { // bit_a bit_b 必须都为 0
                ans += bit_a + bit_b;
            } else { // bit_a bit_b 有一个为 1 即可
                ans += (bit_a + bit_b == 0) ? 1 : 0;
            }
        }
        return ans;
    }

}
