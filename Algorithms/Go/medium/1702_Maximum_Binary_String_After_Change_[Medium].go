/*
1702. Maximum Binary String After Change


You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following operations any number of times:

Operation 1: If the number contains the substring "00", you can replace it with "10".
For example, "00010" -> "10010"
Operation 2: If the number contains the substring "10", you can replace it with "01".
For example, "00010" -> "00001"
Return the maximum binary string you can obtain after any number of operations. Binary string x is greater than binary string y if x's decimal representation is greater than y's decimal representation.



Example 1:

Input: binary = "000110"
Output: "111011"
Explanation: A valid transformation sequence can be:
"000110" -> "000101"
"000101" -> "100101"
"100101" -> "110101"
"110101" -> "110011"
"110011" -> "111011"

Example 2:

Input: binary = "01"
Output: "01"
Explanation: "01" cannot be transformed any further.


Constraints:

1. 1 <= binary.length <= 10^5
2. binary consist of '0' and '1'.
*/

/*
func maximumBinaryString(binary string) string {
	zeroCnt, zeroIdx, n := 0, -1, len(binary)
	for i := n - 1; i >= 0; i-- {
		if binary[i] == '0' {
			zeroCnt++
			zeroIdx = i
		}
	}
	if zeroCnt <= 1 {
		return binary
	}
	idx := zeroIdx + zeroCnt - 1
	bytes := []byte(binary)
	for i := 0; i < n; i++ {
		if i == idx {
			bytes[i] = '0'
		} else {
			bytes[i] = '1'
		}
	}
	return string(bytes)
}
*/

func maximumBinaryString(binary string) string {
	zeroCnt := strings.Count(binary, "0")
	if zeroCnt <= 1 {
		return binary
	}
	zeroIdx := strings.Index(binary, "0")
	bytes := []byte(strings.Repeat("1", len(binary)))
	bytes[zeroIdx+zeroCnt-1] = '0'
	return string(bytes)
}
