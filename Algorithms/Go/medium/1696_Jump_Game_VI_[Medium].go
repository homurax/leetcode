/*
1696. Jump Game VI


You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.



Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.

Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0


Constraints:

1. 1 <= nums.length, k <= 10^5
2. -10^4 <= nums[i] <= 10^4
*/

func maxResult(nums []int, k int) int {
	n := len(nums)
	dp := make([]int, n)
	// 索引递增 对应数值递减
	var queue []int
	for i := 0; i < n; i++ {
		if len(queue) > 0 && i-queue[0] > k {
			queue = queue[1:]
		}
		dp[i] = nums[i]
		if len(queue) > 0 {
			dp[i] += dp[queue[0]]
		}
		for len(queue) > 0 && dp[i] > dp[queue[len(queue)-1]] {
			queue = queue[:len(queue)-1]
		}
		queue = append(queue, i)
	}
	return dp[n-1]
}

func maxResult1(nums []int, k int) int {
	n := len(nums)
	IntMin := ^int(^uint(0) >> 1)
	dp := make([]int, n)
	for i := 1; i < n; i++ {
		dp[i] = IntMin
	}
	dp[0] = nums[0]
	for i := 0; i < n; i++ {
		for j := i + 1; j < n && j <= i+k; j++ {
			if next := dp[i] + nums[j]; next > dp[j] {
				dp[j] = next
			}
			if dp[j] >= dp[i] {
				break
			}
		}
	}
	return dp[n-1]
}
