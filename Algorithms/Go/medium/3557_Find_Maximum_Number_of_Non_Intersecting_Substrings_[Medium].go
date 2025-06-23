/*
3557. Find Maximum Number of Non Intersecting Substrings

You are given a string word.

Return the maximum number of non-intersecting substrings of word that are at least four characters long and start and end with the same letter.

Example 1:

Input: word = "abcdeafdef"

Output: 2

Explanation:

The two substrings are "abcdea" and "fdef".

Example 2:

Input: word = "bcdaaaab"

Output: 1

Explanation:

The only substring is "aaaa". Note that we cannot also choose "bcdaaaab" since it intersects with the other substring.

Constraints:

1 <= word.length <= 2 * 10^5
word consists only of lowercase English letters.
*/

// 贪心：右端点越小越好
// 记录首次出现位置，如果出现相同字母 index - pre_index + 1>= 4 ->  index - pre_index > 2，ans++，重置 pre_index
// 进一步 bit 压缩，考虑三个字符前是否出现过
func maxSubstrings(word string) int {
	ans, seen := 0, 0
	for i := 3; i < len(word); i++ {
		seen |= 1 << (word[i-3] - 'a')
		if seen>>(word[i]-'a')&1 > 0 {
			ans++
			seen = 0
			i += 3
		}
	}
	return ans
}
