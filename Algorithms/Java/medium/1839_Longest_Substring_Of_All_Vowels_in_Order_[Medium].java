/**
 * 1839. Longest Substring Of All Vowels in Order
 *
 *
 * A string is considered beautiful if it satisfies the following conditions:
 *
 * Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
 * The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.
 *
 * Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
 * Output: 13
 * Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
 *
 * Example 2:
 *
 * Input: word = "aeeeiiiioooauuuaeiou"
 * Output: 5
 * Explanation: The longest beautiful substring in word is "aeiou" of length 5.
 *
 * Example 3:
 *
 * Input: word = "a"
 * Output: 0
 * Explanation: There is no beautiful substring, so return 0.
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 5 * 10^5
 * word consists of characters 'a', 'e', 'i', 'o', and 'u'.
 */
public class LongestSubstringOfAllVowelsInOrder {

    // 一定以 a 开头
    public int longestBeautifulSubstring(String word) {
        char[] letters = word.toCharArray();
        int n = letters.length;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        int max = 0;
        for (int l = 0; l < n;) {
            if (letters[l] == 'a') {
                int count = 0;
                for (int i = 0; i < vowels.length; i++) {
                    while (l < n && letters[l] == vowels[i]) {
                        l++;
                        count++;
                    }
                    if (i < 4) {
                        if (l == n || letters[l] != vowels[i + 1]) {
                            break;
                        }
                    } else {
                        max = Math.max(max, count);
                    }
                }
            } else {
                l++;
            }
        }
        return max;
    }

    // 滑窗
    public int longestBeautifulSubstring1(String word) {
        int max = 0;
        int vowel = 1;
        char[] letters = word.toCharArray();
        for (int l = 0, r = 1; r < word.length(); r++) {
            if (letters[r] < letters[r - 1]) {
                l = r;
                vowel = 1;
            } else if (letters[r] > letters[r - 1]) {
                vowel++;
            }
            // 从减少判断次数的角度来说 可以改为在收缩和循环外判断
            if (vowel == 5) {
                max = Math.max(max, r - l + 1);
            }
        }
        return max;
    }

}
