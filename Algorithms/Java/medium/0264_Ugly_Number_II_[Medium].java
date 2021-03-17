/**
 * 264. Ugly Number II
 *
 *
 * Given an integer n, return the nth ugly number.
 *
 * Ugly number is a positive number whose prime factors only include 2, 3, and/or 5.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 is typically treated as an ugly number.
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 1690
 */
public class UglyNumberII {

    public static Ugly u = new Ugly();

    public int nthUglyNumber(int n) {
        return u.nums[n - 1];
    }

}

// 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
class Ugly {
    public int[] nums = new int[1690];

    Ugly() {
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < 1690; i++) {
            int ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;
            // 不用 if else 避免重复
            if (ugly == nums[i2] * 2) {
                i2++;
            }
            if (ugly == nums[i3] * 3) {
                i3++;
            }
            if (ugly == nums[i5] * 5) {
                i5++;
            }
        }
    }
}
