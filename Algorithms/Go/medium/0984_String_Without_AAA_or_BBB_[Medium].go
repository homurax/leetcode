/*
984. String Without AAA or BBB


Given two integers a and b, return any string s such that:

s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
The substring 'aaa' does not occur in s, and
The substring 'bbb' does not occur in s.


Example 1:

Input: a = 1, b = 2
Output: "abb"
Explanation: "abb", "bab" and "bba" are all correct answers.

Example 2:

Input: a = 4, b = 1
Output: "aabaa"


Constraints:

1. 0 <= a, b <= 100
2. It is guaranteed such an s exists for the given a and b.
*/

func strWithout3a3b(a int, b int) string {
	var buff bytes.Buffer
	for a > b && b > 0 {
		buff.WriteString("aab")
		a -= 2
		b--
	}
	for b > a && a > 0 {
		buff.WriteString("bba")
		b -= 2
		a--
	}
	for a > 0 && b > 0 {
		buff.WriteString("ab")
		a--
		b--
	}
	for a > 0 {
		buff.WriteByte('a')
		a--
	}
	for b > 0 {
		buff.WriteByte('b')
		b--
	}
	return buff.String()
}
