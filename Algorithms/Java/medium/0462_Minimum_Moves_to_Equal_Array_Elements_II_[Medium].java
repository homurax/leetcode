/**
 * 462. Minimum Moves to Equal Array Elements II
 *
 *
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 *
 * You may assume the array's length is at most 10,000.
 *
 * Example:
 *
 * Input:
 * [1,2,3]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 *
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class MinimumMovesToEqualArrayElementsII {

    /**
     * 找到一个点 x，使得这 N 个点到 x 的距离之和最小
     * 数量为奇数: x 必须为这 N 个数中的唯一中位数
     * 数量为偶数: x 只要是区间 [p, q] 中的任意一个数即可
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int sum = 0, mid = nums[nums.length / 2];
        for (int num : nums) {
            sum += Math.abs(mid - num);
        }
        return sum;
    }

}
