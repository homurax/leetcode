/**
 * 1663. Smallest String With A Given Numeric Value
 *
 *
 * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
 *
 * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values. For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
 *
 * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 *
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 27
 * Output: "aay"
 * Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
 *
 * Example 2:
 *
 * Input: n = 5, k = 73
 * Output: "aaszz"
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10^5
 * 2. n <= k <= 26 * n
 */
public class SmallestStringWithAGivenNumericValue {

    // 后面的尽可能大 前面的就可以尽可能小
    // 当后全用最大值仍然用不完 k 时 多出的部分就分给当前位置
    public String getSmallestString(int n, int k) {
        char[] letters = new char[n];
        int pos = 0;
        while (pos < n) {
            int offset = k - (n - pos - 1) * 26;
            if (offset <= 0) {
                letters[pos] = 'a';
            } else {
                letters[pos] = (char) ('a' + offset - 1);
            }
            k -= letters[pos] - 'a' + 1;
            pos++;
        }
        return new String(letters);
    }

    // 贪心
    public String getSmallestString2(int n, int k) {
        char[] letters = new char[n];
        Arrays.fill(letters, 'a');
        k -= n;
        for (int i = n - 1; i >= 0; i--) {
            if (k < 26) {
                letters[i] = (char) ('a' + k);
                return new String(letters);
            }
            letters[i] = 'z';
            k -= 25;
        }
        return new String(letters);
    }

}
