/**
 * 3326. Minimum Division Operations to Make Array Non Decreasing
 *
 *
 * You are given an integer array nums.
 *
 * Any positive divisor of a natural number x that is strictly less than x is called a proper divisor of x. For example, 2 is a proper divisor of 4, while 6 is not a proper divisor of 6.
 *
 * You are allowed to perform an operation any number of times on nums, where in each operation you select any one element from nums and divide it by its greatest proper divisor.
 *
 * Return the minimum number of operations required to make the array non-decreasing.
 *
 * If it is not possible to make the array non-decreasing using any number of operations, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [25,7]
 *
 * Output: 1
 *
 * Explanation:
 *
 * Using a single operation, 25 gets divided by 5 and nums becomes [5, 7].
 *
 *
 *
 * Example 2:
 *
 * Input: nums = [7,7,6]
 *
 * Output: -1
 *
 * Example 3:
 *
 * Input: nums = [1,1,1,1]
 *
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 */
public class MinimumDivisionOperationsToMakeArrayNonDecreasing {

    private static final int MAX = 1_000_001;
    private static final int[] lpf = new int[MAX];

    // 埃式筛预处理得到最小质因数
    static {
        for (int i = 2; i < MAX; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j < MAX; j += i) {
                    if (lpf[j] == 0) {
                        lpf[j] = i;
                    }
                }
            }
        }
    }

    // nums[i] / 最大因数 = LPY(nums[i]) -> nums[i] 的最小质因数
    public int minOperations(int[] nums) {
        int ans = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                nums[i] = lpf[nums[i]];
                if (nums[i] > nums[i + 1]) {
                    return -1;
                }
                ans++;
            }
        }
        return ans;
    }

}
