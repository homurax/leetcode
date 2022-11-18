/*
556. Next Greater Element III


Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.



Example 1:

Input: n = 12
Output: 21


Example 2:

Input: n = 21
Output: -1


Constraints:

1. 1 <= n <= 2^31 - 1
*/

func nextGreaterElement(n int) int {
	ans, count := n, 1
	for ans >= 10 && ans/10%10 >= ans%10 {
		ans /= 10
		count++
	}
	ans /= 10
	if ans == 0 {
		return -1
	}
	target := ans % 10
	temp, index := n, 0
	for temp%10 <= target {
		temp /= 10
		index++
	}
	ans += temp%10 - target
	for i := 0; i < count; i++ {
		d := target
		if i != index {
			d = n % 10
		}
		if ans > math.MaxInt32/10 || (ans == math.MaxInt32/10 && d > 7) {
			return -1
		}
		ans = ans*10 + d
		n /= 10
	}
	return ans
}
