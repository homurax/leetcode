/**
 * 1209. Remove All Adjacent Duplicates in String II
 *
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 *
 * It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 *
 * Example 2:
 *
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 *
 * Example 3:
 *
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lower case English letters.
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int len = -1;
        while (len != sb.length()) {
            len = sb.length();
            for (int i = 0, count = 0; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[s.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }

    public String removeDuplicates3(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] stack = new int[s.length()];
        int cur = -1;
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                stack[++cur] = 1;
            } else {
                int incremented = stack[cur--] + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    stack[++cur] = incremented;
                }
            }
        }
        return sb.toString();
    }

    // 重置慢指针避免 delete 方法中的 arraycopy 开销
    public String removeDuplicates4(String s, int k) {
        int[] stack = new int[s.length()];
        char[] letters = s.toCharArray();
        int cur = -1, j = 0;
        for (int i = 0; i < s.length(); i++, j++) {
            letters[j] = letters[i];
            if (j == 0 || letters[j] != letters[j - 1]) {
                stack[++cur] = 1;
            } else {
                int incremented = stack[cur--] + 1;
                if (incremented == k) {
                    j = j - k;
                } else {
                    stack[++cur] = incremented;
                }
            }
        }
        return new String(letters, 0, j);
    }

    public String removeDuplicates5(String s, int k) {
        char pre = ' ';
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (pre == letter) {
                count++;
            } else {
                count = 1;
                pre = letter;
            }
            if (count == k) {
                return removeDuplicates5(s.substring(0, i - k + 1) + s.substring(i + 1), k);
            }
        }
        return s;
    }

}
