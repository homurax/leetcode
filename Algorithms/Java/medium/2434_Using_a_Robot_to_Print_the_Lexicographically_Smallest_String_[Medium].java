/**
 * 2434. Using a Robot to Print the Lexicographically Smallest String
 *
 *
 * You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
 *
 * Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
 * Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
 * Return the lexicographically smallest string that can be written on the paper.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zza"
 * Output: "azz"
 * Explanation: Let p denote the written string.
 * Initially p="", s="zza", t="".
 * Perform first operation three times p="", s="", t="zza".
 * Perform second operation three times p="azz", s="", t="".
 *
 *
 * Example 2:
 *
 * Input: s = "bac"
 * Output: "abc"
 * Explanation: Let p denote the written string.
 * Perform first operation twice p="", s="c", t="ba".
 * Perform second operation twice p="ab", s="c", t="".
 * Perform first operation p="ab", s="", t="c".
 * Perform second operation p="abc", s="", t="".
 *
 *
 * Example 3:
 *
 * Input: s = "bdda"
 * Output: "addb"
 * Explanation: Let p denote the written string.
 * Initially p="", s="bdda", t="".
 * Perform first operation four times p="", s="", t="bdda".
 * Perform second operation four times p="addb", s="", t="".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 */
public class UsingARobotToPrintTheLexicographicallySmallestString {

    public String robotWithString(String s) {
        StringBuilder sb = new StringBuilder();
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        int min = 0;
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : chars) {
            cnt[c - 'a']--;
            while (min < 25 && cnt[min] == 0 ) {
                min++;
            }
            deque.push(c);
            while (!deque.isEmpty() && deque.peek() - 'a' <= min) {
                sb.append(deque.pop());
            }
        }
        return sb.toString();
    }

}
