/**
 * 436. Find Right Interval
 *
 *
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 *
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 *
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 * Example 2:
 *
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
 *
 * Example 3:
 *
 * Input: intervals = [[1,4],[2,3],[3,4]]
 * Output: [-1,2,-1]
 * Explanation: There is no right interval for [1,4] and [3,4].
 * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 *
 *
 * Constraints:
 *
 * 1. 1 <= intervals.length <= 2 * 10^4
 * 2. intervals[i].length == 2
 * 3. -10^6 <= starti <= endi <= 10^6
 * 4. The start point of each interval is unique.
 */
public class FindRightInterval {

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        TreeMap<Integer, Integer> indexMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(intervals[i][0], i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> entry = indexMap.ceilingEntry(intervals[i][1]);
            if (entry == null) {
                ans[i] = -1;
            } else {
                ans[i] = entry.getValue();
            }
        }
        return ans;
    }

    public int[] findRightInterval1(int[][] intervals) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            min = Math.min(min, interval[0]);
            max = Math.max(max, interval[1]);
        }
        int[] index = new int[max - min + 1];
        Arrays.fill(index, -1);
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            index[intervals[i][0] - min] = i;
        }
        for (int i = index.length - 2; i >= 0; i--) {
            if (index[i] == -1) {
                index[i] = index[i + 1];
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = index[intervals[i][1] - min];
        }
        return ans;
    }

}
