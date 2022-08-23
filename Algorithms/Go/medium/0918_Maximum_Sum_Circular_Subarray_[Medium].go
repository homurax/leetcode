/*
918. Maximum Sum Circular Subarray


Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.



Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.


Constraints:

1. n == nums.length
2. 1 <= n <= 3 * 10^4
3. -3 * 10^4 <= nums[i] <= 3 * 10^4
*/

func maxSubarraySumCircular(nums []int) int {
	dp, sum, maxSub := 0, 0, nums[0]
	for _, v := range nums {
		sum += v
		if dp > 0 {
			dp = v + dp
		} else {
			dp = v
		}
		if dp > maxSub {
			maxSub = dp
		}
	}
	dp, minSub, n := nums[0], 0, len(nums)
	for i := 1; i < n-1; i++ {
		v := nums[i]
		if dp > 0 {
			dp = v
		} else {
			dp = v + dp
		}
		if minSub > dp {
			minSub = dp
		}
	}
	if sum-minSub > maxSub {
		return sum - minSub
	}
	return maxSub
}
