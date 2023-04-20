/*
1871. Jump Game VII


You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.



Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3.
In the second step, move from index 3 to index 5.


Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false


Constraints:

1. 2 <= s.length <= 10^5
2. s[i] is either '0' or '1'.
3. s[0] == '0'
4. 1 <= minJump <= maxJump < s.length
*/

func canReach(s string, minJump int, maxJump int) bool {
	n := len(s)
	dp := make([]int, n)
	pre := make([]int, n)
	dp[0] = 1
	for i := 0; i < minJump; i++ {
		pre[i] = 1
	}
	for i := minJump; i < n; i++ {
		if s[i] == '0' {
			l, r := i-maxJump, i-minJump
			cnt := -1
			if l <= 0 {
				cnt = pre[r]
			} else {
				cnt = pre[r] - pre[l-1]
			}
			if cnt > 0 {
				dp[i] = 1
			}
		}
		pre[i] = pre[i-1] + dp[i]
	}
	return dp[n-1] == 1
}
