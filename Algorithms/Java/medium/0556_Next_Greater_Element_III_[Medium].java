/**
 * 556. Next Greater Element III
 *
 *
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 21
 *
 * Example 2:
 *
 * Input: n = 21
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 2^31 - 1
 */
public class NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        int ans = n;
        int count = 1;
        // 找到次低位小于最低位的位置
        while (ans >= 10 && ans / 10 % 10 >= ans % 10) {
            ans /= 10;
            count++;
        }
        ans /= 10;
        if (ans == 0) {
            return -1;
        }
        int target = ans % 10;
        int temp = n, index = 0;
        // 找到比次低位更大的值
        while (temp % 10 <= target) {
            temp /= 10;
            index++;
        }
        // 更换次低位
        ans += temp % 10 - target;
        // 反转
        for (int i = 0; i < count; i++, n /= 10) {
            int d = i == index ? target : n % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && d > 7)) {
                return -1;
            }
            ans = ans * 10 + d;
        }
        return ans;
    }

}
