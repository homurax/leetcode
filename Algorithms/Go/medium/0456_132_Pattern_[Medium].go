/*
456. 132 Pattern


Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.



Example 1:

Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:

Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].


Constraints:

1. n == nums.length
2. 1 <= n <= 2 * 10^5
3. -10^9 <= nums[i] <= 10^9
*/

func find132pattern(nums []int) bool {
	n := len(nums)
	k := ^int(^uint(0) >> 1)
	var deque []int
	for i := n - 1; i >= 0; i-- {
		if nums[i] < k {
			return true
		}
		for len(deque) > 0 && deque[len(deque)-1] < nums[i] {
			k = deque[len(deque)-1]
			deque = deque[:len(deque)-1]
		}
		deque = append(deque, nums[i])
	}
	return false
}
