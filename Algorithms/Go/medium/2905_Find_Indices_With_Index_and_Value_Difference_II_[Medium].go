/*
2905. Find Indices With Index and Value Difference II

You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer valueDifference.

Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:

abs(i - j) >= indexDifference, and
abs(nums[i] - nums[j]) >= valueDifference
Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1] otherwise. If there are multiple choices for the two indices, return any of them.

Note: i and j may be equal.

Example 1:

Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
Output: [0,3]
Explanation: In this example, i = 0 and j = 3 can be selected.
abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
Hence, a valid answer is [0,3].
[3,0] is also a valid answer.

Example 2:

Input: nums = [2,1], indexDifference = 0, valueDifference = 0
Output: [0,0]
Explanation: In this example, i = 0 and j = 0 can be selected.
abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
Hence, a valid answer is [0,0].
Other valid answers are [0,1], [1,0], and [1,1].

Example 3:

Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
Output: [-1,-1]
Explanation: In this example, it can be shown that it is impossible to find two indices that satisfy both conditions.
Hence, [-1,-1] is returned.

Constraints:

1 <= n == nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= indexDifference <= 10^5
0 <= valueDifference <= 10^9
*/
// i <= j - indexDifference
// 从 j = indexDifference 开始同时遍历 i 与 j, 满足 j - i >= indexDifference
// 对于每一个 nums[i] 维护 min 与 max, 判断 max - nums[j] >= valueDifference 或 nums[j] - min >= valueDifference
// 注意不需要考虑绝对值
// 因为 nums[j] - min >= nums[j] - max, 如果出现 max < nums[j] 但是 abs(max - nums[j]) >= valueDifference 的情况
// 一定也满足 nums[j] - min >= valueDifference
func findIndices(nums []int, indexDifference int, valueDifference int) []int {
	minIdx, maxIdx := 0, 0
	for j := indexDifference; j < len(nums); j++ {
		i := j - indexDifference
		if nums[i] > nums[maxIdx] {
			maxIdx = i
		} else if nums[i] < nums[minIdx] {
			minIdx = i
		}
		if nums[maxIdx]-nums[j] >= valueDifference {
			return []int{maxIdx, j}
		}
		if nums[j]-nums[minIdx] >= valueDifference {
			return []int{minIdx, j}
		}
	}
	return []int{-1, -1}
}
