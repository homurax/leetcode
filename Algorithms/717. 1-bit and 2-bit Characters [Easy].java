/**
 * 717. 1-bit and 2-bit Characters
 *
 * We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).
 *
 * Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.
 *
 * Example 1:
 * Input:
 * bits = [1, 0, 0]
 * Output: True
 * Explanation:
 * The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
 *
 * Example 2:
 * Input:
 * bits = [1, 1, 1, 0]
 * Output: False
 * Explanation:
 * The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
 * Note:
 *
 * 1 <= len(bits) <= 1000.
 * bits[i] is always 0 or 1.
 */
public class BitAnd2BitCharacters {


    /**
     * 从头开始遍历遇到1必定为two bits 遇到0必定为one bit
     * 判断最后截止位置即可
     */
    public boolean isOneBitCharacter(int[] bits) {

        int length = bits.length;
        if (length == 1 || bits[length - 2] == 0) return true;

        int path = 0, index = 0;
        while (index < length) {
            path = bits[index] == 1 ? 2 : 1;
            index += path;
        }
        return path == 1 && index == length;
    }

}
