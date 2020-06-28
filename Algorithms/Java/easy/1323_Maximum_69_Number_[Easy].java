/**
 * 1323. Maximum 69 Number
 *
 * Given a positive integer num consisting only of digits 6 and 9.
 *
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 *
 *
 * Example 1:
 *
 * Input: num = 9669
 * Output: 9969
 * Explanation:
 * Changing the first digit results in 6669.
 * Changing the second digit results in 9969.
 * Changing the third digit results in 9699.
 * Changing the fourth digit results in 9666.
 * The maximum number is 9969.
 *
 * Example 2:
 *
 * Input: num = 9996
 * Output: 9999
 * Explanation: Changing the last digit 6 to 9 results in the maximum number.
 *
 * Example 3:
 *
 * Input: num = 9999
 * Output: 9999
 * Explanation: It is better not to apply any change.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 10^4
 * num's digits are 6 or 9.
 */
public class Maximum69Number {

    public int maximum69Number (int num) {
        int temp = num;
        int digits = -1, i = 0;
        while (temp > 0) {
            if (temp % 10 == 6) {
                digits = i;
            }
            temp /= 10;
            i++;
        }
        if (digits == -1) {
            return num;
        } else {
            return num + (int) Math.pow(10, digits) * 3;
        }
    }

    public int maximum69Number2(int num) {
        if (num / 1000 == 6) {
            num += 3000;
        } else if (num % 1000 / 100 == 6) {
            num += 300;
        } else if (num % 100 / 10 == 6) {
            num += 30;
        } else if (num % 10 == 6) {
            num += 3;
        }
        return num;
    }

}
