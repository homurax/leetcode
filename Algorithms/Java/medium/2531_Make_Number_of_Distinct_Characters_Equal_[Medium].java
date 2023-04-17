/**
 * 2531. Make Number of Distinct Characters Equal
 *
 *
 * You are given two 0-indexed strings word1 and word2.
 *
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 *
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "ac", word2 = "b"
 * Output: false
 * Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
 *
 *
 * Example 2:
 *
 * Input: word1 = "abcc", word2 = "aab"
 * Output: true
 * Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
 *
 *
 * Example 3:
 *
 * Input: word1 = "abcde", word2 = "fghij"
 * Output: true
 * Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
 *
 *
 * Constraints:
 *
 * 1. 1 <= word1.length, word2.length <= 10^5
 * 2. word1 and word2 consist of only lowercase English letters.
 */
public class MakeNumberOfDistinctCharactersEqual {

    // 最多遍历 26 * 26 次
    public boolean isItPossible(String word1, String word2) {
        Map<Character, Integer> cntMap1 = new HashMap<>();
        Map<Character, Integer> cntMap2 = new HashMap<>();
        for (char c : word1.toCharArray()) {
            cntMap1.merge(c, 1, Integer::sum);
        }
        for (char c : word2.toCharArray()) {
            cntMap2.merge(c, 1, Integer::sum);
        }
        int size1 = cntMap1.size();
        int size2 = cntMap2.size();
        for (Map.Entry<Character, Integer> entry1 : cntMap1.entrySet()) {
            for (Map.Entry<Character, Integer> entry2 : cntMap2.entrySet()) {
                char c1 = entry1.getKey(), c2 = entry2.getKey();
                int cnt1 = entry1.getValue(), cnt2 = entry2.getValue();
                if (c1 == c2) {
                    if (size1 == cntMap2.size()) {
                        return true;
                    }
                } else {
                    // 如果交换走的字符数量 > 1, 交换后不需要减少种类; 如果交换来的字符不存在, 种类 + 1
                    int x = (size1 - (cnt1 == 1 ? 1 : 0)) + (cntMap1.containsKey(c2) ? 0 : 1);
                    int y = (size2 - (cnt2 == 1 ? 1 : 0)) + (cntMap2.containsKey(c1) ? 0 : 1);
                    if (x == y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
