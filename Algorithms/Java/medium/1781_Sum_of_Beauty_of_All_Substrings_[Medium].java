/**
 * 1781. Sum of Beauty of All Substrings
 *
 *
 * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
 *
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabcb"
 * Output: 5
 * Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
 *
 * Example 2:
 *
 * Input: s = "aabcbaa"
 * Output: 17
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 500
 * 2. s consists of only lowercase English letters.
 */
public class SumOfBeautyOfAllSubstrings {

    // 滑窗
    // len = 1, 2 的字串 max - min 均为 0
    public int beautySum1(String s) {
        int sum = 0;
        int n = s.length();
        char[] letters = s.toCharArray();
        for (int len = 3; len <= n; len++) {
            int l = 0, r = 0;
            int[] count = new int[26];
            while (r < n) {
                count[letters[r++] - 'a']++;
                if (l + len == r) {
                    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                    for (int num : count) {
                        if (num > 0) {
                            min = Math.min(min, num);
                            max = Math.max(max, num);
                        }
                    }
                    sum += max - min;
                    count[letters[l++] - 'a']--;
                }
            }
        }
        return sum;
    }

    public int beautySum(String s) {
        int sum = 0;
        int n = s.length();
        char[] letters = s.toCharArray();
        for (int l = 0; l < n; l++) {
            int[] count = new int[26];
            int num = letters[l] - 'a', maxNum = num, minNum = num;
            count[num]++;
            for (int r = l + 1; r < n; r++) {
                int curr =  letters[r] - 'a';
                count[curr]++;
                // 考虑更新 maxNum
                if (count[curr] > count[maxNum]) {
                    maxNum = curr;
                }
                // 考虑更新 minNum
                if (curr == minNum) {
                    for (int i = 0; i < 26; i++) {
                        if (count[i] > 0 && count[i] < count[minNum]) {
                            minNum = i;
                        }
                    }
                } else if (count[curr] < count[minNum]) {
                    minNum = curr;
                }
                sum += count[maxNum] - count[minNum];
            }
        }
        return sum;
    }

}
