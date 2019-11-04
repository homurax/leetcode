/**
 * 171. Excel Sheet Column Number
 *
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * Example 1:
 *
 * Input: "A"
 * Output: 1
 *
 * Example 2:
 *
 * Input: "AB"
 * Output: 28
 *
 * Example 3:
 *
 * Input: "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {

    /**
     * ASCII A-Z -> 65-90
     * ABC = A * 26^2 + B * 26^1 + C * 26^0 = 1*26^2 + 2*26^1 + 3
     */
    public int titleToNumber(String s) {

        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int n = s.charAt(i) - 'A' + 1;
            sum += n * Math.pow(26, length - i - 1);
        }
        return sum;
    }



    public int titleToNumber2(String s) {

        int ans =0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            ans *= 26;
            ans = ans + s.charAt(i) - 'A' + 1;
        }
        return ans;
    }


}
