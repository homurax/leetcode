/**
 * 792. Number of Matching Subsequences
 *
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 *
 * Note:
 *
 * 1. All words in words and S will only consists of lowercase letters.
 * 2. The length of S will be in the range of [1, 50000].
 * 3. The length of words will be in the range of [1, 5000].
 * 4. The length of words[i] will be in the range of [1, 50].
 */
public class NumberOfMatchingSubsequences {

    public int numMatchingSubseq1(String S, String[] words) {
        int ans = 0;
        for (String word : words) {
            if (isSubsequence(S, word)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isSubsequence(String source, String target) {
        for (int i = 0, j = -1; i < target.length(); i++) {
            j = source.indexOf(target.charAt(i), j + 1);
            if (j == -1) {
                return false;
            }
        }
        return true;
    }


    class Node {
        String word;
        int index;
        public Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    // S 较长时考虑只遍历一次的方法
    // 通过首字母分为 26 个 bucket
    // 遍历 S 时不断从 bucket 中取出 Node 更新 index 放入别的桶
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        List<ArrayDeque<Node>> buckets = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            buckets.add(new ArrayDeque<>());
        }
        for (String word : words) {
            buckets.get(word.charAt(0) - 'a').add(new Node(word, 0));
        }
        for (char c : S.toCharArray()) {
            // word 可能存在连续字母 也就可能从 curBucket 中 remove 后又被 add 进来
            // 如果使用 List 就会出现 ConcurrentModificationException 异常
            // 所以使用 Queue 或者 Deque
            ArrayDeque<Node> bucket = buckets.get(c - 'a');
            int size = bucket.size();
            for (int i = 0; i < size; i++) {
                Node node = bucket.remove();
                if (++node.index == node.word.length()) {
                    ans++;
                } else {
                    buckets.get(node.word.charAt(node.index) - 'a').add(node);
                }
            }
        }
        return ans;
    }


}
