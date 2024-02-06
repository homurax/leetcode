/**
 * 2165. Smallest Value of the Rearranged Number
 * 
 * 
 * You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
 *
 * Return the rearranged number with minimal value.
 *
 * Note that the sign of the number does not change after rearranging the digits.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 310
 * Output: 103
 * Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310. 
 * The arrangement with the smallest value that does not contain any leading zeros is 103.
 * 
 * 
 * Example 2:
 *
 * Input: num = -7605
 * Output: -7650
 * Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
 * The arrangement with the smallest value that does not contain any leading zeros is -7650.
 *
 *
 * Constraints:
 *
 * -10^15 <= num <= 10^15
 */
public class SmallestValueOfTheRearrangedNumber {

    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        long temp = Math.abs(num);
        int n = (int) Math.log10(temp) + 1;
        int[] nums = new int[n];
        for (int i = 0; temp > 0; i++) {
            nums[i] = (int) (temp % 10);
            temp /= 10;
        }
        Arrays.sort(nums);
        long ans = 0;
        if (num < 0) {
            for (int i = n - 1; i >= 0; i--) {
                ans *= 10;
                ans += nums[i];
            }
            ans = -ans;
        } else {
            int i = 0;
            while (nums[i] == 0) {
                i++;
            }
            if (i > 0) {
                nums[0] ^= nums[i];
                nums[i] ^= nums[0];
                nums[0] ^= nums[i];
            }
            for (int t : nums) {
                ans *= 10;
                ans += t;
            }
        }
        return ans;
    }

}
