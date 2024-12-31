/*
2217. Find Palindrome With Fixed Length

Given an integer array queries and a positive integer intLength, return an array answer where answer[i] is either the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.

A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.

Example 1:

Input: queries = [1,2,3,4,5,90], intLength = 3
Output: [101,111,121,131,141,999]
Explanation:
The first few palindromes of length 3 are:
101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
The 90th palindrome of length 3 is 999.

Example 2:

Input: queries = [2,4,6], intLength = 4
Output: [1111,1331,1551]
Explanation:
The first six palindromes of length 4 are:
1001, 1111, 1221, 1331, 1441, and 1551.

Constraints:

1 <= queries.length <= 5 * 10^4
1 <= queries[i] <= 10^9
1 <= intLength <= 15
*/
// 回文数字从小到大排列, 每个数字具有对称性
// 左半部分, 对于奇数长度含中间值, 121 -> 12 & (2)1, 1221 -> 12 & 21
// 长度为 len 的第 q 个回文数字的左半边 为 10^k + q - 1
// k = (len - 1) / 2
func kthPalindrome(queries []int, intLength int) []int64 {
	ans := make([]int64, len(queries))
	base := int(math.Pow10((intLength - 1) / 2))
	for i, q := range queries {
		if q > 9*base {
			ans[i] = -1
			continue
		}
		v := base + q - 1
		t := v
		if intLength%2 != 0 {
			t /= 10
		}
		for ; t > 0; t /= 10 {
			v = v*10 + t%10
		}
		ans[i] = int64(v)
	}
	return ans
}
