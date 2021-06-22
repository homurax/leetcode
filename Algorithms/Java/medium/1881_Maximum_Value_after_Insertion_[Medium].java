/**
 * 1881. Maximum Value after Insertion
 *
 * You are given a very large integer n, represented as a string, and an integer digit x. The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 *
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n. You cannot insert x to the left of the negative sign.
 *
 * For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: n = "99", x = 9
 * Output: "999"
 * Explanation: The result is the same regardless of where you insert 9.
 *
 * Example 2:
 *
 * Input: n = "-13", x = 2
 * Output: "-123"
 * Explanation: You can make n one of {-213, -123, -132}, and the largest of those three is -123.
 *
 *
 * Constraints:
 *
 * 1. 1 <= n.length <= 10^5
 * 2. 1 <= x <= 9
 * 3. The digits in n are in the range [1, 9].
 * 4. n is a valid representation of an integer.
 * 5. In the case of a negative n, it will begin with '-'.
 */
public class MaximumValueAfterInsertion {


    // n >= 0, 大数尽量高位插入, n[idx] < x, n.insert(idx, x)
    // n < 0, 小数尽量高位插入, n[idx] > x, n.insert(idx, x)
    public String maxValue(String n, int x) {
        int write = 0;
        int len = n.length();
        char[] ans = new char[len + 1];
        char[] digits = n.toCharArray();
        boolean minus = n.charAt(0) == '-';
        for (int i = 0; i < len; i++) {
            char digit = digits[i];
            if (minus && digit - '0' > x) {
                ans[write++] = (char) (x + '0');
                while (i < len) {
                    ans[write++] =  digits[i++];
                }
                break;
            } else if (!minus && digit - '0' < x) {
                ans[write++] = (char) (x + '0');
                while (i < len) {
                    ans[write++] =  digits[i++];
                }
                break;
            } else {
                ans[write++] = digit;
            }
        }
        if (write < len + 1) {
            ans[write] = (char) (x + '0');
        }
        return new String(ans);
    }

    // n[:pos] + str(x) + n[pos:]
    public String maxValue2(String n, int x) {
        StringBuilder sb = new StringBuilder();
        char digit = (char) (x + '0');
        int pos;
        if (n.charAt(0) == '-') {
            pos = 1;
            while (pos < n.length() && n.charAt(pos) <= digit) {
                pos++;
            }
        } else {
            pos = 0;
            while (pos < n.length() && n.charAt(pos) >= digit) {
                pos++;
            }
        }
        sb.append(n, 0, pos);
        sb.append(digit);
        sb.append(n.substring(pos));
        return sb.toString();
    }

}
