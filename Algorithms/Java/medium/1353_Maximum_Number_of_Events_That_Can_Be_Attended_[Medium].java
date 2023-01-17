/**
 * 1353. Maximum Number of Events That Can Be Attended
 *
 *
 * You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 *
 *
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1. 1 <= events.length <= 10^5
 * 2. events[i].length == 2
 * 3. 1 <= startDayi <= endDayi <= 10^5
 */
public class MaximumNumberOfEventsThatCanBeAttended {

    // 开始时间相同的会议中，优先选择结束时间最小的会议 -> 小顶堆
    public int maxEvents(int[][] events) {
        int ans = 0;
        int n = events.length;
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0, day = events[0][0]; i < n || !pq.isEmpty(); day++) {
            while (i < n && events[i][0] == day) {
                pq.offer(events[i][1]);
                i++;
            }
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }
        return ans;
    }

}
