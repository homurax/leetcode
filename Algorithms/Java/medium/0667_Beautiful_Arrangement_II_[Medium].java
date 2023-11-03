/**
 * 667. Beautiful Arrangement II
 *
 *
 * Given two integers n and k, construct a list answer that contains n different positive integers ranging from 1 to n and obeys the following requirement:
 *
 * Suppose this list is answer = [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
 * Return the list answer. If there multiple valid answers, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 1
 * Output: [1,2,3]
 * Explanation: The [1,2,3] has three different positive integers ranging from 1 to 3, and the [1,1] has exactly 1 distinct integer: 1
 *
 *
 * Example 2:
 *
 * Input: n = 3, k = 2
 * Output: [1,3,2]
 * Explanation: The [1,3,2] has three different positive integers ranging from 1 to 3, and the [2,1] has exactly 2 distinct integers: 1 and 2.
 *
 *
 * Constraints:
 *
 * 1 <= k < n <= 10^4
 */
public class BeautifulArrangementII {

    // 构建 k 个不同的差值需要 k + 1 个数
    // 先构建 n - (k + 1) 个公差为 1 的等差数列
    // [1, n, 2, n - 1, 3, ...] 相邻差值会从 n - 1、n - 2 开始递减至 1，共 n − 1 种
    // [n - k, n, ...]
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int t = n - (k + 1);
        for (int i = 0; i < t; i++) {
            ans[i] = i + 1;
        }
        for (int i = t, a = n - k, b = n; i < n; ) {
            ans[i++] = a++;
            if (i < n) {
                ans[i++] = b--;
            }
        }
        return ans;
    }

}
