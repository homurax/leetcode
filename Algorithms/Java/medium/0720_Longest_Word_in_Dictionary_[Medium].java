/**
 * 720. Longest Word in Dictionary
 *
 *
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 *
 * Note that the word should be built from left to right with each additional character being added to the end of a previous word.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 *
 *
 * Example 2:
 *
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists of lowercase English letters.
 */
public class LongestWordInDictionary {

    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        String ans = "";
        for (String word : set) {
            int m = word.length();
            int n = ans.length();
            if (m < n || (m == n && word.compareTo(ans) > 0)) {
                continue;
            }
            boolean ok = true;
            for (int i = 1; i <= m; i++) {
                String sub = word.substring(0, i);
                if (!set.contains(sub)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = word;
            }
        }
        return ans;
    }

}