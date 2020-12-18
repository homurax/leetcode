/**
 * 1461. Check If a String Contains All Binary Codes of Size K
 *
 *
 * Given a binary string s and an integer k.
 *
 * Return True if every binary code of length k is a substring of s. Otherwise, return False.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 *
 * Example 2:
 *
 * Input: s = "00110", k = 2
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 *
 * Example 4:
 *
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
 *
 * Example 5:
 *
 * Input: s = "0000000001011100", k = 4
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 5 * 10^5
 * 2. s consists of 0's and 1's only.
 * 3. 1 <= k <= 20
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {

    // TLE
    public boolean hasAllCodes1(String s, int k) {
        return check(new StringBuilder(), k, s);
    }

    private boolean check(StringBuilder sb, int k, String s) {
        if (sb.length() == k) {
            return s.contains(sb);
        }
        boolean flag1 = check(sb.append('0'), k, s);
        sb.deleteCharAt(sb.length() - 1);
        boolean flag2 = check(sb.append('1'), k, s);
        sb.deleteCharAt(sb.length() - 1);
        return flag1 && flag2;
    }

    //  s 如果过包含 2^k 个长度为 k 的二进制串，它的长度至少为 2^k + k - 1
    public boolean hasAllCodes2(String s, int k) {
        if (s.length() < (1 << k) + k - 1) {
            return false;
        }
        Set<String> exists = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            exists.add(s.substring(i, i + k));
        }
        return exists.size() == (1 << k);
    }


    public boolean hasAllCodes(String s, int k) {
        if (s.length() < (1 << k) + k - 1) {
            return false;
        }
        int n = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < k - 1; i++) {
            n = (n << 1) + (chars[i] - '0');
        }
        int size = 1 << k;
        int mask = size - 1;
        boolean[] exists = new boolean[size];
        for (int i = k - 1; i < chars.length; i++) {
            n = ((n << 1) + chars[i] - '0') & mask;
            if (!exists[n]) {
                exists[n] = true;
                if (--size == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
