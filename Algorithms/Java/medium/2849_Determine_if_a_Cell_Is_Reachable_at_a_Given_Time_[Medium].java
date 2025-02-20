/**
 * 2849. Determine if a Cell Is Reachable at a Given Time
 *
 *
 * You are given four integers sx, sy, fx, fy, and a non-negative integer t.
 *
 * In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.
 *
 * Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
 *
 * A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
 * Output: true
 * Explanation: Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells depicted in the picture above.
 *
 *
 * Example 2:
 *
 *
 * Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
 * Output: false
 * Explanation: Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3) by going through the cells depicted in the picture above. Hence, we cannot reach cell (7, 3) at the third second.
 *
 *
 * Constraints:
 *
 * 1 <= sx, sy, fx, fy <= 10^9
 * 0 <= t <= 10^9
 */
public class DetermineIfACellIsReachableAtAGivenTime {

    // 最短距离能达到即可, 由于是 8 个方向可达, 围绕目标转圈即可
    // 最快的方式是斜着走 + 直着走, 即两边的投影
    // 特殊情况, 两点相同时 t != 1
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        return Math.max(Math.abs(sx - fx), Math.abs(sy - fy)) <= t;
    }

}
