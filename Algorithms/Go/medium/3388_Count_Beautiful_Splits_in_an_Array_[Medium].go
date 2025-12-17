/*
3388. Count Beautiful Splits in an Array


You are given an array nums.

A split of an array nums is beautiful if:

The array nums is split into three subarrays: nums1, nums2, and nums3, such that nums can be formed by concatenating nums1, nums2, and nums3 in that order.
The subarray nums1 is a prefix of nums2 OR nums2 is a prefix of nums3.
Return the number of ways you can make this split.



Example 1:

Input: nums = [1,1,2,1]

Output: 2

Explanation:

The beautiful splits are:

A split with nums1 = [1], nums2 = [1,2], nums3 = [1].
A split with nums1 = [1], nums2 = [1], nums3 = [2,1].
Example 2:

Input: nums = [1,2,3,4]

Output: 0

Explanation:

There are 0 beautiful splits.



Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 50
*/

func calcZ(s []int) []int {
	n := len(s)
	z := make([]int, n)
	boxL, boxR := 0, 0 // z-box 左右边界
	for i := 1; i < n; i++ {
		if i <= boxR {
			z[i] = min(z[i-boxL], boxR-i+1)
		}
		for i+z[i] < n && s[z[i]] == s[i+z[i]] {
			boxL, boxR = i, i+z[i]
			z[i]++
		}
	}
	return z
}

func beautifulSplits(nums []int) int {
	n := len(nums)
	z0 := calcZ(nums)
	ans := 0
	for i := 1; i < n-1; i++ {
		z := calcZ(nums[i:])
		for j := i + 1; j < n; j++ {
			if i <= j-i && z0[i] >= i || z[j-i] >= j-i {
				ans++
			}
		}
	}
	return ans
}
