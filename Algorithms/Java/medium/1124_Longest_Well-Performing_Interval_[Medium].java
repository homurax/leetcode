/**
 * 1124. Longest Well-Performing Interval
 *
 *
 * We are given hours, a list of the number of hours worked per day for a given employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 *
 *
 *
 * Example 1:
 *
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 *
 * Example 2:
 *
 * Input: hours = [6,6,6]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= hours.length <= 10^4
 * 2. 0 <= hours[i] <= 16
 */
public class LongestWellPerformingInterval {

    // cur > 0 -> 从开始到现在都满足，必然是最长的
    // cur <= 0 -> 如果存在 j == seen[cur - 1]，则 i - j 为可选区间，因为 cur - (cur - 1) > 0
    // seen 记录最小下标，cur - i, (i > 1) 一定不会出现在 cur - 1 的左边
    // cur - 1 不存在，cur - i, (i > 1) 也一定不存在
    public int longestWPI(int[] hours) {
        int ans = 0;
        int cur = 0;
        int n = hours.length;
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < n; i++) {
            cur += (hours[i] > 8) ? 1 : -1;
            if (cur > 0) {
                ans = i + 1;
            } else {
                if (seen.containsKey(cur - 1)) {
                    ans = Math.max(ans, i - seen.get(cur - 1));
                }
                if (!seen.containsKey(cur)) {
                    seen.put(cur, i);
                }
            }
        }
        return ans;
    }

}
