/**
 * 821. Shortest Distance to a Character
 *
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 */
public class ShortestDistanceToACharacter {


    /**
     * 前序后序遍历一次
     * 取最小值
     */
    public int[] shortestToChar(String S, char C) {

        int length = S.length();
        int[] result = new int[length];

        int index = -10000;
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == C)
                index = i;
            result[i] = i - index;
        }

        index = 10000;
        for (int i = length-1; i >= 0; i--) {
            if (S.charAt(i) == C)
                index = i;
            result[i] = Math.min(result[i], index - i);
        }

        return result;
    }

}
