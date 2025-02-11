/**
 * 424. Minimum Cost to Make Arrays Identical
 *
 *
 * You are given two integer arrays arr and brr of length n, and an integer k. You can perform the following operations on arr any number of times:
 *
 * Split arr into any number of contiguous subarrays and rearrange these subarrays in any order. This operation has a fixed cost of k.
 * Choose any element in arr and add or subtract a positive integer x to it. The cost of this operation is x.
 *
 * Return the minimum total cost to make arr equal to brr.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [-7,9,5], brr = [7,-2,-5], k = 2
 *
 * Output: 13
 *
 * Explanation:
 *
 * Split arr into two contiguous subarrays: [-7] and [9, 5] and rearrange them as [9, 5, -7], with a cost of 2.
 * Subtract 2 from element arr[0]. The array becomes [7, 5, -7]. The cost of this operation is 2.
 * Subtract 7 from element arr[1]. The array becomes [7, -2, -7]. The cost of this operation is 7.
 * Add 2 to element arr[2]. The array becomes [7, -2, -5]. The cost of this operation is 2.
 * The total cost to make the arrays equal is 2 + 2 + 7 + 2 = 13.
 *
 *
 * Example 2:
 *
 * Input: arr = [2,1], brr = [2,1], k = 0
 *
 * Output: 0
 *
 * Explanation:
 *
 * Since the arrays are already equal, no operations are needed, and the total cost is 0.
 *
 *
 *
 * Constraints:
 *
 * 1 <= arr.length == brr.length <= 10^5
 * 0 <= k <= 2 * 10^10
 * -10^5 <= arr[i] <= 10^5
 * -10^5 <= brr[i] <= 10^5
 */
public class MinimumCostToMakeArraysIdentical {

    // 可以分为长度 1 的 sub, 即代价 k 可以直接对 arr 排序
    // cost_2 = sum(abs(arr[i] - brr[i]))
    // cost_1 = k + sum(abs(arr[i] - brr[i])), arr 与 brr 排序
    public long minCost(int[] arr, int[] brr, long k) {
        long cost2 = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            cost2 += Math.abs(arr[i] - brr[i]);
        }
        if (cost2 <= k) {
            return cost2;
        }
        long cost1 = k;
        Arrays.sort(arr);
        Arrays.sort(brr);
        for (int i = 0; i < n; i++) {
            cost1 += Math.abs(arr[i] - brr[i]);
        }
        return Math.min(cost1, cost2);
    }

}
