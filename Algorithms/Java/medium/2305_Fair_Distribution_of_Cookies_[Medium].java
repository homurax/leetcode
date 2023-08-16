/**
 * 2305. Fair Distribution of Cookies
 *
 *
 * You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.
 *
 * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.
 *
 * Return the minimum unfairness of all distributions.
 *
 *
 *
 * Example 1:
 *
 * Input: cookies = [8,15,10,20,8], k = 2
 * Output: 31
 * Explanation: One optimal distribution is [8,15,8] and [10,20]
 * - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
 * - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
 * The unfairness of the distribution is max(31,30) = 31.
 * It can be shown that there is no distribution with an unfairness less than 31.
 *
 *
 * Example 2:
 *
 * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
 * Output: 7
 * Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
 * - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
 * - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
 * - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
 * The unfairness of the distribution is max(7,7,7) = 7.
 * It can be shown that there is no distribution with an unfairness less than 7.
 *
 *
 * Constraints:
 *
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 10^5
 * 2 <= k <= cookies.length
 */
public class FairDistributionOfCookies {

    // 1. 使用 bit 标识是否选取; m 代表一种选取状态; sum[m] 代表选取状态对应的饼干之和
    // 2. f[i][m] 代表前 i 个人分配情况为 m 的时候的最小不公平度
    // 3. 设 p 为 m 子集, 对 i 分配 p 后, 最小不公平度变为 max(f[i - 1][m - p], sum[p])
    // 4. f[i][m] = min(f[i][m], max(f[i - 1][m - p], sum[p]))
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int mask = 1 << n;
        int INF = 0x3f3f3f;
        int[] sum = new int[mask];
        for (int m = 0; m < mask; m++) {
            for (int i = 0; i < n; i++) {
                sum[m] += ((m >> i) & 1) == 1 ? cookies[i] : 0;
            }
        }
        int[][] f = new int[k + 10][mask];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(f[i], INF);
        }
        f[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int m = 0; m < mask; m++) {
                for (int p = m; p != 0; p = (p - 1) & m) {
                    f[i][m] = Math.min(f[i][m], Math.max(f[i - 1][m - p], sum[p]));
                }
            }
        }
        return f[k][mask - 1];
    }

}
