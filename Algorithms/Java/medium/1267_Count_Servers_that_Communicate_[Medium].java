/**
 * 1267. Count Servers that Communicate
 *
 *
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 *
 * Example 2:
 *
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 *
 * Example 3:
 *
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m <= 250
 * 4. 1 <= n <= 250
 * 5. grid[i][j] == 0 or 1
 */
public class CountServersThatCommunicate {

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int ans = 0;
        // 逐个判断每个点
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                    ans++;
                }
            }
        }*/
        // row[i] > 1 可直接累加 row[i]; row[i] = 1, 找到唯一的点 row[i][j], 判断 col[j] 是否大于 1, 大于则 ans++
        for (int i = 0; i < m; i++) {
            if (row[i] > 1) {
                ans += row[i];
            } else if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if (col[j] > 1) {
                            ans++;
                        }
                        break;
                    }
                }
            }
        }
        return ans;
    }

}
