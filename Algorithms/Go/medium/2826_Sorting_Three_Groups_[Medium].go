/*
2826. Sorting Three Groups

You are given an integer array nums. Each element in nums is 1, 2 or 3. In each operation, you can remove an element from nums. Return the minimum number of operations to make nums non-decreasing.

Example 1:

Input: nums = [2,1,3,2,1]

Output: 3

Explanation:

One of the optimal solutions is to remove nums[0], nums[2] and nums[3].

Example 2:

Input: nums = [1,3,2,1,3,3]

Output: 2

Explanation:

One of the optimal solutions is to remove nums[1] and nums[2].

Example 3:

Input: nums = [2,2,2,2,3,3]

Output: 0

Explanation:

nums is already non-decreasing.

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 3
*/
func minimumOperations(nums []int) int {
	cnt := [4]int{}
	for _, num := range nums {
		cnt[num]++
		cnt[2] = max(cnt[1], cnt[2])
		cnt[3] = max(cnt[2], cnt[3])
	}
	return len(nums) - cnt[3]
}
