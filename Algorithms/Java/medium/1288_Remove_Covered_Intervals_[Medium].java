/**
 * 1288. Remove Covered Intervals
 *
 * Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 *
 * After doing so, return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        // 对起点进行升序排序，如果起点相同，则对终点进行降序排序
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] == arr2[0] ? arr2[1] - arr1[1]: arr1[0] - arr2[0]);
        int ans = 0;
        int currEnd, preEnd = 0;
        for (int[] interval : intervals) {
            currEnd = interval[1];
            if (preEnd < currEnd) {
                ans++;
                preEnd = currEnd;
            }
        }
        return ans;
    }

}
