/**
 * 2257. Count Unguarded Cells in the Grid
 *
 * You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
 *
 * A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
 *
 * Return the number of unoccupied cells that are not guarded.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
 * Output: 7
 * Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
 * There are a total of 7 unguarded cells, so we return 7.
 *
 *
 * Example 2:
 *
 *
 * Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * Output: 4
 * Explanation: The unguarded cells are shown in green in the above diagram.
 * There are a total of 4 unguarded cells, so we return 4.
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= guards.length, walls.length <= 5 * 10^4
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * All the positions in guards and walls are unique.
 */
public class CountUnguardedCellsInTheGrid {

    // 模拟
    // 行列中两段墙中有 guard 则标记被保卫的格子
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] tab = new int[m][n];
        for (int[] guard : guards) {
            tab[guard[0]][guard[1]] = 1;
        }
        for (int[] wall : walls) {
            tab[wall[0]][wall[1]] = 2;
        }
        // 行
        for (int[] row : tab) {
            for (int j = 0; j < n; j++) {
                if (row[j] == 2) {
                    continue;
                }
                int t = j;
                boolean hasGuard = false;
                for (; j < n && row[j] != 2; j++) {
                    if (row[j] == 1) {
                        hasGuard = true;
                    }
                }
                if (hasGuard) {
                    for (; t < j; t++) {
                        if (row[t] == 0) {
                            row[t] = 3;
                        }
                    }
                }
            }
        }
        // 列
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (tab[i][j] == 2) {
                    continue;
                }
                int t = i;
                boolean hasGuard = false;
                for (; i < m && tab[i][j] != 2; i++) {
                    if (tab[i][j] == 1) {
                        hasGuard = true;
                    }
                }
                if (hasGuard) {
                    for (; t < i; t++) {
                        if (tab[t][j] == 0) {
                            tab[t][j] = 3;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int[] row : tab) {
            for (int v : row) {
                if (v == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
