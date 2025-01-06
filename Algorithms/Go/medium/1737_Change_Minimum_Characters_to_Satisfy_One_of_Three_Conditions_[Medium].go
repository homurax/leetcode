/*
1737. Change Minimum Characters to Satisfy One of Three Conditions

You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.

Your goal is to satisfy one of the following three conditions:

Every letter in a is strictly less than every letter in b in the alphabet.
Every letter in b is strictly less than every letter in a in the alphabet.
Both a and b consist of only one distinct letter.
Return the minimum number of operations needed to achieve your goal.

Example 1:

Input: a = "aba", b = "caa"
Output: 2
Explanation: Consider the best way to make each condition true:
1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
The best way was done in 2 operations (either condition 1 or condition 3).

Example 2:

Input: a = "dabadd", b = "cda"
Output: 3
Explanation: The best way is to make condition 1 true by changing b to "eee".

Constraints:

1 <= a.length, b.length <= 10^5
a and b consist only of lowercase letters.
*/
// 分别计算取最小值
func minCharacters(a string, b string) int {
	n, m := len(a), len(b)
	cnt1, cnt2 := [26]int{}, [26]int{}
	for _, c := range a {
		cnt1[c-'a']++
	}
	for _, c := range b {
		cnt2[c-'a']++
	}
	ans := math.MaxInt
	for i := 0; i < 26 && ans != 0; i++ {
		// a b 全变为 i
		ans = min(ans, n-cnt1[i]+m-cnt2[i])
		if i == 0 { // 字符不能小于 a
			continue
		}
		r1, r2 := 0, 0
		// a < i && b >= i
		for j := i; j < 26; j++ { // a 中 >= i 的都需要修改
			r1 += cnt1[j]
		}
		for j := 0; j < i; j++ { // b 中 < i 的都需要修改
			r1 += cnt2[j]
		}
		// a >= i && b < i
		for j := 0; j < i; j++ { // a 中 < i 的都需要修改
			r2 += cnt1[j]
		}
		for j := i; j < 26; j++ { // b 中 >= i 的都需要修改
			r2 += cnt2[j]
		}
		ans = min(ans, min(r1, r2))
	}
	return ans
}
