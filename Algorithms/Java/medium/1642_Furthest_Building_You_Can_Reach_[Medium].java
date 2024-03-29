/**
 * 1642. Furthest Building You Can Reach
 *
 *
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * Output: 4
 * Explanation: Starting at building 0, you can follow these steps:
 * - Go to building 1 without using ladders nor bricks since 4 >= 2.
 * - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
 * - Go to building 3 without using ladders nor bricks since 7 >= 6.
 * - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
 * It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
 *
 * Example 2:
 *
 * Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * Output: 7
 * Example 3:
 *
 * Input: heights = [14,3,19,3], bricks = 17, ladders = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1. 1 <= heights.length <= 10^5
 * 2. 1 <= heights[i] <= 10^6
 * 3. 0 <= bricks <= 10^9
 * 4. 0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach {

    // 贪心
    // 优先用 bricks，再之后用 ladder 去置换之前单次消耗最多的 bricks，直至无法前进
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 1; i < heights.length; i++) {
            int diff = heights[i] - heights[i - 1];
            if (diff > 0) {
                pq.add(diff);
                bricks -= diff;
                if (bricks < 0) {
                    if (ladders > 0) {
                        ladders--;
                        bricks += pq.remove();
                    } else {
                        return i - 1;
                    }
                }
            }
        }
        return heights.length - 1;
    }



    // TLE
    private int max = 0;

    public int furthestBuilding1(int[] heights, int bricks, int ladders) {
        if (ladders >= heights.length - 1) {
            return heights.length - 1;
        }
        dfs(heights, 0, bricks, ladders);
        return max;
    }

    private void dfs(int[] heights, int index, int bricks, int ladders) {
        max = Math.max(max, index);
        if (max == heights.length - 1) {
            return;
        }
        int diff = heights[index + 1] - heights[index];
        if (diff <= 0) {
            dfs(heights, index + 1, bricks, ladders);
            return;
        }
        if (bricks >= diff) {
            dfs(heights, index + 1, bricks - diff, ladders);
        }
        if (ladders > 0) {
            dfs(heights, index + 1, bricks, ladders - 1);
        }
    }

}
