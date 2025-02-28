/**
 * 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
 *
 *
 * You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:
 *
 * Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
 * Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
 *
 * The gcd of two integers is the greatest common divisor of the two integers.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,6,3,4]
 * Output: 4
 * Explanation: We can do the following operations:
 * - Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
 * - Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
 * - Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
 * - Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].
 *
 *
 * Example 2:
 *
 * Input: nums = [2,10,6,14]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make all the elements equal to 1.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 50
 * 1 <= nums[i] <= 10^6
 */
public class MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {

    // gcd(nums) > 1, 则返回 -1
    // count(nums[i] == 1) > 0, 则只需要操作 n - count_1
    public int minOperations(int[] nums) {
        int gcdAll = 0;
        int cnt1 = 0;
        int n = nums.length;
        for (int num : nums) {
            gcdAll = gcd(num, gcdAll);
            if (num == 1) {
                cnt1++;
            }
        }
        if (gcdAll > 1) {
            return -1;
        }
        if (cnt1 > 0) {
            return n - cnt1;
        }
        int minSize = n;
        List<int[]> g = new ArrayList<>(); // [GCD，相同 GCD 闭区间的右端点]
        for (int i = 0; i < n; ++i) {
            g.add(new int[]{nums[i], i});
            // 原地去重，相同的 GCD 都相邻在一起
            int j = 0;
            for (int[] p : g) {
                p[0] = gcd(p[0], nums[i]);
                if (g.get(j)[0] == p[0]) { // 合并相同值，下标取最小的
                    g.get(j)[1] = p[1];
                } else {
                    g.set(++j, p);
                }
            }
            g.subList(j + 1, g.size()).clear();
            if (g.getFirst()[0] == 1) {
                minSize = Math.min(minSize, i - g.getFirst()[1] + 1);
            }
        }
        return minSize + n - 2;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
