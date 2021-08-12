/**
 * 1233. Remove Sub-Folders from the Filesystem
 *
 *
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 *
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 *
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 *
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 *
 * Constraints:
 *
 * 1. 1 <= folder.length <= 4 * 10^4
 * 2. 2 <= folder[i].length <= 100
 * 3. folder[i] contains only lowercase letters and '/'
 * 4. folder[i] always starts with character '/'
 * 5. Each folder name is unique.
 */
public class RemoveSubFoldersFromTheFilesystem {

    // 修改的其实是 TreeMap 的 comparator
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparingInt(String::length));
        Set<String> set = new TreeSet<>((s1, s2) -> (s1.startsWith(s2 + "/")) ? 0 : s1.compareTo(s2));
        Collections.addAll(set, folder);
        return new ArrayList<>(set);
    }


    class TrieNode {
        int index;
        TrieNode[] next = new TrieNode[27];

        TrieNode() {
            this.index = -1;
        }
    }

    public List<String> removeSubfolders2(String[] folder) {
        // path 长度排序
        List<Integer>[] arr = new ArrayList[101];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < folder.length; i++) {
            arr[folder[i].length()].add(i);
        }

        TrieNode root = new TrieNode();
        boolean[] isSub = new boolean[folder.length];
        for (List<Integer> idxList : arr) {
            for (int pathIdx : idxList) {
                String path = folder[pathIdx];
                TrieNode temp = root;
                for (int pos = 0; pos < path.length(); pos++) {
                    char ch = path.charAt(pos);
                    if (ch == '/' && temp.index != -1) {
                        isSub[pathIdx] = true;
                        break;
                    }
                    int index = ch == '/' ? 26 : ch - 'a';
                    if (temp.next[index] == null) {
                        temp.next[index] = new TrieNode();
                    }
                    temp = temp.next[index];
                }
                if (!isSub[pathIdx]) {
                    temp.index = pathIdx;
                }
            }
        }
        List<String> rst = new ArrayList<>();
        for (int i = 0; i < folder.length; i++) {
            if (!isSub[i]) {
                rst.add(folder[i]);
            }
        }
        return rst;
    }

}
