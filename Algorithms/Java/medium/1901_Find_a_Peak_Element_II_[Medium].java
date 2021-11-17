/**
 * 1901. Find a Peak Element II
 *
 *
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
 *
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,4],[3,2]]
 * Output: [0,1]
 * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
 *
 * Example 2:
 *
 * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * Output: [1,1]
 * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 *
 *
 * Constraints:
 *
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 500
 * 4. 1 <= mat[i][j] <= 10^5
 */
public class FindAPeakElementII {

    private int[][] matrix;
    private int M;
    private int N;

    public int[] findPeakGrid(int[][] mat) {
        this.matrix = mat;
        this.M = mat.length;
        this.N = mat[0].length;
        return find(0, 0);
    }

    // 相邻节点较大就尝试相邻节点
    // 都小于就返回当前位置
    private int[] find(int i, int j) {
        int curr = matrix[i][j];
        if (getNeighbor(i - 1, j) > curr) {
            return find(i - 1, j);
        }
        if (getNeighbor(i + 1, j) > curr) {
            return find(i + 1, j);
        }
        if (getNeighbor(i, j - 1) > curr) {
            return find(i, j - 1);
        }
        if (getNeighbor(i, j + 1) > curr) {
            return find(i, j + 1);
        }
        return new int[]{i, j};
    }

    private int getNeighbor(int i, int j) {
        if (0 <= i && i <= M - 1 && 0 <= j && j <= N - 1) {
            return matrix[i][j];
        }
        return -1;
    }

}
