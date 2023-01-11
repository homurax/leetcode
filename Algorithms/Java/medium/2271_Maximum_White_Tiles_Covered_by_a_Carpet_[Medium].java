/**
 * 2271. Maximum White Tiles Covered by a Carpet
 *
 *
 * You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
 *
 * You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
 *
 * Return the maximum number of white tiles that can be covered by the carpet.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * Output: 9
 * Explanation: Place the carpet starting on tile 10.
 * It covers 9 white tiles, so we return 9.
 * Note that there may be other places where the carpet covers 9 white tiles.
 * It can be shown that the carpet cannot cover more than 9 white tiles.
 *
 *
 * Example 2:
 *
 *
 * Input: tiles = [[10,11],[1,1]], carpetLen = 2
 * Output: 2
 * Explanation: Place the carpet starting on tile 10.
 * It covers 2 white tiles, so we return 2.
 *
 *
 * Constraints:
 *
 * 1. 1 <= tiles.length <= 5 * 10^4
 * 2. tiles[i].length == 2
 * 3. 1 <= li <= ri <= 10^9
 * 4. 1 <= carpetLen <= 10^9
 * 5. The tiles are non-overlapping.
 */
public class MaximumWhiteTilesCoveredByACarpet {

    // 毯子右移，左端点处可能失去可能不变，右端点处可能增加可能不变
    // 毯子左移，左端点处可能增加可能不变，右端点处可能失去可能不变
    // 即左移不劣，右移不劣
    // 考虑枚举每段瓷砖的右端点来摆放毯子的右端点
    // 毯子右端点位于 tiles[i] 段时（tiles[i]），毯子左端点位于 tiles[i][1] - carpetLen + 1
    // 左指针 left 需要满足其指向的那段瓷砖的右端点被毯子覆盖
    // 即 tiles[left][1] >= 毯子左端点 = tiles[i][1] - carpetLen + 1
    // 毯子左端点位于 tiles[left] 中时需要减去没有覆盖的部分 = 左端点 - tiles[left][0]
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int ans = 0, cover = 0, left = 0;
        for (int[] tile : tiles) {
            int l = tile[0], r = tile[1];
            cover += r - l + 1;
            while (tiles[left][1] + carpetLen - 1 < r) { // 覆盖不了 tiles[left] 段
                cover -= tiles[left][1] - tiles[left][0] + 1;
                left++;
            }
            ans = Math.max(ans, cover - Math.max(r - carpetLen + 1 - tiles[left][0], 0));
        }
        return ans;
    }

}
