/*
423. Reconstruct Original Digits from English


Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.



Example 1:

Input: s = "owoztneoer"
Output: "012"


Example 2:

Input: s = "fviefuro"
Output: "45"


Constraints:

1 <= s.length <= 10^5
s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
s is guaranteed to be valid.
*/

func originalDigits(s string) string {
	chars := make(map[rune]int)
	for _, c := range s {
		chars[c]++
	}
	cnt := [10]int{}
	cnt[0] = chars['z']
	cnt[2] = chars['w']
	cnt[4] = chars['u']
	cnt[6] = chars['x']
	cnt[8] = chars['g']
	cnt[3] = chars['h'] - cnt[8]
	cnt[5] = chars['f'] - cnt[4]
	cnt[7] = chars['s'] - cnt[6]
	cnt[1] = chars['o'] - cnt[0] - cnt[2] - cnt[4]
	cnt[9] = chars['i'] - cnt[5] - cnt[6] - cnt[8]
	var ans []byte
	for i, c := range cnt {
		ans = append(ans, bytes.Repeat([]byte{byte('0' + i)}, c)...)
	}
	return string(ans)
}
