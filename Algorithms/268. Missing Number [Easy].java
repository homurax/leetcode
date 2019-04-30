package com.homurax.algorithm;

/**
 * 268. Missing Number
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {

        int length = nums.length;
        int ans = (length * (length + 1)) / 2;
        for (int num : nums) {
            ans -= num;
        }
        return ans;
    }

    /**
     * 用异或没有溢出风险
     * Index	0	1	2	3
     * Value	0	1	3	4
     *
     * missing = 4 ∧ (0∧0) ∧ (1∧1) ∧ (2∧3) ∧ (3∧4)
     *         = (4∧4) ∧ (0∧0) ∧ (1∧1) ∧ (3∧3) ∧ 2
     *         = 0∧0∧0∧0∧2
     *         = 2
     */
    public int missingNumber2(int[] nums) {

        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
