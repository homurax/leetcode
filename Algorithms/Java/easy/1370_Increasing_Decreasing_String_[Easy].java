/**
 * 1370. Increasing Decreasing String
 *
 * Given a string s. You should re-order the string using the following algorithm:
 *
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
 *
 * Return the result string after sorting s with this algorithm.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaabbbbcccc"
 * Output: "abccbaabccba"
 * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
 * After steps 4, 5 and 6 of the first iteration, result = "abccba"
 * First iteration is done. Now s = "aabbcc" and we go back to step 1
 * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
 * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
 *
 * Example 2:
 *
 * Input: s = "rat"
 * Output: "art"
 * Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.
 *
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: "cdelotee"
 *
 * Example 4:
 *
 * Input: s = "ggggggg"
 * Output: "ggggggg"
 *
 * Example 5:
 *
 * Input: s = "spo"
 * Output: "ops"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s contains only lower-case English letters.
 */
public class IncreasingDecreasingString {

    public String sortString2(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        int[] buckets = new int[26];
        for (char c : s.toCharArray()) {
            buckets[c - 'a']++;
        }
        char[] ans = new char[length];
        int index = 0;
        while (index < length) {
            for (int i = 0; i < buckets.length; i++) {
                if (--buckets[i] >= 0) {
                    ans[index++] = (char) ('a' + i);
                }
            }
            for (int i = buckets.length - 1; i >= 0; i--) {
                if (--buckets[i] >= 0) {
                    ans[index++] = (char) ('a' + i);
                }
            }
        }
        return new String(ans);
    }


    public String sortString(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        LinkedList<Character> source = new LinkedList<>();
        for (char c : arr) { source.add(c); }
        StringBuilder rst = new StringBuilder();
        travel(source, rst);
        return rst.toString();
    }

    private void travel(LinkedList<Character> source, StringBuilder rst) {

        if (source.isEmpty()) {
            return;
        }
        Character curr = ' ';
        ListIterator<Character> iterator = source.listIterator();
        while (iterator.hasNext()) {
            Character character = iterator.next();
            if (character > curr) {
                rst.append(character);
                curr = character;
                iterator.remove();
            }
        }
        curr = '~';
        while (iterator.hasPrevious()) {
            Character character = iterator.previous();
            if (character < curr) {
                rst.append(character);
                curr = character;
                iterator.remove();
            }
        }
        travel(source, rst);
    }

}
