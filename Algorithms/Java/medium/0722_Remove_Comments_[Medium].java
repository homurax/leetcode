/**
 * 722. Remove Comments
 *
 *
 * Given a C++ program, remove comments from it. The program source is an array of strings source where source[i] is the ith line of the source code. This represents the result of splitting the original source code string by the newline character '\n'.
 *
 *
 * Constraints:
 *
 * 1 <= source.length <= 100
 * 0 <= source[i].length <= 80
 * source[i] consists of printable ASCII characters.
 * Every open block comment is eventually closed.
 * There are no single-quote or double-quote in the input.
 */
public class RemoveComments {

    // inBlock:
    //      */ -> inBlock = false
    // !inBlock:
    //      // -> break
    //      /* -> inBlock = true
    //      .. -> line.append
    public List<String> removeComments(String[] source) {
        StringBuilder line = new StringBuilder();
        List<String> ans = new ArrayList<>();
        boolean inBlock = false;
        for (String s : source) {
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (inBlock) {
                    if (i + 1 < n && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        inBlock = false; // 不能 break, */ 后面是要记录的内容
                        i++;
                    }
                } else {
                    if (i + 1 < n && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        inBlock = true; // 不能 break, 当前行内可能存在 */
                        i++;
                    } else if (i + 1 < n && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break; // break, // 为行注释
                    } else {
                        line.append(s.charAt(i));
                    }
                }
            }
            if (!inBlock && !line.isEmpty()) {
                ans.add(line.toString());
                line.setLength(0);
            }
        }
        return ans;
    }

}
