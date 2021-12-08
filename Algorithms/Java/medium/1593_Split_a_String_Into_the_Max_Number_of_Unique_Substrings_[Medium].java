/**
 * 1593. Split a String Into the Max Number of Unique Substrings
 *
 *
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 *
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 *
 * Example 3:
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 16
 *
 * 2. s contains only lower case English letters.
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {

    private int max = 1;

    public int maxUniqueSplit(String s) {
        backtrack(0, 0, s, new HashSet<>());
        return max;
    }

    private void backtrack(int index, int split, String s, Set<String> set) {
        if (index == s.length()) {
            max = Math.max(max, split);
            if (max == 11) {
                System.out.println(set);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            if (set.add(str)) {
                backtrack(i + 1, split + 1, s, set);
                set.remove(str);
            }
        }
    }

}
