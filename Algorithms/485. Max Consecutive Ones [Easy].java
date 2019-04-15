/**
 * 485. Max Consecutive Ones
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *     The maximum number of consecutive 1s is 3.
 * Note:
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes {

    /**
     * return前的max计算是应对数组中全为1的情况
     * 有点累赘
     */
    public int findMaxConsecutiveOnes(int[] nums) {

        int count = 0, max = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        return max;
    }


    public int findMaxConsecutiveOnes2(int[] nums) {

        int max = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int val = nums[i];
            if (val == 1) {
                int end = i + 1;
                while (end < length && nums[end] == 1) {
                    end++;
                }
                max = Math.max(max, end - i);
                i = end;
            }
        }
        return max;
    }

}
