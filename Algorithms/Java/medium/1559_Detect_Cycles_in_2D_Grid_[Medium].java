/**
 * 1559. Detect Cycles in 2D Grid
 *
 *
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 *
 * Example 2:
 *
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 *
 * Example 3:
 *
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 500
 * 4. grid consists only of lowercase English letters.
 */
public class DetectCyclesIn2DGrid {

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && grid[i - 1][j] == grid[i][j] && union((i - 1) * n + j, i * n + j, parent)) {
                    return true;
                }
                if (j > 0 && grid[i][j - 1] == grid[i][j] && union(i * n + j - 1, i * n + j, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean union(int id1, int id2, int[] parent) {
        int parent1 = find(id1, parent);
        int parent2 = find(id2, parent);
        if (parent1 == parent2) {
            return true;
        }
        parent[parent2] = parent1;
        return false;
    }

    private int find(int id, int[] parent) {
        if (id != parent[id]) {
            parent[id] = parent[parent[id]];
            id = parent[id];
        }
        return id;
    }

}
