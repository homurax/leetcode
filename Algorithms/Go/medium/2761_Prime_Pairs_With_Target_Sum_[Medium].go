/*
2761. Prime Pairs With Target Sum

You are given an integer n. We say that two integers x and y form a prime number pair if:

1 <= x <= y <= n
x + y == n
x and y are prime numbers
Return the 2D sorted list of prime number pairs [xi, yi]. The list should be sorted in increasing order of xi. If there are no prime number pairs at all, return an empty array.

Note: A prime number is a natural number greater than 1 with only two factors, itself and 1.

Example 1:

Input: n = 10
Output: [[3,7],[5,5]]
Explanation: In this example, there are two prime pairs that satisfy the criteria.
These pairs are [3,7] and [5,5], and we return them in the sorted order as described in the problem statement.

Example 2:

Input: n = 2
Output: []
Explanation: We can show that there is no prime number pair that gives a sum of 2, so we return an empty array.

Constraints:

1 <= n <= 10^6
*/

// 埃拉托斯特尼筛法
const LIMIT = 1_000_000

// 质数
var primes []int

// 质数标记
var isP = [LIMIT + 1]bool{}

func init() {
	for i := 2; i <= LIMIT; i++ {
		isP[i] = true
	}
	for i := 2; i <= LIMIT; i++ {
		if isP[i] {
			primes = append(primes, i)
			// 标记质数 i 的倍数不是质数
			for j := i * i; j <= LIMIT; j += i {
				isP[j] = false
			}
		}
	}
}

func findPrimePairs(n int) [][]int {
	ans := [][]int{}
	if n%2 > 0 {
		// n 是奇数时 只能为 偶数 + 奇数 = 奇数
		// 偶数质数只有 2
		if n > 4 && isP[n-2] {
			return [][]int{{2, n - 2}}
		}
		return ans
	}
	for _, x := range primes {
		y := n - x
		if y < x {
			break
		}
		if isP[y] {
			ans = append(ans, []int{x, y})
		}
	}
	return ans
}
