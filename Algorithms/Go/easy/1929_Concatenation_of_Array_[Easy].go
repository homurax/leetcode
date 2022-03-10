/*
1929. Concatenation of Array


Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).

Specifically, ans is the concatenation of two nums arrays.

Return the array ans.



Example 1:

Input: nums = [1,2,1]
Output: [1,2,1,1,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
- ans = [1,2,1,1,2,1]

Example 2:

Input: nums = [1,3,2,1]
Output: [1,3,2,1,1,3,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
- ans = [1,3,2,1,1,3,2,1]


Constraints:

1. n == nums.length
2. 1 <= n <= 1000
3. 1 <= nums[i] <= 1000
*/

// 下标遍历似乎比 range 遍历要快
func getConcatenation(nums []int) []int {
	// return append(append(make([]int, 0), nums...), nums...)
	// return append(nums, nums...)
	ans := make([]int, len(nums)*2)
	for i := 0; i < len(nums); i++ {
		ans[i] = nums[i]
		ans[i+len(nums)] = nums[i]
	}
	return ans
}
