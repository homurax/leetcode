/**
 * 526. Beautiful Arrangement
 *
 *
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1,2]:
 *     - perm[1] = 1 is divisible by i = 1
 *     - perm[2] = 2 is divisible by i = 2
 * The second beautiful arrangement is [2,1]:
 *     - perm[1] = 2 is divisible by i = 1
 *     - i = 2 is divisible by perm[2] = 1
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 15
 */
public class BeautifulArrangement {

    private int ans;
    private List<Integer>[] match;
    private boolean[] visited;

    public int countArrangement1(int N) {
        this.ans = 0;
        this.visited = new boolean[N + 1];
        this.match = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            match[i] = new ArrayList<>();
        }
        // 每个 index 的可选数
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(1, N);
        return ans;
    }
    // 回溯
    public void backtrack(int index, int n) {
        if (index == n + 1) {
            ans++;
            return;
        }
        for (int num : match[index]) {
            if (!visited[num]) {
                visited[num] = true;
                backtrack(index + 1, n);
                visited[num] = false;
            }
        }
    }


    /**
     * 用二进制表示选取状态
     *
     * 二进制表示中，二进制的第 i 位为 1，代表数字 i+1 已被选取
     * 100110 有三个 1，代表排列前三位已被选取，三个 1 分别在二进制第 1、2、5 位置上, 所以是 2、3、6 三个数字被选取，即排列前三位是 2、3、6
     * f[100110] 代表前三位是 2、3、6 时的排列数量
     * ans = f[(1 << n) - 1]
     */
    public int countArrangement(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        // 通过 mask 枚举
        for (int mask = 1; mask < (1 << n); mask++) {
            int num = Integer.bitCount(mask);
            // 遍历 mask 的每一位，以 mask = 100110 为例，此 mask 代表 2 3 6 三个数字在排列的前三位
            // 求三个数字 2 3 6 的排列方式，先确定 2 3 6 哪些数字能放到第三位，然后累加另外两个数字的排列数量来获得
            // 第三位可以为 6，则 f[100110] += f[000110] (2、3在前两位时的排列数量)
            // 第三位可以为 3，则 f[100110] += f[100010] (2、6在前两位时的排列数量)
            for (int i = 0; i < n; i++) {
                // 先从被选取的数字中找到能放到最高位 num 的数字，然后将剩余 num-1 个数字的排列方式累加到 f[mask] 中
                // mask & (1<<i) 用来判断 mask 第 i 位是否为 1，如果为 1，说明第 i+1 个数字被选取
                // ((num % (i + 1)) == 0 || (i + 1) % num == 0) 判断被选取的数字 i+1 能否放到位置 num 上，
                if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                    // 将 mask 第 i 位设置为 0
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }


}
