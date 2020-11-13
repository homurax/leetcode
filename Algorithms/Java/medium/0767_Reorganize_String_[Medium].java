/**
 * 767. Reorganize String
 *
 *
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 *
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * 1. S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {

    /**
     * 还可以用贪心堆
     * 指定排序规则 每次输出剩余出现次数最多的字符就可以了
     * (a, b) -> a.count == b.count ? a.letter - b.letter : b.count - a.count
     */
    public String reorganizeString(String S) {
        int n = S.length();
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[S.charAt(i) - 'a'] += 100;
        }
        for (int i = 0; i < 26; i++) {
            counts[i] += i;
        }
        Arrays.sort(counts);

        char[] ans = new char[n];
        int index = 1;
        for (int code : counts) {
            int count = code / 100;
            if (n - count < count - 1) {
                return "";
            }
            char letter = (char) ('a' + (code % 100));
            for (int i = 0; i < count; i++) {
                if (index >= n) {
                    index = 0;
                }
                ans[index] = letter;
                index += 2;
            }
        }
        return String.valueOf(ans);
    }

}
