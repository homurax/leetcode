/*
368. Largest Divisible Subset


Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.



Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.


Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]


Constraints:

1. 1 <= nums.length <= 1000
2. 1 <= nums[i] <= 2 * 10^9
3. All the integers in nums are unique.
*/

func largestDivisibleSubset(nums []int) []int {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	for i := 0; i < n; i++ {
		length, prev := 1, i
		for j := 0; j < i; j++ {
			if nums[i]%nums[j] == 0 {
				if f[j]+1 > length {
					length = f[j] + 1
					prev = j
				}
			}
		}
		f[i] = length
		g[i] = prev
	}
	max, idx := -1, -1
	for i := 0; i < n; i++ {
		if f[i] > max {
			max = f[i]
			idx = i
		}
	}
	ans := make([]int, max)
	for i := 0; i < max; i++ {
		ans[i] = nums[idx]
		idx = g[idx]
	}
	return ans
}
