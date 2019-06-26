package com.homurax.algorithm;

/**
 * 754. Reach a Number
 *
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 *
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 *
 * Return the minimum number of steps required to reach the destination.
 *
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 *
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 *
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 */
public class ReachANumber {

    public int reachNumber(int target) {

        int i = 0, j = 0;
        target = Math.abs(target);
        while (j < target || (j - target) % 2 != 0) {
            j += ++i;
        }
        return i;
    }

    public int reachNumber2(int target) {

        target = Math.abs(target);
        int num = (int) Math.ceil((Math.sqrt(1 + 8.0 * target) - 1) / 2);
        int diff = num * (num + 1) / 2 - target;
        return num + (diff % 2) * (num % 2 + 1);
    }
}
