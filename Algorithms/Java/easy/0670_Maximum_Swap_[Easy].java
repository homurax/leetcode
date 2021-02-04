/**
 * 670. Maximum Swap
 *
 *
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Note:
 * The given number is in the range [0, 10^8]
 */
public class MaximumSwap {

    public int maximumSwap(int num) {
        char[] nums = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < nums.length; i++) {
            last[nums[i] - '0'] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int d = 9; d > nums[i] - '0'; d--) {
                if (last[d] > i) {
                    char temp = nums[i];
                    nums[i] = nums[last[d]];
                    nums[last[d]] = temp;
                    return Integer.parseInt(new String(nums));
                }
            }
        }
        return num;
    }

}
