/**
 * 3439. Reschedule Meetings for Maximum Free Time I
 *
 *
 * You are given an integer eventTime denoting the duration of an event, where the event occurs from time t = 0 to time t = eventTime.
 *
 * You are also given two integer arrays startTime and endTime, each of length n. These represent the start and end time of n non-overlapping meetings, where the ith meeting occurs during the time [startTime[i], endTime[i]].
 *
 * You can reschedule at most k meetings by moving their start time while maintaining the same duration, to maximize the longest continuous period of free time during the event.
 *
 * The relative order of all the meetings should stay the same and they should remain non-overlapping.
 *
 * Return the maximum amount of free time possible after rearranging the meetings.
 *
 * Note that the meetings can not be rescheduled to a time outside the event.
 *
 *
 *
 * Example 1:
 *
 * Input: eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * Reschedule the meeting at [1, 2] to [2, 3], leaving no meetings during the time [0, 2].
 *
 *
 * Example 2:
 *
 * Input: eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
 *
 * Output: 6
 *
 * Explanation:
 *
 *
 *
 * Reschedule the meeting at [2, 4] to [1, 3], leaving no meetings during the time [3, 9].
 *
 *
 * Example 3:
 *
 * Input: eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
 *
 * Output: 0
 *
 * Explanation:
 *
 * There is no time during the event not occupied by meetings.
 *
 *
 *
 * Constraints:
 *
 * 1 <= eventTime <= 10^9
 * n == startTime.length == endTime.length
 * 2 <= n <= 10^5
 * 1 <= k <= n
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
 */
public class RescheduleMeetingsForMaximumFreeTimeI {

    // 连续间隔 -> 滑窗
    // 累加间隔, i >= k 时减去 i - k 的区间
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int ans = 0;
        int sum = 0;
        for (int i = 0; i <= startTime.length; i++) {
            sum += duration(i, startTime, endTime, eventTime);
            if (i >= k) {
                ans = Math.max(ans, sum);
                sum -= duration(i - k, startTime, endTime, eventTime);
            }
        }
        return ans;
    }

    private int duration(int i, int[] startTime, int[] endTime, int eventTime) {
        if (i == 0) { // 第一个间隔是第一个会议之前的区间
            return startTime[0];
        }
        if (i == startTime.length) { // 最后一个间隔是总时长 - 最后一个会议结束
            return eventTime - endTime[i - 1];
        }
        // 当前会议开始 - 上个会议结束
        return startTime[i] - endTime[i - 1];
    }

}
