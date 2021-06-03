/**
 * 43. Multiply Strings
 *
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1. 1 <= num1.length, num2.length <= 200
 * 2. num1 and num2 consist of digits only.
 * 3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // len(num1) = m, len(num2) = m, max_len(num1 * num2) = m + n
        int m = num1.length(), n = num2.length();
        int[] ans = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                // num1[i] x num2[j] <-> ans[i + j], ans[i + j + 1]
                int sum = (ans[i + j + 1] + n1 * n2);
                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (i == 0 && ans[i] == 0) {
                continue;
            }
            sb.append(ans[i]);
        }
        return sb.toString();
    }

}
