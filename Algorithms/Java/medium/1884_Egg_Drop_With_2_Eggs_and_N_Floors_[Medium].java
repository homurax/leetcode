/**
 * 1884. Egg Drop With 2 Eggs and N Floors
 *
 *
 * You are given two identical eggs and you have access to a building with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
 *
 * In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: We can drop the first egg from floor 1 and the second egg from floor 2.
 * If the first egg breaks, we know that f = 0.
 * If the second egg breaks but the first egg didn't, we know that f = 1.
 * Otherwise, if both eggs survive, we know that f = 2.
 *
 * Example 2:
 *
 * Input: n = 100
 * Output: 14
 * Explanation: One optimal strategy is:
 * - Drop the 1st egg at floor 9. If it breaks, we know f is between 0 and 8. Drop the 2nd egg starting
 *   from floor 1 and going up one at a time to find f within 7 more drops. Total drops is 1 + 7 = 8.
 * - If the 1st egg does not break, drop the 1st egg again at floor 22. If it breaks, we know f is between 9
 *   and 21. Drop the 2nd egg starting from floor 10 and going up one at a time to find f within 12 more
 *   drops. Total drops is 2 + 12 = 14.
 * - If the 1st egg does not break again, follow a similar process dropping the 1st egg from floors 34, 45,
 *   55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.
 * Regardless of the outcome, it takes at most 14 drops to determine f.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 */
public class EggDropWith2EggsAndNFloors {

    /**
     * 动态规划
     *
     *  dp[i][j] -> 有 i 枚鸡蛋时，验证 j 层楼需要的最少操作数
     *      dp[1][j] = j
     *
     *      dp[2][j] = dp[1][k - 1] + 1, (k 层破碎，转为 i = 1 的情况)
     *               = dp[2][j - k] + 1, (k 层没碎，向上检查)
     *               = min(dp[2][j], max(dp[1][k - 1] + 1, dp[2][j - k] + 1)), (考虑最坏情况)
     */
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= j; k++) {
                dp[j] = Math.min(dp[j], Math.max(k, dp[j - k] + 1));
            }
        }
        return dp[n];
    }


    /**
     * 第一枚鸡蛋，意义在于大范围确认
     *      k 层破碎 -> [0, k - 1]
     *      K 层不碎 -> [k + 1, n]
     *
     * 第二枚鸡蛋，意义在于逐层确认
     *      第一枚鸡蛋破碎时，只能按照 k + 1, k + 2, ..., n 的顺序逐层验证
     *
     *
     * 假设对于 n 层楼计算并返回要确定 f 确切的值的最小操作次数为 M
     *
     * 1. 第一次操作必然选择在 x <= M 层。
     *      反证法：当 x > M ，如果第一次操作后鸡蛋破碎，则转入第 2 枚鸡蛋任务，需要 x - 1 次操作逐层验证，总操作次数为 1 + (x - 1) = x > M ，违背总操作次数为 M 的假设
     * 2. 第 k 次操作第 1 枚鸡蛋的覆盖层数必须小于等于 M - k + 1，原因同 1
     * 3. 综合 1、2 的限制，可以得出 M 次操作可以覆盖的最大楼层数量为 sum = M + (M - 1) + (M - 2) + ... + 1 = (M + 1) * M / 2
     * 4. (M + 1) * M / 2 ≥ n，则满足条件的 M 最小值即为最小操作次数，用数学方法求解即可
     */
    public int twoEggDrop2(int n) {
        return (int) Math.ceil((Math.sqrt(1 + 8 * n) - 1) / 2);
    }

}
