/**
 * 1962. Remove Stones to Minimize the Total
 *
 *
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:
 *
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 *
 * Return the minimum possible total number of stones remaining after applying the k operations.
 *
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 *
 * Example 2:
 *
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 *
 *
 * Constraints:
 *
 * 1. 1 <= piles.length <= 10^5
 * 2. 1 <= piles[i] <= 10^4
 * 3. 1 <= k <= 10^5
 */
public class RemoveStonesToMinimizeTheTotal {

    public int minStoneSum(int[] piles, int k) {
        int[] frequency = new int[10001];
        for (int pile : piles) {
            frequency[pile]++;
        }
        int sum = 0;
        for (int i = 10000; i > 0; i--) {
            if (frequency[i] == 0) {
                continue;
            }
            if (k > 0) {
                int f = Math.min(frequency[i], k);
                frequency[(i + 1) / 2] += f;
                frequency[i] -= f;
                k -= f;
            }
            sum += i * frequency[i];
        }
        return sum;
    }

    public int minStoneSum1(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(piles.length, Comparator.reverseOrder());
        for (int pile : piles) {
            pq.add(pile);
        }
        while (--k >= 0) {
            pq.add((pq.remove() + 1) / 2);
        }
        int sum = 0;
        for (Integer pile : pq) {
            sum += pile;
        }
        return sum;
    }
}
