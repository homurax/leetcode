/**
 * 788. Rotated Digits
 *
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored); 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 *
 * Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Note:
 *
 * N  will be in range [1, 10000].
 */
public class RotatedDigits {

    public int rotatedDigits(int N) {
        char[] digits = String.valueOf(N).toCharArray();
        int K = digits.length;
        // i: 正在写第 i 位数字
        // equality: 已经写出的 j 位数字是否等于 N 的 j 位前缀
        // involution: 从最高位到比当前位高一位的这段前缀中是否含有 2569 中的任意一个数字
        // dp(i, equality, involution)
        int[][][] memory = new int[K + 1][2][2];
        memory[K][0][1] = memory[K][1][1] = 1;
        for (int i = K - 1; i >= 0; i--) {
            for (int equality = 0; equality <= 1; equality++)
                for (int involution = 0; involution <= 1; involution++) {
                    int ans = 0;
                    for (char d = '0'; d <= (equality == 1 ? digits[i] : '9'); d++) {
                        if (d == '3' || d == '4' || d == '7') {
                            continue;
                        }
                        boolean flag = (d == '2' || d == '5' || d == '6' || d == '9');
                        ans += memory[i + 1][d == digits[i] ? equality : 0][flag ? 1 : involution];
                    }
                    memory[i][equality][involution] = ans;
                }
        }

        // dp(0, true, false)
        return memory[0][1][0];
    }
}
