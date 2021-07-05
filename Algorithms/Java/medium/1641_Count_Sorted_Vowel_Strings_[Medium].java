/**
 * 1641. Count Sorted Vowel Strings
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 */
public class CountSortedVowelStrings {

    /**
     *          a   e   i   o   u   开头
     * n = 1    1   1   1   1   1
     * n = 2    5   4   3   2   1
     * n = 3   15   10  6   3   1
     * a 可以接所有开头的（a/e/i/o/u）    count[i][0] = sum(count[i-1][0:-1])
     * e 可以接 a 以外开头的（e/i/o/u）   count[i][1] = sum(count[i-1][1:-1])
     * ......
     * u 只能接 u 开头的                count[i][-1] = sum(count[i-1][-1])
     */
    public int countVowelStrings(int n) {
        int[] count = new int[5];
        Arrays.fill(count, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 3; j >= 0; j--) {
                count[j] += count[j + 1];
            }
        }
        int ans = 0;
        for (int i : count) {
            ans += i;
        }
        return ans;
    }

}
