/**
 * 2384. Largest Palindromic Number
 *
 *
 * You are given a string num consisting of digits only.
 *
 * Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.
 *
 * Notes:
 *
 * You do not need to use all the digits of num, but you must use at least one digit.
 * The digits can be reordered.
 *
 *
 * Example 1:
 *
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation:
 * Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can be formed.
 *
 * Example 2:
 *
 * Input: num = "00009"
 * Output: "9"
 * Explanation:
 * It can be shown that "9" is the largest palindromic integer that can be formed.
 * Note that the integer returned should not contain leading zeroes.
 *
 *
 * Constraints:
 *
 * 1. 1 <= num.length <= 10^5
 * 2. num consists of digits.
 */
public class LargestPalindromicNumber {

    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (int i = 0; i < num.length(); i++) {
            cnt[num.charAt(i) - '0']++;
        }
        if (cnt[0] == num.length()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0 || (i == 0 && sb.length() > 0); i--) {
            int l = cnt[i] / 2;
            if (l > 0) {
                for (int j = 0; j < l; j++) {
                    sb.append(i);
                }
            }
        }
        int n = sb.length() - 1;
        // 中心可以添加一个
        for (int i = 9; i >= 0; i--) {
            if ((cnt[i] & 1) == 1) {
                sb.append(i);
                break;
            }
        }
        // 镜像
        for (int i = n; i >= 0; i--) {
            sb.append(sb.charAt(i));
        }
        return sb.toString();
    }

}
