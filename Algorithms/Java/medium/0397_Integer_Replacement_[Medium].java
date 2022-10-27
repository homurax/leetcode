/**
 * 397. Integer Replacement
 *
 *
 * Given a positive integer n, you can apply one of the following operations:
 *
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 8
 * Output: 3
 * Explanation: 8 -> 4 -> 2 -> 1
 *
 *
 * Example 2:
 *
 * Input: n = 7
 * Output: 4
 * Explanation: 7 -> 8 -> 4 -> 2 -> 1
 * or 7 -> 6 -> 3 -> 2 -> 1
 *
 *
 * Example 3:
 *
 * Input: n = 4
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 2^31 - 1
 */
public class IntegerReplacement {

    // 贪心 从二进制考虑
    // 偶数 -> 右移
    // 奇数
    //      -> +1 从最低位开始，将连续一段的 1 进行置零，并在连续一段的高一位添加一个 1
    //      -> -1 抵消最低位的 1
    // 即次低位为 1 优先考虑 +1 操作（3 除外）
    public int integerReplacement(int n) {
        long t = n;
        int operation = 0;
        while (t != 1) {
            if (t % 2 == 0) {
                t >>= 1;
            } else {
                if (t != 3 && ((t >> 1) & 1) == 1) {
                    t++;
                } else {
                    t--;
                }
            }
            operation++;
        }
        return operation;
    }

    private Map<Long, Integer> map = new HashMap<>();

    // dfs
    public int integerReplacement1(int n) {
        return dfs(n);
    }

    private int dfs(long n) {
        if (n == 1) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int operation = (n % 2 == 0) ? dfs(n / 2) : Math.min(dfs(n - 1), dfs(n + 1));
        map.put(n, ++operation);
        return operation;
    }

}
