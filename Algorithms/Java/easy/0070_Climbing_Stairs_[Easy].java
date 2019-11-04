/**
 * 70. Climbing Stairs
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {

    /**
     * 递归模拟全部的情况
     * 超时了
     * 递归树存在重复部分
     */
    public int climbStairs(int n) {

        return climb(0, n);
    }

    private int climb(int curr, int target) {

        if (curr > target) return 0;
        if (curr == target) return 1;

        return climb(curr + 1, target) + climb(curr + 2, target);
    }

    /**
     * 递归
     * 避免重复部分
     */
    public int climbStairs2(int n) {

        int[] temp = new int[n + 1];
        return climb(0, n, temp);
    }

    private int climb(int curr, int target, int[] nums) {

        if (curr > target) return 0;
        if (curr == target) return 1;

        if (nums[curr] > 0) {
            return nums[curr];
        }

        nums[curr] = climb(curr + 1, target, nums) + climb(curr + 2, target, nums);
        return nums[curr];
    }


    /**
     * 动态规划
     * ans[i] = ans[i-1] + ans[i-2]
     */
    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int[] ansArr = new int[n + 1];
        ansArr[1] = 1;
        ansArr[2] = 2;
        for (int i = 3; i <= n; i++) {
            ansArr[i] = ansArr[i - 1] + ansArr[i - 2];
        }
        return ansArr[n];
    }
}
