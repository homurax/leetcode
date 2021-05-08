/**
 * 373. Find K Pairs with Smallest Sums
 *
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums1.length, nums2.length <= 10^4
 * 2. -10^9 <= nums1[i], nums2[i] <= 10^9
 * 3. nums1 and nums2 both are sorted in ascending order.
 * 4. 1 <= k <= 1000
 */
public class FindKPairsWithSmallestSums {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> qp = new PriorityQueue<>((a, b) -> b[0] + b[1] - a[0] - a[1]);
        for (int i : nums1) {
            for (int j : nums2) {
                if (qp.size() < k) {
                    qp.offer(new int[]{i, j});
                } else if (i + j < qp.peek()[0] + qp.peek()[1]) {
                    qp.poll();
                    qp.offer(new int[]{i, j});
                }
            }
        }
        LinkedList<List<Integer>> ans = new LinkedList<>();
        while (!qp.isEmpty()) {
            int[] poll = qp.poll();
            ans.addFirst(Arrays.asList(poll[0], poll[1]));
        }
        return ans;
    }

}
