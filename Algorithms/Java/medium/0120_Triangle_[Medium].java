/**
 * 120. Triangle
 *
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 *
 * Example 2:
 *
 * Input: triangle = [[-10]]
 * Output: -10
 *
 *
 * Constraints:
 *
 * 1. 1 <= triangle.length <= 200
 * 2. triangle[0].length == 1
 * 3. triangle[i].length == triangle[i - 1].length + 1
 * 4. -10^4 <= triangle[i][j] <= 10^4
 *
 *
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
public class Triangle {

    // 模拟 太慢了且修改了 triangle
    public int minimumTotal1(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (j == 0) {
                    row.set(j, row.get(j) + prevRow.get(j));
                }
                else if (j == row.size() - 1) {
                    row.set(j, row.get(j) + prevRow.get(j - 1));
                }
                else {
                    row.set(j, row.get(j) + Math.min(prevRow.get(j - 1), prevRow.get(j)));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer num : triangle.get(triangle.size() - 1)) {
            min = Math.min(min, num);
        }
        for (List<Integer> integers : triangle) {
            System.out.println(integers);
        }
        return min;
    }


    /**
     * dp
     *
     * dp[i][j] = min_path((0, 0) -> (i, j))
     * dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j]
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            dp[i][0] = dp[i - 1][0] + row.get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + row.get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + row.get(i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }


    /**
     * dp + 空间优化
     * 显然 dp[i][j] 只与 dp[i - 1][...] 有关
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            List<Integer> row = triangle.get(i);
            dp[curr][0] = dp[prev][0] + row.get(0);
            for (int j = 1; j < i; j++) {
                dp[curr][j] = Math.min(dp[prev][j], dp[prev][j - 1]) + row.get(j);
            }
            dp[curr][i] = dp[prev][i - 1] + row.get(i);
        }
        int min = dp[(n - 1) % 2][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[(n - 1) % 2][i]);
        }
        return min;
    }

    /**
     * 用 i -> 0 这样递减的方式去枚举 j  就只需要一个长度为 n 的一维数组
     * dp[j] = min(dp[j − 1], dp[j]) + triangle[i][j]
     */
    public int minimumTotal4(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            dp[i] = dp[i - 1] + row.get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + row.get(j);
            }
            dp[0] += row.get(0);
        }
        int min = dp[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

}
