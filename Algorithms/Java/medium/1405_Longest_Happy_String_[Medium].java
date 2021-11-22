/**
 * 1405. Longest Happy String
 *
 *
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
 *
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 *
 * s is happy and longest possible.
 * s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
 * s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 *
 * Example 2:
 *
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 *
 * Example 3:
 *
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 *
 *
 * Constraints:
 *
 * 1. 0 <= a, b, c <= 100
 * 2. a + b + c > 0
 */
public class LongestHappyString {

    /**
     * 优先放置剩余最多的
     * 如果 prev 放置的两个和当前最多的相同，就放置次最多的
     */
    public String longestDiverseString(int a, int b, int c) {
        int[] count = new int[]{a, b, c};
        char[] letters = new char[a + b + c];
        int index = 0;
        while (count[0] != 0 || count[1] != 0 || count[2] != 0) {
            char letter;
            if (index < 2 || letters[index - 1] != letters[index - 2]) {
                letter = next(count, ' ');
            } else {
                letter = next(count, letters[index - 1]);
            }
            if (--count[letter - 'a'] < 0) {
                break;
            }
            letters[index++] = letter;
        }
        return new String(letters, 0, index);
    }

    private char next(int[] count, char exclude) {
        char next;
        if (exclude == 'a') {
            next = count[1] > count[2] ? 'b' : 'c';
        }
        else if (exclude == 'b') {
            next = count[0] > count[2] ? 'a' : 'c';
        }
        else if (exclude == 'c') {
            next = count[0] > count[1] ? 'a' : 'b';
        }
        else {
            next = count[0] > count[1] ? 'a' : 'b';
            next = count[next - 'a'] > count[2] ? next : 'c';
        }
        return next;
    }


}
