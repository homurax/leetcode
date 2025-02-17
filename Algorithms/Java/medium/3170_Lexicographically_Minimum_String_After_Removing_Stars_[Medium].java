/**
 * 3170. Lexicographically Minimum String After Removing Stars
 *
 *
 * You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
 *
 * While there is a '*', do the following operation:
 *
 * Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
 * Return the lexicographically smallest resulting string after removing all '*' characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaba*"
 *
 * Output: "aab"
 *
 * Explanation:
 *
 * We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.
 *
 *
 *
 * Example 2:
 *
 * Input: s = "abc"
 *
 * Output: "abc"
 *
 * Explanation:
 *
 * There is no '*' in the string.
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists only of lowercase English letters and '*'.
 * The input is generated such that it is possible to delete all '*' characters.
 */
public class LexicographicallyMinimumStringAfterRemovingStars {


    // 26 个栈维护每中字母的位置, 由于移除最小字典序字符且要求最终字典序最小, 即优先移除靠后的字典序
    public String clearStars(String s) {
        char[] chars = s.toCharArray();
        List<Integer>[] idxList = new ArrayList[26];
        Arrays.setAll(idxList, i -> new ArrayList<>());
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '*') {
                idxList[chars[i] - 'a'].add(i);
                continue;
            }
            for (List<Integer> list : idxList) {
                if (!list.isEmpty()) {
                    list.removeLast();
                    break;
                }
            }
        }

        List<Integer> idx = new ArrayList<>();
        for (List<Integer> p : idxList) {
            idx.addAll(p);
        }
        Collections.sort(idx);

        StringBuilder sb = new StringBuilder(idx.size());
        for (int i : idx) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

}
