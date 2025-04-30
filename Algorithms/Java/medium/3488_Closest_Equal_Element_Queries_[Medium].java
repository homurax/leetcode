/**
 * 3488. Closest Equal Element Queries
 *
 *
 * You are given a circular array nums and an array queries.
 *
 * For each query i, you have to find the following:
 *
 * The minimum distance between the element at index queries[i] and any other index j in the circular array, where nums[j] == nums[queries[i]]. If no such index exists, the answer for that query should be -1.
 * Return an array answer of the same size as queries, where answer[i] represents the result for query i.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
 *
 * Output: [2,-1,3]
 *
 * Explanation:
 *
 * Query 0: The element at queries[0] = 0 is nums[0] = 1. The nearest index with the same value is 2, and the distance between them is 2.
 * Query 1: The element at queries[1] = 3 is nums[3] = 4. No other index contains 4, so the result is -1.
 * Query 2: The element at queries[2] = 5 is nums[5] = 3. The nearest index with the same value is 1, and the distance between them is 3 (following the circular path: 5 -> 6 -> 0 -> 1).
 * Example 2:
 *
 * Input: nums = [1,2,3,4], queries = [0,1,2,3]
 *
 * Output: [-1,-1,-1,-1]
 *
 * Explanation:
 *
 * Each value in nums is unique, so no index shares the same value as the queried element. This results in -1 for all queries.
 *
 *
 *
 * Constraints:
 *
 * 1 <= queries.length <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 0 <= queries[i] < nums.length
 */
public class ClosestEqualElementQueries {

    // 维护相同值坐标, 二分查找后比较前后取最小
    // 对于某值的 indexList 可以前后添加哨兵以规避考虑循环的情况
    // indexList = [a, b, c] -> [c - n, a, b, c, a + n]
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            int t = list.getFirst();
            list.addFirst(list.getLast() - n);
            list.add(t + n);
        }
        List<Integer> ans = new ArrayList<>();
        for (int query : queries) {
            List<Integer> list = map.get(nums[query]);
            if (list.size() == 3) {
                ans.add(-1);
                continue;
            }
            int i = Collections.binarySearch(list, query);
            ans.add(Math.min(query - list.get(i - 1), list.get(i + 1) - query));
        }
        return ans;
    }

}
