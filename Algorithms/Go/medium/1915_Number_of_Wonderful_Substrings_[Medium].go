/*
1915. Number of Wonderful Substrings


A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"


Example 2:

Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
Example 3:

Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"


Constraints:

1. 1 <= word.length <= 10^5
2. word consists of lowercase English letters from 'a' to 'j'.
*/

// word[i:j] 为目标字符串
// 则字符 c 在 word[0, i - 1] word[0, j] 中出现次数的奇偶性应当相同
// 最多允许一个字符奇偶性不同
// 即 mask(i - 1) 与 mask(j) 最多有一位不同
// 遍历生成 mask 累加相同的 mask 以及有一位不同的 mask 的出现次数
func wonderfulSubstrings(word string) int64 {
	var ans int64
	mask := 0
	freq := [1024]int{0: 1}
	for _, c := range word {
		mask ^= 1 << (c - 'a')
		ans += int64(freq[mask])
		for i := 0; i < 10; i++ {
			ans += int64(freq[mask^(1<<i)])
		}
		freq[mask]++
	}
	return ans
}
