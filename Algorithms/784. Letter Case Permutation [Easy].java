/**
 * 784. Letter Case Permutation
 *
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 * Note:
 *
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {


    /**
     * BFS
     */
    public List<String> letterCasePermutation(String S) {

        LinkedList<String> deque = new LinkedList<>();
        deque.add(S);

        int length = S.length();
        for (int i = 0; i < length; i++) {

            if (Character.isDigit(S.charAt(i))) {
                continue;
            }

            int size = deque.size();
            for (int j = 0; j < size; j++) {
                char[] chars = deque.poll().toCharArray();
                chars[i] = Character.toLowerCase(chars[i]);
                deque.add(new String(chars));
                chars[i] = Character.toUpperCase(chars[i]);
                deque.add(new String(chars));
            }
        }

        return deque;
    }

    /**
     * DFS
     */
    public List<String> letterCasePermutation2(String S) {

        LinkedList<String> deque = new LinkedList<>();
        permutation(0, S.toCharArray(), deque);
        return deque;
    }

    private void permutation(int index, char[] chars, LinkedList<String> ans) {

        if (index == chars.length) {
            ans.add(new String(chars));
            return;
        }

        if (Character.isDigit(chars[index])) {
            permutation(index + 1, chars, ans);
            return;
        }

        chars[index] = Character.toLowerCase(chars[index]);
        permutation(index + 1, chars, ans);
        chars[index] = Character.toUpperCase(chars[index]);
        permutation(index + 1, chars, ans);
    }
}
