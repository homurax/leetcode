/**
 * 415. Add Strings
 *
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();
        int temp = 0;
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        while (index1 >= 0 || index2>= 0 || temp != 0) {
            if (index1 >= 0)
                temp += num1.charAt(index1--) - '0';
            if (index2 >= 0)
                temp += num2.charAt(index2--) - '0';
            sb.append(temp % 10);
            temp /= 10;
        }
        return sb.reverse().toString();
    }
}
