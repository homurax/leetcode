/**
 * 2929. Distribute Candies Among Children II
 *
 *
 * You are given two positive integers n and limit.
 *
 * Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, limit = 2
 * Output: 3
 * Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
 *
 *
 * Example 2:
 *
 * Input: n = 3, limit = 3
 * Output: 10
 * Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^6
 * 1 <= limit <= 10^6
 */
public class DistributeCandiesAmongChildrenII {

    // C(n + 2, 2) = 所有方案数, 选择两个位置 分出三个区域
    // C(n - (limit + 1) + 2, 2) = 至少一个人分配超过 limit 数
    // C(n - 2(limit + 1) + 2, 2) = 至少两个人分配超过 limit 数
    // C(n - 3 * limit - 1) = 三个人分配超过 limit 数
    public long distributeCandies(int n, int limit) {
        return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
    }

    private long c2(int n) {
        return n > 1 ? (long) n * (n - 1) / 2 : 0;
    }

}
