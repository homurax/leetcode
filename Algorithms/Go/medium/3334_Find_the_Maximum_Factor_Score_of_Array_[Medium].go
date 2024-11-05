/*
3334. Find the Maximum Factor Score of Array

You are given an integer array nums.

The factor score of an array is defined as the product of the LCM and GCD of all elements of that array.

Return the maximum factor score of nums after removing at most one element from it.

Note that both the LCM and GCD of a single number are the number itself, and the factor score of an empty array is 0.

Example 1:

Input: nums = [2,4,8,16]

Output: 64

Explanation:

On removing 2, the GCD of the rest of the elements is 4 while the LCM is 16, which gives a maximum factor score of 4 * 16 = 64.

Example 2:

Input: nums = [1,2,3,4,5]

Output: 60

Explanation:

The maximum factor score of 60 can be obtained without removing any elements.

Example 3:

Input: nums = [3]

Output: 9

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 30
*/
// gcd(a, b, c) = gcd(gcd(a, b), c)
// lcm(a, b, c) = lcm(lcm(a, b), c)
// gcd(0, x) = x
// lcd(0, x) = 0
func maxScore(nums []int) int64 {
	n := len(nums)
	sufGcd := make([]int, n+1)
	sufLcm := make([]int, n+1)
	sufLcm[n] = 1
	// go1.23.0
	for i, x := range slices.Backward(nums) {
		sufGcd[i] = gcd(sufGcd[i+1], x)
		sufLcm[i] = lcm(sufLcm[i+1], x)
	}

	ans := sufGcd[0] * sufLcm[0]
	preGcd, preLcm := 0, 1
	for i, x := range nums {
		ans = max(ans, gcd(preGcd, sufGcd[i+1])*lcm(preLcm, sufLcm[i+1]))
		preGcd = gcd(preGcd, x)
		preLcm = lcm(preLcm, x)
	}
	return int64(ans)
}

func gcd(a, b int) int {
	for a != 0 {
		a, b = b%a, a
	}
	return b
}

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
