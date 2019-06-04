/**
 * 441. Arranging Coins
 *
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 *
 * Example 1:
 *
 * n = 5
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Because the 3rd row is incomplete, we return 2.
 * Example 2:
 *
 * n = 8
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 * Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {

    public int arrangeCoins(int n) {

        int row = 1;
        while (n >= row) {
            n -= row++;
        }
        return --row;
    }


    /**
     * (1 + K)K / 2 = n => k^2 + k -2n = 0
     *                  => k = (√(1 + 8n) - 1) / 2 = √(2n + 0.25) - 0.25
     */
    public static int arrangeCoins2(int n) {

        long temp = 2 * (long) n;
        long sqrt = (int) Math.floor(Math.sqrt(temp));
        if (sqrt * (sqrt + 1) <= temp)
            return (int) sqrt;
        return (int) sqrt - 1;
    }

}
