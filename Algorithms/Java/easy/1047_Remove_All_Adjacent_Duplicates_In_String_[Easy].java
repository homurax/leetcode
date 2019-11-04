/**
 * 1047. Remove All Adjacent Duplicates In String
 *
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 *
 * Example 1:
 *
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 *
 * Note:
 *
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 */
public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String S) {

        StringBuilder sb = new StringBuilder();
        int len = 0;
        for (char character : S.toCharArray()) {
            if (len != 0 && character == sb.charAt(len - 1)) {
                sb.deleteCharAt(--len);
            }
            else {
                sb.append(character);
                len++;
            }
        }
        return sb.toString();
    }

    public String removeDuplicates2(String S) {

        char[] arr = S.toCharArray();
        int pivot = 0;
        for (int i = 1; i < arr.length; i++) {
            if (pivot > -1 && arr[i] == arr[pivot]) {
                pivot--;
            } else {
                arr[++pivot] = arr[i];
            }
        }
        return new String(arr, 0, pivot + 1);
    }
}
