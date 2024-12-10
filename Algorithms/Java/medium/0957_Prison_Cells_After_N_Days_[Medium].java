/**
 * 957. Prison Cells After N Days
 *
 *
 * There are 8 prison cells in a row and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
 *
 * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith cell is vacant, and you are given an integer n.
 *
 * Return the state of the prison after n days (i.e., n such changes described above).
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], n = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation: The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Constraints:
 *
 * cells.length == 8
 * cells[i] is either 0 or 1.
 * 1 <= n <= 10^9
 */
public class PrisonCellsAfterNDays {

    // 第 8 天的时候 cells[1] ~ cells[n - 2] 颠倒顺序, 再过 7 天恢复为初始状态
    // 14 天一个周期, 准确说是第 1 天除外, 15 天后 14 天一个周期
    public int[] prisonAfterNDays(int[] cells, int n) {
        int len = cells.length;
        int[] temp = new int[len];
        n = (n % 14) == 0 ? 14 : n % 14;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < len - 1; j++) {
                temp[j] = cells[j - 1] ^ cells[j + 1] ^ 1;
            }
            cells = Arrays.copyOf(temp, len);
        }
        return temp;
    }

}
