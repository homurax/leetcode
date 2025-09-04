/**
 * 2910. Minimum Number of Groups to Create a Valid Assignment
 *
 *
 * You are given a collection of numbered balls and instructed to sort them into boxes for a nearly balanced distribution. There are two rules you must follow:
 *
 * Balls with the same box must have the same value. But, if you have more than one ball with the same number, you can put them in different boxes.
 * The biggest box can only have one more ball than the smallest box.
 * Return the fewest number of boxes to sort these balls following these rules.
 *
 *
 *
 * Example 1:
 *
 * Input: balls = [3,2,3,2,3]
 *
 * Output: 2
 *
 * Explanation:
 *
 * We can sort balls into boxes as follows:
 *
 * [3,3,3]
 * [2,2]
 * The size difference between the two boxes doesn't exceed one.
 *
 *
 * Example 2:
 *
 * Input: balls = [10,10,10,3,1,1]
 *
 * Output: 4
 *
 * Explanation:
 *
 * We can sort balls into boxes as follows:
 *
 * [10]
 * [10,10]
 * [3]
 * [1,1]
 * You can't use fewer than four boxes while still following the rules. For example, putting all three balls numbered 10 in one box would break the rule about the maximum size difference between boxes.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class MinimumNumberOfGroupsToCreateAValidAssignment {

    // 对于组内数量为 k, 假设分 q = cnt[x] / k 组, r = cnt[x] % k
    // 如果 q < r, 则无法分为组内数量为 k 或者 k + 1 组, 因为 r 无法分配到 q 组中
    public int minGroupsForValidAssignment(int[] balls) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int ball : balls) {
            cnt.merge(ball, 1, Integer::sum);
        }
        int k = balls.length;
        for (int c : cnt.values()) {
            k = Math.min(k, c);
        }
        for (; ; k--) {
            int ans = 0;
            for (int c : cnt.values()) {
                if (c / k < c % k) {
                    ans = 0;
                    break;
                }
                ans += (c + k) / (k + 1);
            }
            if (ans > 0) {
                return ans;
            }
        }
    }

}
