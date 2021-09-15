/**
 * 1899. Merge Triplets to Form Target Triplet
 *
 *
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
 * The target triplet [2,7,5] is now an element of triplets.
 *
 * Example 2:
 *
 * Input: triplets = [[1,3,4],[2,5,8]], target = [2,5,8]
 * Output: true
 * Explanation: The target triplet [2,5,8] is already an element of triplets.
 *
 * Example 3:
 *
 * Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
 * - Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
 * The target triplet [5,5,5] is now an element of triplets.
 *
 * Example 4:
 *
 * Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
 * Output: false
 * Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.
 *
 *
 * Constraints:
 *
 * 1. 1 <= triplets.length <= 10^5
 * 2. triplets[i].length == target.length == 3
 * 3. 1 <= ai, bi, ci, x, y, z <= 1000
 */
public class MergeTripletsToFormTargetTriplet {

    // 存在 triplet[i] > target[i] 的 triplet 不可选
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int x = target[0], y = target[1], z = target[2];
        int a = 0, b = 0, c = 0;
        for (int[] triplet : triplets) {
            int ai = triplet[0], bi = triplet[1], ci = triplet[2];
            if (ai <= x && bi <= y && ci <= z) {
                a = Math.max(a, ai);
                b = Math.max(b, bi);
                c = Math.max(c, ci);
            }
        }
        return a == x && b == y && c == z;
    }

    public boolean mergeTriplets1(int[][] triplets, int[] target) {
        boolean found1 = false, found2 = false, found3 = false;
        int x = target[0], y = target[1], z = target[2];
        for (int[] triplet : triplets) {
            if (triplet[0] <= x && triplet[1] <= y && triplet[2] <= z) {
                found1 |= triplet[0] == x;
                found2 |= triplet[1] == y;
                found3 |= triplet[2] == z;
                if (found1 && found2 && found3) {
                    return true;
                }
            }
        }
        return false;
    }

}
