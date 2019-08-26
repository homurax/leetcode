/**
 * 712. Minimum ASCII Delete Sum for Two Strings
 *
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 *
 * Example 1:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 *
 * Example 2:
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 *
 * Note:
 *
 * 0 < s1.length, s2.length <= 1000.
 * All elements of each string will have an ASCII value in [97, 122].
 */
public class MinimumASCIIDeleteSumForTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i), dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public int minimumDeleteSum2(String s1, String s2) {

        int len = s1.length();
        int total = 0;
        int[][] pos = new int[26][0];
        char[] s1Arr = s1.toCharArray();

        for (int i = 0; i < len; i++) {
            total += (int) s1Arr[i];
            int index = s1Arr[i] - 'a';
            int[] temp = pos[index];
            pos[index] = Arrays.copyOf(temp, temp.length + 1);
            pos[index][temp.length] = i;
        }

        int[] dp = new int[len];
        for (int i = 0; i < s2.length(); i++) {
            int ch = (int) s2.charAt(i);
            total += ch;
            int[] ps = pos[ch - 'a'];
            for (int j = ps.length - 1; j >= 0; j--) {
                int p = ps[j];
                int v = ch + (p > 0 ? dp[p - 1] : 0);
                while (p < len && v > dp[p]) {
                    dp[p] = v;
                    p += 1;
                }
            }
        }

        return total - (dp[len - 1] << 1);
    }
}
