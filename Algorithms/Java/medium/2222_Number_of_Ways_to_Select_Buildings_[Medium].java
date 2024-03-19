/**
 * 2222. Number of Ways to Select Buildings
 *
 *
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 *
 * s[i] = '0' denotes that the ith building is an office and
 * s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection. However, to ensure variety, no two consecutive buildings out of the selected buildings can be of the same type.
 *
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011" which is not allowed due to having two consecutive buildings of the same type.
 * Return the number of valid ways to select 3 buildings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "001101"
 * Output: 6
 * Explanation:
 * The following sets of indices selected are valid:
 * - [0,2,4] from "001101" forms "010"
 * - [0,3,4] from "001101" forms "010"
 * - [1,2,4] from "001101" forms "010"
 * - [1,3,4] from "001101" forms "010"
 * - [2,4,5] from "001101" forms "101"
 * - [3,4,5] from "001101" forms "101"
 * No other selection is valid. Thus, there are 6 total ways.
 *
 *
 * Example 2:
 *
 * Input: s = "11100"
 * Output: 0
 * Explanation: It can be shown that there are no valid selections.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 */
public class NumberOfWaysToSelectBuildings {

    // 010 -> 对于每个1，累加左边0个数 x 右边0个数
    // 101 -> 对于每个0，累加左边1个数 x 右边1个数
    public long numberOfWays(String s) {
        int total0 = 0;
        int n = s.length();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '0') {
                total0++;
            }
        }
        long ans = 0;
        int cnt0 = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                ans += (long) cnt0 * (total0 - cnt0);
            } else {
                int cnt1 = i - cnt0;
                ans += (long) cnt1 * (n - total0 - cnt1);
                cnt0++;
            }
        }
        return ans;
    }

}
