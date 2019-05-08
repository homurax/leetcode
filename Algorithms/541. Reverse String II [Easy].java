/**
 * 541. Reverse String II
 *
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 *
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 */
public class ReverseStringII {


    public String reverseStr(String s, int k) {

        char[] chars = s.toCharArray();
        int length = chars.length;
        int division = 2 * k;
        int group = length / division;
        int remaining = length % division;

        for (int i = 0; i < group; i++) {
            int offset = i * division;
            swap(offset, offset + k - 1, chars);
        }

        if (remaining < k) {
            swap(group * division, group * division + remaining - 1, chars);
        } else {
            swap(group * division, group * division + k - 1, chars);
        }

        return new String(chars);
    }

    private void swap(int start, int end, char[] source) {
        while (start < end) {
            char temp = source[start];
            source[start] = source[end];
            source[end] = temp;
            start++;
            end--;
        }
    }
}
