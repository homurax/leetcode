/**
 * 1189. Maximum Number of Balloons
 *
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 *
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 *
 * Example 1:
 *
 *
 * Input: text = "nlaebolko"
 * Output: 1
 *
 * Example 2:
 *
 *
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 *
 * Input: text = "leetcode"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 */
public class MaximumNumberOfBalloons {


    public int maxNumberOfBalloons(String text) {

        int[] table = new int[26];
        for (char c : text.toCharArray()) {
            table[c - 'a']++;
        }
        table['l' - 'a'] /= 2;
        table['o' - 'a'] /= 2;
        int ans = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) {
            ans = Math.min(ans, table[c - 'a']);
        }
        return ans;
    }
}
