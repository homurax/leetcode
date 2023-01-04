/*
324. Wiggle Sort II


Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

You may assume the input array always has a valid answer.



Example 1:

Input: nums = [1,5,1,1,6,4]
Output: [1,6,1,5,1,4]
Explanation: [1,4,1,5,1,6] is also accepted.


Example 2:

Input: nums = [1,3,2,2,3,1]
Output: [2,3,1,3,1,2]


Constraints:

1. 1 <= nums.length <= 5 * 10^4
2. 0 <= nums[i] <= 5000
3. It is guaranteed that there will be an answer for the given input nums.
*/

func wiggleSort(nums []int) {
	n := len(nums)
	temp := make([]int, n)
	for i, v := range nums {
		temp[i] = v
	}
	sort.Ints(temp)
	idx := (n + 1) / 2
	i, j, k := 0, idx-1, n-1
	for i < n {
		nums[i] = temp[j]
		if i+1 < n {
			nums[i+1] = temp[k]
		}
		i += 2
		j--
		k--
	}
}
