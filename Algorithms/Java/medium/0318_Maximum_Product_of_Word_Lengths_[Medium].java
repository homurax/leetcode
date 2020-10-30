/**
 * 318. Maximum Product of Word Lengths
 *
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
 *
 * Example 1:
 *
 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 *
 * Example 2:
 *
 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 *
 * Example 3:
 *
 * Input: ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 *
 *
 * Constraints:
 *
 * 0 <= words.length <= 10^3
 * 0 <= words[i].length <= 10^3
 * words[i] consists only of lowercase English letters.
 */
public class MaximumProductOfWordLengths {

    // 果然 TLE 了
    public int maxProduct(String[] words) {
        int max = 0;
        Set<Character> seen1 = new HashSet<>();
        Set<Character> seen2 = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String word1 = words[i];
                String word2 = words[j];
                seen1.clear();
                seen2.clear();
                for (int idx = 0; idx < word1.length(); idx++) {
                    seen1.add(word1.charAt(idx));
                }
                for (int idx = 0; idx < word2.length(); idx++) {
                    seen2.add(word2.charAt(idx));
                }
                seen1.retainAll(seen2);
                if (seen1.isEmpty()) {
                    max = Math.max(max, word1.length() * word2.length());
                }
            }
        }
        return max;
    }


    // 快想起二进制...
    public int maxProduct2(String[] words) {
        int max = 0;
        int[] flag = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                flag[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        for (int i = 0; i < flag.length; i++) {
            for (int j = i + 1; j < flag.length; j++) {
                if ((flag[i] & flag[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

}
