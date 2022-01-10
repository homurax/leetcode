/**
 * 2055. Plates Between Candles
 *
 *
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * ex-1
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 *
 * Example 2:
 *
 * ex-2
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 *
 *
 * Constraints:
 *
 * 1. 3 <= s.length <= 10^5
 * 2. s consists of '*' and '|' characters.
 * 3. 1 <= queries.length <= 10^5
 * 4. queries[i].length == 2
 * 5. 0 <= lefti <= righti < s.length
 */
public class PlatesBetweenCandles {

    // 预先准备好快速确定左右下标及计算蜡烛数
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] plates = new int[n + 1];
        int[] leftCandles = new int[n];
        int[] rightCandles = new int[n];
        char[] table = s.toCharArray();
        for (int i = 0, candleIdx = -1; i < n; i++) {
            plates[i + 1] = plates[i];
            if (table[i] == '|') {
                candleIdx = i;
            } else {
                plates[i + 1]++;
            }
            leftCandles[i] = candleIdx;
        }
        for (int i = n - 1, candleIdx = n; i >= 0; i--) {
            if (table[i] == '|') {
                candleIdx = i;
            }
            rightCandles[i] = candleIdx;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = rightCandles[queries[i][0]], r = leftCandles[queries[i][1]];
            if (l + 1 < r) {
                ans[i] = plates[r] - plates[l];
            }
        }
        System.out.println(Arrays.toString(leftCandles));
        System.out.println(Arrays.toString(rightCandles));
        System.out.println(Arrays.toString(plates));
        return ans;
    }



    // TLE
    public int[] platesBetweenCandles1(String s, int[][] queries) {
        char[] table = s.toCharArray();
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (r - l < 2) {
                continue;
            }
            int l_index = l;
            while (table[l_index] != '|' && l_index < r - 1) {
                l_index++;
            }
            if (table[l_index] != '|') {
                continue;
            }
            int r_index = r;
            while (table[r_index] != '|' && r_index > l_index + 1) {
                r_index--;
            }
            if (table[r_index] != '|') {
                continue;
            }
            for (int j = l_index + 1; j < r_index; j++) {
                if (table[j] == '*') {
                    ans[i]++;
                }
            }
        }
        return ans;
    }


}
