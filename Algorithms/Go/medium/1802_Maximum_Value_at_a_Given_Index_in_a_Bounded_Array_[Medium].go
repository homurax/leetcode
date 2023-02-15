/*
1802. Maximum Value at a Given Index in a Bounded Array


You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.



Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].


Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3


Constraints:

1. 1 <= n <= maxSum <= 10^9
2. 0 <= index < n
*/

func maxValue(n int, index int, maxSum int) int {
	sum := func(max, cnt int) int {
		if max >= cnt {
			return (1 + max + max - cnt) * cnt / 2
		}
		return (1+max)*max/2 + cnt - max
	}
	return sort.Search(maxSum, func(max int) bool {
		max++
		return sum(max-1, index)+sum(max, n-index) > maxSum
	})
}
