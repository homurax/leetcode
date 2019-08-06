/**
 * 1017. Convert to Base -2
 *
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 *
 * The returned string must have no leading zeroes, unless the string is "0".
 *
 *
 *
 * Example 1:
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
 *
 * Example 2:
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 *
 * Example 3:
 *
 * Input: 4
 * Output: "100"
 * Explantion: (-2) ^ 2 = 4
 */
public class ConvertToBaseNegative2 {

    public String baseNeg2(int N) {

        String zero = "0";
        if (N == 0) {
            return zero;
        }

        StringBuilder sb = new StringBuilder();
        String one = "1";
        int n = N;
        while (n != 0) {
            if (n % 2 == 0) {
                sb.append(zero);
                n = n / (-2);
            } else {
                sb.append(one);
                n = (n - 1) / (-2);
            }
        }

        return sb.reverse().toString();
    }


    public String baseNeg22(int N) {
        return Integer.toBinaryString(1431655765 ^ (1431655765 - N));
    }

}
