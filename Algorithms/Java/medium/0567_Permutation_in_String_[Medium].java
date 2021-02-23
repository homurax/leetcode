/**
 * 567. Permutation in String
 *
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Constraints:
 *
 * 1. The input strings only contain lower case letters.
 * 2. The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {

    // 相等长度内是否为字串
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']--;
            count[s2.charAt(i) - 'a']++;
        }
        int diff = 0;
        for (int i : count) {
            if (i != 0) {
                diff++;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; i++) {
            int in = s2.charAt(i) - 'a';
            int out = s2.charAt(i - n) - 'a';
            if (in == out) {
                continue;
            }
            // 此位将存在不同 diff++
            if (count[in] == 0) {
                diff++;
            }
            // 更新 count 后如果平衡则 diff--
            count[in]++;
            if (count[in] == 0) {
                diff--;
            }
            // 同理
            if (count[out] == 0) {
                diff++;
            }
            count[out]--;
            if (count[out] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    // 特征相同检查长度
    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int right = 0; right < m; right++) {
            int curr = s2.charAt(right) - 'a';
            count[curr]++;
            while (count[curr] > 0) {
                count[s2.charAt(left) - 'a']--;
                left++;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }

}
