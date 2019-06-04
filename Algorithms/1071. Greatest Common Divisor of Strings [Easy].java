/**
 * 1071. Greatest Common Divisor of Strings
 *
 * For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)
 *
 * Return the largest string X such that X divides str1 and X divides str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 *
 * Note:
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] and str2[i] are English uppercase letters.
 */
public class GreatestCommonDivisorOfStrings {


    public String gcdOfStrings(String str1, String str2) {

        String shortest = str1.length() > str2.length() ? str2 : str1;

        int length = shortest.length();

        for (int i = 1; i <= length; i++) {

            if (length % i != 0) continue;

            String pattern = shortest.substring(0, length / i);
            if (str1.replaceAll(pattern, "").equals("") && str2.replaceAll(pattern, "").equals(""))
                return pattern;
        }

        return "";
    }


    /**
     * 确定存在字符串的最大公因子后确定因子长度即可
     */
    public String gcdOfStrings2(String s1, String s2) {

        String s3 = s1 + s2;
        String s4 = s2 + s1;

        if (!s3.equals(s4))
            return "";

        int len = extent(s1.length(), s2.length());

        return s2.substring(0, len);
    }

    private static int extent(int len1, int len2) {
        if (len2 == 0) return len1;
        return extent(len2, len1 % len2);
    }

}
