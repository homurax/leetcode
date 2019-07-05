/**
 * 463. Island Perimeter
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example:
 *
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Output: 16
 *
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class IslandPerimeter {

    /**
     * 考虑全边界条件遍历即可...
     */
    public int islandPerimeter(int[][] grid) {

        int length = grid.length;
        int depth = grid[0].length;
        int sum = 0;

        for (int i = 0; i < length; i++) {

            for (int j = 0; j < depth; j++) {
                if (grid[i][j] == 0) continue;
                if (i > 0 && grid[i-1][j] == 0 || i == 0)
                    sum++;
                if (i < length - 1 && grid[i+1][j] == 0 || i == length - 1)
                    sum++;
                if (j > 0 && grid[i][j-1] == 0 || j == 0)
                    sum++;
                if (j < depth - 1 && grid[i][j+1] == 0 || j == depth - 1)
                    sum++;
            }
        }

        return sum;
    }

    /**
     * 周长 = 个数 * 4 - 重合边数量 * 2
     */
    public int islandPerimeter2(int[][] grid) {

        int side = 0;
        int duplicate = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    side++;
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1)
                        duplicate++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
                        duplicate++;
                }
            }
        }
        return 4 * side - 2 * duplicate;
    }

}
