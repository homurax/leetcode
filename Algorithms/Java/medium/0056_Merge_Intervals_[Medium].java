/**
 * 56. Merge Intervals
 *
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1. 1 <= intervals.length <= 10^4
 * 2. intervals[i].length == 2
 * 3. 0 <= starti <= endi <= 10^4
 */
public class MergeIntervals {


    // PriorityQueue
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        for (int[] interval : intervals) {
            pq.offer(interval);
        }
        List<int[]> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            while (!pq.isEmpty() && pq.peek()[0] <= curr[1]) {
                int[] next = pq.poll();
                if (next[1] > curr[1]) {
                    curr[1] = next[1];
                }
            }
            list.add(curr);
        }
        return list.toArray(new int[list.size()][]);
    }

}
