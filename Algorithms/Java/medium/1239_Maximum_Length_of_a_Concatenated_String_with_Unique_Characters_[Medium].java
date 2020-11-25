/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 *
 *
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 *
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 16
 * 2. 1 <= arr[i].length <= 26
 * 3. arr[i] contains only lower case English letters.
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    public int maxLength(List<String> arr) {
        return dfs(arr, 0,0);
    }

    private int dfs(List<String> arr, int i, int mask) {
        if (i == arr.size()) return 0;
        int ans = 0;
        for (; i < arr.size(); i++) {
            int bit = mask(arr.get(i));
            if (bit == 0 || ((mask & bit) > 0)) continue;
            ans = Math.max(ans, dfs(arr, i + 1, mask | bit) + arr.get(i).length());
        }
        return ans;
    }

    private int mask(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            if ((mask & bit) > 0) {
                return 0;
            }
            mask |= bit;
        }
        return mask;
    }

}
