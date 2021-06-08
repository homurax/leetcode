/**
 * 71. Simplify Path
 *
 *
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 *
 *
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * Example 4:
 *
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 *
 *
 * Constraints:
 *
 * 1. 1 <= path.length <= 3000
 * 2. path consists of English letters, digits, period '.', slash '/' or '_'.
 * 3. path is a valid absolute Unix path.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        LinkedList<String> deque = new LinkedList<>();
        char[] chars = path.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '/') {
                continue;
            }
            int r = i;
            while (r + 1 < chars.length && chars[r + 1] != '/') {
                r++;
            }
            String str = path.substring(i, r + 1);
            if ("..".equals(str)) {
                if (!deque.isEmpty()) {
                    deque.removeFirst();
                }
            } else if (!".".equals(str)) {
                deque.addFirst(str);
            }
            i = r + 1;
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/").append(deque.removeLast());
        }
        return sb.toString();
    }

}
