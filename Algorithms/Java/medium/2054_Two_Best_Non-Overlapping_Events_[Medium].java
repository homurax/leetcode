/**
 * 2054. Two Best Non-Overlapping Events
 *
 *
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 *
 * Example 2:
 *
 * Example 1 Diagram
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 *
 * Example 3:
 *
 *
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 *
 *
 * Constraints:
 *
 * 1. 2 <= events.length <= 10^5
 * 2. events[i].length == 3
 * 3. 1 <= startTimei <= endTimei <= 10^9
 * 4. 1 <= valuei <= 10^6
 */
public class TwoBestNonOverlappingEvents {

    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int n = events.length;
        int[] suffixMax = new int[n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, events[i][2]);
            suffixMax[i] = max;
        }
        int ans = 0;
        for (int[] event : events) {
            int l = 0, r = events.length - 1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (events[mid][0] <= event[1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l < n) {
                ans = Math.max(ans, event[2] + suffixMax[l]);
            } else {
                ans = Math.max(ans, event[2]);
            }
        }
        return ans;
    }

    public int maxTwoEvents1(int[][] events) {
        int n = events.length;
        int[][] eventArr = new int[n * 2][3];
        for (int i = 0; i < n; i++) {
            eventArr[i * 2] = new int[]{events[i][0], 0, events[i][2]};
            eventArr[i * 2 + 1] = new int[]{events[i][1], 1, events[i][2]};
        }
        Arrays.sort(eventArr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int ans = 0, prevMax = 0;
        for (int[] event : eventArr) {
            if (event[1] == 0) {
                ans = Math.max(ans, prevMax + event[2]);
            } else { // 比较之前累积的活动 和 当前事件当作前一个活动的价值
                prevMax = Math.max(prevMax, event[2]);
            }
        }
        return ans;
    }

}
