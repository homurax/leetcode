/*
3282. Reach End of Array With Max Score

You are given an integer array nums of length n.

Your goal is to start at index 0 and reach index n - 1. You can only jump to indices greater than your current index.

The score for a jump from index i to index j is calculated as (j - i) * nums[i].

Return the maximum possible total score by the time you reach the last index.

Example 1:

Input: nums = [1,3,1,5]

Output: 7

Explanation:

First, jump to index 1 and then jump to the last index. The final score is 1 * 1 + 2 * 3 = 7.

Example 2:

Input: nums = [4,3,1,3,2]

Output: 16

Explanation:

Jump directly to the last index. The final score is 4 * 4 = 16.

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/
func findMaximumScore(nums []int) int64 {
	ans, mx := 0, 0
	for _, num := range nums[:len(nums)-1] {
		mx = max(mx, num)
		ans += mx
	}
	return int64(ans)
}
