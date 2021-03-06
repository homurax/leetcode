/**
 * 205. Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {


    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if (map.containsKey(charS)) {
                if (map.get(charS) != charT)
                    return false;
            } else {
                if (map.containsValue(charT))
                    return false;
                map.put(charS, charT);
            }
        }
        return true;
    }


    public boolean isIsomorphic2(String s, String t) {

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        char[] chs = new char[256];
        char[] cht = new char[256];

        for(int i = 0; i < charArrayS.length; i++){
            char cs = charArrayS[i];
            char ct = charArrayT[i];
            if (chs[cs] == 0 && cht[ct] == 0) {
                chs[cs] = ct;
                cht[ct] = cs;
            } else if (chs[cs] != ct || cht[ct] != cs) {
                return false;
            }
        }
        return true;
    }
}
