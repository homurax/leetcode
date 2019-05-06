/**
 * 844. Backspace String Compare
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {

    /**
     * Deque不能像List,Set一样调用{@link java.util.Collection#equals(Object)}进行比较相等性
     */
    public boolean backspaceCompare(String S, String T) {

        int length1 = S.length();
        int length2 = T.length();
        if (length1 == 0 && length2 == 0) return true;

        ArrayDeque<Character> deque1 = new ArrayDeque<>(length1);
        ArrayDeque<Character> deque2 = new ArrayDeque<>(length2);

        for (int i = 0; i < length1; i++) {
            char c = S.charAt(i);
            if (c == '#') {
                deque1.pollLast();
            } else {
                deque1.offer(c);
            }
        }
        for (int i = 0; i < length2; i++) {
            char c = T.charAt(i);
            if (c == '#') {
                deque2.pollLast();
            } else {
                deque2.offer(c);
            }
        }

        if (deque1.size() != deque2.size()) return false;

        while (!deque1.isEmpty()) {
            if (deque1.poll() != deque2.poll())
                return false;
        }

        return true;
    }

    /**
     * 双指针从后往前逐个有效位依次比较
     * skip变量用于对应连续删除符的情况
     * 注意不可以用指针-2的方式 ##代表前面的内容要删除两次 遇到后一个#时 如果指针-2会把删除操作略过
     */
    public boolean backspaceCompare2(String S, String T) {

        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {

            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }


            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;

            if ((i >= 0) != (j >= 0))
                return false;

            i--;
            j--;
        }

        return true;
    }
}
