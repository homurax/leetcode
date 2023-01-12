/*
1712. Ways to Split Array Into Three Subarrays


A split of an integer array is good if:

The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.



Example 1:

Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].


Example 2:

Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]


Example 3:

Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.


Constraints:

1. 3 <= nums.length <= 10^5
2. 0 <= nums[i] <= 10^4
*/

func waysToSplit(nums []int) int {
	n := len(nums)
	preSum := make([]int, n+1)
	for i := 0; i < n; i++ {
		preSum[i+1] = preSum[i] + nums[i]
	}
	ans := 0
	for i, j, k := 1, 2, 1; i < n-1 && preSum[i]*3 <= preSum[n]; i++ {
		left := preSum[i]
		if i+1 > j {
			j = i + 1
		}
		for j < n-1 && preSum[j]-preSum[i] < left {
			j++
		}
		for k < n-1 && preSum[k+1]-preSum[i] <= preSum[n]-preSum[k+1] {
			k++
		}
		ans += k - j + 1
	}
	return ans % 1_000_000_007
}
