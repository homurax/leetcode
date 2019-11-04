/**
 * 438. Find All Anagrams in a String
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {


    /**
     * 滑窗
     */
    public List<Integer> findAnagrams(String s, String p) {

        if (s.length() < p.length())
            return new ArrayList<>();

        int[] target = new int[26];
        int length = p.length();
        for (int i = 0; i < length; i++) {
            target[p.charAt(i) - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        char[] array = s.toCharArray();
        int start = 0;
        int read = 0;
        while (read < array.length) {
            if (target[array[read] - 'a'] > 0) {
                target[array[read] - 'a']--;
                read++;
            } else {
                target[array[start] - 'a']++;
                start++;
            }
            if (read - start == length)
                ans.add(start);
        }
        return ans;
    }

}
