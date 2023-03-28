/*
1201. Ugly Number III


An ugly number is a positive integer that is divisible by a, b, or c.

Given four integers n, a, b, and c, return the nth ugly number.



Example 1:

Input: n = 3, a = 2, b = 3, c = 5
Output: 4
Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.


Example 2:

Input: n = 4, a = 2, b = 3, c = 4
Output: 6
Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.


Example 3:

Input: n = 5, a = 2, b = 11, c = 13
Output: 10
Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.


Constraints:

1. 1 <= n, a, b, c <= 10^9
2. 1 <= a * b * c <= 10^18
3. It is guaranteed that the result will be in range [1, 2 * 10^9].
*/

func nthUglyNumber(n int, a int, b int, c int) int {
	lcmAb, lcmBc, lcmAc, lcmAbc := lcm(a, b), lcm(b, c), lcm(a, c), lcm(a, lcm(b, c))
	mi := min(a, min(b, c))
	l, r := 0, mi*n+1
	for l+1 < r {
		mid := l + (r-l)/2
		count := mid/a + mid/b + mid/c - mid/lcmAb - mid/lcmAc - mid/lcmBc + mid/lcmAbc
		if count < n {
			l = mid
		} else {
			r = mid
		}
	}
	return r
}

func lcm(a, b int) int {
	return a * (b / gcd(a, b))
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	} else {
		return gcd(b, a%b)
	}
}
