/**
 * 387. First Unique Character in a String
 *
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {


    public int firstUniqChar(String s) {

        int length = s.length();
        if (length == 1) {
            return 0;
        }
        int[] table = new int[26];
        for (int i = 0; i < length; i++) {
            table[s.charAt(i) - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < table.length; i++) {
            if (table[i] == 1) {
                ans = Math.min(ans, s.indexOf(i+'a'));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * 通过 {@link String#indexOf(int)} 结合 {@link String#lastIndexOf(int)}
     * 判断是否存在以及是否出现一次
     */
    public int firstUniqChar2(String s) {
        int index = -1;
        for (int i = 'a'; i <= 'z'; i++) {
            int first = s.indexOf(i);
            int last = s.lastIndexOf(i);
            if (first == last && first != -1) {
                if (index == -1 || index > first) {
                    index = first;
                }
            }
        }
        return index;
    }
}
