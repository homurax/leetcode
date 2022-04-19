/**
 * 2121. Intervals Between Identical Elements
 *
 *
 * You are given a 0-indexed array of n integers arr.
 *
 * The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
 *
 * Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
 *
 * Note: |x| is the absolute value of x.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,3,1,2,3,3]
 * Output: [4,2,7,2,4,4,5]
 * Explanation:
 * - Index 0: Another 2 is found at index 4. |0 - 4| = 4
 * - Index 1: Another 1 is found at index 3. |1 - 3| = 2
 * - Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
 * - Index 3: Another 1 is found at index 1. |3 - 1| = 2
 * - Index 4: Another 2 is found at index 0. |4 - 0| = 4
 * - Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
 * - Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
 *
 * Example 2:
 *
 * Input: arr = [10,5,10,10]
 * Output: [5,0,3,4]
 * Explanation:
 * - Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
 * - Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
 * - Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
 * - Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
 *
 *
 * Constraints:
 *
 * 1. n == arr.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= arr[i] <= 10^5
 */
public class IntervalsBetweenIdenticalElements {

    public long[] getDistances(int[] arr) {
        int n = arr.length;
        List<Integer>[] buckets = new List[100001];
        for (int i = 0; i < n; i++) {
            if (buckets[arr[i]] == null) {
                buckets[arr[i]] = new ArrayList<>();
            }
            buckets[arr[i]].add(i);
        }
        long[] ans = new long[n];
        for (List<Integer> list : buckets) {
            if (list == null) {
                continue;
            }
            // 先计算第一个
            for (Integer index : list) {
                ans[list.get(0)] += index - list.get(0);
            }
            // i 之前的需要增加 list.get(i) - list.get(i - 1)
            // i 之后的（含自身）需要减少 list.get(i) - list.get(i - 1)
            // i * (list[i] - list[i - 1]) - (n - i) * list[i] - list[i - 1]
            for (int i = 1; i < list.size(); i++) {
                ans[list.get(i)] = ans[list.get(i - 1)] + (2L * i - list.size()) * (list.get(i) - list.get(i - 1));
            }
        }
        return ans;
    }

}
