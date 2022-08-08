/*
954. Array of Doubled Pairs


Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.



Example 1:

Input: arr = [3,1,3,6]
Output: false


Example 2:

Input: arr = [2,1,2,6]
Output: false


Example 3:

Input: arr = [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].


Constraints:

1. 2 <= arr.length <= 3 * 10^4
2. arr.length is even.
3. -10^5 <= arr[i] <= 10^5
*/

func canReorderDoubled(arr []int) bool {
	offset := 100010 * 2
	cnt := make([]int, offset*2)
	var nums []int
	for _, v := range arr {
		cnt[v+offset]++
		if cnt[v+offset] == 1 {
			nums = append(nums, v)
		}
	}
	sort.Slice(nums, func(i, j int) bool {
		return abs(nums[i]) < abs(nums[j])
	})
	for _, v := range nums {
		if cnt[v+offset] > cnt[v*2+offset] {
			return false
		}
		cnt[v*2+offset] -= cnt[v+offset]
	}
	return true
}

func abs(num int) int {
	if num >= 0 {
		return num
	}
	return -num
}
