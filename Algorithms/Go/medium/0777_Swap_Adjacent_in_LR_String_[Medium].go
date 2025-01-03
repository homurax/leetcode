/*
777. Swap Adjacent in LR String

In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string result, return True if and only if there exists a sequence of moves to transform start to result.

Example 1:

Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
Output: true
Explanation: We can transform start to result following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX

Example 2:

Input: start = "X", result = "L"
Output: false

Constraints:

1 <= start.length <= 10^4
start.length == result.length
Both start and result will only consist of characters in 'L', 'R', and 'X'.
*/
// L 和 R 不能互相穿过
// L && i < j || R && i > j
func canTransform(start string, result string) bool {
	if strings.ReplaceAll(start, "X", "") != strings.ReplaceAll(result, "X", "") {
		return false
	}
	j := 0
	for i, c := range start {
		if c == 'X' {
			continue
		}
		for result[j] == 'X' {
			j++
		}
		if i != j && c == 'L' != (i > j) {
			return false
		}
		j++
	}
	return true
}
