/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 *
 *
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * Output: 3
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 4 for each city are:
 * City 0 -> [City 1, City 2]
 * City 1 -> [City 0, City 2, City 3]
 * City 2 -> [City 0, City 1, City 3]
 * City 3 -> [City 1, City 2]
 * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
 *
 * Example 2:
 *
 *
 * Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * Output: 0
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 2 for each city are:
 * City 0 -> [City 1]
 * City 1 -> [City 0, City 4]
 * City 2 -> [City 3, City 4]
 * City 3 -> [City 2, City 4]
 * City 4 -> [City 1, City 2, City 3]
 * The city 0 has 1 neighboring city at a distanceThreshold = 2.
 *
 *
 * Constraints:
 *
 * 1. 2 <= n <= 100
 * 2. 1 <= edges.length <= n * (n - 1) / 2
 * 3. edges[i].length == 3
 * 4. 0 <= fromi < toi < n
 * 5. 1 <= weighti, distanceThreshold <= 10^4
 * 6. All pairs (fromi, toi) are distinct.
 */
public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

    /**
     * 定义 dp[i][j] -> i 到 j 的距离
     * 如果存在 dp[i][k] + dp[k][j] < dp[i][j] 说明找到了更近的路径 应当更新 dp[i][j]
     *
     * dp[i][j] = min(dp[i][k] + dp[k][j], dp[i][j])
     */
    public int findTheCity1(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[100][100];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        for (int[] edge : edges) {
            dp[edge[0]][edge[1]] = edge[2];
            dp[edge[1]][edge[0]] = edge[2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
                }
            }
        }
        int ans = 0;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dp[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                ans = i;
            }
        }
        return ans;
    }


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for (int[] edge : edges) {
            if (edge[2] <= distanceThreshold) {
                dp[edge[0]][edge[1]] = edge[2];
                dp[edge[1]][edge[0]] = edge[2];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = i; j < n; j++) {
                    if (dp[k][j] < Integer.MAX_VALUE && dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[j][i] = dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        int ans = 0;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dp[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minCount) {
                minCount = count;
                ans = i;
            }
        }
        return ans;
    }

}
