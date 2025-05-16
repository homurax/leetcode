/**
 * 3181. Maximum Total Reward Using Operations II
 *
 *
 * You are given an integer array rewardValues of length n, representing the values of rewards.
 *
 * Initially, your total reward x is 0, and all indices are unmarked. You are allowed to perform the following operation any number of times:
 *
 * Choose an unmarked index i from the range [0, n - 1].
 * If rewardValues[i] is greater than your current total reward x, then add rewardValues[i] to x (i.e., x = x + rewardValues[i]), and mark the index i.
 * Return an integer denoting the maximum total reward you can collect by performing the operations optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: rewardValues = [1,1,3,3]
 *
 * Output: 4
 *
 * Explanation:
 *
 * During the operations, we can choose to mark the indices 0 and 2 in order, and the total reward will be 4, which is the maximum.
 *
 *
 * Example 2:
 *
 * Input: rewardValues = [1,6,4,3,2]
 *
 * Output: 11
 *
 * Explanation:
 *
 * Mark the indices 0, 2, and 1 in order. The total reward will then be 11, which is the maximum.
 *
 *
 *
 * Constraints:
 *
 * 1 <= rewardValues.length <= 5 * 10^4
 * 1 <= rewardValues[i] <= 5 * 10^4
 */
public class MaximumTotalRewardUsingOperationsII {

    public int maxTotalReward(int[] rewardValues) {
        // 如果存在 max - 1, 或者两数之和为 max - 1, 则直接返回
        int m = 0;
        for (int v : rewardValues) {
            m = Math.max(m, v);
        }
        Set<Integer> set = new HashSet<>();
        for (int v : rewardValues) {
            if (v == m - 1) {
                return m * 2 - 1;
            }
            if (set.contains(v)) {
                continue;
            }
            if (set.contains(m - 1 - v)) {
                return m * 2 - 1;
            }
            set.add(v);
        }
        Arrays.sort(rewardValues);
        int prev = 0;
        BigInteger f = BigInteger.ONE;
        for (int v : rewardValues) {
            if (v == prev) {
                continue;
            }
            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(v));
            prev = v;
        }
        return f.bitLength() - 1;
    }

}
