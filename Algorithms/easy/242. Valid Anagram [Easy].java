/**
 * 242. Valid Anagram
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {

    /**
     * s t长度应相同
     * array[target]++ 与 array[target]-- 可以放在一个循环中去做
     */
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            array[t.charAt(i) - 'a']--;
        }
        for (int i : array) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 循环中检查
     */
    public boolean isAnagram2(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
