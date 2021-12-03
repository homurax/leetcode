/**
 * 833. Find And Replace in String
 *
 *
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 * Example 2:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. k == indices.length == sources.length == targets.length
 * 3. 1 <= k <= 100
 * 4. 0 <= indexes[i] < s.length
 * 5. 1 <= sources[i].length, targets[i].length <= 50
 * 6. s consists of only lowercase English letters.
 * 7. sources[i] and targets[i] consist of only lowercase English letters.
 */
public class FindAndReplaceInString {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[] match = new int[S.length()];
        Arrays.fill(match, -1);
        for (int i = 0; i < indexes.length; i++) {
            int idx = indexes[i];
            if (S.startsWith(sources[i], idx)) {
                match[idx] = i;
            }
        }

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < S.length()) {
            if (match[idx] >= 0) {
                sb.append(targets[match[idx]]);
                idx += sources[match[idx]].length();
            } else {
                sb.append(S.charAt(idx++));
            }
        }
        return sb.toString();
    }



    public String findReplaceString1(String S, int[] indexes, String[] sources, String[] targets) {
        int k = indexes.length;
        Operation[] operations = new Operation[k];
        for (int i = 0; i < k; i++) {
            operations[i] = new Operation(indexes[i], sources[i], targets[i]);
        }
        Arrays.sort(operations);

        int prev = 0;
        char[] chars = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (Operation o : operations) {
            if (o.idx == S.indexOf(o.source, o.idx)) {
                if (o.idx > prev) {
                    sb.append(chars, prev, o.idx - prev);
                }
                prev = o.idx + o.source.length();
                sb.append(o.target);
            } else {
                sb.append(chars, prev, o.idx - prev);
                prev = o.idx;
            }
        }
        if (prev < chars.length) {
            sb.append(chars, prev, chars.length - prev);
        }
        return sb.toString();
    }

    class Operation implements Comparable<Operation> {
        int idx;
        String source;
        String target;
        Operation(int idx, String source, String target) {
            this.idx = idx;
            this.source = source;
            this.target = target;
        }
        @Override
        public int compareTo(Operation o) {
            return Integer.compare(this.idx, o.idx);
        }
    }


}
