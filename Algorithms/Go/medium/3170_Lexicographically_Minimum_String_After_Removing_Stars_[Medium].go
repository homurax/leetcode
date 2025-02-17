/*
3170. Lexicographically Minimum String After Removing Stars

You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.

While there is a '*', do the following operation:

Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.

Example 1:

Input: s = "aaba*"

Output: "aab"

Explanation:

We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:

Input: s = "abc"

Output: "abc"

Explanation:

There is no '*' in the string.

Constraints:

1 <= s.length <= 10^5
s consists only of lowercase English letters and '*'.
The input is generated such that it is possible to delete all '*' characters.
*/
// 26 个栈维护每中字母的位置, 由于移除最小字典序字符且要求最终字典序最小, 即优先移除靠后的字典序
func clearStars(s string) string {
	st := make([][]int, 26)
	for i, c := range s {
		if c != '*' {
			st[c-'a'] = append(st[c-'a'], i)
			continue
		}
		for j, p := range st {
			if len(p) > 0 {
				st[j] = p[:len(p)-1]
				break
			}
		}
	}

	idx := []int{}
	for _, p := range st {
		idx = append(idx, p...)
	}
	slices.Sort(idx)

	t := make([]byte, len(idx))
	for i, j := range idx {
		t[i] = s[j]
	}
	return string(t)
}
