/**
 * 28. Implement strStr()
 *
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 *
 *
 * Constraints:
 *
 * haystack and needle consist only of lowercase English characters.
 */
public class ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        int sourceLen = haystack.length();
        int targetLen = needle.length();
        int anchor = 0;
        int limitIndex = sourceLen - targetLen + 1;
        while (anchor < limitIndex) {
            while (anchor < limitIndex && haystack.charAt(anchor) != needle.charAt(0)) {
                anchor++;
            }
            int r = 0;
            while (r < targetLen && anchor < sourceLen && haystack.charAt(anchor) == needle.charAt(r)) {
                anchor++;
                r++;
            }
            if (r == targetLen) {
                return anchor - targetLen;
            }
            anchor -= r - 1;
        }
        return -1;
    }

}
