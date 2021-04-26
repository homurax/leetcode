/**
 * 826. Most Profit Assigning Work
 *
 *
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 *
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 *
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 *
 * What is the most profit we can make?
 *
 * Example 1:
 *
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 * Notes:
 *
 * 1. 1 <= difficulty.length = profit.length <= 10000
 * 2. 1 <= worker.length <= 10000
 * 3. difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 */
public class MostProfitAssigningWork {

    // 从难度最小的向后找, ability 递增 记录更新 max
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(worker);

        int ans = 0, i = 0, max = 0;
        for (int ability : worker) {
            while (i < n && ability >= jobs[i][0]) {
                max = Math.max(max, jobs[i++][1]);
            }
            ans += max;
        }
        return ans;
    }

    // dp[ability] -> ability 能获得的最大收益
    // dp[i] = max(dp[i], dp[i - 1])
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        int maxAbility = 0, maxDifficulty = 0;
        for (int i : worker) {
            maxAbility = Math.max(i, maxAbility);
        }
        for (int i : difficulty) {
            maxDifficulty = Math.max(i, maxDifficulty);
        }

        int[] dp = new int[Math.max(maxAbility, maxDifficulty) + 1];
        // 相同 ability 能获得的最大收益
        for (int i = 0; i < difficulty.length; i++) {
            dp[difficulty[i]] = Math.max(dp[difficulty[i]], profit[i]);
        }
        // 维护 dp
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        int ans = 0;
        for (int ability : worker) {
            ans += dp[ability];
        }
        return ans;
    }
}
