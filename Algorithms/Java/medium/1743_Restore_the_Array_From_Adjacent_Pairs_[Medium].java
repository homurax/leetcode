/**
 * 1743. Restore the Array From Adjacent Pairs
 *
 *
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
 *
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
 *
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 *
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 *
 * Example 2:
 *
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 *
 * Example 3:
 *
 * Input: adjacentPairs = [[100000,-100000]]
 * Output: [100000,-100000]
 *
 *
 * Constraints:
 *
 * 1. nums.length == n
 * 2. adjacentPairs.length == n - 1
 * 3. adjacentPairs[i].length == 2
 * 4. 2 <= n <= 10^5
 * 5. -10^5 <= nums[i], ui, vi <= 10^5
 * 6. There exists some nums that has adjacentPairs as its pairs.
 */
public class RestoreTheArrayFromAdjacentPairs {

    // 邻接一个元素的元素一定是端点
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        // Integer begin = graph.entrySet().stream().filter(e -> e.getValue().size() == 1).findFirst().get().getKey();
        Integer begin = null;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                begin = entry.getKey();
                break;
            }
        }
        int n = adjacentPairs.length + 1;
        int[] rst = new int[n];
        rst[0] = begin;
        rst[1] = graph.get(begin).get(0);
        for (int i = 1; i < n - 1; i++) {
            List<Integer> pair = graph.get(rst[i]);
            rst[i + 1] = pair.get(0) == rst[i - 1] ? pair.get(1) : pair.get(0);
        }
        return rst;
    }

    public int[] restoreArray2(int[][] adjacentPairs) {
        int[] index = new int[200001];
        boolean[] exists = new boolean[200001];
        for (int i = 0; i < adjacentPairs.length; i++) {
            int[] pair = adjacentPairs[i];
            index[pair[0] + 100000] += i;
            index[pair[1] + 100000] += i;
            exists[pair[0] + 100000] = !exists[pair[0] + 100000];
            exists[pair[1] + 100000] = !exists[pair[1] + 100000];
        }
        // 两两抵消 只有端点 exists 为 true
        int next = 0;
        for (int i = 0; i < 200001; i++) {
            if (exists[i]) {
                next = i - 100000;
                break;
            }
        }
        int[] rst = new int[adjacentPairs.length + 1];
        for (int i = 0, idx = 0; i < rst.length; i++) {
            rst[i] = next;
            int[] pair = adjacentPairs[index[next + 100000] - idx];
            // 端点以外的点在 index[...] 中记录的是两个 adjacentPairs 中的 index 之和
            idx = index[next + 100000] - idx;
            // next 就是 pair 中一员 下个元素 = sum(pair) - next
            next = pair[0] + pair[1] - next;
        }
        return rst;
    }


}
