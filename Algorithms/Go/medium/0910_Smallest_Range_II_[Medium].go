/*
910. Smallest Range II


You are given an integer array nums and an integer k.

For each index i where 0 <= i < nums.length, change nums[i] to be either nums[i] + k or nums[i] - k.

The score of nums is the difference between the maximum and minimum elements in nums.

Return the minimum score of nums after changing the values at each index.



Example 1:

Input: nums = [1], k = 0
Output: 0
Explanation: The score is max(nums) - min(nums) = 1 - 1 = 0.


Example 2:

Input: nums = [0,10], k = 2
Output: 6
Explanation: Change nums to be [2, 8]. The score is max(nums) - min(nums) = 8 - 2 = 6.


Example 3:

Input: nums = [1,3,6], k = 3
Output: 3
Explanation: Change nums to be [4, 6, 3]. The score is max(nums) - min(nums) = 6 - 3 = 3.


Constraints:

1. 1 <= nums.length <= 10^4
2. 0 <= nums[i] <= 10^4
3. 0 <= k <= 10^4
*/

// 排序后不妨考虑让较小值 + k, 较大值 - k
// 对于 nums[i] < nums[j], (nums[i] + k, nums[j] - k) 一定在 (nums[i] - k, nums[j] + k) 范围内
// 假设在 nums[i] 点分割, nums[0, i] + k, nums[i + 1, n) - k
// A = nums[0] + k
// B = nums[i] + k
// C = nums[i + 1] - k
// D = nums[n - 1] - k
// ans = min(max(B, D) - min(A, C))
func smallestRangeII(nums []int, k int) int {
	n := len(nums)
	if n == 1 {
		return 0
	}
	sort.Ints(nums)
	a, d := nums[0]+k, nums[n-1]-k
	ans := nums[n-1] - nums[0]
	for i := 0; i < n-1; i++ {
		b, c := nums[i]+k, nums[i+1]-k
		ans = min(ans, max(b, d)-min(a, c))
	}
	return ans
}
