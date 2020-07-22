/**
 * 893. Groups of Special-Equivalent Strings
 *
 * You are given an array A of strings.
 *
 * A move onto S consists of swapping any two even indexed characters of S, or any two odd indexed characters of S.
 *
 * Two strings S and T are special-equivalent if after any number of moves onto S, S == T.
 *
 * For example, S = "zzxy" and T = "xyzz" are special-equivalent because we may make the moves "zzxy" -> "xzzy" -> "xyzz" that swap S[0] and S[2], then S[1] and S[3].
 *
 * Now, a group of special-equivalent strings from A is a non-empty subset of A such that:
 *
 * Every pair of strings in the group are special equivalent, and;
 * The group is the largest size possible (ie., there isn't a string S not in the group such that S is special equivalent to every string in the group)
 * Return the number of groups of special-equivalent strings from A.
 *
 *
 * Example 1:
 *
 * Input: ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
 * Output: 3
 * Explanation:
 * One group is ["abcd", "cdab", "cbad"], since they are all pairwise special equivalent, and none of the other strings are all pairwise special equivalent to these.
 *
 * The other two groups are ["xyzz", "zzxy"] and ["zzyx"].  Note that in particular, "zzxy" is not special equivalent to "zzyx".
 *
 * Example 2:
 *
 * Input: ["abc","acb","bac","bca","cab","cba"]
 * Output: 3
 *
 *
 * Note:
 *
 * 1 <= A.length <= 1000
 * 1 <= A[i].length <= 20
 * All A[i] have the same length.
 * All A[i] consist of only lowercase letters.
 */
public class GroupsOfSpecialEquivalentStrings {

    // fun(S) = fun(T)
    public int numSpecialEquivGroups(String[] A) {
        Set<String> seen = new HashSet<>();
        for (String S: A) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); i++)
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            seen.add(Arrays.toString(count));
        }
        return seen.size();
    }


    public int numSpecialEquivGroups2(String[] A) {
        Set<String> seen = new HashSet<>();
        for (String s : A) {
            seen.add(refactor(s.toCharArray()));
        }
        return seen.size();
    }

    private String refactor(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 2; j < chars.length; j += 2) {
                if (chars[i] > chars[j]) {
                    swap(chars, i, j);
                }
            }
        }
        return String.valueOf(chars);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
