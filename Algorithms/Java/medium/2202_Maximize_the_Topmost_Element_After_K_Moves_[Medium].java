/**
 * 2202. Maximize the Topmost Element After K Moves
 *
 *
 * You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.
 *
 * In one move, you can perform either of the following:
 *
 * If the pile is not empty, remove the topmost element of the pile.
 * If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
 * You are also given an integer k, which denotes the total number of moves to be made.
 *
 * Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,2,4,0,6], k = 4
 * Output: 5
 * Explanation:
 * One of the ways we can end with 5 at the top of the pile after 4 moves is as follows:
 * - Step 1: Remove the topmost element = 5. The pile becomes [2,2,4,0,6].
 * - Step 2: Remove the topmost element = 2. The pile becomes [2,4,0,6].
 * - Step 3: Remove the topmost element = 2. The pile becomes [4,0,6].
 * - Step 4: Add 5 back onto the pile. The pile becomes [5,4,0,6].
 * Note that this is not the only way to end with 5 at the top of the pile. It can be shown that 5 is the largest answer possible after 4 moves.
 *
 *
 * Example 2:
 *
 * Input: nums = [2], k = 1
 * Output: -1
 * Explanation:
 * In the first move, our only option is to pop the topmost element of the pile.
 * Since it is not possible to obtain a non-empty pile after one move, we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i], k <= 10^9
 */
public class MaximizeTheTopmostElementAfterKMoves {

    // len(nums) = 1, if (k & 1) == 0 -> nums[0] else -1
    // i = 0,
    //      k 为偶数时总是可以保证 nums[0] 位于栈顶;
    //      k 为奇数时, 可以移除 nums[0], 移除 nums[1], 添加 nums[0] 来保证 nums[0] 位于栈顶
    // i = k - 1, 只能移除 nums[i]
    // i = k, 移除前 k 个元素, nums[i] 位于栈顶
    // 即 i < k - 1 && i = k 的元素可以位于栈顶, 求其中最大值即可
    public int maximumTop(int[] nums, int k) {
        int n = nums.length;
        if (n == 1 || k == 0) {
            return (k & 1) == 1 ? -1 : nums[0];
        }
        int ans = -1;
        if (n > k) {
            for (int i = 0; i < k - 1; i++) {
                ans = Math.max(ans, nums[i]);
            }
            ans = Math.max(ans, nums[k]);
        } else if (n == k) {
            for (int i = 0; i < n - 1; i++) {
                ans = Math.max(ans, nums[i]);
            }
        } else {
            // k > n, nums[i] 均属于 i < k - 1
            for (int num : nums) {
                ans = Math.max(ans, num);
            }
        }
        return ans;
    }

}
