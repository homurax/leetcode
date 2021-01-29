/**
 * 738. Monotone Increasing Digits
 *
 *
 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
 *
 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
 *
 * Example 1:
 * Input: N = 10
 * Output: 9
 *
 * Example 2:
 * Input: N = 1234
 * Output: 1234
 *
 * Example 3:
 * Input: N = 332
 * Output: 299
 *
 * Note: N is an integer in the range [0, 10^9].
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        int n = 0;
        int temp = N;
        while (temp > 0) {
            n++;
            temp /= 10;
        }

        int[] digits = new int[n];
        temp = N;
        for (int i = n - 1; i >= 0; i--) {
            digits[i] = temp % 10;
            temp /= 10;
        }

        for (int i = n - 1; i > 0; i--) {
            if (digits[i - 1] > digits[i]) {
                // 比如 1432 -> 1399 (1499 > 1432 不符合)
                digits[i - 1]--;
                for (int j = i; j < n; j++) {
                    digits[j] = 9;
                }
            }
        }

        int ans = 0;
        int d = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans += digits[i] * d;
            d *= 10;
        }
        return ans;
    }

}
