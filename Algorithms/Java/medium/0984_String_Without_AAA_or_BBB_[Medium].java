/**
 * 984. String Without AAA or BBB
 *
 *
 * Given two integers a and b, return any string s such that:
 *
 * s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
 * The substring 'aaa' does not occur in s, and
 * The substring 'bbb' does not occur in s.
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: "abb"
 * Explanation: "abb", "bab" and "bba" are all correct answers.
 *
 * Example 2:
 *
 * Input: a = 4, b = 1
 * Output: "aabaa"
 *
 *
 * Constraints:
 *
 * 1. 0 <= a, b <= 100
 * 2. It is guaranteed such an s exists for the given a and b.
 */
public class StringWithoutAAAorBBB {

    // 从直觉上考虑 那个剩余的先用哪个 直到已经连续出现两次 必须换另一个字符
    // 考虑贪心
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        while (a > b && b > 0) {
            sb.append("aab");
            a -= 2;
            b--;
        }
        while (b > a && a > 0) {
            sb.append("bba");
            b -= 2;
            a--;
        }
        while (a > 0 && b > 0) {
            sb.append("ab");
            a--;
            b--;
        }
        while (a > 0) {
            sb.append("a");
            a--;
        }
        while (b > 0) {
            sb.append("b");
            b--;
        }
        return sb.toString();
    }

}
