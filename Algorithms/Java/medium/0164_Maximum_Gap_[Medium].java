/**
 * 164. Maximum Gap
 *
 *
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 *
 *
 * Example 2:
 *
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class MaximumGap {

    // 基数排序
    // 分为十个桶（0 ~ 9），所有数字按照最小位（个位数）依次排序归入，将所有桶中数据串联
    // 所有数字按照十位数依次排序归入，将所有桶中数据串联
    // 知道处理完所有数字中的最高位，将所有桶中数据串联即为排序结果
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int[] temp = new int[n];
        long divisor = 1;
        int max = Arrays.stream(nums).max().getAsInt();
        while (max >= divisor) {
            int[] buckets = new int[10];
            for (int num : nums) { // 余数个数
                buckets[(num / (int) divisor) % 10]++;
            }
            for (int i = 1; i < 10; i++) { // 累加较小的余数代表当前余数对应本轮排序后的位置
                buckets[i] += buckets[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) { // 桶中同样余数的位置是从大变小的 所以 nums 倒序遍历
                int digit = (nums[i] / (int) divisor) % 10;
                temp[buckets[digit] - 1] = nums[i];
                buckets[digit]--;
            }
            System.arraycopy(temp, 0, nums, 0, n);
            divisor *= 10;
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }

    // 利用最大值不小于平均值特性
    // 如果把两两之差小于 d 的数，分到同一桶中，那么答案一定不会是同一个桶内的两数之差，而是不同的桶的两数之差
    // 取第一个桶的最大值，和第二个桶的最小值作差；取第二个桶的最大值，和第三个桶的最小值作差；依此类推
    // 从 m 开始，把元素值在 m, m+1, ⋯, m+d−1 中的数分到第一个桶，把元素值在 m+d, m+d+1, ⋯, m+2d−1 中的数分到第二个桶，依此类推
    // nums[i] 放入桶 ⌊(nums[i] - m) / d⌋
    public int maximumGap1(int[] nums) {
        int m = Integer.MAX_VALUE;
        int M = Integer.MIN_VALUE;
        for (int x : nums) {
            m = Math.min(m, x);
            M = Math.max(M, x);
        }
        if (M - m <= 1) {
            return M - m;
        }
        int n = nums.length;
        int d = (M - m + n - 2) / (n - 1); // 答案至少是 d
        int[][] buckets = new int[(M - m) / d + 1][2];
        for (int[] b : buckets) {
            b[0] = Integer.MAX_VALUE;
            b[1] = Integer.MIN_VALUE;
        }
        for (int x : nums) {
            int[] b = buckets[(x - m) / d];
            b[0] = Math.min(b[0], x); // 维护桶内元素的最小值和最大值
            b[1] = Math.max(b[1], x);
        }
        int ans = 0;
        int preMax = Integer.MAX_VALUE;
        for (int[] b : buckets) {
            if (b[0] != Integer.MAX_VALUE) { // 非空桶
                // 桶内最小值，减去上一个非空桶的最大值
                ans = Math.max(ans, b[0] - preMax);
                preMax = b[1];
            }
        }
        return ans;
    }

}
