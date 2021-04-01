/**
 * 139. Word Break
 *
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 300
 * 2. 1 <= wordDict.length <= 1000
 * 3. 1 <= wordDict[i].length <= 20
 * 4. s and wordDict[i] consist of only lowercase English letters.
 * 5. All the strings of wordDict are unique.
 */
public class WordBreak {

    public boolean wordBreak1(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        HashSet<String> set = new HashSet<>(wordDict);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (dp[i] && set.contains(s.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }



    public boolean wordBreak(String s, List<String> wordDict) {
        return dp(s, 0, wordDict, new Boolean[s.length()]);
    }

    // 不用 boolean 用 Boolean 是需要区分是未访问过 还是不可选
    private boolean dp(String s, int index, List<String> wordDict, Boolean[] record) {
        if (index == s.length()) {
            return true;
        }
        if (record[index] == null) {
            record[index] = false;
            for (String dict : wordDict) {
                if (check(s, index, dict) && dp(s, index + dict.length(), wordDict, record)) {
                    record[index] = true;
                }
            }
        }
        return record[index];
    }

    private boolean check(String s, int fromIndex, String dict) {
        for (int i = 0; i < dict.length(); i++) {
            if (fromIndex + i == s.length() || s.charAt(fromIndex + i) != dict.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
