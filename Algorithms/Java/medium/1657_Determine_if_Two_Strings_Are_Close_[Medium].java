/**
 * 1657. Determine if Two Strings Are Close
 *
 *
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 *
 * Example 2:
 *
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 *
 * Example 3:
 *
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 *
 * Example 4:
 *
 * Input: word1 = "cabbba", word2 = "aabbss"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.
 *
 *
 * Constraints:
 *
 * 1. 1 <= word1.length, word2.length <= 10^5
 * 2. word1 and word2 contain only lowercase English letters.
 */
public class DetermineIfTwoStringsAreClose {

    // 没有不同给的 char & 频率相同
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        if (word1.equals(word2)){
            return true;
        }
        int[] buckets1 = new int[26];
        for (char c : word1.toCharArray()) {
            buckets1[c - 'a']++;
        }
        int[] buckets2 = new int[26];
        for (char c : word2.toCharArray()) {
            if (buckets1[c - 'a'] == 0) {
                return false;
            }
            buckets2[c - 'a']++;
        }
        Arrays.sort(buckets1);
        Arrays.sort(buckets2);
        return Arrays.equals(buckets1, buckets2);
    }

}
