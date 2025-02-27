/**
 * 2761. Prime Pairs With Target Sum
 *
 *
 * You are given an integer n. We say that two integers x and y form a prime number pair if:
 *
 * 1 <= x <= y <= n
 * x + y == n
 * x and y are prime numbers
 * Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in increasing order of xi. If there are no prime number pairs at all, return an empty array.
 *
 * Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: [[3,7],[5,5]]
 * Explanation: In this example, there are two prime pairs that satisfy the criteria.
 * These pairs are [3,7] and [5,5], and we return them in the sorted order as described in the problem statement.
 *
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: []
 * Explanation: We can show that there is no prime number pair that gives a sum of 2, so we return an empty array.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^6
 */
public class PrimePairsWithTargetSum {

    // 埃拉托斯特尼筛法
    private final static int LIMIT = 1_000_000;
    // 质数
    private final static int[] primes = new int[78498];
    // 非质数标记
    private final static boolean[] np = new boolean[LIMIT + 1];

    static {
        int idx = 0;
        for (int i = 2; i <= LIMIT; i++) {
            if (!np[i]) {
                primes[idx++] = i;
                // 标记质数 i 的倍数不是质数
                for (int j = i; j <= LIMIT / i; ++j) {
                    np[i * j] = true;
                }
            }
        }
    }

    public List<List<Integer>> findPrimePairs(int n) {
        if (n % 2 > 0) {
            // n 是奇数时 只能为 偶数 + 奇数 = 奇数
            // 偶数质数只有 2
            return n > 4 && !np[n - 2] ? List.of(List.of(2, n - 2)) : List.of();
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int x : primes) {
            int y = n - x;
            if (y < x) {
                break;
            }
            if (!np[y]) {
                ans.add(List.of(x, y));
            }
        }
        return ans;
    }

}
