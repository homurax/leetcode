/*
2104. Sum of Subarray Ranges


You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.

Return the sum of all subarray ranges of nums.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.


Example 2:

Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.


Example 3:

Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.


Constraints:

1 <= nums.length <= 1000
-10^9 <= nums[i] <= 10^9


Follow-up: Could you find a solution with O(n) time complexity?
*/

// ans 为若干个区间的 max - min
// 假设 nums[i] 在 k1 个区间中充当 max，在 k2 个区间中充当 min，则对 ans 的贡献为 (k1 - k2) * nums[i]
// nums[i] 作为最大值次数：左右找到最近的一个不满足的位置（nums[l] > nums[i]、nums[r] > nums[i]）,(i - l) * (r - i)
// nums[i] 作为最小值次数：同理（nums[l] < nums[i]、nums[r] < nums[i]）,(i - l) * (r - i)
// 通过单调栈找 nums[i] 左右最近的位置
func subArrayRanges(nums []int) int64 {
	ans := getCnt(nums)
	for i, v := range nums { // 所有元素取反后算的就是最小值的贡献
		nums[i] = -v
	}
	return ans + getCnt(nums)
}

func getCnt(nums []int) (ans int64) {
	n := len(nums)
	// left[i] 为左侧严格大于 num[i] 的最近元素位置（不存在时为 -1）
	left := make([]int, n)
	// right[i] 为右侧大于等于 num[i] 的最近元素位置（不存在时为 n）
	right := make([]int, n)
	for i := range right {
		right[i] = n
	}
	st := []int{-1}
	for i, v := range nums {
		for len(st) > 1 && nums[st[len(st)-1]] <= v {
			right[st[len(st)-1]] = i
			st = st[:len(st)-1]
		}
		left[i] = st[len(st)-1]
		st = append(st, i)
	}
	for i, v := range nums {
		ans += int64(i-left[i]) * int64(right[i]-i) * int64(v)
	}
	return
}

func subArrayRanges1(nums []int) int64 {
	var ans int64
	for i, v1 := range nums {
		mi, ma := v1, v1
		for _, v2 := range nums[i+1:] {
			if v2 < mi {
				mi = v2
			}
			if v2 > ma {
				ma = v2
			}
			ans += int64(ma - mi)
		}
	}
	return ans
}
