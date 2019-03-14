/**
 * 944. Delete Columns to Make Sorted
 *
 * We are given an array A of N lowercase letter strings, all of the same length.
 *
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
 *
 * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"], and the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].  (Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]].)
 *
 * Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in non-decreasing sorted order.
 *
 * Return the minimum possible value of D.length.
 *
 *
 * Example 1:
 *
 * Input: ["cba","daf","ghi"]
 * Output: 1
 * Explanation:
 * After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
 * If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
 *
 *
 * Example 2:
 *
 * Input: ["a","b"]
 * Output: 0
 * Explanation: D = {}
 *
 *
 * Example 3:
 *
 * Input: ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation: D = {0, 1, 2}
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 1000
 */
public class DeleteColumnsToMakeSorted {

    /**
     * 这题目描述的也太复杂了
     * 意思是对于一个string矩阵(数组形式 每一个元素即为一行)
     * 最少删除多少行(被删除的行即为删除序列中的元素 最少删除的行数即为删除索引的最小长度)
     * 才可以保证剩下的列均为非降序列
     *
     * 遍历比较 string数组中相邻按位比较
     */
    public int minDeletionSize(String[] A) {

        int strLength = A[0].length();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < A.length - 1; i++) {
            String str = A[i];
            String next = A[i+1];
            for (int j = 0; j < strLength; j++) {
                if (!set.contains(j) && str.charAt(j) > next.charAt(j))
                    set.add(j);
            }
            if (set.size() == strLength)
                return strLength;
        }
        return set.size();
    }

    /**
     * 上面的写的还是太绕了 可以优化一下
     * 外层遍历索引 内层string数组相邻依次按位比较
     */
    public int minDeletionSize2(String[] A) {

        int size = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 0; j < A.length - 1; j++) {
                if (A[j].charAt(i) > A[j+1].charAt(i)) {
                    size++;
                    break;
                }
            }
        }
        return size;
    }
}
