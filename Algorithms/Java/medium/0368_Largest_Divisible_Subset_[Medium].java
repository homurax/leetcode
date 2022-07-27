/**
 * 368. Largest Divisible Subset
 *
 *
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 2 * 10^9
 * 3. All the integers in nums are unique.
 */
public class LargestDivisibleSubset {

    // 最长上升子序列变形
    // f[i] 代表以 nums[i] 结尾的子集长度
    // g[i] 代表 i 由谁转移而来
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            int len = 1, prev = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (f[j] + 1 > len) {
                        len = f[j] + 1;
                        prev = j;
                    }
                }
            }
            f[i] = len;
            g[i] = prev;
        }
        int max = -1, index = -1;
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                max = f[i];
                index = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() != max) {
            ans.add(nums[index]);
            index = g[index];
        }
        return ans;
    }

}
