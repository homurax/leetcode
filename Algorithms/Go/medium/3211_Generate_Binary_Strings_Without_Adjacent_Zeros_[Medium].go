/*
3211. Generate Binary Strings Without Adjacent Zeros

You are given a positive integer n.

A binary string x is valid if all substrings of x of length 2 contain at least one "1".

Return all valid strings with length n, in any order.

Example 1:

Input: n = 3

Output: ["010","011","101","110","111"]

Explanation:

The valid strings of length 3 are: "010", "011", "101", "110", and "111".

Example 2:

Input: n = 1

Output: ["0","1"]

Explanation:

The valid strings of length 1 are: "0" and "1".

Constraints:

1 <= n <= 18
*/

// 对 x 判断是否有相邻的 0 -> x 取反后低 n 位是否有相邻的 1 -> x & (x >> 1) 是否为 0
// x & (x >> 1) == 0 -> x 中没有相邻的 1 -> x 低 n 位取反后有相邻的 1
// x ^ ((1 << n) - 1)
func validStrings(n int) []string {
	var ans []string
	mask := 1<<n - 1
	for x := range 1 << n {
		if x>>1&x == 0 {
			ans = append(ans, fmt.Sprintf("%0*b", n, x^mask))
		}
	}
	return ans
}
