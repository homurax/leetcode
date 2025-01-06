/**
 * 1737. Change Minimum Characters to Satisfy One of Three Conditions
 *
 *
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.
 *
 * Your goal is to satisfy one of the following three conditions:
 *
 * Every letter in a is strictly less than every letter in b in the alphabet.
 * Every letter in b is strictly less than every letter in a in the alphabet.
 * Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "caa"
 * Output: 2
 * Explanation: Consider the best way to make each condition true:
 * 1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
 * 2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
 * 3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
 * The best way was done in 2 operations (either condition 1 or condition 3).
 *
 *
 * Example 2:
 *
 * Input: a = "dabadd", b = "cda"
 * Output: 3
 * Explanation: The best way is to make condition 1 true by changing b to "eee".
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 10^5
 * a and b consist only of lowercase letters.
 */
public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {

    // 分别计算取最小值
    public int minCharacters(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            cnt1[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            cnt2[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26 && ans != 0; i++) {
            int c1 = n - cnt1[i];
            int c2 = m - cnt2[i];
            // a b 全变为 i
            ans = Math.min(ans, c1 + c2);
            if (i == 0) { // 不能小于 i
                continue;
            }
            int r1 = 0, r2 = 0;
            // a < i && b >= i
            for (int j = i; j < 26; j++) { // a 中 >= i 的都需要修改
                r1 += cnt1[j];
            }
            for (int j = 0; j < i; j++) { // b 中 < i 的都需要修改
                r1 += cnt2[j];
            }
            // a >= i && b < i
            for (int j = 0; j < i; j++) { // a 中 < i 的都需要修改
                r2 += cnt1[j];
            }
            for (int j = i; j < 26; j++) { // b 中 >= i 的都需要修改
                r2 += cnt2[j];
            }
            ans = Math.min(ans, Math.min(r1, r2));
        }
        return ans;
    }

}
