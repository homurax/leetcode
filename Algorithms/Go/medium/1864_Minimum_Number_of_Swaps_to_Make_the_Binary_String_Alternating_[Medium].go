/*
1864. Minimum Number of Swaps to Make the Binary String Alternating


Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.



Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.

Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.

Example 3:

Input: s = "1110"
Output: -1


Constraints:

1. 1 <= s.length <= 1000
2. s[i] is either '0' or '1'.
*/

// 只有两种合法序列
// 01010...
// 10101...
// 当前序列与之相比位置不同的数量分别为 diff1 diff2
// ans = min(diff1 / 2, diff2 / 2)
func minSwaps(s string) int {
	n := len(s)
	ans := n
	count0 := strings.Count(s, "0")
	count1 := strings.Count(s, "1")
	// 10101... 1 可以多一位
	if count1 == (n+1)/2 && count0 == n/2 {
		diff := 0
		for i, v := range s {
			if i%2 == int(v-'0') {
				diff++
			}
		}
		ans = diff / 2
	}
	// 01010... 0 可以多一位
	if count0 == (n+1)/2 && count1 == n/2 {
		diff := 0
		for i, v := range s {
			if i%2 != int(v-'0') {
				diff++
			}
		}
		if ans > diff/2 {
			ans = diff / 2
		}
	}
	if ans == n {
		return -1
	}
	return ans
}
