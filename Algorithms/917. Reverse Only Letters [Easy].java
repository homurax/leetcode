/**
 * 917. Reverse Only Letters
 *
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 *
 * Example 1:
 *
 * Input: "ab-cd"
 * Output: "dc-ba"
 *
 * Example 2:
 *
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 *
 * Example 3:
 *
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 *
 *
 * Note:
 *
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S doesn't contain \ or "
 */
public class ReverseOnlyLetters {

    /**
     * A-Z => 65-90
     * a-z => 97-122
     * {@link Character#isLetter(char)}
     */
    public String reverseOnlyLetters(String S) {

        Deque<Character> deque = new LinkedList<>();
        char[] chars = S.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                deque.offer(c);
            }
        }

        char[] array = new char[S.length()];
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                array[i] = deque.pollLast();
            } else {
                array[i] = chars[i];
            }
        }

        return new String(array);
    }

    /**
     * 反向指针
     */
    public String reverseOnlyLetters2(String S) {

        int length = S.length();
        char[] ans = new char[length];
        int index = length - 1;
        for (int i = 0; i < length; i++) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(index))) {
                    index--;
                }
                ans[i] = S.charAt(index--);
            } else {
                ans[i] = S.charAt(i);
            }
        }
        return new String(ans);
    }
}
