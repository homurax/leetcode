/**
 * 1717. Maximum Score From Removing Substrings
 *
 *
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 *
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 *
 * Example 2:
 *
 * Input: s = "aabbaaxybbaabb", x = 5, y = 4
 * Output: 20
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. 1 <= x, y <= 10^4
 * 3. s consists of lowercase English letters.
 */
public class MaximumScoreFromRemovingSubstrings {

    // TLE
    public int maximumGain(String s, int x, int y) {
        int count1, count2;
        char[] letters = s.toCharArray();
        if (x >= y) {
            count1 = removeCount(letters, 'a', 'b');
            count2 = removeCount(letters, 'b', 'a');
        } else {
            count2 = removeCount(letters, 'b', 'a');
            count1 = removeCount(letters, 'a', 'b');
        }
        return count1 * x + count2 * y;
    }

    private int removeCount(char[] letters, char letter1, char letter2) {
        int count = 0;
        boolean change;
        do {
            change = false;
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == letter1) {
                    for (int j = i + 1; j < letters.length; j++) {
                        if (letters[j] == '-') {
                            continue;
                        }
                        if (letters[j] == letter2) {
                            change = true;
                            count++;
                            letters[i] = '-';
                            letters[j] = '-';
                        }
                        break;
                    }
                }
            }
        } while (change);
        return count;
    }


    // 连续的 ab 一次处理完
    public int maximumGain1(String s, int x, int y) {
        int point = 0;
        int countA= 0, countB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                if (x > y && countA > 0) {
                    point += x;
                    countA--;
                } else {
                    countB++;
                }
            } else if (c == 'a') {
                if (x < y && countB > 0) {
                    point += y;
                    countB--;
                } else {
                    countA++;
                }
            } else {
                if (countA > 0 && countB > 0) {
                    // 没有在匹配到 a 或 b 的时候就直接累加 一定是组合值较小的情况
                    point += Math.min(countA, countB) * Math.min(x, y);
                }
                countA= 0;
                countB = 0;
            }
        }
        if (countA > 0 && countB > 0) {
            point += Math.min(countA, countB) * Math.min(x, y);
        }
        return point;
    }

    public int maximumGain2(String s, int x, int y) {
        int point = 0;
        char letter1 = 'a', letter2 = 'b';
        int countA = 0, countB = 0;
        if (x < y) {
            x = x + y;
            y = x - y;
            x = x - y;
            letter1 = 'b';
            letter2 = 'a';
        }
        for (char c : s.toCharArray()) {
            if (c == letter1) {
                countA++;
            } else if (c == letter2) {
                if (countA > 0) {
                    point += x;
                    countA--;
                } else {
                    countB++;
                }
            } else {
                point += y * Math.min(countA, countB);
                countA = 0;
                countB = 0;
            }
        }
        return point + y * Math.min(countA, countB);
    }

}
