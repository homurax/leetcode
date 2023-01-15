/**
 * 2400. Number of Ways to Reach a Position After Exactly k Steps
 *
 *
 * You are given two positive integers startPos and endPos. Initially, you are standing at position startPos on an infinite number line. With one step, you can move either one position to the left, or one position to the right.
 *
 * Given a positive integer k, return the number of different ways to reach the position endPos starting from startPos, such that you perform exactly k steps. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Two ways are considered different if the order of the steps made is not exactly the same.
 *
 * Note that the number line includes negative integers.
 *
 *
 *
 * Example 1:
 *
 * Input: startPos = 1, endPos = 2, k = 3
 * Output: 3
 * Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * It can be proven that no other way is possible, so we return 3.
 *
 * Example 2:
 *
 * Input: startPos = 2, endPos = 5, k = 10
 * Output: 0
 * Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.
 *
 *
 * Constraints:
 *
 * 1. 1 <= startPos, endPos, k <= 1000
 */
public class NumberOfWaysToReachAPositionAfterExactlyKSteps {

    private Map<Integer, Long> memo;
    private int endPos;

    public int numberOfWays(int startPos, int endPos, int k) {
        this.memo = new HashMap<>();
        this.endPos = endPos;
        return (int) dfs(startPos, k);
    }

    private long dfs(int pos, int left) {
        if (Math.abs(pos - endPos) > left) {
            return 0;
        }
        if (left == 0) {
            return 1;
        }
        int key = pos * 1001 + left;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long v = (dfs(pos + 1, left - 1) + dfs(pos - 1, left - 1)) % 1_000_000_007;
        memo.put(key, v);
        return v;
    }

}
