/*
1658. Minimum Operations to Reduce X to Zero


You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.



Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.


Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1


Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.


Constraints:

1. 1 <= nums.length <= 10^5
2. 1 <= nums[i] <= 10^4
3. 1 <= x <= 10^9
*/

func minOperations(nums []int, x int) int {
	sum := 0
	for _, num := range nums {
		sum += num
	}
	target := sum - x
	if target < 0 {
		return -1
	}
	ans := -1
	n := len(nums)
	l, r := 0, 0
	subSum := 0
	for r < n {
		subSum += nums[r]
		for subSum > target {
			subSum -= nums[l]
			l++
		}
		if subSum == target {
			if r-l+1 > ans {
				ans = r - l + 1
			}
		}
		r++
	}
	if ans == -1 {
		return -1
	}
	return n - ans
}
