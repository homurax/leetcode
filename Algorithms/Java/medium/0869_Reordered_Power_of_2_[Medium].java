/**
 * 869. Reordered Power of 2
 *
 *
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 *
 * Example 2:
 *
 * Input: 10
 * Output: false
 *
 * Example 3:
 *
 * Input: 16
 * Output: true
 *
 * Example 4:
 *
 * Input: 24
 * Output: false
 *
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOf2 {

    // 比较数字组成
    public boolean reorderedPowerOf2(int N) {
        int[] target = countNum(N);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(target, countNum(1 << i))) {
                return true;
            }
        }
        return false;
    }

    // 统计每个数字出现过的此时当作特征
    private int[] countNum(int n) {
        int[] count = new int[10];
        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }
        return count;
    }


}
