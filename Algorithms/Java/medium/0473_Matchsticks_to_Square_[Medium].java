/**
 * 473. Matchsticks to Square
 *
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matchsticks = [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 *
 * Example 2:
 *
 * Input: matchsticks = [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 *
 * Constraints:
 *
 * 1. 1 <= matchsticks.length <= 15
 * 2. 0 <= matchsticks[i] <= 10^9
 */
public class MatchsticksToSquare {


    private int squareSide;
    private int[] matchsticks;
    private int[] sums;

    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) {
            return false;
        }
        int perimeter = 0;
        for (int matchstick : matchsticks) {
            perimeter += matchstick;
        }
        this.squareSide = perimeter / 4;
        if (perimeter != squareSide * 4) {
            return false;
        }
        this.sums = new int[4];
        this.matchsticks = matchsticks;
        // 从最长的开始 可以尽早发现单根火柴大于边长的情况 便于剪枝
        Arrays.sort(matchsticks);
        return dfs(matchsticks.length - 1);
    }

    private boolean dfs(int index) {
        if (index == -1) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }
        int matchstick = matchsticks[index];
        for (int i = 0; i < 4; i++) {
            if (sums[i] + matchstick <= squareSide) {
                sums[i] += matchstick;
                if (dfs(index - 1)) {
                    return true;
                }
                sums[i] -= matchstick;
            }
        }
        return false;
    }

}
