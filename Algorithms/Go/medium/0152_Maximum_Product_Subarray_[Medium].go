/*
152. Maximum Product Subarray


Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.


Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


Constraints:

1. 1 <= nums.length <= 2 * 10^4
2. -10 <= nums[i] <= 10
3. The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/

func maxProduct(nums []int) int {
	ans := nums[0]
	temp := 1
	for _, v := range nums {
		temp *= v
		if temp > ans {
			ans = temp
		}
		if temp == 0 {
			temp = 1
		}
	}
	temp = 1
	for i := len(nums) - 1; i >= 0; i-- {
		temp *= nums[i]
		if temp > ans {
			ans = temp
		}
		if temp == 0 {
			temp = 1
		}
	}
	return ans
}
