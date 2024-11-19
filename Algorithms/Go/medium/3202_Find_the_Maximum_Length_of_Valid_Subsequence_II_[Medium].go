/*
3202. Find the Maximum Length of Valid Subsequence II

You are given an integer array nums and a positive integer k.
A subsequence sub of nums with length x is called valid if it satisfies:

(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
Return the length of the longest valid subsequence of nums.

Example 1:

Input: nums = [1,2,3,4,5], k = 2

Output: 5

Explanation:

The longest valid subsequence is [1, 2, 3, 4, 5].

Example 2:

Input: nums = [1,4,2,3,1,4], k = 3

Output: 4

Explanation:

The longest valid subsequence is [1, 4, 1, 4].

Constraints:

2 <= nums.length <= 10^3
1 <= nums[i] <= 10^7
1 <= k <= 10^3
*/
// (a + b) % k = (b + c) % k -> (a + b - (b + c)) % k  = 0 -> (a - c) % k = 0
// 即子序列的奇数项都关于模 k 同余，偶数项都关于模 k 同余
// 枚举相邻两项模 k 的结果为 m = 0, 1, 2, ..., k - 1
func maximumLength(nums []int, k int) int {
	ans := 0
	f := make([]int, k)
	for m := 0; m < k; m++ {
		clear(f)
		for _, x := range nums {
			x %= k
			f[x] = f[(m-x+k)%k] + 1
			ans = max(ans, f[x])
		}
	}
	return ans
}
