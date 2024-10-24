/**
 * 3305. Count of Substrings Containing Every Vowel and K Consonants I
 *
 *
 * You are given a string word and a non-negative integer k.
 *
 * Return the total number of
 * substrings
 *  of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aeioqq", k = 1
 *
 * Output: 0
 *
 * Explanation:
 *
 * There is no substring with every vowel.
 *
 * Example 2:
 *
 * Input: word = "aeiou", k = 0
 *
 * Output: 1
 *
 * Explanation:
 *
 * The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".
 *
 * Example 3:
 *
 * Input: word = "ieaouqqieaouqq", k = 1
 *
 * Output: 3
 *
 * Explanation:
 *
 * The substrings with every vowel and one consonant are:
 *
 * word[0..5], which is "ieaouq".
 * word[6..11], which is "qieaou".
 * word[7..12], which is "ieaouq".
 *
 *
 * Constraints:
 *
 * 5 <= word.length <= 250
 * word consists only of lowercase English letters.
 * 0 <= k <= word.length - 5
 */
public class CountOfSubstringsContainingEveryVowelAndKConsonantsI {

    // f(word, k) = 存在 aeiou 且辅音 >= k
    // f(word, k + 1) = 存在 aeiou 且辅音 >= k + 1
    // ans = f(word, k) - f(word, k + 1)
    public int countOfSubstrings(String word, int k) {
        char[] letters = word.toCharArray();
        return f(letters, k) - f(letters, k + 1);
    }

    private int f(char[] word, int k) {
        int ans = 0;
        Map<Character, Integer> cnt1 = new HashMap<>();
        int cnt2 = 0;
        int left = 0;
        for (char c : word) {
            if ("aeiou".indexOf(c) >= 0) {
                cnt1.merge(c, 1, Integer::sum);
            } else {
                cnt2++;
            }
            while (cnt1.size() == 5 && cnt2 >= k) {
                char out = word[left];
                if ("aeiou".indexOf(out) >= 0) {
                    if (cnt1.merge(out, -1, Integer::sum) == 0) {
                        cnt1.remove(out);
                    }
                } else {
                    cnt2--;
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }

}
