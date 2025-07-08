/**
 * 3233. Find the Count of Numbers Which Are Not Special
 *
 *
 * You are given 2 positive integers l and r. For any number x, all positive divisors of x except x are called the proper divisors of x.
 *
 * A number is called special if it has exactly 2 proper divisors. For example:
 *
 * The number 4 is special because it has proper divisors 1 and 2.
 * The number 6 is not special because it has proper divisors 1, 2, and 3.
 * Return the count of numbers in the range [l, r] that are not special.
 *
 *
 *
 * Example 1:
 *
 * Input: l = 5, r = 7
 *
 * Output: 3
 *
 * Explanation:
 *
 * There are no special numbers in the range [5, 7].
 *
 *
 * Example 2:
 *
 * Input: l = 4, r = 16
 *
 * Output: 11
 *
 * Explanation:
 *
 * The special numbers in the range [4, 16] are 4 and 9.
 *
 *
 *
 * Constraints:
 *
 * 1 <= l <= r <= 10^9
 */
public class FindTheCountOfNumbersWhichAreNotSpecial {

    // 特殊数字就是质数的平方 可以预处理
    private static final int MAX = 31622; // Math.sqrt(10^9)
    private static final int[] PRE_SUM = new int[MAX + 1];

    static {
        for (int i = 2; i <= MAX; i++) {
            if (PRE_SUM[i] == 0) { // 质数
                PRE_SUM[i] = PRE_SUM[i - 1] + 1; // 前缀和
                for (int j = i * i; j <= MAX; j += i) {
                    PRE_SUM[j] = -1; // 标记合数
                }
            } else {
                PRE_SUM[i] = PRE_SUM[i - 1];
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        return r - l + 1 - (PRE_SUM[(int) Math.sqrt(r)] - PRE_SUM[(int) Math.sqrt(l - 1)]);
    }

}
