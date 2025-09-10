/**
 * 3413. Maximum Coins From K Consecutive Bags
 *
 *
 * There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.
 *
 * You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci coins.
 *
 * The segments that coins contain are non-overlapping.
 *
 * You are also given an integer k.
 *
 * Return the maximum amount of coins you can obtain by collecting k consecutive bags.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4
 *
 * Output: 10
 *
 * Explanation:
 *
 * Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.
 *
 *
 * Example 2:
 *
 * Input: coins = [[1,10,3]], k = 2
 *
 * Output: 6
 *
 * Explanation:
 *
 * Selecting bags at positions [1, 2] gives the maximum number of coins: 3 + 3 = 6.
 *
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 10^5
 * 1 <= k <= 10^9
 * coins[i] == [li, ri, ci]
 * 1 <= li <= ri <= 10^9
 * 1 <= ci <= 1000
 * The given segments are non-overlapping.
 */
public class MaximumCoinsFromKConsecutiveBags {

    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, Comparator.comparingInt(a -> a[0]));
        long ans = maximumWhiteTiles(coins, k);
        // 反转数组
        for (int i = 0, j = coins.length - 1; i < j; i++, j--) {
            int[] temp = coins[i];
            coins[i] = coins[j];
            coins[j] = temp;
        }
        // 反转每个区间
        for (int[] t : coins) {
            int temp = t[0];
            t[0] = -t[1];
            t[1] = -temp;
        }
        return Math.max(ans, maximumWhiteTiles(coins, k));
    }

    private long maximumWhiteTiles(int[][] tiles, int carpetLen) {
        long ans = 0;
        long cover = 0;
        int left = 0;
        for (int[] tile : tiles) {
            int tl = tile[0], tr = tile[1], c = tile[2];
            cover += (long) (tr - tl + 1) * c;
            int carpetLeft = tr - carpetLen + 1;
            while (tiles[left][1] < carpetLeft) {
                cover -= (long) (tiles[left][1] - tiles[left][0] + 1) * tiles[left][2];
                left++;
            }
            long uncover = Math.max((long) (carpetLeft - tiles[left][0]) * tiles[left][2], 0);
            ans = Math.max(ans, cover - uncover);
        }
        return ans;
    }
    
}
