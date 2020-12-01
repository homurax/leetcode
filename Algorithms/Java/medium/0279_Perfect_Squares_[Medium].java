/**
 * 279. Perfect Squares
 *
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {

    /**
     * 四平方定理：每个自然数都可以表示为四个整数平法和
     *     p = a0^2 + a1^2 + a2^2 + a3^2
     *
     * 正整数可以表示为三个平方和的一个特殊条件
     *     n != 4^k * (8m + 7) <-> n = a0^2 + a1^2 + a2^2
     *
     * 如果 n 如果是 4^k * (8m + 7) 形式，那么 n 不能分解为 3 个平方和，同时可以断言不能分解为 2 个平方和，n 本身也不是完全平方数（不能分解为 1 个平方和）。
     * 因为如果是，就可以通过为表达式添加平方数 0，来把 n 分解为 3 个平方和，这与三平方定理相矛盾。
     * 那么 n 就只能分解为 4 个平方和。
     *
     * 如果 n 是可以被分解为 3 个平方和，还需要检查两种情况
     *     1. n 本身为完全平方数（n == int(sqrt(n)) ^ 2）。
     *     2. n 可以分解成 2 个完全平方数和（无数学定理辅助检查，枚举）。
     */
    public int numSquares(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }

        if (isSquare(n)) {
            return 1;
        }
        for (int i = 1; i * i <= n; i++) {
            if (isSquare(n - i * i)) {
                return 2;
            }
        }
        return 3;
    }

    private boolean isSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n == sqrt * sqrt;
    }


    // 动态规划
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, Integer.MAX_VALUE);

        int maxSquare = (int) Math.sqrt(n) + 1;
        int[] squares = new int[maxSquare];
        for (int i = 1; i < maxSquare; i++) {
            squares[i] = i * i;
        }

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s < maxSquare; s++) {
                if (i < squares[s]) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - squares[s]] + 1);
            }
        }
        return dp[n];
    }


}
