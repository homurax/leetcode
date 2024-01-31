/**
 * 2593. Find Score of an Array After Marking All Elements
 *
 *
 * You are given an array nums consisting of positive integers.
 *
 * Starting with score = 0, apply the following algorithm:
 *
 * Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
 * Add the value of the chosen integer to score.
 * Mark the chosen element and its two adjacent elements if they exist.
 * Repeat until all the array elements are marked.
 * Return the score you get after applying the above algorithm.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,3,4,5,2]
 * Output: 7
 * Explanation: We mark the elements as follows:
 * - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
 * - 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
 * - 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
 * Our score is 1 + 2 + 4 = 7.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,3,5,1,3,2]
 * Output: 5
 * Explanation: We mark the elements as follows:
 * - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
 * - 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
 * - 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
 * Our score is 1 + 2 + 2 = 5.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class FindScoreOfAnArrayAfterMarkingAllElements {

    // 对下标排序即可
    // visited 偏移一位避免 -1
    public long findScore(int[] nums) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(i -> nums[i]));
        boolean[] visited = new boolean[n + 2];
        long ans = 0;
        for (int i : ids) {
            if (!visited[i + 1]) {
                visited[i] = visited[i + 2] = true;
                ans += nums[i];
            }
        }
        return ans;
    }


    public long findScore1(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            treeMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        boolean[] visited = new boolean[n];
        long ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            Integer num = entry.getKey();
            List<Integer> idxList = entry.getValue();
            for (int idx : idxList) {
                if (visited[idx]) {
                    continue;
                }
                ans += num;
                visited[idx] = true;
                if (idx > 0) {
                    visited[idx - 1] = true;
                }
                if (idx < n - 1) {
                    visited[idx + 1] = true;
                }
            }
        }
        return ans;
    }

}
