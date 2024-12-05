/*
722. Remove Comments

Given a C++ program, remove comments from it. The program source is an array of strings source where source[i] is the ith line of the source code. This represents the result of splitting the original source code string by the newline character '\n'.

Constraints:

1 <= source.length <= 100
0 <= source[i].length <= 80
source[i] consists of printable ASCII characters.
Every open block comment is eventually closed.
There are no single-quote or double-quote in the input.
*/

// inBlock:
//
//	*/ -> inBlock = false
//
// !inBlock:
//
//	// -> break
//	/* -> inBlock = true
//	.. -> line.append
func removeComments(source []string) []string {
	line := []byte{}
	ans := []string{}
	inBlock := false
	for _, s := range source {
		for i := 0; i < len(s); i++ {
			if inBlock {
				if i+1 < len(s) && s[i] == '*' && s[i+1] == '/' {
					inBlock = false // 不能 break, */ 后面是要记录的内容
					i++
				}
			} else {
				if i+1 < len(s) && s[i] == '/' && s[i+1] == '*' {
					inBlock = true // 不能 break, 当前行内可能存在 */
					i++
				} else if i+1 < len(s) && s[i] == '/' && s[i+1] == '/' {
					break // break, // 为行注释
				} else {
					line = append(line, s[i])
				}
			}
		}
		if !inBlock && len(line) > 0 {
			ans = append(ans, string(line))
			line = []byte{}
		}
	}
	return ans
}
