/**
 * 2326. Spiral Matrix IV
 *
 *
 * You are given two integers m and n, which represent the dimensions of a matrix.
 *
 * You are also given the head of a linked list of integers.
 *
 * Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
 *
 * Return the generated matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
 * Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
 * Explanation: The diagram above shows how the values are printed in the matrix.
 * Note that the remaining spaces in the matrix are filled with -1.
 *
 *
 * Example 2:
 *
 *
 * Input: m = 1, n = 4, head = [0,1,2]
 * Output: [[0,1,2,-1]]
 * Explanation: The diagram above shows how the values are printed from left to right in the matrix.
 * The last space in the matrix is set to -1.
 *
 *
 * Constraints:
 *
 * 1. 1 <= m, n <= 10^5
 * 2. 1 <= m * n <= 10^5
 * 3. The number of nodes in the list is in the range [1, m * n].
 * 4. 0 <= Node.val <= 1000
 */
public class SpiralMatrixIV {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 右下左上
    private int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int[] row : ans) {
            Arrays.fill(row, -1);
        }
        for (int x = 0, y = 0, d = 0; head != null; head = head.next) {
            ans[x][y] = head.val;
            int[] dir = direction[d & 3];
            int xx = x +  dir[0];
            int yy = y +  dir[1];
            if (xx < 0 || xx >= m || yy < 0 || yy >= n || ans[xx][yy] != -1) {
                d++;
                dir = direction[d & 3];
            }
            x += dir[0];
            y += dir[1];
        }
        return ans;
    }

}
