/**
 * 1927. Sum Game
 *
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * You are given a string num of even length consisting of digits and '?' characters. On each turn, a player will do the following if there is still at least one '?' in num:
 *
 * Choose an index i where num[i] == '?'.
 * Replace num[i] with any digit between '0' and '9'.
 * The game ends when there are no more '?' characters in num.
 *
 * For Bob to win, the sum of the digits in the first half of num must be equal to the sum of the digits in the second half. For Alice to win, the sums must not be equal.
 *
 * For example, if the game ended with num = "243801", then Bob wins because 2+4+3 = 8+0+1. If the game ended with num = "243803", then Alice wins because 2+4+3 != 8+0+3.
 * Assuming Alice and Bob play optimally, return true if Alice will win and false if Bob will win.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "5023"
 * Output: false
 * Explanation: There are no moves to be made.
 * The sum of the first half is equal to the sum of the second half: 5 + 0 = 2 + 3.
 *
 * Example 2:
 *
 * Input: num = "25??"
 * Output: true
 * Explanation: Alice can replace one of the '?'s with '9' and it will be impossible for Bob to make the sums equal.
 *
 * Example 3:
 *
 * Input: num = "?3295???"
 * Output: false
 * Explanation: It can be proven that Bob will always win. One possible outcome is:
 * - Alice replaces the first '?' with '9'. num = "93295???".
 * - Bob replaces one of the '?' in the right half with '9'. num = "932959??".
 * - Alice replaces one of the '?' in the right half with '2'. num = "9329592?".
 * - Bob replaces the last '?' in the right half with '7'. num = "93295927".
 * Bob wins because 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7.
 *
 *
 * Constraints:
 *
 * 1. 2 <= num.length <= 10^5
 * 2. num.length is even.
 * 3. num consists of only digits and '?'.
 */
public class SumGame {

    /**
     * 1. ? 奇数 Alice 获胜
     * 2. ? 偶数且均匀分布 左右和相等 Bob 获胜
     * 3. ? 偶数且同侧  Bob 获胜当且仅当包含问号的一半的数字和恰好比不包含问号的一半的数字和小 9
     */
    public boolean sumGame(String num) {
        int count1 = 0, count2 = 0, sum1 = 0, sum2 = 0;
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                if (i < chars.length / 2) {
                    count1++;
                } else {
                    count2++;
                }
            } else {
                int temp = chars[i] - '0';
                if (i < chars.length / 2) {
                    sum1 += temp;
                } else {
                    sum2 += temp;
                }
            }
        }
        // 奇数 ?
        if ((count1 + count2) % 2 == 1) {
            return true;
        }
        // 如果问号多的一边数字和大
        if ((count1 - count2) * (sum1 - sum2) > 0) {
            return true;
        }
        return Math.abs(sum1 - sum2) != 9 * Math.abs(count1 - count2) / 2;
    }

}
