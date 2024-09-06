/**
 * 522. Longest Uncommon Subsequence II
 *
 *
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 *
 *
 * Example 1:
 *
 * Input: strs = ["aba","cdc","eae"]
 * Output: 3
 *
 *
 * Example 2:
 *
 * Input: strs = ["aaa","aaa","aa"]
 * Output: -1
 *
 *
 * Constraints:
 *
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] consists of lowercase English letters.
 */
public class LongestUncommonSubsequenceII {

    public int findLUSlength(String[] strs) {
        int n = strs.length;
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        next:
        for (int i = 0; i < n; i++) {
            String sub = strs[i];
            for (int j = 0; j < n; j++) {
                if (j != i && isSubSeq(sub, strs[j])) {
                    continue next;
                }
            }
            return strs[i].length();
        }
        return -1;
    }

    private boolean isSubSeq(String sub, String target) {
        int i = 0;
        int subLen = sub.length();
        for (char c : target.toCharArray()) {
            if (sub.charAt(i) == c && subLen == ++i) {
                return true;
            }
        }
        return false;
    }

}
