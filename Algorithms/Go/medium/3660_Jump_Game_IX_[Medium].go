/*
3660. Jump Game IX

You are given an integer array nums.

From any index i, you can jump to another index j under the following rules:

Jump to index j where j > i is allowed only if nums[j] < nums[i].
Jump to index j where j < i is allowed only if nums[j] > nums[i].
For each index i, find the maximum value in nums that can be reached by following any sequence of valid jumps starting at i.

Return an array ans where ans[i] is the maximum value reachable starting from index i.

Example 1:

Input: nums = [2,1,3]

Output: [2,2,3]

Explanation:

For i = 0: No jump increases the value.
For i = 1: Jump to j = 0 as nums[j] = 2 is greater than nums[i].
For i = 2: Since nums[2] = 3 is the maximum value in nums, no jump increases the value.
Thus, ans = [2, 2, 3].

Example 2:

Input: nums = [2,3,1]

Output: [3,3,3]

Explanation:

For i = 0: Jump forward to j = 2 as nums[j] = 1 is less than nums[i] = 2, then from i = 2 jump to j = 1 as nums[j] = 3 is greater than nums[2].
For i = 1: Since nums[1] = 3 is the maximum value in nums, no jump increases the value.
For i = 2: Jump to j = 1 as nums[j] = 3 is greater than nums[2] = 1.
Thus, ans = [3, 3, 3].

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

// 向后/右跳必须更小, 向前/左跳必须更大
// 对于 nums[n - 1] 一定能调到最大值, 最大值就是自身或者在左边, 一定可以跳到
// 从后向前考虑
// 将 nums 分为左右两个区域, 如果左侧的最大值 <= 右侧的最小值, 则左区均无法跳到右区, 左区变为子问题
// ans[i] = preMax[i], preMax[i] <= sufMin[i + 1], 左区最大 <= 右区最小
// ans[i] = ans[i + 1], preMax[i] > sufMin[i + 1], i 可以跳到左区最大后再跳到右区最小再跳到 i + 1
func maxValue(nums []int) []int {
	n := len(nums)
	preMax := make([]int, n)
	preMax[0] = nums[0]
	for i := 1; i < n; i++ {
		preMax[i] = max(preMax[i-1], nums[i])
	}

	ans := make([]int, n)
	sufMin := math.MaxInt
	for i := n - 1; i >= 0; i-- {
		if preMax[i] <= sufMin {
			ans[i] = preMax[i]
		} else {
			ans[i] = ans[i+1]
		}
		sufMin = min(sufMin, nums[i])
	}
	return ans
}
