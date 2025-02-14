/*
2918. Minimum Equal Sum of Two Arrays After Replacing Zeros


You are given two arrays nums1 and nums2 consisting of positive integers.

You have to replace all the 0's in both arrays with strictly positive integers such that the sum of elements of both arrays becomes equal.

Return the minimum equal sum you can obtain, or -1 if it is impossible.



Example 1:

Input: nums1 = [3,2,0,1,0], nums2 = [6,5,0]
Output: 12
Explanation: We can replace 0's in the following way:
- Replace the two 0's in nums1 with the values 2 and 4. The resulting array is nums1 = [3,2,2,1,4].
- Replace the 0 in nums2 with the value 1. The resulting array is nums2 = [6,5,1].
Both arrays have an equal sum of 12. It can be shown that it is the minimum sum we can obtain.

Example 2:

Input: nums1 = [2,0,2,0], nums2 = [1,4]
Output: -1
Explanation: It is impossible to make the sum of both arrays equal.


Constraints:

1 <= nums1.length, nums2.length <= 10^5
0 <= nums1[i], nums2[i] <= 10^6
*/
// sum 较大的一方为了和最小 0 一定替换为 1
// sum 较小的一方必须存在 0 才可以追平较大的一方
// 能追平的情况下, 追平前较大的一方即为最小和
func minSum(nums1 []int, nums2 []int) int64 {
	s1 := int64(0)
	zero1 := false
	for _, x := range nums1 {
		if x == 0 {
			zero1 = true
			s1++
		} else {
			s1 += int64(x)
		}
	}

	s2 := int64(0)
	zero2 := false
	for _, x := range nums2 {
		if x == 0 {
			zero2 = true
			s2++
		} else {
			s2 += int64(x)
		}
	}
	if !zero1 && s1 < s2 || !zero2 && s2 < s1 {
		return -1
	}
	return max(s1, s2)
}
