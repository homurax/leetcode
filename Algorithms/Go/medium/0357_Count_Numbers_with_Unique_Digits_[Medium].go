/*
357. Count Numbers with Unique Digits


Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.



Example 1:

Input: n = 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99


Example 2:

Input: n = 0
Output: 1


Constraints:

0 <= n <= 8
*/

// 最高位不为 0, 可选个数为 9；次高位可选 0，从次高位开始可选个数从 9 递减
func countNumbersWithUniqueDigits(n int) int {
	if n == 0 {
		return 1
	}
	ans := 10
	for i, last := 2, 9; i <= n; i++ {
		curr := last * (10 - i + 1)
		ans += curr
		last = curr
	}
	return ans
}
