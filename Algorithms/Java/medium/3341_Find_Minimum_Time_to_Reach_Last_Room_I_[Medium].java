/**
 * 3341. Find Minimum Time to Reach Last Room I
 *
 *
 * There is a dungeon with n x m rooms arranged as a grid.
 *
 * You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
 *
 * Return the minimum time to reach the room (n - 1, m - 1).
 *
 * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
 *
 *
 *
 * Example 1:
 *
 * Input: moveTime = [[0,4],[4,4]]
 *
 * Output: 6
 *
 * Explanation:
 *
 * The minimum time required is 6 seconds.
 *
 * At time t == 4, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 5, move from room (1, 0) to room (1, 1) in one second.
 * Example 2:
 *
 * Input: moveTime = [[0,0,0],[0,0,0]]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The minimum time required is 3 seconds.
 *
 * At time t == 0, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 1, move from room (1, 0) to room (1, 1) in one second.
 * At time t == 2, move from room (1, 1) to room (1, 2) in one second.
 * Example 3:
 *
 * Input: moveTime = [[0,1],[1,2]]
 *
 * Output: 3
 *
 *
 *
 * Constraints:
 *
 * 2 <= n == moveTime.length <= 50
 * 2 <= m == moveTime[i].length <= 50
 * 0 <= moveTime[i][j] <= 10^9
 */
public class FindMinimumTimeToReachLastRoomI {

    // 移动到 (i, j) 时的时间为 max(t, max(i, j)) + 1
    // 维护每个点的最小移动时间
    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;
        int[][] minTime = new int[m][n];
        for (int[] row : minTime) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minTime[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // t = 0, i = 0, j = 0
        pq.offer(new int[]{0, 0, 0});
        for (; ; ) {
            int[] p = pq.poll();
            int t = p[0], i = p[1], j = p[2];
            if (i == m - 1 && j == n - 1) {
                return t;
            }
            if (t > minTime[i][j]) {
                continue;
            }
            for (int[] dir : DIRS) {
                int x = i + dir[0], y = j + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    int newTime = Math.max(t, moveTime[x][y]) + 1;
                    if (newTime < minTime[x][y]) {
                        minTime[x][y] = newTime;
                        pq.add(new int[]{newTime, x, y});
                    }
                }
            }
        }
    }
    
}
